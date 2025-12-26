package ntu.khoi.du_an_android;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import ntu.khoi.du_an_android.database.Score;

public class ScoreAdapter extends RecyclerView.Adapter<ScoreAdapter.ScoreViewHolder> {

    private List<Score> listScore;
    private Context context; // Thêm biến context để lấy màu

    public ScoreAdapter(List<Score> listScore, Context context) {
        this.listScore = listScore;
        this.context = context;
    }

    @NonNull
    @Override
    public ScoreViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_score, parent, false);
        return new ScoreViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ScoreViewHolder holder, int position) {
        Score score = listScore.get(position);
        if (score == null) return;

        // 1. Hiển thị Level và đổi màu
        holder.tvLevelName.setText(score.level);

        int colorResId = R.color.text_green; // Mặc định Easy
        if (score.level.equals("Normal")) colorResId = R.color.text_yellow;
        else if (score.level.equals("Hard")) colorResId = R.color.text_orange;

        // Đổi màu cả chữ Level và dấu gạch chéo
        int color = ContextCompat.getColor(context, colorResId);
        holder.tvLevelName.setTextColor(color);
        holder.tvSlash.setTextColor(color);

        // 2. Hiển thị Điểm
        holder.tvScoreVal.setText("Score: " + score.score);

        // 3. Tính toán Accuracy (Độ chính xác)
        // Công thức: (Số câu đúng / Tổng câu) * 100
        double accuracy = 0;
        if (score.totalQuestions > 0) {
            accuracy = ((double) score.correct / score.totalQuestions) * 100;
        }
        holder.tvAccuracy.setText(String.format("Accuracy: %.1f%%", accuracy));

        // 4. Hiển thị Ngày giờ
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yy hh:mm a", Locale.getDefault());
        holder.tvDateVal.setText(sdf.format(new Date(score.date)));
    }

    @Override
    public int getItemCount() {
        if (listScore != null) return listScore.size();
        return 0;
    }

    public class ScoreViewHolder extends RecyclerView.ViewHolder {
        TextView tvLevelName, tvSlash, tvScoreVal, tvAccuracy, tvDateVal;

        public ScoreViewHolder(@NonNull View itemView) {
            super(itemView);
            tvLevelName = itemView.findViewById(R.id.tvLevelName);
            tvSlash = itemView.findViewById(R.id.tvSlash);
            tvScoreVal = itemView.findViewById(R.id.tvScoreVal);
            tvAccuracy = itemView.findViewById(R.id.tvAccuracy);
            tvDateVal = itemView.findViewById(R.id.tvDateVal);
        }
    }
}