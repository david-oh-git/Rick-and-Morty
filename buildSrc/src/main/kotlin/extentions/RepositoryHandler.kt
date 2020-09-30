package extentions

import org.gradle.api.artifacts.dsl.RepositoryHandler

/**
 * Adds default repositories.
 */
fun RepositoryHandler.applyDefault(){
    google()
    jcenter()
}