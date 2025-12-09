package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button; // Nhớ import dòng này
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.Score;

public class ScoreboardActivity extends AppCompatActivity {

    RecyclerView rvScore;
    ScoreAdapter scoreAdapter;
    Button btnBack; // Khai báo biến

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        // 1. Ánh xạ (Tìm View trong XML)
        rvScore = findViewById(R.id.rvScore);
        btnBack = findViewById(R.id.btnBack);

        // 2. Kiểm tra xem có tìm thấy nút không (Debug an toàn)
        if (btnBack == null) {
            // Nếu vào đây nghĩa là XML chưa có id btnBack
            return;
        }

        rvScore.setLayoutManager(new LinearLayoutManager(this));

        // 3. Load dữ liệu
        loadData();

        // 4. Bắt sự kiện click
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreboardActivity.this, MainActivity.class);
            // Dòng lệnh quan trọng: Xóa sạch các Activity cũ (Quiz, Result) để làm mới lại từ đầu
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });
    }

    private void loadData() {
        List<Score> listScore = AppDatabase.getDbInstance(this).scoreDao().getAllScores();
        scoreAdapter = new ScoreAdapter(listScore);
        rvScore.setAdapter(scoreAdapter);
    }
}