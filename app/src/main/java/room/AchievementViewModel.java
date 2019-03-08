package room;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class AchievementViewModel extends AndroidViewModel {
    private AchievementRepository achievementRepository;
    private LiveData<List<AchievementEntity>> allAchievements;

    public AchievementViewModel(@NonNull Application application) {
        super(application);
        achievementRepository = new AchievementRepository(application);
        allAchievements = achievementRepository.getAllAchievements();
    }

    public void insert(AchievementEntity achievement) {
        achievementRepository.insert(achievement);
    }

    public void update(AchievementEntity achievement) {
        achievementRepository.update(achievement);
    }

    public void delete(AchievementEntity achievement) {
        achievementRepository.delete(achievement);
    }

    public void deleteAllAchievements() {
        achievementRepository.deleteAllAchievements();
    }

    public LiveData<List<AchievementEntity>> getAllAchievements() {
        return allAchievements;
    }
}
