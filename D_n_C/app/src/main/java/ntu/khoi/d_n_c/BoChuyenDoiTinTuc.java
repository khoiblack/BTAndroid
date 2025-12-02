package ntu.khoi.d_n_c;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import java.util.ArrayList;
import java.util.List;

public class BoChuyenDoiTinTuc extends RecyclerView.Adapter<BoChuyenDoiTinTuc.NguoiGiuView> {

    private List<BaiViet> danhSachBaiViet = new ArrayList<>();
    private SuKienClick suKienClick;

    public void capNhatDanhSach(List<BaiViet> danhSachMoi) {
        this.danhSachBaiViet = danhSachMoi;
        notifyDataSetChanged(); // Báo giao diện vẽ lại
    }

    @NonNull
    @Override
    public NguoiGiuView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Liên kết với file giao diện XML
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tin_tuc, parent, false);
        return new NguoiGiuView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NguoiGiuView holder, int position) {
        BaiViet baiViet = danhSachBaiViet.get(position);
        holder.ganDuLieu(baiViet);
    }

    @Override
    public int getItemCount() {
        return danhSachBaiViet.size();
    }

    // Interface để xử lý sự kiện bấm vào bài viết
    public interface SuKienClick {
        void khiBamVaoBaiViet(BaiViet baiViet);
    }

    public void datSuKienClick(SuKienClick suKien) {
        this.suKienClick = suKien;
    }

    class NguoiGiuView extends RecyclerView.ViewHolder {
        ImageView anhBaiViet;
        TextView tieuDe, moTa, nguonTin, ngayDang;

        public NguoiGiuView(@NonNull View itemView) {
            super(itemView);
            anhBaiViet = itemView.findViewById(R.id.imgAnhBaiViet);
            tieuDe = itemView.findViewById(R.id.tvTieuDe);
            moTa = itemView.findViewById(R.id.tvMoTa);
            nguonTin = itemView.findViewById(R.id.tvNguonTin);
            ngayDang = itemView.findViewById(R.id.tvNgayDang);

            itemView.setOnClickListener(v -> {
                int viTri = getAdapterPosition();
                if (suKienClick != null && viTri != RecyclerView.NO_POSITION) {
                    suKienClick.khiBamVaoBaiViet(danhSachBaiViet.get(viTri));
                }
            });
        }

        public void ganDuLieu(BaiViet baiViet) {
            tieuDe.setText(baiViet.getTieuDe());
            moTa.setText(baiViet.getMoTa());
            nguonTin.setText(baiViet.getTacGia());
            ngayDang.setText(baiViet.getNgayDang());

            // Dùng Glide để tải ảnh
            Glide.with(itemView)
                    .load(baiViet.getDuongDanAnh())
                    .into(anhBaiViet);
        }
    }
}
