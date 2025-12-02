package ntu.khoi.d_n_c;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface TruyVanDao {
    // Thêm hoặc cập nhật bài viết
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void luuBaiViet(BaiViet baiViet);

    // Lấy tất cả bài viết đã lưu
    @Query("SELECT * FROM bang_tin_tuc")
    LiveData<List<BaiViet>> layTatCaBaiViet();

    // Xóa bài viết
    @Delete
    void xoaBaiViet(BaiViet baiViet);
}