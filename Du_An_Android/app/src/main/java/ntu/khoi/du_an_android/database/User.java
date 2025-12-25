package ntu.khoi.du_an_android.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "users")
public class User {
    @PrimaryKey
    @NonNull
    public String username; // Dùng tên đăng nhập làm khóa chính (không trùng nhau)
    public String password;

    public User(@NonNull String username, String password) {
        this.username = username;
        this.password = password;
    }
}