name := "hazelcast-spray-akka"

version := "0.1.0"

val sprayV = "1.2.0"
    
libraryDependencies ++= Seq(
  "io.spray" % "spray-can" % sprayV,
  "io.spray" % "spray-routing" % sprayV,
  "io.spray" % "spray-httpx" % sprayV,
  "com.typesafe.akka" %% "akka-actor" % "2.2.3",
  "com.hazelcast" % "hazelcast" % "3.2",
  "com.hazelcast" % "hazelcast-client" % "3.2")