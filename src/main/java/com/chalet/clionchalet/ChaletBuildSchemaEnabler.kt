package com.chalet.clionchalet

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.jetbrains.jsonSchema.extension.JsonSchemaEnabler


class ChaletBuildSchemaEnabler : JsonSchemaEnabler {
    override fun isEnabledForFile(file: VirtualFile, project: Project?): Boolean {
        return SchemaUtils.isBuildFile(file)
    }

    override fun shouldShowSwitcherWidget(file: VirtualFile): Boolean {
        return false
    }
}