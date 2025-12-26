package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView; // Nhớ import ImageView
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.Score;

public class ScoreboardActivity extends AppCompatActivity {

    RecyclerView rvScore;
    ScoreAdapter scoreAdapter;

    // 1. Khai báo biến cho nút Home
    ImageView btnHomeNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        // Ánh xạ RecyclerView (giữ nguyên code cũ của bạn)
        rvScore = findViewById(R.id.rvScore);
        rvScore.setLayoutManager(new LinearLayoutManager(this));
        loadData();

        // 2. Ánh xạ nút Home từ XML (ID: btnHomeNav)
        btnHomeNav = findViewById(R.id.btnHomeNav);

        // 3. Bắt sự kiện Click để quay về MainActivity
        btnHomeNav.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreboardActivity.this, MainActivity.class);

            // Lệnh này quan trọng: Xóa các màn hình cũ để khi về Home bấm Back sẽ thoát App
            // chứ không quay lại màn hình Scoreboard nữa.
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);

            startActivity(intent);
            finish(); // Đóng màn hình Scoreboard hiện tại
        });
    }

    private void loadData() {
        // Code load dữ liệu cũ của bạn giữ nguyên
        List<Score> listScore = AppDatabase.getDbInstance(this).scoreDao().getAllScores();
        scoreAdapter = new ScoreAdapter(listScore, this); // Nhớ truyền context nếu adapter yêu cầu
        rvScore.setAdapter(scoreAdapter);
    }
}