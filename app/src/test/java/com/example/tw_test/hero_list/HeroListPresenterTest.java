package com.example.tw_test.hero_list;

import android.content.Context;

import com.example.tw_test.model.Hero;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HeroListPresenterTest {

    private HeroListContract.View view = mock(HeroListContract.View.class);

    Context context = mock(Context.class);

    private HeroListPresenter presenter = new HeroListPresenter(context, view);

    @Test
    public void testDataReadyShouldTriggerViewUpdate() {
        List<Hero> heroes = new ArrayList<>();
        heroes.add(new Hero("tom"));
        heroes.add(new Hero("jerry"));

        presenter.onFinished(heroes);

        verify(view).setDataToRecyclerView(heroes);
        verify(view).hideProgress();
    }
}