package ntu.khoi.d_n_c;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class PhanHoiTinTuc {

    @SerializedName("status")
    private String trangThai;

    @SerializedName("totalResults")
    private int tongSoKetQua;

    @SerializedName("articles")
    private List<BaiViet> danhSachBaiViet;

    public List<BaiViet> getDanhSachBaiViet() {
        return danhSachBaiViet;
    }
}