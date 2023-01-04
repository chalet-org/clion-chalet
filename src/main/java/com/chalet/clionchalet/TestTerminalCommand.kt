package com.chalet.clionchalet

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.DumbAwareAction
import org.jetbrains.plugins.terminal.ShellTerminalWidget
import org.jetbrains.plugins.terminal.TerminalView
import java.io.IOException


class TestTerminalCommand : DumbAwareAction() {
    private var terminal: ShellTerminalWidget? = null

    override fun actionPerformed(event: AnActionEvent) {

        val project = event.getData(PlatformDataKeys.PROJECT)
        if (project != null) {
            val terminalView = TerminalView.getInstance(project)
            val command = "${ChaletVersion.Release} build"
            try {
                if (terminal == null || !terminal!!.isValid) {
                    terminal = terminalView.createLocalShellWidget(project.basePath, "Chalet")
                }
                if (!terminal!!.hasRunningCommands()) {
                    terminal!!.executeCommand(command)
                }
            } catch (err: IOException) {
                err.printStackTrace()
            }
        }
    }

}
