package com.valoy.events.di

import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.sql.SqlNormalizedCacheFactory

actual fun platformModule() = MemoryCacheFactory(10 * 1024 * 1024)
    .chain(SqlNormalizedCacheFactory("events.db"))