package adapter;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.achievement.EmptyRecyclerView;
import com.example.android.achievement.R;
import java.util.ArrayList;
import java.util.List;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import room.AchievementEntity;

public class AchievementAdapter extends EmptyRecyclerView.Adapter<AchievementAdapter.AchievementViewHolder> {
    private List<AchievementEntity> achievementList = new ArrayList<>();
    private OnItemClickListener listener;

    @NonNull
    @Override
    public AchievementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_layout, parent, false);
        return new AchievementViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull AchievementViewHolder holder, int position) {
        AchievementEntity currentAchievement = achievementList.get(position);
        holder.title_tv.setText(currentAchievement.getTitle());
        holder.details_tv.setText(currentAchievement.getDetails());
        holder.type_tv.setText(currentAchievement.getType());
        holder.date_tv.setText(currentAchievement.getDate());

        if (currentAchievement.getType().equals("دينى")) {
            holder.title_tv.setBackgroundColor(Color.parseColor("#5DBCD2"));
            holder.type_tv.setTextColor(Color.parseColor("#5DBCD2"));
        } else if (currentAchievement.getType().equals("تعليمى")) {
            holder.title_tv.setBackgroundColor(Color.parseColor("#FE9F89"));
            holder.type_tv.setTextColor(Color.parseColor("#FE9F89"));
        } else if (currentAchievement.getType().equals("إجتماعى")) {
            holder.title_tv.setBackgroundColor(Color.parseColor("#6DCC6E"));
            holder.type_tv.setTextColor(Color.parseColor("#6DCC6E"));
        } else {
            holder.title_tv.setBackgroundColor(Color.parseColor("#FF637B"));
            holder.type_tv.setTextColor(Color.parseColor("#FF637B"));
        }

    }

    public void setAchievementList(List<AchievementEntity> achievementList) {
        this.achievementList = achievementList;
        notifyDataSetChanged();
    }

    public AchievementEntity getAchievementAt(int position) {
        return achievementList.get(position);
    }

    @Override
    public int getItemCount() {
        return achievementList.size();
    }

    public class AchievementViewHolder extends RecyclerView.ViewHolder {
        private TextView title_tv;
        private TextView details_tv;
        private TextView type_tv;
        private TextView date_tv;

        public AchievementViewHolder(@NonNull View view) {
            super(view);
            title_tv = view.findViewById(R.id.title_tv);
            details_tv = view.findViewById(R.id.details_tv);
            type_tv = view.findViewById(R.id.type_tv);
            date_tv = view.findViewById(R.id.date_tv);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null && getAdapterPosition() != RecyclerView.NO_POSITION) {
                        listener.onItemClick(achievementList.get(getAdapterPosition()));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(AchievementEntity achievement);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
