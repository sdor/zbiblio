ThisBuild / scalaVersion     := "3.3.1"
ThisBuild / version          := "0.1.0-SNAPSHOT"
ThisBuild / organization     := "com.biosystechnologies"
ThisBuild / organizationName := "biosystechnologies"

ThisBuild / assemblyMergeStrategy := {
  case "application.conf"  => MergeStrategy.concat
  case "module-info.class" => MergeStrategy.discard
//  case "unwanted.txt"                                => MergeStrategy.discard
  case x =>
    val oldStrategy = (ThisBuild / assemblyMergeStrategy).value
    oldStrategy(x)
}

val ScalaTestVersion = "3.2.17"
val ZioVersion       = "2.0.21"
//val MongoScalaDriverVersion = "4.11.1"
val ReactiveMongoVersion = "1.1.0-noshaded-RC11"

val scalaTestSettings = Seq(
  libraryDependencies += "org.scalactic" %% "scalactic" % ScalaTestVersion,
  libraryDependencies += "org.scalatest" %% "scalatest" % ScalaTestVersion % Test
)

val zioSettings = Seq(
  libraryDependencies ++= Seq(
    "dev.zio"       %% "zio"               % ZioVersion,
    "dev.zio"       %% "zio-macros"        % ZioVersion,
    "dev.zio"       %% "zio-test"          % ZioVersion % Test,
    "dev.zio"       %% "zio-test-sbt"      % ZioVersion % Test,
    "dev.zio"       %% "zio-test-magnolia" % ZioVersion % Test,
    "dev.zio"       %% "zio-test-junit"    % ZioVersion % Test,
    "com.github.sbt" % "junit-interface"   % "0.13.3"   % Test
  ),
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)

val zioStreamSettings = Seq(
  libraryDependencies ++= Seq(
    "dev.zio" %% "zio-streams" % ZioVersion
  )
)

//val mongoDbSettings = Seq(
//  libraryDependencies ++= Seq(
//    "org.reactivemongo" %% "reactivemongo" % ReactiveMongoVersion % Provided
//  )
//)

lazy val xml = (project in file("xml"))
  .settings(zioSettings)
  .settings(zioStreamSettings)
  .settings(
    libraryDependencies += "com.fasterxml" % "aalto-xml" % "1.3.2",
    libraryDependencies += "dev.zio"      %% "zio-nio"   % "2.0.1"
  )

lazy val pubmed = (project in file("pubmed"))
  .settings(zioSettings)
  .settings(zioStreamSettings)
  .settings(
//    libraryDependencies += "dev.zio" %% "zio-nio" % "2.0.1",
    // https://mvnrepository.com/artifact/org.scala-lang.modules/scala-xml
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.2.0",
    libraryDependencies += "dev.zio"                %% "zio-json"  % "0.6.2",
  )
  .dependsOn(xml)

lazy val `pubmed-utils` = (project in file("pubmed-utils"))
  .settings(zioSettings)
  .settings(zioStreamSettings)
  .settings(
    libraryDependencies += "dev.zio" %% "zio-json" % "0.6.2"
  )
  .dependsOn(xml,pubmed)

lazy val root = (project in file("."))
  .settings(zioSettings)
  .settings(
    name                       := "zbiblio",
    assembly / assemblyJarName := "zbiblio.jar"
  )
  .dependsOn(xml, `pubmed`, `pubmed-utils`)
