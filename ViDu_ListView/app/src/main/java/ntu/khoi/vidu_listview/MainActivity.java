package ntu.khoi.vidu_listview;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView lv_lstGame;

    ArrayList<String> listGame = new ArrayList<String>(); //2 dòng khai báo ở bước 1
    ArrayAdapter<String> gameAdapter; //dòng tạo Adapter ở bước 2
    void TimDK(){
        lv_lstGame = findViewById(R.id.lv_list);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TimDK();
        //(1)---------Chuẩn bị lấy dữ liệu
        //Khai báo
        //ArrayList<String> listGame; ---- đem 2 dòng này để ra bên ngoài để tránh mỗi lần mở app sẽ bị mất dữ liệu cữ
        //listGame = new ArrayList<String>();

        // Lấy dữ liệu đưa vào listGame
        //Lấy ở đâu ??  =file, database, internet(cloud)
        listGame = getData();

        //(2)----------Tạo Adapter
        //ArrayAdapter<String> gameAdapter; ---- đem dòng này để ra bên ngoài để tránh mỗi lần mở app sẽ bị mất dữ liệu cữ
        gameAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                listGame
        );
        //(3)
        lv_lstGame.setAdapter(gameAdapter);

        //(4)

        lv_lstGame.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //Xử lý việc chọn item ở đây
                //Biến position chứa vị trí của Item được chọn
                //(4.1)-------Lấy giá trị của phần tử được chọn
                //Cách 1------Lấy gián tiếp qua Adapter
                String gameDuocChon = gameAdapter.getItem(position).toString();
                //Cách 2------Lấy trực tiếp từ biến chứa danh sách
                //String gameDuocChon2 = listGame.get(position);
                //(4.2)-------Làm gì với nó thì tùy bài toán
                String thongBao = "Bạn đã chọn vào game: " +gameDuocChon;
                Toast.makeText(MainActivity.this,thongBao,Toast.LENGTH_LONG).show();
            }
        });
    }
    ArrayList<String> getData(){

        //Code đọc dữ liệu và vào biến tạm, trước khi return
        ArrayList<String> dsTam = new ArrayList<String>();
        //Code ở đây
        //Bài này, ta hard-code
        dsTam.add("Black Myth Wukhong");
        dsTam.add("PUBG");
        dsTam.add("Liên Minh Huyền Thoại");
        dsTam.add("Apex Legends");
        dsTam.add("StarRail");
        dsTam.add("ZZZ");
        dsTam.add("Wuwa");
        return dsTam;
    }
}