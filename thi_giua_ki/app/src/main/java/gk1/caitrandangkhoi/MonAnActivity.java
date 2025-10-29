package gk1.caitrandangkhoi;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MonAnActivity extends AppCompatActivity {

    RecyclerView recyclerViewMonAn;
    MonAnAdapter monAnAdapter;
    List<String> danhSachTenMonAn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_danhmucmonan);

        recyclerViewMonAn = findViewById(R.id.recyclerViewMonAn);

        danhSachTenMonAn = new ArrayList<>();
        danhSachTenMonAn.add("Phở Bò Tái Chín");
        danhSachTenMonAn.add("Bún Chả Hà Nội");
        danhSachTenMonAn.add("Cơm Tấm Sườn Bì Chả");
        danhSachTenMonAn.add("Bánh Mì Heo Quay");
        danhSachTenMonAn.add("Mì Quảng");
        danhSachTenMonAn.add("Hủ Tiếu Nam Vang");

        monAnAdapter = new MonAnAdapter(danhSachTenMonAn);
        recyclerViewMonAn.setAdapter(monAnAdapter);
        recyclerViewMonAn.setLayoutManager(new LinearLayoutManager(this));
    }
}