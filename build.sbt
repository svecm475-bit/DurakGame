scalaVersion := "3.9.0"

lazy val root = (project in file("."))
  .settings(
    name := "Durak_game",
    libraryDependencies ++= Seq(
      //You can add library dependencies here, for example,
      "org.scalatest" %% "scalatest" % "3.2.20" % Test,
      "org.scalameta" %% "munit" % "1.2.3" % Test
    )
  )
