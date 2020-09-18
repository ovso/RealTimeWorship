// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
  //apply(from = "./scripts/versioning.gradle.kts")
  repositories {
    google()
    jcenter()
  }
  dependencies {
    classpath("com.android.tools.build:gradle:4.0.1")
    classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.10")
    classpath("com.google.gms:google-services:4.3.3")
    classpath("com.google.android.gms:oss-licenses-plugin:0.10.2")
    classpath("com.google.dagger:hilt-android-gradle-plugin:2.28.1-alpha")
  }
}

allprojects {
  repositories {
    google()
    jcenter()
    maven { url = uri("https://jitpack.io") }
  }
}

tasks.register("clean", Delete::class) {
  delete(rootProject.buildDir)
}
