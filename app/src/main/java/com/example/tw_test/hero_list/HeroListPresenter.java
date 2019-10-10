package com.example.tw_test.hero_list;

import android.content.Context;

import com.example.tw_test.model.Hero;

import java.util.List;

public class HeroListPresenter implements HeroListContract.Presenter, HeroListContract.Model.OnFinishedListener {

    private HeroListContract.View heroListView;

    private HeroListContract.Model heroListModel;

    public HeroListPresenter(Context context, HeroListContract.View heroListView) {
        this.heroListView = heroListView;
        heroListModel = new HeroListModel(context);
    }

    @Override
    public void onFinished(List<Hero> heroList) {

        heroListView.setDataToRecyclerView(heroList);
        if (heroListView != null) {
            heroListView.hideProgress();
        }

    }

    @Override
    public void onFailure(Throwable t) {

        heroListView.onResponseFailure(t);
        if (heroListView != null) {
            heroListView.hideProgress();
        }

    }

    @Override
    public void onDestroy() {
        this.heroListView = null;


    }

    @Override
    public void requestDataFromServer() {

        if (heroListView != null) {
            heroListView.showProgress();
        }
        heroListModel.getHeroList(this);

    }

    }

