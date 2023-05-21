package com.example.player.main.navigation.personal.histrory_record;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.player.R;
import com.example.player.main.navigation.home.favorites.FavoritesActivity;
import com.example.player.view.DelDialog;
import com.example.player.view.ToastUtil;

import java.util.ArrayList;
import java.util.List;
class HistoryRecordBean{
    String TitleName;
    String type;
    String  actorName;
    String describe;
    String score;
    boolean isFavorites;
    boolean isCheck;
    public HistoryRecordBean(String titleName, String type, String actorName, String describe, String score, boolean isFavorites,boolean isCheck) {
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
public class HistoryRecordActivity extends AppCompatActivity {
    HistoryRecordAdapter historyRecordAdapter;
    private LinearLayout ll_back,ll_edit,ll_edit_bottom,ll_selectAll,ll_del;
    private TextView tv_del,tv_edit;
    private Boolean isEdit=false;
    private RecyclerView rv_history_record;
    private List<HistoryRecordBean> historyRecordBeanList = new ArrayList<>();
    private int editeTotals=0;
    private boolean isSelectAll=false;
    public Boolean getEdit() {
        return isEdit;
    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_record);
        findViewById();
        initAdapter();
        onClick();
    }

    private void initAdapter() {
        historyRecordBeanList.clear();
        historyRecordBeanList.add(new HistoryRecordBean("Doctor strange1","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        historyRecordBeanList.add(new HistoryRecordBean("Doctor strange2","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        historyRecordBeanList.add(new HistoryRecordBean("Doctor strange3","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        historyRecordBeanList.add(new HistoryRecordBean("Doctor strange4","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        historyRecordBeanList.add(new HistoryRecordBean("Doctor strange5","现代/剧情/爱情","安圣基/张根硕/张美姬/金太贤贤…","已更新至38集","9.0",true,false));
        rv_history_record.setLayoutManager(new LinearLayoutManager(this));
        historyRecordAdapter=new HistoryRecordAdapter(historyRecordBeanList,this);
        rv_history_record.setAdapter(historyRecordAdapter);
        historyRecordAdapter.callbackInterface = new HistoryRecordAdapter.CallbackInterface() {
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
                historyRecordAdapter.notifyDataSetChanged();
            }
        });
        ll_selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!isSelectAll){
                    isSelectAll=true;
                    for(int i=0;i<historyRecordBeanList.size();i++){
                        historyRecordBeanList.get(i).isCheck=true;
                    }
                    editeTotals=historyRecordBeanList.size();
                    tv_del.setText("Delete ("+ editeTotals+")");
                }else{
                    isSelectAll=false;
                    for(int i=0;i<historyRecordBeanList.size();i++){
                        historyRecordBeanList.get(i).isCheck=false;
                    }
                    editeTotals=0;
                    tv_del.setText("Delete ("+ editeTotals+")");
                }
                historyRecordAdapter.notifyDataSetChanged();
            }
        });
        ll_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editeTotals==0){
                    ToastUtil.errorShortToast("Please select the item you want to delete");
                    return;
                }
                DelDialog.Builder builder= new DelDialog.Builder(HistoryRecordActivity.this);
                builder.setTitle("Historical record");
                builder.setInfo("Confirm to delete history ?");
                builder.setButtonConfirm("delete", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        for(int i=0;i<historyRecordBeanList.size();i++){
                            if(historyRecordBeanList.get(i).isCheck==true){
                                historyRecordBeanList.remove(i);
                                i--;
                            }
                        }
                        historyRecordAdapter.notifyDataSetChanged();
                    }
                });
                builder.setButtonCancel("no", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                builder.create().show();
            }
        });
    }

    private void findViewById() {
        ll_back=findViewById(R.id.ll_back);
        rv_history_record=findViewById(R.id.rv_history_record);
        ll_edit=findViewById(R.id.ll_edit);
        ll_edit_bottom=findViewById(R.id.ll_edit_bottom);
        ll_selectAll=findViewById(R.id.ll_selectAll);
        ll_del=findViewById(R.id.ll_del);
        tv_del=findViewById(R.id.tv_del);
        tv_edit=findViewById(R.id.tv_edit);
    }
}
