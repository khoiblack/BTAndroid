package ntu.khoi.d_n_c;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import java.util.List;

public class TinTucViewModel extends AndroidViewModel {

    private KhoDuLieu khoDuLieu;
    private LiveData<List<BaiViet>> danhSachTin;

    public TinTucViewModel(@NonNull Application application) {
        super(application);
        CoSoDuLieu db = CoSoDuLieu.layCoSoDuLieu(application);
        khoDuLieu = new KhoDuLieu(db);
        danhSachTin = khoDuLieu.layDuLieuTinTuc();
    }

    // Hàm gọi lấy tin
    public void taiTinTuc(String maQuocGia) {
        khoDuLieu.taiTinTucTuMang(maQuocGia, 1);
    }

    // Hàm để màn hình lắng nghe dữ liệu
    public LiveData<List<BaiViet>> layDanhSachTin() {
        return danhSachTin;
    }

    // Lưu bài viết
    public void luuBaiViet(BaiViet baiViet) {
        khoDuLieu.luuBaiVietVaoMay(baiViet);
    }
}