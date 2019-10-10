package com.example.tw_test.hero_list;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;
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
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initUI();

        //Initializing presenter
        heroListPresenter = new HeroListPresenter(getApplicationContext(), this);

        heroListPresenter.requestDataFromServer();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        heroesList = new ArrayList<>();

        heroAdapter = new HeroAdapter(this, heroesList);
        recyclerView.setAdapter(heroAdapter);

        progressDialog = new ProgressDialog(HeroListActivity.this);
        progressDialog.setMax(100);
        progressDialog.setMessage("Its loading....");
        progressDialog.setTitle("ProgressDialog bar example");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);

        //add loading here

        progressDialog.show();

    }

    @Override
    public void showProgress() {

       progressDialog.show();

    }

    @Override
    public void hideProgress() {
        //hide loading
        progressDialog.dismiss();
    }

    @Override
    public void setDataToRecyclerView(List<Hero> heroList) {

        heroesList.addAll(heroList);
        heroAdapter.notifyDataSetChanged();

    }

    @Override
    public void onResponseFailure(Throwable throwable) {
        Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
        progressDialog.dismiss();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        heroListPresenter.onDestroy();
    }
}
