package com.chalet.clionchalet

import com.intellij.json.JsonFileType


class ChaletSettingsFileType private constructor() : JsonFileType() {
    override fun getDisplayName(): String {
        return "Chalet Settings File"
    }

    override fun getName(): String {
        return "Chalet Settings File"
    }

    override fun getDescription(): String {
        return "Chalet settings file"
    }

    override fun getDefaultExtension(): String {
        return "chaletrc"
    }
}