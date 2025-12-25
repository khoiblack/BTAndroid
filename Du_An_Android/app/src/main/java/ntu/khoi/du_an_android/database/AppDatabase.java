package ntu.khoi.du_an_android.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Score.class, User.class}, version = 2)
public abstract class AppDatabase extends RoomDatabase {

    public abstract ScoreDao scoreDao();
    public abstract UserDao userDao();

    private static AppDatabase INSTANCE;

    public static AppDatabase getDbInstance(Context context) {
        if(INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            AppDatabase.class, "MathQuizDB") // Tên file database là MathQuizDB
                    .allowMainThreadQueries() // QUAN TRỌNG: Cho phép chạy trên luồng chính để code đơn giản hơn
                    .fallbackToDestructiveMigration()
                    .build();


        }
        return INSTANCE;
    }
}