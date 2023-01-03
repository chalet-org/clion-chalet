package com.chalet.clionchalet

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.DumbAwareAction
import org.jetbrains.plugins.terminal.TerminalView
import java.io.IOException


class TestTerminalCommand : DumbAwareAction() {
    override fun actionPerformed(event: AnActionEvent) {

        val project = event.getData(PlatformDataKeys.PROJECT)
        if (project != null) {
            val terminalView = TerminalView.getInstance(project)
            val command = "${ChaletVersion.Release} build"
            try {
                terminalView.createLocalShellWidget(project.basePath, "Chalet").executeCommand(command)
            } catch (err: IOException) {
                err.printStackTrace()
            }
        }
    }

}
