package com.example

import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*


fun runServer(port: Int = 8080){
    embeddedServer(Netty, port = port, host = "0.0.0.0") {
        configureRouting()
    }.start(wait = true)
}

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        runServer()
    }
    else if (args[0] == "--port"){
        val a: Int? = try {
            val p = args[1].toInt()
            if (p in 0..65536) p else throw RuntimeException("Incorrect port")
        } catch (e: NumberFormatException) {
            throw RuntimeException("Incorrect port")
        }
        a?.let { runServer(it) }
    }
    else {
        throw RuntimeException("Invalid option ${args[0]}")
    }
}