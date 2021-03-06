import sbt.Keys._
import sbt._
import LibraryDependencies._

object SamOpsSettings extends AutoPlugin {
  override def trigger = noTrigger

  override def requires = plugins.JvmPlugin

  override def projectSettings =
    GlobalSettings.commonSettings ++ Seq(
      crossScalaVersions := Seq("2.11.12", "2.12.4"),
      libraryDependencies += libScalajHttp,
      libraryDependencies += libSbtIO,
    )
}