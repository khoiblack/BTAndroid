package ntu.khoi.d_n_c;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "bang_tin_tuc") // Tên bảng trong cơ sở dữ liệu
public class BaiViet implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private int id; // Mã định danh riêng cho việc lưu offline

    @SerializedName("author")
    private String tacGia;

    @SerializedName("title")
    private String tieuDe;

    @SerializedName("description")
    private String moTa;

    @SerializedName("url")
    private String duongDanBaiViet;

    @SerializedName("urlToImage")
    private String duongDanAnh; // Link ảnh đại diện bài viết

    @SerializedName("publishedAt")
    private String ngayDang;

    // Hàm khởi tạo (Constructor)
    public BaiViet(String tacGia, String tieuDe, String moTa, String duongDanBaiViet, String duongDanAnh, String ngayDang) {
        this.tacGia = tacGia;
        this.tieuDe = tieuDe;
        this.moTa = moTa;
        this.duongDanBaiViet = duongDanBaiViet;
        this.duongDanAnh = duongDanAnh;
        this.ngayDang = ngayDang;
    }

    // Các hàm lấy dữ liệu (Getters) và gán dữ liệu (Setters)
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTacGia() { return tacGia; }
    public String getTieuDe() { return tieuDe; }
    public String getMoTa() { return moTa; }
    public String getDuongDanBaiViet() { return duongDanBaiViet; }
    public String getDuongDanAnh() { return duongDanAnh; }
    public String getNgayDang() { return ngayDang; }
}
