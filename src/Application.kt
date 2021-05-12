package com.renner

import io.ktor.application.*
import io.ktor.jackson.*
import io.ktor.response.*
import io.ktor.request.*
import io.ktor.features.*
import org.slf4j.event.*
import io.ktor.routing.*
import io.ktor.http.*

data class computeData(val exp: String, val val_1: String, val val_2: String)
data class returnData(var result: String)

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    install(CallLogging) {
        level = Level.INFO
        filter { call -> call.request.path().startsWith("/") }
    }
    install(ContentNegotiation) {
        jackson()
    }

    routing {
        get("/") {
            call.respondText("Testroute Called!", contentType = ContentType.Text.Plain)
        }
        post(path = "/compute"){
            val requestData = call.receive<computeData>()
            call.respond(compute(requestData))
        }
    }
}

fun compute(inData: computeData): returnData {
    val var_1 = inData.val_1.toDouble()
    val var_2 = inData.val_2.toDouble()
    var outData = returnData("")
    when{
        inData.exp.contains('+') -> {outData.result = (var_1+var_2).toString()}
        inData.exp.contains('-') -> {outData.result = (var_1-var_2).toString()}
        inData.exp.contains('*') -> {outData.result = (var_1*var_2).toString()}
        inData.exp.contains('/') -> {outData.result = (var_1/var_2).toString()}

    }
    return outData
}

