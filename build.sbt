name := "Akka_Typed_Actors"

version := "0.1"

scalaVersion := "2.13.1"

lazy val akkaVersion = "2.6.10"

// the dependencies for the typed akka actors
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion

// logging
libraryDependencies += "org.apache.logging.log4j" % "log4j-api" % "2.4.1"
libraryDependencies += "org.apache.logging.log4j" % "log4j-core" % "2.4.1"

