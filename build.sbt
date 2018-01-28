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
crossScalaVersions := Seq("2.11.12", "2.12.4")

enablePlugins(ScalaJSBundlerPlugin)

scalaJSLinkerConfig ~= { _.withModuleKind(ModuleKind.CommonJSModule) }
scalaJSUseMainModuleInitializer := false
requiresDOM := false
testFrameworks += new TestFramework("utest.runner.Framework")

npmDependencies in Compile ++= Seq(
  "better-sqlite3" -> "4.0.1"
)

libraryDependencies ++= Seq(
  "org.scala-lang.modules" %% "scala-async" % "0.9.7",
  "com.lihaoyi" %%% "utest" % "0.6.0" % "test"
)

useGpg := true

licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

scmInfo := Some(
  ScmInfo(
    url("https://github.com/PepRoll/sqlite-sjs"),
    "scm:git@github.com:PepRoll/sqlite-sjs.git"
  )
)

developers := List(
  Developer(
    id = "PepRoll",
    name = "Stanislav Kovalenko",
    email = "peproll@protonmail.com",
    url = url("http://peproll.me")
  )
)

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)