plugins {
    id("com.android.application")
//    id("com.chaquo.python")


}

android {
    namespace = "com.example.accident_detection"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.accident_detection"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

//        ndk {
//            // On Apple silicon, you can omit x86_64.
//            abiFilters += listOf("arm64-v8a", "x86_64")
//        }
//        ndk {
//            abiFilters+= listOf("arm64-v8a", "x86", "x86_64")
//        }
//        python{
//            version ="3.9.11"
//        }
//        python{
//            buildPython = "C:\Users\Mani\AppData\Local\Programs\Python\Python39\python.exe"
//        }
//        sourceSets {
//            main {
//                python.srcDir = "src/main/python"
//            }
//        }
//
   }
//
//    python {
//        version = "3.9.11"
//        buildPython = "C:\\Users\\Mani\\AppData\\Local\\Programs\\Python\\Python39\\python.exe"
//        sourceSets {
//            main {
//                python.srcDir("src/main/python")
//            }
//        }
//    }
    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {

    constraints {
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.8.0") {
            because("kotlin-stdlib-jdk7 is now a part of kotlin-stdlib")
        }
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.8.0") {
            because("kotlin-stdlib-jdk8 is now a part of kotlin-stdlib")
        }
    }

    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.5.0")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.annotation:annotation:1.6.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.2")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.2")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation ("com.google.android.gms:play-services-maps:18.0.1")
    implementation ("com.google.android.gms:play-services-location:18.0.0")
    implementation ("com.github.haifengl:smile-core:2.6.0")
// Use the latest version
//    implementation("com.google.cloud:google-cloud-dialogflow:2.1.0")
//    implementation ('io.grpc:grpc-okhttp:1.29.0')


}