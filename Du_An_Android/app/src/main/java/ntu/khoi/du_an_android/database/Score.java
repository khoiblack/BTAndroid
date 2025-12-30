package ntu.khoi.du_an_android.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores")
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int id;

    public String name;
    public String level;
    public int totalQuestions;
    public int correct;
    public int wrong;
    public int score;
    public long date;


    public Score(String name, String level, int totalQuestions, int correct, int wrong, int score, long date) {
        this.name = name;
        this.level = level;
        this.totalQuestions = totalQuestions;
        this.correct = correct;
        this.wrong = wrong;
        this.score = score;
        this.date = date;
    }
}