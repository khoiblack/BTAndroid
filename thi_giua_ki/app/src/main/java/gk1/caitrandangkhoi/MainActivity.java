package gk1.caitrandangkhoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;

import com.google.android.material.button.MaterialButton;
public class MainActivity extends AppCompatActivity {

    MaterialButton btnFunc1, btnFunc2, btnFunc3, btnAboutMe, btnExtra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnFunc1 = findViewById(R.id.btnFunc1);
        btnFunc2 = findViewById(R.id.btnFunc2);
        btnFunc3 = findViewById(R.id.btnFunc3);
        btnAboutMe = findViewById(R.id.btnAboutMe);
        btnExtra = findViewById(R.id.btnExtra);


        btnFunc1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, BMIActivity.class);
                startActivity(intent);
            }
        });


        btnFunc2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MonAnActivity.class);
                startActivity(intent);
            }
        });


        btnFunc3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, BaiThuocActivity.class);
                startActivity(intent);
            }
        });


        btnAboutMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutMeActivity.class);
                startActivity(intent);
            }
        });


        btnExtra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ExtraActivity.class);
                startActivity(intent);
            }
        });
    }
}