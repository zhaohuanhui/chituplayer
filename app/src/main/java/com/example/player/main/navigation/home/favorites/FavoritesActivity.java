package com.example.player.main.navigation.home.favorites;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.player.R;
import com.example.player.view.DelDialog;
import com.example.player.view.ToastUtil;


import java.util.ArrayList;
import java.util.List;


class FavoritesBean{
    String TitleName;
    String type;
    String  actorName;
    String describe;
    String score;
    boolean isFavorites;
    boolean isCheck;
    public FavoritesBean(String titleName, String type, String actorName, String describe, String score, boolean isFavorites,boolean isCheck) {
        TitleName = titleName;
        this.type = type;
        this.actorName = actorName;
        this.describe = describe;
        this.score = score;
        this.isFavorites = isFavorites;
        this.isCheck=isCheck;
    }
    public String getTitleName() {
        return TitleName;
    }

    public void setTitleName(String titleName) {
        TitleName = titleName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getActorName() {
        return actorName;
    }

    public void setActorName(String actorName) {
        this.actorName = actorName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public boolean isFavorites() {
        return isFavorites;
    }

    public void setFavorites(boolean favorites) {
        isFavorites = favorites;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }
}
public class FavoritesActivity  extends AppCompatActivity {
    private RecyclerView rv_favorites;
    private FavoritesAdapter favoritesAdapter;
    private LinearLayout ll_back,ll_edit,ll_edit_bottom,ll_selectAll,ll_del;
    private TextView tv_del,tv_edit;
    private Boolean isEdit=false;
    private List<FavoritesBean> favoritesBeanList = new ArrayList<>();
    private int editeTotals=0;
    public Boolean getEdit() {
        return isEdit;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);
        findViewById();
        initAdapter();
        onClick();
    }

    private void initAdapter() {
        favoritesBeanList.clear();
        favoritesBeanList.add(new FavoritesBean("Doctor strange1","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        favoritesBeanList.add(new FavoritesBean("Doctor strange2","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        favoritesBeanList.add(new FavoritesBean("Doctor strange3","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        favoritesBeanList.add(new FavoritesBean("Doctor strange4","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        favoritesBeanList.add(new FavoritesBean("Doctor strange5","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        rv_favorites.setLayoutManager(new LinearLayoutManager(this));
        favoritesAdapter=new FavoritesAdapter(favoritesBeanList,this);
        rv_favorites.setAdapter(favoritesAdapter);
        favoritesAdapter.callbackInterface = new FavoritesAdapter.CallbackInterface() {
            @Override
            public void callbackMethod(int position,int editeTotal) {
                editeTotals=editeTotal;
                tv_del.setText("Delete ("+ editeTotal+")");
            }
        };
    }

    private void onClick() {
        ll_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ll_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isEdit==true){
                    isEdit=false;
                    ll_edit_bottom.setVisibility(View.GONE);
                    tv_edit.setText("Edit");
                }else {
                    isEdit=true;
                    ll_edit_bottom.setVisibility(View.VISIBLE);
                    tv_edit.setText("Cancel");
                }
                favoritesAdapter.notifyDataSetChanged();
            }
        });
        ll_selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i=0;i<favoritesBeanList.size();i++){
                    favoritesBeanList.get(i).isCheck=true;
                }
                editeTotals=favoritesBeanList.size();
                favoritesAdapter.notifyDataSetChanged();
            }
        });
        ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editeTotals==0){
                    ToastUtil.errorShortToast("请选中要删除的项");
                    return;
                }
                DelDialog.Builder builder= new DelDialog.Builder(FavoritesActivity.this);
                builder.setButtonConfirm("Setting", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i=0;i<favoritesBeanList.size();i++){
                            if(favoritesBeanList.get(i).isCheck==true){
                                favoritesBeanList.remove(i);
                                i--;
                            }
                        }
                        favoritesAdapter.notifyDataSetChanged();
                    }
                });
                builder.setButtonCancel("Close", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                builder.create().show();
            }
        });
    }

    private void findViewById() {
        rv_favorites=findViewById(R.id.rv_favorites);
        ll_back=findViewById(R.id.ll_back);
        ll_edit=findViewById(R.id.ll_edit);
        ll_edit_bottom=findViewById(R.id.ll_edit_bottom);
        ll_selectAll=findViewById(R.id.ll_selectAll);
        ll_del=findViewById(R.id.ll_del);
        tv_del=findViewById(R.id.tv_del);
        tv_edit=findViewById(R.id.tv_edit);
    }
}
