//apply plugin: "com.android.dynamic-feature"
//apply plugin: 'kotlin-android'
//apply plugin: 'kotlin-android-extensions'

import dependencies.BuildDependencies

plugins {
    id("commons.android-dynamic-feature")
}

dependencies {

    implementation( project(BuildModules.APP))
    implementation( project(BuildModules.Features.CHARACTERS_LIST))

    implementation(BuildDependencies.NAVIGATION_UI)
    implementation(BuildDependencies.APPCOMPAT)
}