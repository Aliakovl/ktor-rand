package com.example.plugins

import kotlin.random.*
import io.ktor.routing.*
import io.ktor.http.*
import io.ktor.application.*
import io.ktor.response.*
import java.io.File
import java.lang.IllegalArgumentException
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Application.configureRouting() {

    val charPool : List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

    fun randomString(x: Int) = (1..x)
        .map { _ -> Random.nextInt(0, charPool.size) }
        .map(charPool::get)
        .joinToString("")

    fun getFormattedDate(): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd kk:mm:ss.SSS")
        return LocalDateTime.now().format(formatter)
    }

    routing {
        get("/") {
            call.request.queryParameters["length"]?.let { length ->
                try {
                    val randStr = randomString(length.toInt())
                    call.respondText(randStr, ContentType.Text.Plain, HttpStatusCode.OK)
                    File("log").appendText("${getFormattedDate()} - $randStr\n")
                } catch (e: NumberFormatException){
                    call.respond(HttpStatusCode.BadRequest, "To large length")
                }
            }
            call.request.queryParameters["begin"]?.let { begin ->
                call.request.queryParameters["end"]?.let { end ->
                    try {
                        val randInt = Random.nextInt(begin.toInt(), end.toInt()+1).toString()
                        File("log").appendText("${getFormattedDate()} - $randInt\n")
                        call.respondText(randInt, ContentType.Text.Plain, HttpStatusCode.OK)
                    } catch (e: NumberFormatException){
                        call.respond(HttpStatusCode.BadRequest, "To large begin or end")
                    } catch (e: IllegalArgumentException){
                        call.respond(HttpStatusCode.BadRequest, "End must be greater than begin or equal")
                    }
                }
            }
        }
        get("/*") {
            call.request.let { _ -> call.respond(HttpStatusCode.NotFound, "No page found!") }
        }
    }
}
