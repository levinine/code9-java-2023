import * as cdk from "aws-cdk-lib";
import { Duration } from "aws-cdk-lib";
import * as ec2 from "aws-cdk-lib/aws-ec2";
import * as ecs from "aws-cdk-lib/aws-ecs";
import {
  ApplicationLoadBalancer,
  ApplicationProtocol,
  ApplicationTargetGroup,
  Protocol,
  TargetType,
} from "aws-cdk-lib/aws-elasticloadbalancingv2";
import {
  Effect,
  Policy,
  PolicyStatement,
  Role,
  ServicePrincipal,
} from "aws-cdk-lib/aws-iam";
import { Construct } from "constructs";
import { EcrRepository } from "./docker-image-to-ecr";

export class ProjectCdkStack extends cdk.Stack {
  constructor(
    scope: Construct,
    id: string,
    ecrRepository: EcrRepository,
    props?: cdk.StackProps
  ) {
    super(scope, id, props);

    const vpc = new ec2.Vpc(this, "cdk-vpc-cod9-ecs", {
      cidr: "10.0.0.0/16",
      natGateways: 0,
    });

    const targetGroupHttp = new ApplicationTargetGroup(
      this,
      "cdk-target-group",
      {
        port: 8080,
        vpc,
        protocol: ApplicationProtocol.HTTP,
        targetType: TargetType.IP,
      }
    );

    targetGroupHttp.configureHealthCheck({
      path: "/",
      protocol: Protocol.HTTP,
    });
    const alb = new ApplicationLoadBalancer(this, `cdk-alb`, {
      vpc,
      vpcSubnets: { subnets: vpc.publicSubnets },
      internetFacing: true,
    });

    const listener = alb.addListener("cdk-alb-listener", {
      open: true,
      port: 80,
    });

    listener.addTargetGroups("cdk-alb-listener-target-group", {
      targetGroups: [targetGroupHttp],
    });

    const cluster = new ecs.Cluster(this, "cdk-cluster", {
      clusterName: "cdk-cluster",
      vpc: vpc,
    });

    const taskRole = new Role(this, "task-role", {
      assumedBy: new ServicePrincipal("ecs-tasks.amazonaws.com"),
      roleName: "task-role",
    });

    taskRole.attachInlinePolicy(
      new Policy(this, "cdk-task-policy", {
        statements: [
          // probably add cloud watch
          new PolicyStatement({
            effect: Effect.ALLOW,
            actions: ["ecr:*"],
            resources: ["*"],
          }),
        ],
      })
    );
    const taskDefinition = new ecs.TaskDefinition(this, "cdk-task-definition", {
      family: "task",
      compatibility: ecs.Compatibility.FARGATE,
      cpu: "1024",
      memoryMiB: "2048",
      networkMode: ecs.NetworkMode.AWS_VPC,
      executionRole: taskRole,
    });

    const container = taskDefinition.addContainer("cdk-container-name", {
      containerName: "cdk-task",
      image: ecs.RepositoryImage.fromEcrRepository(
        ecrRepository.repository,
        ecrRepository.tag
      ),
      memoryLimitMiB: 512,
    });
    container.addPortMappings({ containerPort: 80 });

    const ecsSG = new ec2.SecurityGroup(this, "cdk-ecsSG", {
      vpc,
      allowAllOutbound: true,
    });
    ecsSG.addIngressRule(
      ec2.Peer.anyIpv4(),
      ec2.Port.tcp(80),
      "Allow HTTP traffic"
    );

    const service = new ecs.FargateService(this, "cdk-fargate-service", {
      serviceName: "cdk-fargate-service",
      cluster,
      desiredCount: 1,
      healthCheckGracePeriod: Duration.seconds(60),
      taskDefinition,
      securityGroups: [ecsSG],
      assignPublicIp: true,
    });

    service.attachToApplicationTargetGroup(targetGroupHttp);

    const scalableTaget = service.autoScaleTaskCount({
      minCapacity: 2,
      maxCapacity: 5,
    });
  }
}
