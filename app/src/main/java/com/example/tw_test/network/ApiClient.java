package com.example.tw_test.network;

import android.content.Context;

import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    public static final String BASE_URL = "https://simplifiedcoding.net/demos/";
    private static Retrofit retrofit = null;
    int cacheSize = 10 * 1024 * 1024; // 10 MiB
    ConnectionDetector connectionDetector;




    /**
     * This method returns retrofit client instance
     *
     * @return Retrofit object
     */
    public static Retrofit getClient(Context context) {
        if (retrofit == null) {
            final ConnectionDetector connectionDetector = new ConnectionDetector(context);
            Cache cache = new Cache(context.getCacheDir(), 5 * 1024 * 1024);
            OkHttpClient okHttpClient = new OkHttpClient.Builder()
                    .cache(cache)
                    .addInterceptor(new Interceptor() {
                        @Override
                        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
                            Request.Builder requestBuilder = chain.request()
                                    .newBuilder();
                            if (!connectionDetector.isConnectingToInternet()){
                                int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale \
                                requestBuilder.header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale);
                            } else {
                                requestBuilder.header("Cache-Control", "public, max-age=" + 5);
                            }

                            return chain.proceed(requestBuilder.build());
                        }
                    })
                    .build();

                retrofit = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(okHttpClient)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
            return retrofit;
        }

    }

