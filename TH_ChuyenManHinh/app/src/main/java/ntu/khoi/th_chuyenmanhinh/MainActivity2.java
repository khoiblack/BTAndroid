package ntu.khoi.th_chuyenmanhinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity2 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        // Xây dựng lắng nghe và xử lí theo cách khác
        // chú ý : ta có thể làm theo cách giống MH1
        // cách khác
        // 1. Tìm đối tượng cần gắn bộ lắng nghe
        Button btnManHinh2 = findViewById(R.id.btnMH2);
        // 2. gắn bộ lắng nghe
        btnManHinh2.setOnClickListener(BoLangNgheChuyenMH);// code ở dưới

    }
    View.OnClickListener BoLangNgheChuyenMH = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            // hàm xử lí
            // tạo thư
            Intent thuKichHoatMH1 = new Intent(MainActivity2.this, MainActivity.class);
            // gửi đi ko đợi phản hồi
            startActivity(thuKichHoatMH1);
        }
    };

}