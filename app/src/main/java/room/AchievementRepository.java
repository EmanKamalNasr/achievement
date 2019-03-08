package room;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

public class AchievementRepository {
    private AchievementDAO achievementDAO;
    private LiveData<List<AchievementEntity>> allAchievements;

    public AchievementRepository(Application application) {
        AchievementDatabase database = AchievementDatabase.getDb_instance(application);
        achievementDAO = database.achievementDAO();
        allAchievements = achievementDAO.getAllAchievements();
    }

    public void insert(AchievementEntity achievement) {
        new InsertAsyncTask(achievementDAO).execute(achievement);
    }

    public void update(AchievementEntity achievement) {
        new UpdateAsyncTask(achievementDAO).execute(achievement);
    }

    public void delete(AchievementEntity achievement) {
        new DeleteAsyncTask(achievementDAO).execute(achievement);
    }

    public void deleteAllAchievements() {
        new DeleteAllAsyncTask(achievementDAO).execute();
    }

    public LiveData<List<AchievementEntity>> getAllAchievements() {
        return allAchievements;
    }

    private static class InsertAsyncTask extends AsyncTask<AchievementEntity, Void, Void> {
        private AchievementDAO achievementDAO;

        public InsertAsyncTask(AchievementDAO achievementDAO) {
            this.achievementDAO = achievementDAO;
        }

        @Override
        protected Void doInBackground(AchievementEntity... achievements) {
            achievementDAO.insert(achievements[0]);
            return null;
        }
    }

    private static class UpdateAsyncTask extends AsyncTask<AchievementEntity, Void, Void> {
        private AchievementDAO achievementDAO;

        public UpdateAsyncTask(AchievementDAO achievementDAO) {
            this.achievementDAO = achievementDAO;
        }

        @Override
        protected Void doInBackground(AchievementEntity... achievements) {
            achievementDAO.update(achievements[0]);
            return null;
        }
    }

    private static class DeleteAsyncTask extends AsyncTask<AchievementEntity, Void, Void> {
        private AchievementDAO achievementDAO;

        public DeleteAsyncTask(AchievementDAO achievementDAO) {
            this.achievementDAO = achievementDAO;
        }

        @Override
        protected Void doInBackground(AchievementEntity... achievements) {
            achievementDAO.delete(achievements[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void, Void, Void> {
        private AchievementDAO achievementDAO;

        public DeleteAllAsyncTask(AchievementDAO achievementDAO) {
            this.achievementDAO = achievementDAO;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            achievementDAO.deleteAllAchievements();
            return null;
        }
    }
}
