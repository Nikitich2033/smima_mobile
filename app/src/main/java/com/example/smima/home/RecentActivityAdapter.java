package com.example.smima.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.smima.R;
import java.util.List;

public class RecentActivityAdapter extends RecyclerView.Adapter<RecentActivityAdapter.ViewHolder> {

    private List<ActivityItem> items;

    public RecentActivityAdapter(List<ActivityItem> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recent_activity, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ActivityItem item = items.get(position);
        holder.titleText.setText(item.getTitle());
        holder.descriptionText.setText(item.getDescription());
        holder.timeText.setText(item.getTime());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void updateItems(List<ActivityItem> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView descriptionText;
        TextView timeText;

        ViewHolder(View view) {
            super(view);
            titleText = view.findViewById(R.id.activityTitle);
            descriptionText = view.findViewById(R.id.activityDescription);
            timeText = view.findViewById(R.id.activityTime);
        }
    }

    public static class ActivityItem {
        private final String title;
        private final String description;
        private final String time;

        public ActivityItem(String title, String description, String time) {
            this.title = title;
            this.description = description;
            this.time = time;
        }

        public String getTitle() { return title; }
        public String getDescription() { return description; }
        public String getTime() { return time; }
    }
} 