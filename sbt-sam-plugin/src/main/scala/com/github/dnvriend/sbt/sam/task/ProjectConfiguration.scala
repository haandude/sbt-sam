package com.github.dnvriend.sbt.sam.task

import com.github.dnvriend.sbt.aws.domain.IAMDomain.CredentialsRegionAndUser
import com.github.dnvriend.sbt.aws.task.AmazonUser
import com.github.dnvriend.sbt.sam.task.Models.{ DynamoDb, Policies }

case class SamCFTemplateName(value: String)
case class SamS3BucketName(value: String)
case class SamResourcePrefixName(value: String)
case class SamStage(value: String)
case class SamResources(lambdas: Set[LambdaHandler], tables: Set[DynamoDb.TableWithIndex], policies: Set[Policies.Policy], cognito: Option[Models.Cognito.Authpool])
object ProjectConfiguration {
  def fromConfig(
    projectName: String,
    samS3BucketName: String,
    samCFTemplateName: String,
    samResourcePrefixName: String,
    samStage: String,
    credentialsRegionAndUser: CredentialsRegionAndUser,
    amazonUser: AmazonUser,
    samResources: SamResources
  ): ProjectConfiguration = {
    ProjectConfiguration(
      projectName,
      SamS3BucketName(samS3BucketName),
      SamCFTemplateName(samCFTemplateName),
      SamStage(samStage),
      SamResourcePrefixName(samResourcePrefixName),
      credentialsRegionAndUser,
      amazonUser,
      samResources.lambdas,
      samResources.tables,
      samResources.policies,
      samResources.cognito
    )
  }
}
case class ProjectConfiguration(
    projectName: String,
    samS3BucketName: SamS3BucketName,
    samCFTemplateName: SamCFTemplateName,
    samStage: SamStage,
    samResourcePrefixName: SamResourcePrefixName,
    credentialsRegionAndUser: CredentialsRegionAndUser,
    amazonUser: AmazonUser,
    lambdas: Set[LambdaHandler],
    tables: Set[DynamoDb.TableWithIndex],
    policies: Set[Policies.Policy],
    cognito: Option[Models.Cognito.Authpool]
)