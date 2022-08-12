plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
}

version = "1.0"


kotlin {
    android()
    iosX64()
    iosArm64()
    iosSimulatorArm64()

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        podfile = project.file("../iosApp/Podfile")
        framework {
            baseName = "shared"
        }
    }

    val ktorVersion = "1.5.0"
    val coroutinesVersion = "1.4.2-native-mt"
    val serializationVersion = "1.0.1"
    val sqlDelightVersion = "1.4.4"
    val mokMod = "0.13.1"
    val mokNetwork = "0.18.0"

    sourceSets {
        val commonMain by getting {
            dependencies{
                api("org.jetbrains.kotlinx:kotlinx-serialization-core:${serializationVersion}")

                implementation("dev.icerock.moko:network:${mokNetwork}")
                // kbignum serializer
                implementation("dev.icerock.moko:network-bignum:${mokNetwork}")
                // moko-errors integration
                implementation("dev.icerock.moko:network-errors:${mokNetwork}")

                // Coroutines
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${coroutinesVersion}")
                implementation("com.squareup.sqldelight:coroutines-extensions:${sqlDelightVersion}")

                // only ViewModel, EventsDispatcher, Dispatchers.UI
                implementation("dev.icerock.moko:mvvm-core:${mokMod}")

                // api mvvm-core, CFlow for native and binding extensions
                implementation("dev.icerock.moko:mvvm-flow:${mokMod}")

                // api mvvm-core, LiveData and extensions
                implementation("dev.icerock.moko:mvvm-livedata:${mokMod}")

                // api mvvm-livedata, ResourceState class and extensions
                implementation("dev.icerock.moko:mvvm-state:${mokMod}")

                // api mvvm-core, moko-resources, extensions for LiveData with moko-resources
                implementation("dev.icerock.moko:mvvm-livedata-resources:${mokMod}")

                // api mvvm-livedata, Material library android extensions
                implementation("dev.icerock.moko:mvvm-livedata-material:${mokMod}")

                // api mvvm-livedata, Glide library android extensions
                implementation("dev.icerock.moko:mvvm-livedata-glide:${mokMod}")

                // api mvvm-livedata, SwipeRefreshLayout library android extensions
                implementation("dev.icerock.moko:mvvm-livedata-swiperefresh:${mokMod}")

                // api mvvm-livedata, DataBinding support for Android
                implementation("dev.icerock.moko:mvvm-databinding:${mokMod}")

                // api mvvm-livedata, ViewBinding support for Android
                implementation("dev.icerock.moko:mvvm-viewbinding:${mokMod}")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies {
                implementation("com.google.android.material:material:1.2.1")
                api("io.ktor:ktor-client-okhttp:${ktorVersion}")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-android:${coroutinesVersion}")
                api("com.squareup.sqldelight:android-driver:${sqlDelightVersion}")
            }
        }
        val androidTest by getting
        val iosX64Main by getting{
            dependencies {
                implementation("io.ktor:ktor-client-ios:${ktorVersion}")
                implementation("com.squareup.sqldelight:native-driver:${sqlDelightVersion}")
                implementation("dev.icerock.moko:mvvm-core:0.13.1")
                implementation("dev.icerock.moko:mvvm-livedata:0.13.1")
                implementation("dev.icerock.moko:mvvm-livedata-resources:0.13.1")
                implementation("dev.icerock.moko:mvvm-state:0.13.1")
            }
        }
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
        }
        val iosX64Test by getting
        val iosArm64Test by getting
        val iosSimulatorArm64Test by getting
        val iosTest by creating {
            dependsOn(commonTest)
            iosX64Test.dependsOn(this)
            iosArm64Test.dependsOn(this)
            iosSimulatorArm64Test.dependsOn(this)
        }
    }
}

android {
    compileSdk = 32
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdk = 22
        targetSdk = 32
    }
}