package com.bksapp.bookshare

import android.app.Application
import coil3.ImageLoader
import coil3.PlatformContext
import coil3.SingletonImageLoader
import coil3.disk.DiskCache
import coil3.disk.directory
import coil3.memory.MemoryCache
import coil3.request.crossfade
import coil3.util.DebugLogger
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookShareApp : Application(), SingletonImageLoader.Factory {
    override fun newImageLoader(context: PlatformContext): ImageLoader {
        return ImageLoader.Builder(context)
            .crossfade(true)
            .memoryCache {
                MemoryCache.Builder().maxSizePercent(context,0.25)
                .build() }
            .diskCache {
                     DiskCache.Builder()
                     .directory(cacheDir.resolve("book_cache"))
                     .maxSizeBytes(50L * 1024 * 1024).build()
            }

            .logger(DebugLogger())
            .build()
    }
}