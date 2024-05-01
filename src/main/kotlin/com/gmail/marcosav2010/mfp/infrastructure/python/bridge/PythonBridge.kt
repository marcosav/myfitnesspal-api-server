package com.gmail.marcosav2010.mfp.infrastructure.python.bridge

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.io.File

@Component
class PythonBridge {

    @Value("\${python.binary}")
    private lateinit var python: String

    fun execute(executable: String, vararg args: String): PythonExecutionResult {
        val processBuilder = ProcessBuilder(python, File(executable).path, *args)
        processBuilder.redirectErrorStream(true)

        val process = processBuilder.start()
        val result = process.inputStream.bufferedReader().use { it.readText() }

        val exitCode = process.waitFor()

        return PythonExecutionResult(result, exitCode)
    }
}