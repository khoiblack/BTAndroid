package ntu.khoi.th_chuyenmanhinh;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
    }
    public void ChuyenSangMH2(View v){
        // tạo đối tượng intent
        Intent thuKichHoatMH2;
        // hàm tạo có 2 tham số, tham số 2 là màn hình chuyển tới (.class)
        thuKichHoatMH2 = new Intent(MainActivity.this,// màn hình hiện tại
                MainActivity2.class);                              // màn hình chuyển tới
        // gửi thư mà ko đợi phản hồi
        startActivity(thuKichHoatMH2);
    }
}