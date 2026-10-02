scalaVersion := "3.8.3"

val jmeVersion = "3.6.1-stable"

lazy val root = (project in file("."))
  .settings(
    name := "DivideMe",
    fork := true,
    libraryDependencies ++= Seq(
      // JME Core
      "org.jmonkeyengine" % "jme3-core" % jmeVersion,
      "org.jmonkeyengine" % "jme3-desktop" % jmeVersion,
      "org.jmonkeyengine" % "jme3-lwjgl3" % jmeVersion,
      "org.jmonkeyengine" % "jme3-effects" % jmeVersion,
      "org.jmonkeyengine" % "jme3-plugins" % jmeVersion,
      // Physics (pure Java Bullet implementation)
      "org.jmonkeyengine" % "jme3-jbullet" % jmeVersion,
      // Terrain
      "org.jmonkeyengine" % "jme3-terrain" % jmeVersion,
      // Audio (eliminates OGG loader warning)
      "org.jmonkeyengine" % "jme3-jogg" % jmeVersion,
      // j-ogg-all is required by jme3-jogg at runtime
      "com.github.stephengold" % "j-ogg-all" % "1.0.4",
      // Dialogs (eliminates JmeDialogsFactory warning)
      "org.jmonkeyengine" % "jme3-awt-dialogs" % jmeVersion,
      // Logging
      "org.slf4j" % "slf4j-api" % "2.0.16",
      "org.slf4j" % "slf4j-simple" % "2.0.16" % Runtime
    )
  )