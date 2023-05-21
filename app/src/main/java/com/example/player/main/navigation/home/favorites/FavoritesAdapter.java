package com.example.player.main.navigation.home.favorites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.player.R;

import java.util.List;

public class FavoritesAdapter   extends RecyclerView.Adapter<FavoritesAdapter.ViewHolder>{
     CallbackInterface callbackInterface;
    private List<FavoritesBean> mData;
    private Context context;
    private int editeTotal=0;
    public FavoritesAdapter(List<FavoritesBean> mData,Context context){
        this.mData = mData;
        this.context=context;

    }
    public void refresh(List<FavoritesBean> data){
        this.mData = data;
        notifyDataSetChanged();
    }
     interface CallbackInterface{

        void callbackMethod(int position,int editeTotal );
    }


    @NonNull
    @Override
    public FavoritesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View  contView = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_favorites,parent,false);
        return new FavoritesAdapter.ViewHolder(contView);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesAdapter.ViewHolder holder, int position) {
//        holder.textView.setText(mData.get(position));
        FavoritesActivity activity = (FavoritesActivity) context;
        Boolean isEdit = activity.getEdit();
        if (mData.get(position).isCheck){
            holder.iv_isCheck.setBackgroundResource(R.drawable.ic_ischeck_y);
        }else {
            holder.iv_isCheck.setBackgroundResource(R.drawable.ic_ischeck_n);
        }
        if(isEdit){
              holder.ll_isCheck.setVisibility(View.VISIBLE);
          }else {
              holder.ll_isCheck.setVisibility(View.GONE);
          }
        holder.ll_isCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.get(position).isCheck){
                    mData.get(position).isCheck=false;
                    holder.iv_isCheck.setBackgroundResource(R.drawable.ic_ischeck_n);
                }else {
                    mData.get(position).isCheck=true;
                    holder.iv_isCheck.setBackgroundResource(R.drawable.ic_ischeck_y);
                }
                    editeTotal=0;
                for(int i=0;i<mData.size();i++){
                    if(mData.get(i).isCheck==true){
                    editeTotal++;
                    }
                }
                callbackInterface.callbackMethod(position,editeTotal);
                notifyDataSetChanged();
            }
        });
        holder.tv_name.setText(mData.get(position).TitleName);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder  {
        LinearLayout ll_isCheck;
        ImageView iv_isCheck;
        TextView tv_name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ll_isCheck = (LinearLayout) itemView.findViewById(R.id.ll_isCheck);
            iv_isCheck=itemView.findViewById(R.id. iv_isCheck);
            tv_name=itemView.findViewById(R.id.tv_name);
//            ll_isCheck.setOnClickListener(this);
        }
    }
}
