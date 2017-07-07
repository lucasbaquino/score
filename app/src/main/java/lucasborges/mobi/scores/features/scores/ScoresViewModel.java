package lucasborges.mobi.scores.features.scores;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import lucasborges.mobi.scores.data.ApplicationDatabase;
import lucasborges.mobi.scores.models.Score;

/**
 * Created by Lucas Borges on 07/07/17.
 */
public class ScoresViewModel extends AndroidViewModel {

    private final LiveData<List<Score>> scoresList;
    private ApplicationDatabase applicationDatabase;

    public ScoresViewModel(Application application){
        super(application);
        applicationDatabase = ApplicationDatabase.getDatabase(this.getApplication());
        scoresList = applicationDatabase.scoreDao().getAllscores();
    }

    public LiveData<List<Score>> getScoresList() {
        return scoresList;
    }
}