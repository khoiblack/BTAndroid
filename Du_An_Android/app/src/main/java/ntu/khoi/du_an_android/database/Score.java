package ntu.khoi.du_an_android.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "scores")
public class Score {
    @PrimaryKey(autoGenerate = true)
    public int id; // ID tự tăng, không cần điền

    public String name;       // Tên người chơi
    public String level;      // Cấp độ (Easy, Normal...)
    public int totalQuestions; // Tổng số câu hỏi
    public int correct;       // Số câu đúng
    public int wrong;         // Số câu sai
    public int score;         // Tổng điểm
    public long date;         // Ngày giờ chơi (lưu dạng mili-giây)

    // Constructor để tạo đối tượng nhanh
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