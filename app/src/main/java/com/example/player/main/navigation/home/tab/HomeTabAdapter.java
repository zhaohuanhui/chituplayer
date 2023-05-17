package com.example.player.main.navigation.home.tab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;

import java.util.List;

public class HomeTabAdapter extends RecyclerView.Adapter<HomeTabAdapter.ViewHolder> {
    private OnItemClickListener itemClickListener; //自定义接口
    private List<String> mData;

    public HomeTabAdapter(List<String> mData){
        this.mData = mData;
    }
    public void refresh(List<String> data){
        this.mData = data;
        notifyDataSetChanged();
    }
    public void setItemClickListener(OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{ //接口
        void onItemClickMethod(View view,int position);
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  contView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_home_tab,parent,false);
        return new ViewHolder(contView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView textView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
//            textView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
//            if(itemClickListener != null){
//                textView.setElevation(80);
//                itemClickListener.onItemClickMethod(view,getLayoutPosition());
//                textView.setElevation(0);
//            }
        }
    }
}
