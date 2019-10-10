package com.example.tw_test.hero_list;

import android.content.Context;
import android.util.Log;
import com.example.tw_test.model.Hero;
import com.example.tw_test.network.ApiClient;
import com.example.tw_test.network.ApiInterface;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HeroListModel implements HeroListContract.Model {

    private final String TAG = "HeroListModel";

    private final Context context;

    private final ApiInterface apiService;

    public HeroListModel(Context context) {
        this.context = context;
        apiService = ApiClient.getClient(context).create(ApiInterface.class);
    }

    @Override
    public void getHeroList(final OnFinishedListener onFinishedListener) {

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
