package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.User;

public class LoginActivity extends AppCompatActivity {

    EditText etUser, etPass;
    Button btnLogin;
    Button btnRegisterNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        etUser = findViewById(R.id.etUsernameLogin);
        etPass = findViewById(R.id.etPasswordLogin);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegisterNav = findViewById(R.id.btnRegisterNav);


        btnLogin.setOnClickListener(v -> {
            String user = etUser.getText().toString().trim();
            String pass = etPass.getText().toString().trim();

            if(user.isEmpty() || pass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            User userObj = AppDatabase.getDbInstance(this).userDao().checkLogin(user, pass);

            if(userObj != null) {
                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                intent.putExtra("LOGGED_IN_USER", user);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(this, "Sai tài khoản hoặc mật khẩu!", Toast.LENGTH_SHORT).show();
            }
            if(userObj != null) {

                android.content.SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
                android.content.SharedPreferences.Editor editor = prefs.edit();
                editor.putString("KEY_USERNAME", user);
                editor.apply();


                Intent intent = new Intent(LoginActivity.this, MainActivity.class);

                intent.putExtra("LOGGED_IN_USER", user);
                startActivity(intent);
                finish();
            }
        });


        btnRegisterNav.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }
}