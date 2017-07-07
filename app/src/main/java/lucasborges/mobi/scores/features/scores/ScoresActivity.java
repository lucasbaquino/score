package lucasborges.mobi.scores.features.scores;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import lucasborges.mobi.scores.R;
import lucasborges.mobi.scores.models.Score;

/**
 * Created by Lucas Borges on 07/07/17.
 */
public class ScoresActivity extends LifecycleActivity {

    private RecyclerView recyclerview;
    private ScoresViewModel scoresViewModel;
    private ScoresRecyclerViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);

        scoresViewModel = ViewModelProviders.of(this).get(ScoresViewModel.class);
        recyclerview = (RecyclerView) findViewById(R.id.recyclerview);
        setupRecyclerView();

        scoresViewModel.getScoresList().observe(this, new Observer<List<Score>>() {
            @Override
            public void onChanged(@Nullable List<Score> scoreModels) {
                setupItems(scoreModels);
            }
        });
    }

    private void setupRecyclerView() {
        adapter = new ScoresRecyclerViewAdapter(new ArrayList<Score>());
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerview.setAdapter(adapter);
    }

    private void setupItems(List<Score> scoreModels) {
        adapter.setItems(scoreModels);
    }
}
