package dependencies

object BuildDependencies {

    const val KOTLIN = "org.jetbrains.kotlin:kotlin-stdlib:${BuildVersions.KOTLIN}"
    const val APPCOMPAT = "androidx.appcompat:appcompat:${BuildVersions.APPCOMPAT}"
    const val CORE_KTX = "androidx.core:core-ktx:${BuildVersions.CORE_KTX}"
    const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:${BuildVersions.CONSTRAIN_LAYOUT}"
    const val ROOM_DB = "androidx.room:room-runtime:${BuildVersions.ROOM_DB}"
    const val ROOM_KTX = "androidx.room:room-ktx:${BuildVersions.ROOM_DB}"
    const val ROOM_KAPT = "androidx.room:room-compiler:${BuildVersions.ROOM_DB}"
    const val NAVIGATION_FRAGMENT = "androidx.navigation:navigation-fragment-ktx:${BuildVersions.NAVIGATION_FRAGMENT}"
    const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${BuildVersions.NAVIGATION_FRAGMENT}"
    const val RETROFIT = "com.squareup.retrofit2:retrofit:${BuildVersions.RETROFIT}"
    const val RETROFIT_GSON = "com.squareup.retrofit2:converter-gson:${BuildVersions.RETROFIT}"
    const val HTTP_LOGGING = "com.squareup.okhttp3:logging-interceptor:${BuildVersions.HTTP_LOGGING}"
    const val TIMBER = "com.jakewharton.timber:timber:${BuildVersions.TIMBER}"
    const val MOSHI = "com.squareup.moshi:moshi:${BuildVersions.MOSHI}"
    const val MOSHI_KTX = "com.squareup.moshi:moshi-kotlin:${BuildVersions.MOSHI}"
    const val MATERIAL_COMPONENTS = "com.google.android.material:material:${BuildVersions.MATERIAL_COMPONENTS}"
    const val KOTLIN_COROUTINES_ANDROID = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${BuildVersions.KOTLIN_COROUTINES_ANDROID}"


}