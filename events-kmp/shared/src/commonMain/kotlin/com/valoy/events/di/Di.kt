package com.valoy.events.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.normalizedCache
import com.valoy.events.infra.repository.UserNetworkRepository

expect fun platformModule(): NormalizedCacheFactory

object CommonDi{
    val userNetworkRepository by lazy { createUserNetworkRepository() }
    val apolloClient by lazy { createApolloClient(platformModule()) }

    private fun createUserNetworkRepository(): UserNetworkRepository {
        return UserNetworkRepository()
    }

    private fun createApolloClient(normalizedCacheFactory: NormalizedCacheFactory): ApolloClient {
        return ApolloClient.Builder()
            .serverUrl("http://10.0.2.2:8081/graphql")
            .normalizedCache(normalizedCacheFactory, writeToCacheAsynchronously = true)
            .build()
    }

}

