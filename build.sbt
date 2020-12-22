val dottyVersion = "0.26.0-RC1"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala-3-intro",
    version := "0.1.0",

    scalaVersion := dottyVersion,

    libraryDependencies += "com.novocode" % "junit-interface" % "0.11" % "test"
  )
