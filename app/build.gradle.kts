plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.eni_shop"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.eni_shop"
        minSdk = 26
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    //moshi
    implementation("com.squareup.moshi:moshi:1.14.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.14.0")
    //retrofit
    implementation("com.squareup.retrofit2:converter-moshi:2.9.0")

    //ROOM
    val room_version = "2.7.2"
    implementation("androidx.room:room-runtime:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // To use Kotlin annotation processing tool (ksp)
    ksp("androidx.room:room-compiler:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    // optional - Test helpers
    testImplementation("androidx.room:room-testing:$room_version")

    //accès à la méthode viewModel()
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")

    //SQLLite permet interaction avec la base
    val sqliteVersion = "2.5.2"
    implementation("androidx.sqlite:sqlite-ktx:$sqliteVersion")
    implementation("androidx.sqlite:sqlite-framework:$sqliteVersion")
    implementation ("androidx.datastore:datastore-preferences:1.1.7")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    //navigation avec compose
    implementation("androidx.navigation:navigation-compose:2.9.2")
    //accès à la méthode viewModel()
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.9.2")
    //AsyncImage
    implementation("io.coil-kt:coil-compose:2.5.0")
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}