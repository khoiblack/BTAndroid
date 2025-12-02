package ntu.khoi.d_n_c;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {BaiViet.class}, version = 1)
public abstract class CoSoDuLieu extends RoomDatabase {

    public abstract TruyVanDao layTruyVanDao();

    private static volatile CoSoDuLieu INSTANCE;

    // Tạo một luồng riêng để ghi dữ liệu, tránh làm đơ ứng dụng
    public static final ExecutorService luongGhiDuLieu = Executors.newFixedThreadPool(4);

    public static CoSoDuLieu layCoSoDuLieu(final Context context) {
        if (INSTANCE == null) {
            synchronized (CoSoDuLieu.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    CoSoDuLieu.class, "tin_tuc_db") // Tên file database
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}