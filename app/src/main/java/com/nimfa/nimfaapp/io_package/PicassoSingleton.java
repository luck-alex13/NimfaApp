package com.nimfa.nimfaapp.io_package;

import android.content.Context;
import android.net.Uri;
import android.util.Log;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.OkHttpClient;


public class PicassoSingleton {

    private static final String LOG_TAG = PicassoSingleton.class.getSimpleName();

    private static Picasso mInstance;
    private static long mDiskCacheSize = Constants.DISK_CACHE_SIZE * 1024 * 1024; //Disk Cache
    //private static int mMemoryCacheSize = Constants.MEMORY_CACHE_SIZE *1024*1024; //Memory Cache
    private static OkHttpClient mOkHttpClient; //OK Http Client for downloading
    private static Cache diskCache;
    private static LruCache lruCache;


    public static Picasso getSharedInstance(Context context) {
        if (mInstance == null) {
            if (context != null) {
                //Create disk cache folder if does not exist
                File cache = new File(context.getApplicationContext().getCacheDir(), "picasso_cache");
                if (!cache.exists()) {
                    cache.mkdirs();
                }

                diskCache = new Cache(cache, mDiskCacheSize);
                lruCache = new LruCache(context);
                //Create OK Http Client with retry enabled, timeout and disk cache
                mOkHttpClient = new OkHttpClient.Builder()
                        .cache(diskCache)
                        .connectTimeout(Constants.SECONDS_TO_OK_HTTP_TIME_OUT, TimeUnit.SECONDS)
                        .retryOnConnectionFailure(true)
                        .build();
                //For better performence in Memory use set memoryCache(Cache.NONE) in this builder (If needed)
                Picasso.Builder builder = new Picasso.Builder(context)
                        .memoryCache(lruCache)/*.executor(Executors.newSingleThreadExecutor())*/
                        .downloader(new OkHttp3Downloader(mOkHttpClient))
                        .indicatorsEnabled(Constants.SHOW_PICASSO_INDICATORS)
                        .listener(new Picasso.Listener() {
                            @Override
                            public void onImageLoadFailed(Picasso picasso, Uri uri, Exception ex) {
                                Log.d(LOG_TAG, " onImageLoadFailed " + ex);
                                ex.printStackTrace();
                            }
                        });
                mInstance = builder.build();

            }
        }
        return mInstance;
    }

    public static void deletePicassoInstance() {
        mInstance = null;
    }

    public static void clearLRUCache() {
        if (lruCache != null) {
            lruCache.clear();
            Log.d("LOG_TAG", "clearing LRU cache");
        }

        lruCache = null;

    }

    public static void clearDiskCache() {
        try {
            if (diskCache != null) {
                diskCache.evictAll();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        diskCache = null;

    }



}
