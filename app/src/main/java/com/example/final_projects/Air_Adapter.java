package com.example.final_projects;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Air_Adapter extends RecyclerView.Adapter<Air_Adapter.ViewHolder> {

    //機票功能使用Recycler的陣列
    private ArrayList<Air_Post> AData ;

    public Air_Adapter(ArrayList<Air_Post> data) {
        this.AData = data;
    }

    @Override
    public Air_Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 連結項目布局檔list_item
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.air_list_item, parent, false);
        ViewHolder holder = new ViewHolder(view);

        //連接各個元件
        holder.departure_air = (TextView) view.findViewById(R.id.departure_air);
        holder.arrival_air = (TextView) view.findViewById(R.id.arrival_air);
        holder.start_date = (TextView) view.findViewById(R.id.start_date);
        holder.end_date = (TextView) view.findViewById(R.id.end_date);
        holder.start_time = (TextView) view.findViewById(R.id.start_time);
        holder.end_time = (TextView) view.findViewById(R.id.end_time);
        holder.air_id = (TextView) view.findViewById(R.id.air_id);
        holder.update_time = (TextView) view.findViewById(R.id.update_time);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Air_Post air_post = AData.get(position);

        // 設置departure_air要顯示的內容
        holder.departure_air.setText(air_post.departure_air);
        holder.arrival_air.setText(air_post.arrival_air);
        holder.start_date.setText(air_post.start_date);
        holder.end_date.setText(air_post.end_date);
        holder.start_time.setText(air_post.start_time);
        holder.end_time.setText(air_post.end_time);
        holder.air_id.setText(air_post.air_id);
        holder.update_time.setText(air_post.update_time);

    }

    @Override
    public int getItemCount() {
        return AData.size();
    }

    // 建立ViewHolder
    class ViewHolder extends RecyclerView.ViewHolder {
        // 宣告元件
        public TextView departure_air;
        public TextView arrival_air;
        public TextView start_date;
        public TextView end_date;
        public TextView start_time;
        public TextView end_time;
        public TextView air_id;
        public TextView update_time;

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
