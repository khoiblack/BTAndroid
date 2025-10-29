package gk1.caitrandangkhoi;

import androidx.appcompat.app.AppCompatActivity;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;

public class ExtraActivity extends AppCompatActivity implements View.OnClickListener {

    EditText editText;
    Button button;
    TextView result;


    MaterialButton btnBackExtra;

    int ans = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_lamthem);
        setTitle("Bảng Cửu Chương");



        editText = (EditText)findViewById(R.id.editText);
        button = (Button)findViewById(R.id.button);


        result = (TextView)findViewById(R.id.textView);


        btnBackExtra = (MaterialButton) findViewById(R.id.btnBackExtra);


        button.setOnClickListener(this);
        btnBackExtra.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {

        if(v.getId() == R.id.button) {
            StringBuffer buffer = new StringBuffer();
            String fs = editText.getText().toString();


            if(fs.isEmpty()){
                Toast.makeText(this, "Vui lòng nhập số", Toast.LENGTH_SHORT).show();
                return;
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


        else if (v.getId() == R.id.btnBackExtra) {
            finish();
        }
    }
}
