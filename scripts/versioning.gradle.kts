import org.ajoberstar.grgit.Grgit

buildscript {
  repositories {
    maven {
      url = uri("https://plugins.gradle.org/m2/")
    }
  }
  dependencies {
    classpath("org.ajoberstar:grgit:4.0.2")
  }
}

apply(plugin = "org.ajoberstar.grgit")

val git: Grgit = Grgit.open()
val gitVersionName = git.describe()
//val gitVersionCode = git.tag.list().size()
val gitVersionCode = git.lsremote().size
val gitVersionCodeTime = git.head().time


//println(Ext.git.tag)

/*
ext {
    git = Grgit.open(currentDir: projectDir)
    gitVersionName = git.describe()
    gitVersionCode = git.tag.list().size()
    gitVersionCodeTime = git.head().time
}
*/

/*
task printVersion() {
    println("Version Name: $gitVersionName")
    println("Version Code: $gitVersionCode")
    println("Version Code Time: $gitVersionCodeTime")
}
*/
