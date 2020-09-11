
interface BuildType {

    companion object {
        const val DEBUG = "debug"
        const val RELEASE = "release"
    }

    val isMinifyEnabled: Boolean
}

object BuildTypeDebug : BuildType {

    override val isMinifyEnabled: Boolean
        get() = false

    const val applicationIdSuffix = ".debug"
    const val versionNameSuffix = "-debug"
}

object BuildTypeRelease : BuildType {

    override val isMinifyEnabled: Boolean
        get() = true
}