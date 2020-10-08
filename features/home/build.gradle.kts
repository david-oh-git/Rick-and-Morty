
import dependencies.BuildDependencies

plugins {
    id("commons.android-dynamic-feature")
}

android{
    buildFeatures.dataBinding = true
}

dependencies {

    implementation( project(BuildModules.APP))
    implementation( project(BuildModules.Features.CHARACTERS_LIST))

    implementation(BuildDependencies.NAVIGATION_UI)
    implementation(BuildDependencies.APPCOMPAT)
}