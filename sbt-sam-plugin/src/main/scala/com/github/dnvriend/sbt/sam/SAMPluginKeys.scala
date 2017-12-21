// Copyright 2017 Dennis Vriend
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.github.dnvriend.sbt.sam

import com.amazonaws.services.cloudformation.model.Stack
import com.github.dnvriend.sbt.aws.task._
import com.github.dnvriend.sbt.sam.task.Models.{ DynamoDb, Policies }
import com.github.dnvriend.sbt.sam.task.{ LambdaHandler, ProjectClass, ProjectConfiguration, ProjectLambda }
import sbt._

object SAMPluginKeys {
  // sam settings
  lazy val samStage = settingKey[String]("The stage to deploy the service to")
  lazy val samS3BucketName = settingKey[String]("The S3 deployment bucket name for the sam project")
  lazy val samCFTemplateName = settingKey[String]("The cloudformation template name for the sam project")
  lazy val samResourcePrefixName = settingKey[String]("The prefix name to use when creating AWS resources like Lambdas, DynamoDB tables, Kinesis topics and so on")

  // sam worker tasks
  lazy val samProjectClassLoader = TaskKey[ClassLoader]("sam's project classloader")
  lazy val discoveredClassFiles = taskKey[Set[File]]("returns a set of discovered class files")
  lazy val discoveredClasses = taskKey[Set[ProjectClass]]("returns a set of discovered classes")
  lazy val discoveredLambdas = taskKey[Set[ProjectLambda]]("returns a set of discovered unclassified lambdas")
  lazy val classifiedLambdas = taskKey[Set[LambdaHandler]]("returns a set of classified lambdas")
  lazy val discoveredResources = taskKey[Set[Class[_]]]("Returns a set of discovered aws resources")
  lazy val samProjectConfiguration = taskKey[ProjectConfiguration]("The sam project configuration")
  lazy val samUploadArtifact = taskKey[PutObjectResponse]("Upload deployment artifact to the S3 deployment bucket")
  lazy val samDeleteArtifact = taskKey[Unit]("Delete deployment artifact from the S3 deployment bucket")
  lazy val samDeleteCloudFormationStack = taskKey[Unit]("Deletes the cloud formation stack")
  lazy val samCreateCloudFormationStack = taskKey[Unit]("Create the cloud formation stack")
  lazy val samUpdateCloudFormationStack = taskKey[Unit]("Update the cloud formation stack")
  lazy val samDescribeCloudFormationStackForCreate = taskKey[Option[Stack]]("Determine the state of the cloud")
  lazy val samDescribeCloudFormationStack = taskKey[Option[Stack]]("Determine the state of the cloud")
  lazy val samServiceEndpoint = taskKey[Option[ServiceEndpoint]]("Shows the service endpoint")

  // resource tasks
  lazy val dynamoDbTableResources = taskKey[Set[DynamoDb.TableWithIndex]]("Retrieves a set of tables, which are configured in the Lightbend Config.")
  lazy val policyResources = taskKey[Set[Policies.Policy]]("Retrives a set of policies, which are configured in the Lightbend Config.")

  // sam tasks
  lazy val samInfo = taskKey[Unit]("Show info the service")
  lazy val samDeploy = taskKey[Unit]("Deploy a service")
  lazy val samRemove = taskKey[Unit]("Remove a service")
  lazy val samUpdate = inputKey[Unit]("Update a service")
  lazy val samValidate = taskKey[Unit]("Validates the cloud formation template")
}
