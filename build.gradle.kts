import extentions.applyDefault

// Top-level build file where you can add configuration options common to all sub-projects/modules.

allprojects {

    repositories.applyDefault()

    plugins.apply(BuildPlugins.SPOTLESS)
    plugins.apply(BuildPlugins.KTLINT)

}