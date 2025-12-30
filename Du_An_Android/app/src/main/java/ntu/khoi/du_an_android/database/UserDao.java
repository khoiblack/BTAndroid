package ntu.khoi.du_an_android.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface UserDao {

    @Insert
    void registerUser(User user);

    // Kiểm tra đăng nhập: Tìm xem có ai trùng username và password không
    @Query("SELECT * FROM users WHERE username = :user AND password = :pass")
    User checkLogin(String user, String pass);

    // Kiểm tra xem user đã tồn tại chưa (để tránh đăng ký trùng)
    @Query("SELECT * FROM users WHERE username = :user")
    User checkUserExist(String user);
}