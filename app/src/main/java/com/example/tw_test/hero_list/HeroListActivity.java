package com.example.tw_test.hero_list;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tw_test.R;
import com.example.tw_test.adapter.HeroAdapter;
import com.example.tw_test.model.Hero;

import java.util.ArrayList;
import java.util.List;

public class HeroListActivity extends AppCompatActivity implements HeroListContract.View {

    RecyclerView recyclerView;
    HeroAdapter heroAdapter;
    private List<Hero> heroesList;
    private HeroListPresenter heroListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();

        //Initializing presenter
        heroListPresenter = new HeroListPresenter(this);

        heroListPresenter.requestDataFromServer();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        heroesList = new ArrayList<>();

        heroAdapter = new HeroAdapter(this, heroesList);
        recyclerView.setAdapter(heroAdapter);

        //add loading here

    }

    @Override
    public void showProgress() {

        //show loading

    }

    @Override
    public void hideProgress() {
        //hide loading
    }

    @Override
    public void setDataToRecyclerView(List<Hero> heroList) {

        heroesList.addAll(heroList);
        heroAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        heroListPresenter.onDestroy();
    }
}
