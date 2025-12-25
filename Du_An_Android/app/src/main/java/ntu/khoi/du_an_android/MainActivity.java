package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerLevel;
    Button btnStart, btnViewScoreMain, btnLogout;
    TextView tvWelcome; // Biến hiển thị lời chào
    String loggedInUser; // Biến lưu tên người dùng hiện tại

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ View
        tvWelcome = findViewById(R.id.tvWelcome);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        btnStart = findViewById(R.id.btnStart);
        btnViewScoreMain = findViewById(R.id.btnViewScoreMain);
        btnLogout = findViewById(R.id.btnLogout); // Nút đăng xuất mới thêm

        // 2. Nhận tên người dùng từ LoginActivity gửi sang
        Intent receivedIntent = getIntent();
        if (receivedIntent != null) {
            loggedInUser = receivedIntent.getStringExtra("LOGGED_IN_USER");
        }

        // Nếu không lấy được tên (trường hợp lỗi), gán mặc định
        if (loggedInUser == null || loggedInUser.isEmpty()) {
            loggedInUser = "Player";
        }

        // 3. Hiển thị lời chào
        tvWelcome.setText("Xin chào, " + loggedInUser + "!");

        // 4. Cài đặt Spinner (Level)
        String[] levels = {"Easy", "Normal", "Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                levels
        );
        spinnerLevel.setAdapter(adapter);

        // 5. Sự kiện nút Bắt đầu
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Không cần kiểm tra nhập tên nữa vì đã đăng nhập rồi
                String selectedLevel = spinnerLevel.getSelectedItem().toString();

                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("USER_NAME", loggedInUser); // Truyền tên người dùng đăng nhập
                intent.putExtra("LEVEL", selectedLevel);
                startActivity(intent);
            }
        });

        // 6. Sự kiện nút Xem bảng xếp hạng
        btnViewScoreMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreboardActivity.class);
                startActivity(intent);
            }
        });

        // 7. Sự kiện nút Đăng xuất
        btnLogout.setOnClickListener(v -> {
            // Quay về màn hình đăng nhập
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Đóng MainActivity lại
            Toast.makeText(MainActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();
        });
    }
}