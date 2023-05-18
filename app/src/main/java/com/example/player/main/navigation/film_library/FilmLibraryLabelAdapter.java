package com.example.player.main.navigation.film_library;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;
import com.example.player.main.navigation.home.tab.HomeTabAdapter;

import java.util.List;

public class FilmLibraryLabelAdapter extends RecyclerView.Adapter<FilmLibraryLabelAdapter.ViewHolder>  {
    private HomeTabAdapter.OnItemClickListener itemClickListener; //自定义接口
    private List<TagBean> mData;

    public FilmLibraryLabelAdapter(List<TagBean> mData){
        this.mData = mData;
    }
    public void refresh(List<TagBean> data){
        this.mData = data;
        notifyDataSetChanged();
    }
    public void setItemClickListener(HomeTabAdapter.OnItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }
    public interface OnItemClickListener{ //接口
        void onItemClickMethod(View view, int position);
    }


    @NonNull
    @Override
    public FilmLibraryLabelAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  contView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_film_library_label,parent,false);
        return new FilmLibraryLabelAdapter.ViewHolder(contView);
    }

    @Override
    public void onBindViewHolder(@NonNull FilmLibraryLabelAdapter.ViewHolder holder, int position) {
        if(mData.get(position).isCheck){
            holder.textView.setTextColor(Color.parseColor("#FFE5362C"));
            holder.ll_bg.setBackgroundResource(R.drawable.shape_film_library_label_bg);
        }else{
            holder.textView.setTextColor(Color.parseColor("#FF333333"));
            holder.ll_bg.setBackground(null);
        }
        holder.textView.setText(mData.get(position).TagName);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             if(mData.get(position).isCheck==false){
                    for(int i=0;i<mData.size();i++){
                        mData.get(i).isCheck=false;
                    }
                    mData.get(position).isCheck=true;
                }
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  implements View.OnClickListener{
        TextView textView;
        LinearLayout ll_bg;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
            ll_bg=itemView.findViewById(R.id.ll_bg);
//            textView.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            if(itemClickListener != null){
                textView.setElevation(80);
                itemClickListener.onItemClickMethod(v,getLayoutPosition());
                textView.setElevation(0);
            }
        }
    }
}
