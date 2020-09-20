import org.ajoberstar.grgit.Grgit
import org.ajoberstar.grgit.service.TagService
import org.jetbrains.kotlin.konan.properties.Properties
import java.io.FileInputStream

plugins {
  id("com.android.application")
  kotlin("android")
  id("kotlin-android-extensions")
  kotlin("kapt")
  id("com.google.gms.google-services")
  id("com.google.android.gms.oss-licenses-plugin")
  id("dagger.hilt.android.plugin")
  id("org.ajoberstar.grgit") version "4.0.2"
}

val grGit: Grgit = Grgit.open(mapOf("currentDir" to project.rootDir))
fun getVersionName(grGit: Grgit): String {
  return TagService(grGit.repository).list().last().name
}

fun getVersionCode(grGit: Grgit): Int {
  return TagService(grGit.repository).list().size
}

val keystorePropertiesFile = rootProject.file("../jks/keystore.properties")
val keystoreProperties = Properties()
keystoreProperties.load(FileInputStream(keystorePropertiesFile))

android {

  compileSdkVersion(DefaultConfig.compileSdk)
  defaultConfig {
    applicationId = DefaultConfig.appId
    minSdkVersion(DefaultConfig.minSdk)
    targetSdkVersion(DefaultConfig.targetSdk)
    versionCode = getVersionCode(grgit)
    versionName = getVersionName(grgit)
    multiDexEnabled = true
    vectorDrawables.useSupportLibrary = true
    testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    println("versionName = $versionName")
    println("versionCode = $versionCode")
  }

  signingConfigs {
    getByName("debug") {
      keyAlias = keystoreProperties.getProperty("dKeyAlias")
      keyPassword = keystoreProperties.getProperty("dKeyPassword")
      storeFile = file(keystoreProperties.getProperty("dStoreFile"))
      storePassword = keystoreProperties.getProperty("dStorePassword")
    }

    create("release") {
      keyAlias = keystoreProperties.getProperty("keyAlias")
      keyPassword = keystoreProperties.getProperty("keyPassword")
      storeFile = file(keystoreProperties.getProperty("storeFile"))
      storePassword = keystoreProperties.getProperty("storePassword")
    }
  }


  buildTypes {
    getByName("debug") {
      signingConfig = signingConfigs.getByName("debug")
      versionNameSuffix = "-debug"
    }
    getByName("release") {
      signingConfig = signingConfigs.getByName("release")
      isDebuggable = false
      isZipAlignEnabled = true
      isMinifyEnabled = true
      proguardFile(getDefaultProguardFile("proguard-android.txt"))
      // global proguard settings
      proguardFile(file("proguard-rules.pro"))
      // library proguard settings
      val files = rootProject.file("proguard")
        .listFiles()
        ?.filter { it.name.startsWith("proguard") }
        ?.toTypedArray()

      proguardFiles(*files ?: arrayOf())
    }
  }


  buildFeatures {
    dataBinding = true
    viewBinding = true
  }

  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }

  kotlinOptions {
    jvmTarget = "1.8"
  }

  androidExtensions {
    isExperimental = true
  }

  lintOptions {
    disable("MissingTranslation")
  }

  packagingOptions {
    exclude("META-INF/kotlinx-io.kotlin_module")
    exclude("META-INF/atomicfu.kotlin_module")
    exclude("META-INF/kotlinx-coroutines-io.kotlin_module")
  }

}


dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  // coroutines
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.8")
  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.8")

  // kotlin
  implementation("org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}")
  implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${Versions.kotlin}")

  // support library
  implementation("androidx.appcompat:appcompat:1.2.0")
  implementation("com.google.android.material:material:1.2.1")

  // view
  implementation("androidx.viewpager2:viewpager2:1.0.0")
  implementation("androidx.constraintlayout:constraintlayout:2.0.1")

  // annotation
  implementation("androidx.annotation:annotation:1.1.0")

  // lifecycle
  implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
  implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.2.0")

  // activity
  implementation("androidx.activity:activity-ktx:1.2.0-alpha08")

  // fragment
  implementation("androidx.fragment:fragment-ktx:1.3.0-alpha08")

  // navigation
  implementation("androidx.navigation:navigation-ui-ktx:2.3.0")
  implementation("androidx.navigation:navigation-fragment-ktx:2.3.0")

  // ktx
  implementation("androidx.core:core-ktx:1.3.1")

  // startup
  implementation("androidx.startup:startup-runtime:1.0.0-beta01")

  // paging
  implementation("androidx.paging:paging-runtime-ktx:2.1.2")
  implementation("androidx.paging:paging-rxjava2-ktx:2.1.2")

  // dagger
  implementation("com.google.dagger:dagger:${Versions.dagger}")
  kapt("com.google.dagger:dagger-compiler:${Versions.dagger}")
  implementation("com.google.dagger:dagger-android:${Versions.dagger_android}")
  implementation("com.google.dagger:dagger-android-support:${Versions.dagger_android}")
  kapt("com.google.dagger:dagger-android-processor:${Versions.dagger_android}")

  // dagger hilt
  implementation("com.google.dagger:hilt-android:${Versions.hilt_android}")
  kapt("com.google.dagger:hilt-android-compiler:${Versions.hilt_android}")
  implementation("androidx.hilt:hilt-common:${Versions.hilt_viewmodel}")
  implementation("androidx.hilt:hilt-lifecycle-viewmodel:${Versions.hilt_viewmodel}")
  kapt("androidx.hilt:hilt-compiler:${Versions.hilt_viewmodel}")

  // database
  implementation("androidx.room:room-runtime:2.2.5")
  kapt("androidx.room:room-compiler:2.2.5")
  implementation("androidx.room:room-ktx:2.2.5")
  implementation("androidx.room:room-rxjava2:2.2.5")

  // firebase
  implementation("com.google.firebase:firebase-ads:19.4.0")
  implementation("com.google.firebase:firebase-analytics:17.5.0")

  // network
  implementation("com.squareup.retrofit2:retrofit:2.9.0")
  implementation("com.squareup.retrofit2:converter-gson:2.9.0")
  implementation("com.squareup.retrofit2:adapter-rxjava2:2.6.2")
  implementation("com.squareup.okhttp3:logging-interceptor:4.7.2")
  implementation("com.github.akarnokd:rxjava3-retrofit-adapter:3.0.0")

  // rx
  implementation("io.reactivex.rxjava3:rxjava:3.0.5")
  implementation("io.reactivex.rxjava3:rxandroid:3.0.0")
  implementation("io.reactivex.rxjava3:rxkotlin:3.0.0")
  implementation("com.uber.autodispose2:autodispose:2.0.0")

  // licenses
  implementation("com.google.android.gms:play-services-oss-licenses:17.0.0")

  // log
  implementation("com.jakewharton.timber:timber:4.7.1")

  // youtube
  implementation("com.pierfrancescosoffritti.androidyoutubeplayer:core:10.0.5")

  // prefs
  implementation("com.github.AgustaRC.koap:koap:1.0.1")

  // loading
  implementation("com.facebook.shimmer:shimmer:0.5.0")

  // image ani
  implementation("com.airbnb.android:lottie:3.0.2")

  // image
  implementation("com.github.bumptech.glide:glide:4.11.0")
  kapt("com.github.bumptech.glide:compiler:4.11.0")

  // scraping
  implementation("org.jsoup:jsoup:1.13.1")

  // test
  testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:1.3.7")
  testImplementation("org.mockito:mockito-core:2.23.0")

  // atsl
  testImplementation("androidx.test.ext:junit:1.1.2")
  testImplementation("androidx.test:runner:1.3.0")
  testImplementation("androidx.test:rules:1.3.0")
  testImplementation("androidx.test.espresso:espresso-core:3.3.0")
  testImplementation("androidx.test.espresso:espresso-contrib:3.3.0")
  testImplementation("androidx.test.espresso:espresso-intents:3.3.0")
  testImplementation("androidx.arch.core:core-testing:2.1.0")
  testImplementation("androidx.work:work-testing:2.4.0")

}
