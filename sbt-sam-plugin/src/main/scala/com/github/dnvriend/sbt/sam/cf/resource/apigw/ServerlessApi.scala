package com.github.dnvriend.sbt.sam.cf.resource.apigw

import com.github.dnvriend.sbt.sam.cf.resource.Resource
import com.github.dnvriend.sbt.sam.cf.resource.apigw.swagger.Swagger
import com.github.dnvriend.sbt.sam.resource.authorizer.AuthorizerType
import com.github.dnvriend.sbt.sam.resource.cognito.model.{ Authpool, ImportAuthPool }
import com.github.dnvriend.sbt.sam.task.HttpHandler
import com.github.dnvriend.sbt.util.JsMonoids
import play.api.libs.json._

import scalaz.NonEmptyList
import scalaz.syntax.all._

object ServerlessApiSwaggerDefinitionBody {
  implicit val writes: Writes[ServerlessApiSwaggerDefinitionBody] = Writes.apply(model => {
    import model._
    Json.obj("DefinitionBody" -> Swagger.spec(projectName, stage, httpHandlers, authpool, importAuthPool, authorizerType))
  })
}

case class ServerlessApiSwaggerDefinitionBody(
    projectName: String,
    stage: String,
    httpHandlers: List[HttpHandler],
    authpool: Option[Authpool],
    importAuthPool: Option[ImportAuthPool],
    authorizerType: AuthorizerType
)

object ServerlessApiStageName {
  implicit val writes: Writes[ServerlessApiStageName] = Writes.apply(model => {
    import model._
    Json.obj("StageName" -> stageName)
  })
}
case class ServerlessApiStageName(stageName: String)

object ServerlessApiProperties {
  implicit val writes: Writes[ServerlessApiProperties] = Writes.apply(model => {
    import model._
    NonEmptyList(
      Json.toJson(stageName),
      Json.toJson(swaggerDefinitionBody)
    ).fold(JsMonoids.jsObjectMerge)
  })
}
case class ServerlessApiProperties(
    stageName: ServerlessApiStageName,
    swaggerDefinitionBody: ServerlessApiSwaggerDefinitionBody
)

object ServerlessApi {
  implicit val writes: Writes[ServerlessApi] = Writes.apply(model => {
    import model._
    Json.obj(
      "ServerlessRestApi" -> Json.obj(
        "Type" -> "AWS::Serverless::Api",
        "Properties" -> properties
      )
    )
  })
}
case class ServerlessApi(properties: ServerlessApiProperties) extends Resource