name := "sqlite-sjs"

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

enablePlugins(ScalaJSBundlerPlugin, GitVersioning, ScalafmtPlugin)

git.useGitDescribe := true
scalafmtConfig := Some(baseDirectory.in(ThisBuild).value / ".scalafmt")

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

publishMavenStyle := true

publishArtifact in Test := false

licenses := Seq("Apache 2.0" -> url("http://www.apache.org/licenses/LICENSE-2.0.txt"))

homepage := Some(url("https://github.com/PepRoll/sqlite-sjs"))

developers := List(
  Developer(
    id    = "PepRoll",
    name  = "Stanislav Kovalenko",
    email = "peproll@protonmail.com",
    url   = url("http://peproll.me")
  )
)

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)
