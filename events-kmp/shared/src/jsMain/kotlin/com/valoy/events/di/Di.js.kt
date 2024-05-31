package com.valoy.events.di

import com.apollographql.apollo3.cache.normalized.api.MemoryCacheFactory
import com.apollographql.apollo3.cache.normalized.api.NormalizedCacheFactory

actual fun platformModule(): NormalizedCacheFactory = MemoryCacheFactory(10 * 1024 * 1024)