package ntu.khoi.du_an_android.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ScoreDao {

    @Insert
    void insertScore(Score score);

    // Lệnh lấy tất cả điểm, sắp xếp điểm cao nhất lên đầu
    @Query("SELECT * FROM scores WHERE name = :username ORDER BY date DESC")
    List<Score> getScoreByUser(String username);
}