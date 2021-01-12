name := "FlamesGL"

version := "0.1"

scalaVersion := "2.13.4"

libraryDependencies ++= Seq(
  "org.scalafx" %% "scalafx" % "15.0.1-R20",
  "org.jbox2d" % "jbox2d-library" % "2.2.1.1",
  "org.ekrich" %% "sconfig" % "1.3.6",
)

lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")

libraryDependencies ++= javaFXModules.map { module =>
  "org.openjfx" % s"javafx-$module" % "15.0.1"
}

scalacOptions ++= Seq(
  "-deprecation",
  "-feature",
  "-Xdev",
  "-Vimplicits",
  "-Xlint",
)