package com.example

import com.example.entities.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import com.example.plugins.*
import com.example.repository.Functions
import com.example.repository.MySQLRepository
import io.ktor.http.*
import io.ktor.serialization.gson.*
import io.ktor.server.application.*
import io.ktor.server.plugins.callloging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        Module()
    }.start(wait = true)
}

fun Application.Module(){
    install(CallLogging)
    install(ContentNegotiation){
        gson {
            setPrettyPrinting()
        }
    }

    routing {
        val repository: Functions = MySQLRepository()

        get ("/"){
            call.respondText("Expense Sharing Application.")
        }

        get ("/group"){
            call.respond(repository.getAllGroups())
        }


        /*
        get ("/group/{groupno}"){
            //gets all the members for the group.

            val id = call.parameters["groupno"]?.toIntOrNull()

            if (id == null){
                call.respond(HttpStatusCode.BadRequest,
                    "Group no parameter has to be a number")

                return@get
            }

            val todo = repository.getGroup(id)

            if (todo == null){
                call.respond(HttpStatusCode.NotFound,
                    "found no groups with the provided $id")
            }
            else{
                call.respond(todo)
            }
        }

         */




        get ("/transactions"){
            call.respond(repository.getAllTransactions())
        }




        /*
        get ("/transactions/{usid}"){
            //gets all the transactions of the id.

            val id = call.parameters["usid"]?.toIntOrNull()

            if (id == null){
                call.respond(HttpStatusCode.BadRequest,
                    "User id parameter has to be a number")

                return@get
            }

            val todo = repository.getTransactions(id)

            if (todo == null){
                call.respond(HttpStatusCode.NotFound,
                    "found no users with the user id provided $id")
            }
            else{
                call.respond(todo)
            }

        }


         */

        post ("/transaction/{owesto}"){
            //creates a new transaction and updates balance.

            val id = call.parameters["owesto"]?.toIntOrNull()
            val tranDraft = call.receive<TransactionDraft>()

            if (id == null){

                call.respond(HttpStatusCode.BadRequest,
                    "User id parameter has to be a number")
                return@post
            }

            val updated = repository.addTransaction(id, tranDraft)
            call.respond(updated)




        }

        post ("/adduser"){
            //adds a new user



            val id = call.parameters["groupno"]?.toIntOrNull()
            val usedraft = call.receive<UserDraft>()

            val added = repository.addUser(usedraft)

            call.respond(added)


        }

        post ("/newgroup"){
            //creates a new group.

            val grdraft = call.receive<Groupdraft>()
            val done = repository.addgroup(grdraft)

            call.respond(done)
        }

    }

}
