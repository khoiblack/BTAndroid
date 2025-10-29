package gk1.caitrandangkhoi;

import android.os.Bundle;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.button.MaterialButton;

public class AboutMeActivity extends AppCompatActivity {

    // Khai báo nút "Quay lại"
    MaterialButton btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Gắn file layout "activity_aboutme.xml" mà bạn đã cung cấp
        setContentView(R.layout.activity_about_me);

        // Ánh xạ nút "Quay lại" từ file XML
        // ID "btnBack" này phải khớp với ID trong file activity_aboutme.xml của bạn
        btnBack = findViewById(R.id.btnBack);

        // Gán sự kiện click cho nút "Quay lại"
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Lệnh 'finish()' sẽ đóng màn hình AboutMe
                // và tự động quay về màn hình MainActivity
                finish();
            }
        });
    }
}
