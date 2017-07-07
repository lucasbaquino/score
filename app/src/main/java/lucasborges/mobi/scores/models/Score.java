package lucasborges.mobi.scores.models;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.util.Date;

import lucasborges.mobi.scores.data.DateConverter;

/**
 * Created by Lucas Borges on 07/07/17.
 */
@Entity
public class Score {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int goalsHomeTeam;
    private int goalsAwayTeam;
    @TypeConverters(DateConverter.class)
    private Date datePlayed;

    public Score(int id, int goalsHomeTeam, int goalsAwayTeam, Date datePlayed) {
        this.id = id;
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;
        this.datePlayed = datePlayed;
    }

    public int getId() {
        return id;
    }

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public Date getDatePlayed() {
        return datePlayed;
    }
}
