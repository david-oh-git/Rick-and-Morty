// Top-level build file where you can add configuration options common to all sub-projects/modules.

allprojects {

    repositories {
        google()
        jcenter()
    }


}



tasks.register("clean").configure {
    delete(rootProject.buildDir)
}
