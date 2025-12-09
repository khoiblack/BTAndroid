package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.Score;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultDetails;
    Button btnHome, btnReplay, btnViewScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        // 1. Ánh xạ View
        tvResultDetails = findViewById(R.id.tvResultDetails);
        btnHome = findViewById(R.id.btnHome);
        btnReplay = findViewById(R.id.btnReplay);
        btnViewScore = findViewById(R.id.btnViewScore);

        // 2. Nhận dữ liệu từ QuizActivity
        String name = getIntent().getStringExtra("USER_NAME");
        String level = getIntent().getStringExtra("LEVEL");
        // Mảng stats: 0:correct, 1:wrong, 2:score, 3:total
        int[] stats = getIntent().getIntArrayExtra("SCORE_OBJ");

        // 3. Hiển thị thông tin
        if (stats != null) {
            String resultText = "Người chơi: " + name + "\n" +
                    "Cấp độ: " + level + "\n" +
                    "-----------------------" + "\n" +
                    "Điểm số: " + stats[2] + "\n" +
                    "Số câu đúng: " + stats[0] + "\n" +
                    "Số câu sai: " + stats[1] + "\n" +
                    "Tổng số câu: " + stats[3];
            tvResultDetails.setText(resultText);

            // 4. LƯU VÀO DATABASE
            saveScoreToDB(name, level, stats);
        }

        // 5. Xử lý các nút bấm
        btnHome.setOnClickListener(v -> {
            finish(); // Đóng màn hình này để quay về trang chủ
        });

        btnReplay.setOnClickListener(v -> {
            // Chơi lại với tên và level cũ
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("USER_NAME", name);
            intent.putExtra("LEVEL", level);
            startActivity(intent);
            finish();
        });

        btnViewScore.setOnClickListener(v -> {
            // Chuyển sang màn hình Bảng Xếp Hạng
            // Lưu ý: ScoreboardActivity sẽ báo đỏ, hãy tạo nó ở bước tiếp theo
            Intent intent = new Intent(ResultActivity.this, ScoreboardActivity.class);
            startActivity(intent);
        });
    }

    private void saveScoreToDB(String name, String level, int[] stats) {
        // Tạo đối tượng Score
        Score scoreObj = new Score(
                name,
                level,
                stats[3], // total
                stats[0], // correct
                stats[1], // wrong
                stats[2], // score
                System.currentTimeMillis() // thời gian hiện tại
        );

        // Gọi Database để lưu
        AppDatabase.getDbInstance(this).scoreDao().insertScore(scoreObj);

        Toast.makeText(this, "Đã lưu kết quả!", Toast.LENGTH_SHORT).show();
    }
}