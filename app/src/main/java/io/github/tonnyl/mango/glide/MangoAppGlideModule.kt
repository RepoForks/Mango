package io.github.tonnyl.mango.glide

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.Registry
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.integration.okhttp3.OkHttpUrlLoader
import com.bumptech.glide.load.engine.cache.InternalCacheDiskCacheFactory
import com.bumptech.glide.load.engine.cache.LruResourceCache
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.module.AppGlideModule
import java.io.InputStream

/**
 * Created by lizhaotailang on 2017/7/18.
 *
 * To generated glide apis.
 */

@GlideModule
class MangoAppGlideModule : AppGlideModule() {

    companion object {
        // Max cache size of glide.
        @JvmField
        val MAX_CACHE_SIZE = 1024 * 1024 * 512 // 512M

        // The cache directory name.
        @JvmField
        val CACHE_FILE_NAME = "IMG_CACHE" // cache file dir name

    }

    override fun applyOptions(context: Context?, builder: GlideBuilder?) {
        super.applyOptions(context, builder)

        // 36MB, memory cache size
        // default value: 24MB
        val memoryCacheSize = 1024 * 1024 * 36
        builder?.setMemoryCache(LruResourceCache(memoryCacheSize))

        // Internal cache
        builder?.setDiskCache(InternalCacheDiskCacheFactory(context, CACHE_FILE_NAME, MAX_CACHE_SIZE))
    }

    override fun registerComponents(context: Context?, registry: Registry?) {
        super.registerComponents(context, registry)
        // Replace the http connection with okhttp
        registry?.replace(GlideUrl::class.java, InputStream::class.java, OkHttpUrlLoader.Factory())
    }

    /**
     * Disable the parsing of manifest file.
     */
    override fun isManifestParsingEnabled(): Boolean {
        return false
    }

}