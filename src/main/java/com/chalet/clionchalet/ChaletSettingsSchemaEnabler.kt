package com.chalet.clionchalet

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.jsonSchema.extension.JsonSchemaEnabler


class ChaletSettingsSchemaEnabler : JsonSchemaEnabler {
    override fun isEnabledForFile(file: VirtualFile, project: Project?): Boolean {
       return SchemaUtils.isSettingsFile(file)
    }

    override fun shouldShowSwitcherWidget(file: VirtualFile): Boolean {
        return false
    }
}