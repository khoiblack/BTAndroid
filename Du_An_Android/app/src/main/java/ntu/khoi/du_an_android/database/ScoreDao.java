package ntu.khoi.du_an_android.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ScoreDao {
    // Lệnh thêm điểm mới vào bảng
    @Insert
    void insertScore(Score score);

    // Lệnh lấy tất cả điểm, sắp xếp điểm cao nhất lên đầu
    @Query("SELECT * FROM scores ORDER BY score DESC")
    List<Score> getAllScores();
}