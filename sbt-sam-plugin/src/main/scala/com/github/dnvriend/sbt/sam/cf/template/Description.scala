package com.github.dnvriend.sbt.sam.cf.template

import com.github.dnvriend.sbt.sam.SbtSamPluginBuildInfo
import play.api.libs.json.{ Json, Writes }

object Description {
  def description(text: String): Description = {
    val buildAt = SbtSamPluginBuildInfo.builtAtString
    val version = SbtSamPluginBuildInfo.version
    Description(s"$text - $buildAt - $version")
  }

  implicit val writes: Writes[Description] = {
    Writes.apply(model => Json.obj("Description" -> model.value))
  }
}

/**
 * The Description section (optional) enables you to include arbitrary comments about your template.
 * The Description must follow the AWSTemplateFormatVersion section.
 */
case class Description(value: String)
