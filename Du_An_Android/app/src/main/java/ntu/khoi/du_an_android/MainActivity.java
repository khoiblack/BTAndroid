package ntu.khoi.du_an_android;

import android.content.Intent;
import android.content.SharedPreferences; // 1. Import thư viện này
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText etName;
    Spinner spinnerLevel;
    Button btnStart;
    Button btnViewScoreMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        spinnerLevel = findViewById(R.id.spinnerLevel);
        btnStart = findViewById(R.id.btnStart);
        btnViewScoreMain = findViewById(R.id.btnViewScoreMain);

        // --- NÂNG CẤP 1: TỰ ĐỘNG ĐIỀN TÊN CŨ (NẾU CÓ) ---
        // Mở "cuốn sổ" có tên là MyPrefs
        SharedPreferences prefs = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        // Lấy tên đã lưu với chìa khóa "LAST_NAME", nếu chưa có thì để trống ""
        String lastUser = prefs.getString("LAST_NAME", "");
        // Điền sẵn vào ô nhập
        etName.setText(lastUser);
        // ------------------------------------------------

        String[] levels = {"Easy", "Normal", "Hard"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_dropdown_item,
                levels
        );
        spinnerLevel.setAdapter(adapter);

        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString().trim();

                if (name.isEmpty()) {
                    etName.setError("Vui lòng nhập tên để chơi!");
                    return;
                }

                // --- NÂNG CẤP 2: LƯU TÊN VỪA NHẬP LẠI ---
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("LAST_NAME", name); // Lưu tên với chìa khóa "LAST_NAME"
                editor.apply(); // Xác nhận lưu
                // ----------------------------------------

                String selectedLevel = spinnerLevel.getSelectedItem().toString();

                Intent intent = new Intent(MainActivity.this, QuizActivity.class);
                intent.putExtra("USER_NAME", name);
                intent.putExtra("LEVEL", selectedLevel);
                startActivity(intent);
            }
        });

        btnViewScoreMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ScoreboardActivity.class);
                startActivity(intent);
            }
        });
    }
}