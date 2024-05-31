package com.valoy.events.di

import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory
import com.valoy.events.di.AndroidDi.memoryCacheFactory

actual fun platformModule(): NormalizedCacheFactory = memoryCacheFactory

object AndroidDi {
    val memoryCacheFactory by lazy {
        MemoryCacheFactory(10 * 1024 * 1024)
            .chain(SqlNormalizedCacheFactory("events.db"))
    }
}