package com.example.player.main.navigation.personal;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;

import java.util.List;

public class PersonalAdapter  extends RecyclerView.Adapter<PersonalAdapter.ViewHolder>{
    private List<String> mData;

    public PersonalAdapter(List<String> mData){
        this.mData = mData;
    }
    public void refresh(List<String> data){
        this.mData = data;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public PersonalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  contView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_personal,parent,false);
        return new PersonalAdapter.ViewHolder(contView);
    }

    @Override
    public void onBindViewHolder(@NonNull PersonalAdapter.ViewHolder holder, int position) {
        holder.textView.setText(mData.get(position));
        if (position==mData.size()-1){
            holder.iv_cover.setBackgroundResource(R.drawable.ic_add);
        }else {
            holder.iv_cover.setBackgroundResource(R.drawable.ic_01);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView;
        ImageView iv_cover;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv_title);
            iv_cover =itemView.findViewById(R.id.iv_cover);
//            textView.setOnClickListener(this);
        }
    }
}
