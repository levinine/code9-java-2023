import { CodeBuildClient, StartBuildCommand } from "@aws-sdk/client-codebuild";
import * as cdk from "aws-cdk-lib";
import * as codebuild from "aws-cdk-lib/aws-codebuild";
import * as ecr from "aws-cdk-lib/aws-ecr";
import { IRepository } from "aws-cdk-lib/aws-ecr";
import * as iam from "aws-cdk-lib/aws-iam";
import { Construct } from "constructs";

export class DockerImageToEcrStack extends cdk.Stack {
  public project: codebuild.Project;
  public repository: ecr.IRepository;
  public repositoryName: string;
  public image: string;
  public tag: string;

  constructor(
    scope: Construct,
    id: string,
    repositoryName: string,
    image: string,
    tag: string,
    props?: cdk.StackProps
  ) {
    super(scope, id, props);

    this.image = image;
    this.tag = tag;
    this.repositoryName = repositoryName;

      this.repository  = ecr.Repository.fromRepositoryName(this, 'TinyServerECR', repositoryName);

    if (!this.repository.repositoryArn) {
      this.repository = new ecr.Repository(this, 'TinyServerECR', {
        repositoryName: repositoryName
      });
    }

    const codeBuildRole = new iam.Role(this, "CodeBuildRole", {
      assumedBy: new iam.ServicePrincipal("codebuild.amazonaws.com"),
    });

    // Add permissions to the role to access ECR
    const ecrPolicy = new iam.PolicyStatement({
      effect: iam.Effect.ALLOW,
      resources: ["*"],
      actions: ["ecr:*"],
    });
    codeBuildRole.addToPolicy(ecrPolicy);

    this.project = new codebuild.Project(this, "DockerBuildProject", {
      projectName: "PushDockerImageToECR",
      environment: {
        buildImage: codebuild.LinuxBuildImage.STANDARD_5_0,
        privileged: true,
      },
      role: codeBuildRole,
      buildSpec: codebuild.BuildSpec.fromObject({
        version: "0.2",
        phases: {
          pre_build: {
            commands: [
              "aws ecr get-login-password --region $AWS_DEFAULT_REGION | docker login --username AWS --password-stdin " +
                this.repository.repositoryUri.split("/")[0],
            ],
          },
          build: {
            commands: [
              `docker pull ${this.image}:${this.tag}`,
              `docker tag ${this.image}:${this.tag} ${this.repository.repositoryUri}:${this.tag}`,
              `docker push ${this.repository.repositoryUri}:${this.tag}`,
            ],
          },
        },
      }),
    });
  }

  async startCodeBuild(): Promise<EcrRepository> {
    const startBuildOptions = {
      projectName: this.project.projectName,
    };
    const codebuildClient = new CodeBuildClient({});
    await codebuildClient.send(new StartBuildCommand(startBuildOptions));
    return Promise.resolve({ repository: this.repository, tag: this.tag });
  }
}

export interface EcrRepository {
  repository: IRepository;
  tag: string;
}
