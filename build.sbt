name := "sqlite-sjs"

version := "0.0.1"
organization := "me.peproll"
scalaVersion := "2.12.4"
scalacOptions ++= Seq(
  "-deprecation",
  "-encoding", "UTF-8",
  "-feature",
  "-unchecked",
  "-Xlint:-unused,_",
  "-Xfatal-warnings",
  "-language:higherKinds",
  "-Yno-adapted-args",
  "-Ywarn-value-discard"
)
crossScalaVersions := Seq("2.11.11", "2.12.4")

enablePlugins(ScalaJSBundlerPlugin, ScalaJSPlugin)

jsEnv := new org.scalajs.jsenv.nodejs.NodeJSEnv()
testFrameworks += new TestFramework("utest.runner.Framework")

npmDependencies in Compile ++= Seq(
  "better-sqlite3" -> "4.0.1"
)

libraryDependencies ++= Seq(
  "com.lihaoyi" %%% "utest" % "0.6.0" % "test"
)

scalaJSUseMainModuleInitializer := false
requiresDOM := false