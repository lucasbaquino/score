package lucasborges.mobi.scores.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.TypeConverters;
import android.arch.persistence.room.Update;

import java.util.List;

import lucasborges.mobi.scores.data.DateConverter;
import lucasborges.mobi.scores.models.Score;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Lucas Borges on 07/07/17.
 */
@Dao
@TypeConverters(DateConverter.class)
public interface ScoreDao {

    @Query("select * from Score")
    LiveData<List<Score>> getAllscores();

    @Query("select * from Score where id = :id")
    LiveData<Score> getScore(int id);

    @Insert(onConflict = REPLACE)
    void addScore(Score scoreModel);

    @Update
    void updateScore(Score score);
}
