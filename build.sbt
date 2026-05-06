ThisBuild / scalaVersion     := "3.3.6"
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

val ScalaTestVersion = "3.2.20"
val ZioVersion       = "2.1.25"
val ZioJsonVersion   = "0.9.2"

val scalaTestSettings = Seq(
  libraryDependencies += "org.scalactic" %% "scalactic" % ScalaTestVersion,
  libraryDependencies += "org.scalatest" %% "scalatest" % ScalaTestVersion % Test
)

val zioSettings = Seq(
  libraryDependencies ++= Seq(
    "dev.zio"       %% "zio"               % ZioVersion,
    "dev.zio"       %% "zio-macros"        % ZioVersion,
    "dev.zio"       %% "zio-streams"       % ZioVersion,
    "dev.zio"       %% "zio-test"          % ZioVersion % Test,
    "dev.zio"       %% "zio-test-sbt"      % ZioVersion % Test,
    "dev.zio"       %% "zio-test-magnolia" % ZioVersion % Test,
    "dev.zio"       %% "zio-test-junit"    % ZioVersion % Test,
    "com.github.sbt" % "junit-interface"   % "0.13.3"   % Test
  ),
  testFrameworks += new TestFramework("zio.test.sbt.ZTestFramework")
)

//val mongoDbSettings = Seq(
//  libraryDependencies ++= Seq(
//    "org.reactivemongo" %% "reactivemongo" % ReactiveMongoVersion % Provided
//  )
//)

lazy val xml = (project in file("xml"))
  .settings(zioSettings)
  .settings(
    libraryDependencies += "com.fasterxml" % "aalto-xml" % "1.3.4",
    libraryDependencies += "dev.zio"      %% "zio-nio"   % "2.0.2"
  )

lazy val pubmed = (project in file("pubmed"))
  .settings(zioSettings)
  .settings(
//    libraryDependencies += "dev.zio" %% "zio-nio" % "2.0.1",
    // https://mvnrepository.com/artifact/org.scala-lang.modules/scala-xml
    libraryDependencies += "org.scala-lang.modules" %% "scala-xml" % "2.4.0",
    libraryDependencies += "dev.zio"                %% "zio-json"  % ZioJsonVersion
  )
  .dependsOn(xml)

lazy val `pubmed-utils` = (project in file("pubmed-utils"))
  .settings(zioSettings)
  .settings(
    libraryDependencies += "dev.zio" %% "zio-json" % ZioJsonVersion
  )
  .dependsOn(xml, pubmed)

lazy val pmc = (project in file("pmc"))
  .settings(zioSettings)
  .settings(
    libraryDependencies ++= Seq(
      "dev.zio" %% "zio-streams-compress-gzip" % "2.1.0",
      "dev.zio" %% "zio-streams-compress-tar"  % "2.1.0"
    )
  )
  .dependsOn(xml)

lazy val root = (project in file("."))
  .settings(zioSettings)
  .settings(
    name                       := "zbiblio",
    assembly / assemblyJarName := "zbiblio.jar"
  )
  .dependsOn(xml, `pubmed`, `pubmed-utils`)
