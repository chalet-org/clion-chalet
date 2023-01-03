package com.chalet.clionchalet

import com.intellij.ide.BrowserUtil
import com.intellij.openapi.project.Project

class VersionHelper(val valid: Boolean, val ver: SemanticVersion) {
    operator fun component1(): Boolean = valid
    operator fun component2(): SemanticVersion = ver
}

class ChaletToolsLoader(project: Project) {
    private var checkedVersion = false
    private var versionValid = false
    private val dialog = PluginDialog(project)

    private fun getVersionFromChalet(): SemanticVersion {
        val chalet = ChaletVersion.Release
        val str = getProcessOutput(chalet, arrayOf("--version"))
        val versionStr = str?.split(" ")?.toTypedArray()?.get(2) ?: "0.0.0"
        val arr = versionStr.split(".").toTypedArray()

        return SemanticVersion(
            major = arr[0].toInt(),
            minor = arr[1].toInt(),
            patch = arr[2].toInt(),
        )
    }

    private fun getMinimumVersion(): SemanticVersion {
        return SemanticVersion(
            major = 0,
            minor = 6,
            patch = 0
        )
    }

    private fun isVersionValid(): VersionHelper {
        val version = getVersionFromChalet()
        val min = getMinimumVersion()

        println(version)
        println(min)

        if (
            version.major < min.major ||
            (version.major == min.major && version.minor < min.minor) ||
            (version.major == min.major && version.minor == min.minor && version.patch < min.patch)
        ) {
            return VersionHelper(false, version)
        }

        return VersionHelper(true, version)
    }

    fun checkForCompatibleVersion() {
        if (!checkedVersion) {
            val (valid, ver) = isVersionValid()
            versionValid = valid
            if (!versionValid) {
                val min = getMinimumVersion()
                dialog.showInformationMessage(
                    message = "This version of the Chalet extension requires Chalet version ${min.major}.${min.minor}.${min.patch} or higher, but version ${ver.major}.${ver.minor}.${ver.patch} was found.",
                    options = arrayOf("Download","Cancel"),
                    handler = { idx: Int ->
                        if (idx == 0) {
                            BrowserUtil.browse("https://www.chalet-work.space/download")
                        }
                    }
                );
            }

            checkedVersion = true
        }
    }

    fun handleError(err: Error?) {
        dialog.showErrorMessage(err?.message ?: "An unknown error occurred")
    }
}
