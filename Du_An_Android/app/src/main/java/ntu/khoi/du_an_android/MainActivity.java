package ntu.khoi.du_an_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView; // Import ImageView
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Spinner spinnerLevel;
    Button btnStart;
    // Xóa nút btnViewScoreMain cũ, thêm 2 icon mới
    ImageView btnLogout, btnHomeNavMain, btnHistoryNavMain;
    TextView tvWelcome;
    String loggedInUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 1. Ánh xạ View
        tvWelcome = findViewById(R.id.tvWelcome);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        btnStart = findViewById(R.id.btnStart);
        btnLogout = findViewById(R.id.btnLogout);

        // Ánh xạ 2 nút điều hướng mới
        btnHomeNavMain = findViewById(R.id.btnHomeNavMain);
        btnHistoryNavMain = findViewById(R.id.btnHistoryNavMain);

        // (Phần lấy tên user và cài đặt Spinner giữ nguyên...)
        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        loggedInUser = prefs.getString("KEY_USERNAME", "Player");
        tvWelcome.setText("Xin chào, " + loggedInUser + "!");

        String[] levels = {"Easy", "Normal", "Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.item_spinner, // Layout cho dòng chữ khi CHƯA bấm (đang hiển thị)
                levels
        );
        // Layout cho danh sách xổ xuống khi ĐÃ bấm
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerLevel.setAdapter(adapter);


        // Sự kiện nút Bắt đầu (Giữ nguyên)
        btnStart.setOnClickListener(v -> {
            String selectedLevel = spinnerLevel.getSelectedItem().toString();
            Intent intent = new Intent(MainActivity.this, QuizActivity.class);
            intent.putExtra("USER_NAME", loggedInUser);
            intent.putExtra("LEVEL", selectedLevel);
            startActivity(intent);
        });

        // --- XỬ LÝ THANH ĐIỀU HƯỚNG ---

        // Nút HOME: Không làm gì vì đang ở đây
        btnHomeNavMain.setOnClickListener(v -> { });

        // Nút HISTORY: Chuyển sang màn hình Lịch sử
        btnHistoryNavMain.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ScoreboardActivity.class);
            startActivity(intent);
        });
        // -----------------------------

        // Sự kiện nút Đăng xuất (Giữ nguyên)
        btnLogout.setOnClickListener(v -> {
            SharedPreferences.Editor editor = prefs.edit();
            editor.clear();
            editor.apply();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(MainActivity.this, "Đã đăng xuất!", Toast.LENGTH_SHORT).show();
        });
    }
}