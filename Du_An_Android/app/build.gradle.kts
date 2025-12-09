plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "ntu.khoi.du_an_android"
    compileSdk = 36

    defaultConfig {
        applicationId = "ntu.khoi.du_an_android"
        minSdk = 24
        targetSdk = 36
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
}

dependencies {
    // Các thư viện mặc định có sẵn
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)

    // --- THÊM ĐOẠN NÀY: Cấu hình Room Database cho Java ---
    val room_version = "2.6.1"

    // Thư viện chính của Room
    implementation("androidx.room:room-runtime:$room_version")

    // Công cụ xử lý Annotation (Bắt buộc để dùng Room với Java)
    annotationProcessor("androidx.room:room-compiler:$room_version")
    // -------------------------------------------------------
}