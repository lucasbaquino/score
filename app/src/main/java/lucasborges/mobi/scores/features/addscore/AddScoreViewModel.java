package lucasborges.mobi.scores.features.addscore;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.os.AsyncTask;

import java.util.Date;

import lucasborges.mobi.scores.data.ApplicationDatabase;
import lucasborges.mobi.scores.data.SingleLiveEvent;
import lucasborges.mobi.scores.models.Score;

/**
 * Created by Lucas Borges on 07/07/17.
 */
public class AddScoreViewModel extends AndroidViewModel {

    private SingleLiveEvent<Void> scoreSavedActionWithMessage = new SingleLiveEvent<>();
    private ApplicationDatabase applicationDatabase;

    public AddScoreViewModel(Application application){
        super(application);
        applicationDatabase = ApplicationDatabase.getDatabase(this.getApplication());
    }

    public void saveScore(int goalsHomeTeam, int goalsAwayTeam){
        Score scoreModel = new Score(0, goalsHomeTeam, goalsAwayTeam, new Date());
        new SaveScoreAsync(applicationDatabase).execute(scoreModel);
    }

    public SingleLiveEvent<Void> getScoreSavedActionWithMessage() {
        return scoreSavedActionWithMessage;
    }

    private class SaveScoreAsync extends AsyncTask<Score, Void, Void> {
        private ApplicationDatabase applicationDatabase;

        SaveScoreAsync(ApplicationDatabase applicationDatabase){
            this.applicationDatabase = applicationDatabase;
        }

        @Override
        protected Void doInBackground(final Score... params){
            applicationDatabase.scoreDao().addScore(params[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void result){
            scoreSavedActionWithMessage.call();
        }
    }
}
