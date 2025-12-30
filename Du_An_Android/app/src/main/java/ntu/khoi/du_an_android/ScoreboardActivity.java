package ntu.khoi.du_an_android;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;
import ntu.khoi.du_an_android.database.AppDatabase;
import ntu.khoi.du_an_android.database.Score;

public class ScoreboardActivity extends AppCompatActivity {

    RecyclerView rvScore;
    ScoreAdapter scoreAdapter;
    TextView tvNoData;

    ImageView btnHomeNav, btnHistoryNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scoreboard);

        rvScore = findViewById(R.id.rvScore);
        rvScore.setLayoutManager(new LinearLayoutManager(this));
        tvNoData = findViewById(R.id.tvNoData);
        loadData();


        btnHomeNav = findViewById(R.id.btnHomeNav);
        btnHistoryNav = findViewById(R.id.btnHistoryNav);


        btnHomeNav.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreboardActivity.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
        });


        btnHistoryNav.setOnClickListener(v -> {

        });
    }
    private void loadData() {

        SharedPreferences prefs = getSharedPreferences("UserSession", MODE_PRIVATE);
        String currentUser = prefs.getString("KEY_USERNAME", "");

        List<Score> listScore = AppDatabase.getDbInstance(this)
                .scoreDao()
                .getScoreByUser(currentUser);
        if (listScore.isEmpty()) {

            tvNoData.setVisibility(View.VISIBLE);
            rvScore.setVisibility(View.GONE);
        } else {

            tvNoData.setVisibility(View.GONE);
            rvScore.setVisibility(View.VISIBLE);


            scoreAdapter = new ScoreAdapter(listScore, this);
            rvScore.setAdapter(scoreAdapter);
        }
    }

    }
