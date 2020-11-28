name := "Akka_Typed_Actors"

version := "0.1"

scalaVersion := "2.12.8"

val akkaVersion = "2.6.5"


libraryDependencies ++= Seq(
  // akka typed
  "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion,



  // self4j
  "org.slf4j" % "slf4j-simple" % "1.7.30",
  "org.slf4j" % "slf4j-api" % "1.7.30",


  "com.typesafe.akka" %% "akka-actor-testkit-typed" % akkaVersion % Test,
  "org.scalatest" %% "scalatest" % "3.1.0" % Test

)

