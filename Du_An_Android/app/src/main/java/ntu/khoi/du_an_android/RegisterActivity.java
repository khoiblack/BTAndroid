package ntu.khoi.du_an_android;

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

        etUser = findViewById(R.id.etUsernameReg);
        etPass = findViewById(R.id.etPasswordReg);
        etRePass = findViewById(R.id.etRePasswordReg);
        btnRegister = findViewById(R.id.btnRegisterAction);
        btnBack = findViewById(R.id.btnBackToLogin);

        btnRegister.setOnClickListener(v -> {
            String user = etUser.getText().toString().trim();
            String pass = etPass.getText().toString().trim();
            String rePass = etRePass.getText().toString().trim();

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
                // Thêm user mới
                User newUser = new User(user, pass);
                AppDatabase.getDbInstance(this).userDao().registerUser(newUser);
                Toast.makeText(this, "Đăng ký thành công! Hãy đăng nhập.", Toast.LENGTH_SHORT).show();
                finish(); // Đóng màn hình đăng ký để quay về đăng nhập
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}