package room;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface AchievementDAO {
    @Insert
    void insert(AchievementEntity achievement);

    @Update
    void update(AchievementEntity achievement);

    @Delete
    void delete(AchievementEntity achievement);

    @Query("DELETE FROM achievement_table")
    void deleteAllAchievements();

    @Query("SELECT * FROM achievement_table")
    LiveData<List<AchievementEntity>> getAllAchievements();
}
