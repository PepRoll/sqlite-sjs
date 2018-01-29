name := "sqlite-sjs"

version := "0.0.2"
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

publishMavenStyle := false

publishArtifact in Test := false

pomExtra :=
  <url>https://github.com/PepRoll/sqlite-sjs</url>
    <licenses>
      <license>
        <name>Apache 2.0</name>
        <url>http://www.apache.org/licenses/LICENSE-2.0.txt</url>
      </license>
    </licenses>
    <scm>
      <connection>scm:git@github.com:PepRoll/sqlite-sjs.git</connection>
      <developerConnection>scm:git@github.com:PepRoll/sqlite-sjs.git</developerConnection>
      <url>https://github.com/PepRoll/sqlite-sjs</url>
    </scm>
    <developers>
      <developer>
        <id>PepRoll</id>
        <name>Stanislav Kovalenko</name>
        <email>peproll@protonmail.com</email>
        <url>http://peproll.me</url>
      </developer>
    </developers>

publishTo := Some(
  if (isSnapshot.value)
    Opts.resolver.sonatypeSnapshots
  else
    Opts.resolver.sonatypeStaging
)