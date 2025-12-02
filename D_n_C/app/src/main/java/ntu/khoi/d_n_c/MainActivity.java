package ntu.khoi.d_n_c;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private TinTucViewModel viewModel;
    private BoChuyenDoiTinTuc boChuyenDoi;
    private RecyclerView rvDanhSachTin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Nhớ đổi tên file layout XML thành giao_dien_chinh
        setContentView(R.layout.giao_dien_chinh);

        // 1. Cài đặt danh sách (RecyclerView)
        rvDanhSachTin = findViewById(R.id.rvDanhSachTin);
        boChuyenDoi = new BoChuyenDoiTinTuc();
        rvDanhSachTin.setAdapter(boChuyenDoi);
        rvDanhSachTin.setLayoutManager(new LinearLayoutManager(this));

        // 2. Kết nối ViewModel
        viewModel = new ViewModelProvider(this).get(TinTucViewModel.class);

        // 3. Gọi API lấy tin tức Việt Nam (vn)
        viewModel.taiTinTuc("vn");

        // 4. Lắng nghe dữ liệu trả về
        viewModel.layDanhSachTin().observe(this, danhSachBaiViet -> {
            if (danhSachBaiViet != null) {
                boChuyenDoi.capNhatDanhSach(danhSachBaiViet);
            }
        });

        // 5. Xử lý khi bấm vào bài viết
        boChuyenDoi.datSuKienClick(baiViet -> {
            viewModel.luuBaiViet(baiViet);
            Toast.makeText(this, "Đã lưu: " + baiViet.getTieuDe(), Toast.LENGTH_SHORT).show();
        });
    }
}