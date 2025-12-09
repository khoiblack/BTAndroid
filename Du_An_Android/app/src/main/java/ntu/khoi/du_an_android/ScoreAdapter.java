package ntu.khoi.du_an_android;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import ntu.khoi.du_an_android.database.Score;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    // Danh sách dữ liệu sẽ hiển thị
    private List<Score> listScore;

    // Constructor để nhận dữ liệu từ bên ngoài vào
    public ScoreAdapter(List<Score> listScore) {
        this.listScore = listScore;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Nạp layout item_score vào
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        // Lấy dữ liệu tại vị trí position
        Score score = listScore.get(position);
        if (score == null) return;

        // Gán dữ liệu lên giao diện
        holder.tvName.setText(score.name);
        holder.tvScore.setText(score.score + " điểm");
        holder.tvLevel.setText("Cấp độ: " + score.level);

        // Chuyển đổi ngày tháng cho đẹp
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        holder.tvDate.setText(sdf.format(new Date(score.date)));
    }

    @Override
    public int getItemCount() {
        if (listScore != null) return listScore.size();
        return 0;
    }

    // Class nắm giữ các thành phần giao diện (ViewHolder)
    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvScore, tvLevel, tvDate;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvScore = itemView.findViewById(R.id.tvScore);
            tvLevel = itemView.findViewById(R.id.tvLevel);
            tvDate = itemView.findViewById(R.id.tvDate);
        }
    }
}