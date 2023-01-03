package com.chalet.clionchalet

import com.intellij.json.JsonFileType


class ChaletBuildFileType private constructor() : JsonFileType() {
    override fun getDisplayName(): String {
        return "Chalet Build File"
    }

    override fun getName(): String {
        return "Chalet Build File"
    }

    override fun getDescription(): String {
        return "Chalet build file"
    }

    override fun getDefaultExtension(): String {
        return "json"
    }
}