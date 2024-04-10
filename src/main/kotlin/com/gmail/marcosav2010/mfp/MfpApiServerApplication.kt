package com.gmail.marcosav2010.mfp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class MfpApiServerApplication

fun main(args: Array<String>) {
    runApplication<MfpApiServerApplication>(*args)
}
