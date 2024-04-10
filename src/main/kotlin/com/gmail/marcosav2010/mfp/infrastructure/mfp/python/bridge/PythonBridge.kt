package com.gmail.marcosav2010.mfp.infrastructure.mfp.python.bridge

import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class PythonBridge {

    @Value("\${mfp.python.path}")
    private var resourceFile: Resource? = null

    @Value("\${mfp.python.binary}")
    private lateinit var python: String

    fun execute(cmd: String, vararg args: String): PythonExecutionResult {
        val processBuilder = ProcessBuilder(python, resourceFile!!.file.path, cmd, *args)
        processBuilder.redirectErrorStream(true)

        val process = processBuilder.start()
        val result = process.inputStream.bufferedReader().use { it.readText() }

        val exitCode = process.waitFor()

        return PythonExecutionResult(result, exitCode)
    }
}