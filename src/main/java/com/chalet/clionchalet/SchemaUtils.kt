package com.chalet.clionchalet

import com.intellij.openapi.vfs.VirtualFile

object SchemaUtils {
    fun isSettingsFile(file: VirtualFile): Boolean {
        if (file.isDirectory || !file.isValid) {
            return false
        }

        val name = file.nameSequence
        return name == ".chaletrc" || file.path.endsWith(".chalet/config.json")
    }

    fun isBuildFile(file: VirtualFile): Boolean {
        if (file.isDirectory || !file.isValid) {
            return false
        }

        val name = file.nameSequence
        return name == "chalet.json"
    }
}