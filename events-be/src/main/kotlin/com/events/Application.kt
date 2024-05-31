package com.events

import com.events.infra.graphql.user.UserMutation
import com.events.infra.graphql.user.UserQuery
import com.events.infra.repository.UserMemoryRepository
import com.expediagroup.graphql.server.ktor.GraphQL
import com.expediagroup.graphql.server.ktor.graphQLPostRoute
import com.expediagroup.graphql.server.ktor.graphQLSDLRoute
import com.expediagroup.graphql.server.ktor.graphiQLRoute
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.cio.*
import io.ktor.server.routing.routing

fun main() {
    embeddedServer(CIO, port = 8081, host = "localhost", module = Application::graphQLModule)
        .start(wait = true)
}

fun Application.graphQLModule() {
    install(GraphQL) {
        schema {
            packages = listOf(
                "com.events.infra.dto",
                "com.events.infra.graphql",
            )
            queries = listOf(
                UserQuery(UserMemoryRepository),
                //EventQuery(EventMemoryRepository)
            )
            mutations = listOf(
                UserMutation(UserMemoryRepository),
            )
            typeHierarchy = mapOf(
                //EventDTO::class to listOf(MusicalEventDTO::class, SportEventDTO::class, OtherEventDTO::class)
            )
        }
        server {
            //contextFactory = CustomContextFactory()
        }
    }
    routing {
        graphQLPostRoute()
        graphiQLRoute()
        graphQLSDLRoute()
    }
}
