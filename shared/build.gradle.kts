plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {
        val ktorVersion = "2.2.4"
        val nav_version = "2.5.3"

        val commonMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-core:$ktorVersion")
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
                //new
                implementation("io.ktor:ktor-client-content-negotiation:$ktorVersion")
                implementation("io.ktor:ktor-serialization-kotlinx-json:$ktorVersion")
                //KMM-ViewModel
                implementation("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-3")

                // Java language implementation
                implementation("androidx.navigation:navigation-fragment:$nav_version")
                implementation("androidx.navigation:navigation-ui:$nav_version")

                // Kotlin
                implementation("androidx.navigation:navigation-fragment-ktx:$nav_version")
                implementation("androidx.navigation:navigation-ui-ktx:$nav_version")

                // Feature module Support
                implementation("androidx.navigation:navigation-dynamic-features-fragment:$nav_version")

                // Testing Navigation
                implementation("androidx.navigation:navigation-testing:$nav_version")

                // Jetpack Compose Integration
                implementation("androidx.navigation:navigation-compose:$nav_version")
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test"))
            }
        }
        val androidMain by getting{
            dependencies {
                implementation("io.ktor:ktor-client-okhttp:$ktorVersion")
                //new
                implementation("io.ktor:ktor-client-android:$ktorVersion")

                //KMM-ViewModel
                implementation("com.rickclephas.kmm:kmm-viewmodel-core:1.0.0-ALPHA-3")
            }
        }
        val androidUnitTest by getting
        val iosX64Main by getting
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
            dependencies {
                implementation("io.ktor:ktor-client-darwin:$ktorVersion")
            }
        }
    }
}

android {
    namespace = "com.example.mediaplayer"
    compileSdk = 33
    defaultConfig {
        minSdk = 29
    }
}