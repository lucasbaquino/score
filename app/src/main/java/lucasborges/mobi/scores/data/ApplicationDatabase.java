package lucasborges.mobi.scores.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import lucasborges.mobi.scores.data.dao.ScoreDao;
import lucasborges.mobi.scores.models.Score;

/**
 * Created by Lucas Borges on 07/07/17.
 */
@Database(version = 1, entities = {Score.class})
public abstract class ApplicationDatabase extends RoomDatabase {

    private static ApplicationDatabase applicationDatabaseInstance;
    private static String DATABASE_NAME = "scores.db";

    public static ApplicationDatabase getDatabase(Context context){
        if(applicationDatabaseInstance == null){
            applicationDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(), ApplicationDatabase.class, DATABASE_NAME).build();
        }
        return applicationDatabaseInstance;
    }
    public abstract ScoreDao scoreDao();
}