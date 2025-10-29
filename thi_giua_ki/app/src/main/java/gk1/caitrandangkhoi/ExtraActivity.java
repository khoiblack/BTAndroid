package gk1.caitrandangkhoi;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast; // Thêm Toast để báo lỗi

// Thêm import cho MaterialButton (nút Quay lại)
import com.google.android.material.button.MaterialButton;

public class ExtraActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button; // Nút "TÍNH"
    TextView result; // Nơi hiển thị kết quả

    // === THÊM KHAI BÁO NÚT QUAY LẠI ===
    MaterialButton btnBackExtra;

    int ans = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        // === SỬA LỖI 1: DÙNG ĐÚNG LAYOUT ===
        // setContentView(R.layout.activity_main); // SAI
        setContentView(R.layout.activity_lamthem); // ĐÚNG (Phải dùng layout Bảng Cửu Chương)
        setTitle("Bảng Cửu Chương");


        // Ánh xạ các view
        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);

        // ID trong file XML của mình là 'textView'
        result = (TextView)findViewById(R.id.textView);

        // === THÊM ÁNH XẠ NÚT QUAY LẠI ===
        // ID trong file XML của mình là 'btnBackExtra'
        btnBackExtra = (MaterialButton) findViewById(R.id.btnBackExtra);

        // Gán sự kiện click cho cả 2 nút
        button.setOnClickListener(this);
        btnBackExtra.setOnClickListener(this); // Gán listener cho nút quay lại
    }

    @Override
    public void onClick(View v)
    {
        // Dùng if/else để phân biệt nút nào được nhấn

        // === LOGIC NÚT "TÍNH" CỦA BẠN ===
        if(v.getId() == R.id.button) {
            StringBuffer buffer = new StringBuffer();
            String fs = editText.getText().toString();

            // === Thêm kiểm tra lỗi (nên có) ===
            if(fs.isEmpty()){
                Toast.makeText(this, "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                return; // Dừng lại
            }

            try {
                int n = Integer.parseInt(fs);
                for (int i = 1; i <= 10; i++) {
                    ans = (i * n);
                    buffer.append(n + " X " +
                            i + " = " + ans + "\n\n");
                }
                result.setText(buffer);

            } catch (NumberFormatException e){
                Toast.makeText(this, "Số không hợp lệ", Toast.LENGTH_SHORT).show();
            }
        }

        // === THÊM SỰ KIỆN CHO NÚT QUAY LẠI ===
        else if (v.getId() == R.id.btnBackExtra) {
            finish(); // Đóng màn hình này
        }
    }
}
