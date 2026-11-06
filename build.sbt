scalaVersion := "3.9.0"

lazy val root = rootProject
  .settings(
    name := "Durak_game",
    libraryDependencies ++= Seq(
      //You can add library dependencies here, for example,
      "org.scalatest" %% "scalatest" % "3.2.19" % Test,
      "org.scalameta" %% "munit" % "1.2.3" % Test
    ),
    // Run every discovered suite instead of sbt's incremental testQuick task.
    Test / test := (Test / testFull).value,
    // Keep instrumentation data in the standard project target directory.
    coverageDataDir := baseDirectory.value / "target" / s"scala-${scalaVersion.value}"
  )

addCommandAlias("coverageAll", "clean; coverage; Compile / compile; Test / testFull; coverageReport")
