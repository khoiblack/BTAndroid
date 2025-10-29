package gk1.caitrandangkhoi;

public class BaiThuocActivity {
    private String tenBaiThuoc;
    private String moTa;
    private int hinhAnh; // Dùng int để lưu ID của drawable (vd: R.drawable.logo_ntu)

    // Constructor
    public BaiThuocActivity(String tenBaiThuoc, String moTa, int hinhAnh) {
        this.tenBaiThuoc = tenBaiThuoc;
        this.moTa = moTa;
        this.hinhAnh = hinhAnh;
    }

    // Getters
    public String getTenBaiThuoc() {
        return tenBaiThuoc;
    }

    public String getMoTa() {
        return moTa;
    }

    public int getHinhAnh() {
        return hinhAnh;
    }
}