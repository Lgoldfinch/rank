name := "rank"
 
version := "1.0" 
      
lazy val `rank` = (project in file(".")).enablePlugins(PlayScala)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"
      
resolvers += "Akka Snapshot Repository" at "https://repo.akka.io/snapshots/"
      
scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  jdbc,
  ehcache,
  ws,
  "com.uber.sdk" % "uber-rides" % "0.8.0",
    specs2 % Test , guice)


unmanagedResourceDirectories in Test <+=  baseDirectory ( _ /"target/web/public/test" )  

      