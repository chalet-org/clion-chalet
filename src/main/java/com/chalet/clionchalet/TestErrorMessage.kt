package com.chalet.clionchalet

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.DumbAwareAction

class TestErrorMessage : DumbAwareAction() {
    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)
        if (project != null) {
            val loader = ChaletToolsLoader(project)
            loader.handleError(Error("A bunch of garbage happened!"))
        }
    }

}
