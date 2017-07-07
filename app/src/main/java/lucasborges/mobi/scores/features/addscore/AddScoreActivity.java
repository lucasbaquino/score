package lucasborges.mobi.scores.features.addscore;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import lucasborges.mobi.scores.R;
import lucasborges.mobi.scores.features.scores.ScoresActivity;

/**
 * Created by Lucas Borges on 07/07/17.
 */
public class AddScoreActivity extends LifecycleActivity {

    private AddScoreViewModel scoreViewModel;
    private Button btnSaveScore, btnShowScores;
    private EditText edtGoalsHomeTeam, edtGoalsAwayTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_score);
        scoreViewModel = ViewModelProviders.of(this).get(AddScoreViewModel.class);

        btnSaveScore = (Button) findViewById(R.id.btnSaveScore);
        btnShowScores = (Button) findViewById(R.id.btnShowScores);
        edtGoalsHomeTeam = (EditText) findViewById(R.id.edtGoalsHomeTeam);
        edtGoalsAwayTeam = (EditText) findViewById(R.id.edtGoalsAwayTeam);
        btnSaveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                scoreViewModel.saveScore(!TextUtils.isEmpty(edtGoalsHomeTeam.getText()) ? Integer.valueOf(edtGoalsHomeTeam.getText().toString()) : 0,
                        !TextUtils.isEmpty(edtGoalsAwayTeam.getText()) ? Integer.valueOf(edtGoalsAwayTeam.getText().toString()) : 0);
                edtGoalsHomeTeam.setText("");
                edtGoalsAwayTeam.setText("");
            }
        });
        btnShowScores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ScoresActivity.class);
                startActivity(intent);
            }
        });
        scoreViewModel.getScoreSavedActionWithMessage().observe(this, new Observer<Void>() {
            @Override
            public void onChanged(@Nullable Void v) {
                Toast.makeText(AddScoreActivity.this, "Score saved!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
