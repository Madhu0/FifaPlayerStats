name := "fifaplayerstats"
 
version := "1.0" 
      
lazy val fifaplayerstats = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
scalaVersion := "2.11.11"

libraryDependencies ++= Seq(
  guice,
  javaJdbc,
  javaWs,
  "mysql" % "mysql-connector-java" % "5.1.34",
  "com.typesafe.akka" %% "akka-actor"   % "2.2-M3",
  "org.projectlombok" % "lombok" % "1.18.4" % "provided",
  "io.swagger" %% "swagger-play2" % "1.6.0",
  "io.ebean" % "ebean-querybean" % "11.35.1"
)