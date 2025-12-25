package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.User;

public class RegisterActivity extends AppCompatActivity {
    EditText etUser, etPass, etRePass;
    Button btnRegister, btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Ánh xạ View
        etUser = findViewById(R.id.etUsernameReg);
        etPass = findViewById(R.id.etPasswordReg);
        etRePass = findViewById(R.id.etRePasswordReg);
        btnRegister = findViewById(R.id.btnRegisterAction);
        btnBack = findViewById(R.id.btnBackToLogin);

        // XỬ LÝ SỰ KIỆN NÚT ĐĂNG KÝ
        btnRegister.setOnClickListener(v -> {
            String user = etUser.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            String rePass = etRePass.getText().toString().trim();

            // Kiểm tra nhập liệu
            if(user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if(!pass.equals(rePass)) {
                Toast.makeText(this, "Mật khẩu nhập lại không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            // Kiểm tra user đã tồn tại chưa
            User existingUser = AppDatabase.getDbInstance(this).userDao().checkUserExist(user);

            if(existingUser != null) {
                Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            } else {
                // --- ĐOẠN CODE QUAN TRỌNG BẠN CẦN ---

                // 1. Lưu user mới vào Database
                User newUser = new User(user, pass);
                AppDatabase.getDbInstance(this).userDao().registerUser(newUser);

                // 2. Hiện thông báo "Đăng ký thành công"
                Toast.makeText(this, "Đăng ký thành công! Vui lòng đăng nhập.", Toast.LENGTH_SHORT).show();

                // 3. Chuyển ngay sang màn hình Đăng nhập
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);

                // 4. Đóng màn hình Đăng ký lại (để người dùng không bấm Back quay lại được)
                finish();
            }
        });

        // Nút quay lại (nếu người dùng đổi ý không muốn đăng ký nữa)
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        });
    }
}