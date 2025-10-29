package gk1.caitrandangkhoi;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
public class MainActivity extends AppCompatActivity {

    MaterialButton btnFunc1, btnFunc2, btnFunc3, btnAboutMe, btnExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Ánh xạ (tìm) các nút từ file XML
        btnFunc1 = findViewById(R.id.btnFunc1);
        btnFunc2 = findViewById(R.id.btnFunc2);
        btnFunc3 = findViewById(R.id.btnFunc3);
        btnAboutMe = findViewById(R.id.btnAboutMe);
        btnExtra = findViewById(R.id.btnExtra);

        // Gán sự kiện click cho Nút 1
        btnFunc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Tạo một Intent để chuyển từ MainActivity sang Func1Activity
                Intent intent = new Intent(MainActivity.this, Func1Activity.class);
                startActivity(intent);
            }
        });

        // Gán sự kiện click cho Nút 2
        btnFunc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Func2Activity.class);
                startActivity(intent);
            }
        });

        // Gán sự kiện click cho Nút 3
        btnFunc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Func3Activity.class);
                startActivity(intent);
            }
        });

        // Gán sự kiện click cho Nút About Me
        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            }
        });

        // Gán sự kiện click cho Nút Làm thêm
        btnExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExtraActivity.class);
                startActivity(intent);
            }
        });
    }
}