name := "play-minimal"

version := "1.0"

scalaVersion := "2.11.4"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

libraryDependencies += "org.scalatest" % "scalatest_2.11" % "2.2.1" % "test"
