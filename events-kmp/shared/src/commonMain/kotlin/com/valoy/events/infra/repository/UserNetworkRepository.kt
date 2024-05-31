package com.valoy.events.infra.repository

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.api.ApolloRequest
import com.apollographql.apollo3.api.ApolloResponse
import com.apollographql.apollo3.api.Operation
import com.apollographql.apollo3.api.Query
import com.apollographql.apollo3.cache.normalized.CacheFirstInterceptor
import com.apollographql.apollo3.cache.normalized.CacheOnlyInterceptor
import com.apollographql.apollo3.cache.normalized.FetchPolicy
import com.apollographql.apollo3.cache.normalized.fetchPolicy
import com.apollographql.apollo3.cache.normalized.refetchPolicyInterceptor
import com.apollographql.apollo3.cache.normalized.watch
import com.apollographql.apollo3.interceptor.ApolloInterceptor
import com.apollographql.apollo3.interceptor.ApolloInterceptorChain
import com.valoy.events.GetAllUsersQuery
import com.valoy.events.di.CommonDi
import com.valoy.events.domain.repository.UserRepository
import com.valoy.events.domain.model.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class UserNetworkRepository(private val apolloClient: ApolloClient = CommonDi.apolloClient) :
    UserRepository {

    private val refetchPolicyInterceptor = object : ApolloInterceptor {
        var hasSeenValidResponse: Boolean = false
        override fun <D : Operation.Data> intercept(request: ApolloRequest<D>, chain: ApolloInterceptorChain): Flow<ApolloResponse<D>> {
            return if (!hasSeenValidResponse) {
                CacheOnlyInterceptor.intercept(request, chain).onEach {
                    if (it.data != null) {
                        // We have valid data, we can now use the network
                        hasSeenValidResponse = true
                    }
                }
            } else {
                // If for some reason we have a cache miss, get fresh data from the network
                CacheFirstInterceptor.intercept(request, chain)
            }
        }
    }

    override suspend fun add(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun getAll(): Flow<List<User>> {
        return apolloClient.query(GetAllUsersQuery())
            .watch().filter { it.exception == null}
            .map { response -> response.dataOrThrow().getAllUsers.map { users -> users.toUser() } }
    }

    override fun find(id: Int): User? {
        TODO("Not yet implemented")
    }

    override fun upsert(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun delete(user: User): Boolean {
        TODO("Not yet implemented")
    }

    override fun filter(name: String?, email: String?): List<User> {
        TODO("Not yet implemented")
    }

}
