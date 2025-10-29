package gk1.caitrandangkhoi;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;

import java.util.List;

public class MonAnAdapter extends RecyclerView.Adapter<MonAnAdapter.MonAnViewHolder> {

    private List<String> danhSachMonAn;

    public MonAnAdapter(List<String> danhSachMonAn) {
        this.danhSachMonAn = danhSachMonAn;
    }

    @NonNull
    @Override
    public MonAnViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_monan, parent, false);
        return new MonAnViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MonAnViewHolder holder, int position) {
        String tenMonAn = danhSachMonAn.get(position);
        holder.buttonMonAn.setText(tenMonAn);

        holder.buttonMonAn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Bạn đã chọn: " + tenMonAn, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return danhSachMonAn.size();
    }

    public class MonAnViewHolder extends RecyclerView.ViewHolder {
        MaterialButton buttonMonAn;

        public MonAnViewHolder(@NonNull View itemView) {
            super(itemView);
            buttonMonAn = itemView.findViewById(R.id.buttonMonAn);
        }
    }
}