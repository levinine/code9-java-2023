#!/usr/bin/env node
import * as cdk from "aws-cdk-lib";
import "source-map-support/register";
import { DockerImageToEcrStack } from "../lib/docker-image-to-ecr";
import { ProjectCdkStack } from "../lib/project-cdk-stack";

const app = new cdk.App();

let dockerImageToEcrStack = new DockerImageToEcrStack(
    app,
    "ecr-stack",
    "code9",
    "nginxdemos/hello",
    "latest"
);

new ProjectCdkStack(
    app,
    "cdk-stack",
    {
        repository: dockerImageToEcrStack.repository,
        tag: dockerImageToEcrStack.tag,
    },
    {}
);
