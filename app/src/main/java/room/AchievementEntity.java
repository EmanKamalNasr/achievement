package room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "achievement_table")
public class AchievementEntity {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String details;
    private String type;
    private String date;

    public AchievementEntity(String title, String details, String type, String date) {
        this.title = title;
        this.details = details;
        this.type = type;
        this.date = date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDetails() {
        return details;
    }

    public String getType() {
        return type;
    }

    public String getDate() {
        return date;
    }
}
