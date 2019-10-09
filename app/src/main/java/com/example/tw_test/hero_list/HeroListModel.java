package com.example.tw_test.hero_list;

import android.util.Log;

import com.example.tw_test.adapter.HeroAdapter;
import com.example.tw_test.model.Hero;
import com.example.tw_test.network.ApiClient;
import com.example.tw_test.network.ApiInterface;

import java.io.IOException;
import java.util.List;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroListModel implements HeroListContract.Model {

    private final String TAG = "HeroListModel";


    @Override
    public void getHeroList(final OnFinishedListener onFinishedListener) {
//
//        Cache cache = new Cache(getCacheDir(), cacheSize);
//        OkHttpClient okHttpClient = new OkHttpClient.Builder()
//                .cache(cache)
//                .addInterceptor(new Interceptor() {
//                    @Override
//                    public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
//                        Request request = chain.request();
//                        if (!mConnectionDetector.isConnectingToInternet()){
//                            int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale \
//                            request = request
//                                    .newBuilder()
//                                    .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
//                                    .build();
//                        }
//
//                        return chain.proceed(request);
//                    }
//                })
//                .build();

        ApiInterface apiService =
                ApiClient.getClient().create(ApiInterface.class);



        Call<List<Hero>> call = apiService.getHeroes();

        call.enqueue(new Callback<List<Hero>>(){


            @Override
            public void onResponse(Call<List<Hero>> call, Response<List<Hero>> response) {

                List<Hero> heroesList = response.body();

                onFinishedListener.onFinished(heroesList);

            }

            @Override
            public void onFailure(Call<List<Hero>> call, Throwable t) {
                Log.e(TAG, t.toString());
                onFinishedListener.onFailure(t);

            }
        });

    }
}
