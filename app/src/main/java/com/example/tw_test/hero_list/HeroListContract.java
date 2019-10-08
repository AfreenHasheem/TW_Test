package com.example.tw_test.hero_list;

import com.example.tw_test.model.Hero;

import java.util.List;

public interface HeroListContract {

    interface Model {

        void getHeroList(OnFinishedListener onFinishedListener);

            interface OnFinishedListener {

            void onFinished(List<Hero> heroList);

            void onFailure(Throwable t);
        }


    }

    interface View {

        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<Hero> heroList);

        void onResponseFailure(Throwable throwable);

    }

    interface Presenter {

        void onDestroy();

        void requestDataFromServer();

    }
}
