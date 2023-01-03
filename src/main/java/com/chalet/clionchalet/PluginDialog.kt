package com.chalet.clionchalet

import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages

class PluginDialog(private val project: Project) {
    private val errorIcon = Messages.getErrorIcon()
    private val infoIcon = Messages.getInformationIcon()

    fun showErrorMessage(message: String) {
        Messages.showDialog(project, message, "Error", arrayOf("OK"), 0, errorIcon)
    }

    fun showInformationMessage(message: String, options: Array<String> = arrayOf("OK"), handler: ((Int) -> Unit)? = null ) {
        val selection = Messages.showDialog(project, message, "Info", options, 0, infoIcon)
        if (handler != null) handler(selection)
    }
}