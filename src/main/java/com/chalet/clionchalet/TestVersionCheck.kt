package com.chalet.clionchalet

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.DumbAwareAction

class TestVersionCheck : DumbAwareAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        if (project != null) {
            val loader = ChaletToolsLoader(project)
            loader.checkForCompatibleVersion()
        }
    }

}
