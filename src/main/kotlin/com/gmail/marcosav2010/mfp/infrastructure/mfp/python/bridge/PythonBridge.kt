package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.bridge

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

@Component
class PythonBridge {

    @Value("\${mfp.python.path}")
    private lateinit var resourceFile: String

    @Value("\${mfp.python.binary}")
    private lateinit var python: String

    fun execute(cmd: String, vararg args: String): PythonExecutionResult {
        val processBuilder = ProcessBuilder(python, File(resourceFile).path, cmd, *args)
        processBuilder.redirectErrorStream(true)

        val process = processBuilder.start()
        val result = process.inputStream.bufferedReader().use { it.readText() }

        val exitCode = process.waitFor()

        return PythonExecutionResult(result, exitCode)
    }
}