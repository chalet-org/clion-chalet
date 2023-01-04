package com.chalet.clionchalet

import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

fun getProcessOutput(executable: String, args: Array<String>, env: Map<String, String> = mapOf(), cwd: File = File(".")): String? {
    return try {
        val proc = ProcessBuilder(executable, *args)
            .directory(cwd)
            .redirectInput(ProcessBuilder.Redirect.INHERIT)
            .redirectOutput(ProcessBuilder.Redirect.PIPE)
            .redirectError(ProcessBuilder.Redirect.PIPE)

        val procEnv = proc.environment()
        for ((key,value) in env) {
            procEnv[key] = value
        }

        val started = proc.start()
        started.waitFor(5, TimeUnit.SECONDS)

        var ret = started.inputStream.bufferedReader().readText()
        while (ret.endsWith("\n") || ret.endsWith("\r")) {
            ret = ret.dropLast(1)
        }
        ret
    } catch(e: IOException) {
        e.printStackTrace()
        null
    }
}