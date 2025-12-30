package ntu.khoi.du_an_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.Score;

public class ResultActivity extends AppCompatActivity {

    TextView tvResultDetails;
    Button btnReplay;

    ImageView btnHomeNavResult, btnHistoryNavResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        tvResultDetails = findViewById(R.id.tvResultDetails);
        btnReplay = findViewById(R.id.btnReplay);
        btnHomeNavResult = findViewById(R.id.btnHomeNavResult);
        btnHistoryNavResult = findViewById(R.id.btnHistoryNavResult);


        String name = getIntent().getStringExtra("USER_NAME");
        String level = getIntent().getStringExtra("LEVEL");
        int[] stats = getIntent().getIntArrayExtra("SCORE_OBJ");


        if (stats != null) {
            String resultText = "Người chơi: " + name + "\n" +
                    "Cấp độ: " + level + "\n" +
                    "-----------------------" + "\n" +
                    "Điểm số: " + stats[2] + "\n" +
                    "Số câu đúng: " + stats[0] + "\n" +
                    "Số câu sai: " + stats[1] + "\n" +
                    "Tổng số câu: " + stats[3];
            tvResultDetails.setText(resultText);


            saveScoreToDB(name, level, stats);
        }


        btnReplay.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, QuizActivity.class);
            intent.putExtra("USER_NAME", name);
            intent.putExtra("LEVEL", level);
            startActivity(intent);
            finish();
        });


        btnHomeNavResult.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });


        btnHistoryNavResult.setOnClickListener(v -> {
            Intent intent = new Intent(ResultActivity.this, ScoreboardActivity.class);
            startActivity(intent);

        });
    }
    private void saveScoreToDB(String name, String level, int[] stats) {
        Score scoreObj = new Score(
                name,
                level,
                stats[3],
                stats[0],
                stats[1],
                stats[2],
                System.currentTimeMillis()
        );
        AppDatabase.getDbInstance(this).scoreDao().insertScore(scoreObj);
        Toast.makeText(this, "Đã lưu kết quả!", Toast.LENGTH_SHORT).show();
    }
}