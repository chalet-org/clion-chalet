package com.chalet.clionchalet

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.PlatformDataKeys
import com.intellij.openapi.project.DumbAwareAction
import com.intellij.openapi.ui.Messages
import kotlinx.coroutines.runBlocking
import org.json.simple.parser.JSONParser

class GetChaletState : DumbAwareAction() {

    override fun actionPerformed(event: AnActionEvent) {
        val project = event.getData(PlatformDataKeys.PROJECT)

        val result = getProcessOutput("chalet", arrayOf("query", "state-chalet-json"))
        if (result != null) {
            val json = JSONParser().parse(result)
            println(json.toString())

            Messages.showMessageDialog(project, result, "Chalet State", Messages.getInformationIcon())
        }
    }

}
