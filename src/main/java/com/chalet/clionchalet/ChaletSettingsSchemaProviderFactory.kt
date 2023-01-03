package com.chalet.clionchalet

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.jetbrains.jsonSchema.extension.JsonSchemaFileProvider
import com.jetbrains.jsonSchema.extension.JsonSchemaProviderFactory

class ChaletSettingsSchemaProviderFactory : JsonSchemaProviderFactory, DumbAware {
    override fun getProviders(project: Project): List<JsonSchemaFileProvider> {
        val provider = ChaletSettingsSchemaProvider();
        return listOf(provider)
    }

    companion object {
        const val SCHEMA_FILE_NAME = "chalet-settings.schema.json"
    }
}