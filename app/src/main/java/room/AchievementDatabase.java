package room;

import android.content.Context;

import com.example.android.achievement.R;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AchievementEntity.class}, version = 1)
public abstract class AchievementDatabase extends RoomDatabase {
    private static AchievementDatabase db_instance;

    public abstract AchievementDAO achievementDAO();

    //instance of database
    public static synchronized AchievementDatabase getDb_instance(Context context) {
        if (db_instance == null) {
            db_instance = Room.databaseBuilder(context.getApplicationContext(),
                    AchievementDatabase.class,
                    context.getString(R.string.dbname))
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return db_instance;
    }
}
