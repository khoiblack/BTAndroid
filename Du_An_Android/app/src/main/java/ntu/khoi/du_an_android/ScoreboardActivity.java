package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView; // Import ImageView
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.Score;

public class ScoreboardActivity extends AppCompatActivity {

    RecyclerView rvScore;
    ScoreAdapter scoreAdapter;
    // Khai báo 2 nút điều hướng
    ImageView btnHomeNav, btnHistoryNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        rvScore = findViewById(R.id.rvScore);
        rvScore.setLayoutManager(new LinearLayoutManager(this));
        loadData();

        // 1. Ánh xạ 2 nút
        btnHomeNav = findViewById(R.id.btnHomeNav);
        btnHistoryNav = findViewById(R.id.btnHistoryNav);

        // 2. Xử lý nút HOME: Quay về màn hình chính
        btnHomeNav.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreboardActivity.this, MainActivity.class);
            // Xóa lịch sử để khi bấm Back ở Main không quay lại đây
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });

        // 3. Xử lý nút HISTORY: Không làm gì cả vì đang ở đây rồi
        btnHistoryNav.setOnClickListener(v -> {
            // Có thể thêm Toast thông báo nhỏ nếu muốn
            // Toast.makeText(this, "Bạn đang ở màn hình Lịch sử", Toast.LENGTH_SHORT).show();
        });
    }

    private void loadData() {
        List<Score> listScore = AppDatabase.getDbInstance(this).scoreDao().getAllScores();
        scoreAdapter = new ScoreAdapter(listScore, this);
        rvScore.setAdapter(scoreAdapter);
    }
}