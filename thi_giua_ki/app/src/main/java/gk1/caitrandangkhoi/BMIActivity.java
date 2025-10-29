package gk1.caitrandangkhoi;



import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputEditText;

import java.util.Locale;

public class BMIActivity extends AppCompatActivity {


    TextInputEditText etWeight, etHeight;
    MaterialButton btnCalculate, btnBack;
    MaterialCardView cardResult;
    TextView tvScore, tvCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_bmi);
        setTitle("Tính BMI");


        etWeight = findViewById(R.id.et_weight);
        etHeight = findViewById(R.id.et_height);
        btnCalculate = findViewById(R.id.btnCalculateBmi);
        btnBack = findViewById(R.id.btnBackBmi);
        cardResult = findViewById(R.id.cardResult);
        tvScore = findViewById(R.id.tvBmiScore);
        tvCategory = findViewById(R.id.tvBmiCategory);


        btnCalculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                calculateBmi();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }


    private void calculateBmi() {

        String weightStr = etWeight.getText().toString();
        String heightStr = etHeight.getText().toString();


        if (TextUtils.isEmpty(weightStr) || TextUtils.isEmpty(heightStr)) {
            Toast.makeText(this, "Vui lòng nhập đủ cân nặng và chiều cao", Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            float weight = Float.parseFloat(weightStr);
            float height = Float.parseFloat(heightStr);


            if (height <= 0 || weight <= 0) {
                Toast.makeText(this, "Cân nặng và chiều cao phải lớn hơn 0", Toast.LENGTH_SHORT).show();
                return;
            }


            float heightInMeters = height / 100.0f; // Đổi cm sang m
            float bmi = weight / (heightInMeters * heightInMeters);

            String category;
            int categoryColor;


            if (bmi < 18.5) {
                category = "Thiếu cân";
                categoryColor = Color.parseColor("#3498DB");
            } else if (bmi < 24.9) {
                category = "Bình thường";
                categoryColor = Color.parseColor("#2ECC71");
            } else if (bmi < 29.9) {
                category = "Thừa cân";
                categoryColor = Color.parseColor("#F39C12");
            } else {
                category = "Béo phì";
                categoryColor = Color.parseColor("#E74C3C");
            }


            tvScore.setText(String.format(Locale.US, "%.1f", bmi));
            tvCategory.setText(category);
            tvCategory.setTextColor(categoryColor);


            cardResult.setVisibility(View.VISIBLE);

        } catch (NumberFormatException e) {

            Toast.makeText(this, "Vui lòng nhập số hợp lệ", Toast.LENGTH_SHORT).show();
        }
    }
}


