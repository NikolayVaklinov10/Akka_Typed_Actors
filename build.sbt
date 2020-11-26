name := "Akka_Typed_Actors"

version := "0.1"

scalaVersion := "2.13.1"

lazy val akkaVersion = "2.6.10"

// the dependencies for the typed akka actors
libraryDependencies += "com.typesafe.akka" %% "akka-actor-typed" % akkaVersion
libraryDependencies += "com.typesafe.akka" %% "akka-stream" % akkaVersion

//

