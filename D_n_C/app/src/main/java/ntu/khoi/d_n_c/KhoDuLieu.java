package ntu.khoi.d_n_c;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.util.List;

public class KhoDuLieu {

    private GiaoDienApi api;
    private TruyVanDao dao;

    // Biến để báo cho màn hình biết khi nào có dữ liệu mới
    private MutableLiveData<List<BaiViet>> duLieuTinTuc = new MutableLiveData<>();
    private MutableLiveData<String> thongBaoLoi = new MutableLiveData<>();

    public KhoDuLieu(CoSoDuLieu db) {
        this.dao = db.layTruyVanDao();
        this.api = KetNoiMang.layGiaoDienApi();
    }

    // 1. Lấy tin từ Mạng Internet
    public void taiTinTucTuMang(String maQuocGia, int soTrang) {
        String API_KEY = "DIEN_KEY_CUA_BAN_VAO_DAY";

        api.layTinTucMoiNhat(maQuocGia, soTrang, API_KEY).enqueue(new Callback<PhanHoiTinTuc>() {
            @Override
            public void onResponse(Call<PhanHoiTinTuc> call, Response<PhanHoiTinTuc> response) {
                if (response.isSuccessful() && response.body() != null) {
                    // Nếu thành công, bắn danh sách bài viết ra ngoài
                    duLieuTinTuc.postValue(response.body().getDanhSachBaiViet());
                } else {
                    thongBaoLoi.postValue("Lỗi: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<PhanHoiTinTuc> call, Throwable t) {
                thongBaoLoi.postValue(t.getMessage());
            }
        });
    }

    public LiveData<List<BaiViet>> layDuLieuTinTuc() {
        return duLieuTinTuc;
    }

    // 2. Lấy tin đã lưu trong máy
    public LiveData<List<BaiViet>> layTinDaLuu() {
        return dao.layTatCaBaiViet();
    }

    // 3. Lưu tin vào máy (Chạy ngầm)
    public void luuBaiVietVaoMay(BaiViet baiViet) {
        CoSoDuLieu.luongGhiDuLieu.execute(() -> {
            dao.luuBaiViet(baiViet);
        });
    }
}