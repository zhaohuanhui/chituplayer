package com.example.player.main.navigation.film_library;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.player.R;
import java.util.ArrayList;
import java.util.List;
class TagBean{
    String TagName;
    boolean isCheck;

    public TagBean(String tagName, boolean isCheck) {
        TagName = tagName;
        this.isCheck = isCheck;
    }

    public String getTagName() {
        return TagName;
    }

    public void setTagName(String tagName) {
        TagName = tagName;
    }

    public boolean getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(boolean isCheck) {
        this.isCheck = isCheck;
    }
}

public class FilmLibraryFragment extends Fragment {
    private  FilmLibraryLabelAdapter moviceClassAdapter,regionalClassAdapter,regionalTagAdapter,regionalEvaluatueAdapter,regionalYearAdapter;
    private  FilmLibraryCoverAdapter filmLibraryCoverAdapter;
    private RecyclerView rv_movie_class,rv_regional_class,rv_regional_tag,rv_regional_year,rv_regional_evaluate,rv_film_library_cover;
    private static final String NAME = "name";
    private static final String ARG = "arg";
    private List<TagBean> moviceClassData = new ArrayList<>();
    private List<TagBean> regionalClassData = new ArrayList<>();
    private List<TagBean> regionalTagData = new ArrayList<>();
    private List<TagBean> regionalEvaluatueData = new ArrayList<>();
    private List<TagBean> reginalYearData = new ArrayList<>();
    private List<String> mData = new ArrayList<>();

    public static FilmLibraryFragment newInstance(String name, String arg) {
        FilmLibraryFragment fragment = new FilmLibraryFragment();
        Bundle args = new Bundle();
        args.putString(NAME, name);
        args.putString(ARG, arg);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(R.layout.fragment_film_library, container, false);
        findViewById(inflate);
        initAdapter();
        return inflate;
    }

    private void findViewById(View inflate) {
        rv_movie_class = inflate.findViewById(R.id.rv_movie_class);
        rv_regional_class = inflate.findViewById(R.id.rv_regional_class);
        rv_regional_tag = inflate.findViewById(R.id.rv_regional_tag);
        rv_regional_evaluate = inflate.findViewById(R.id.rv_regional_evaluate);
        rv_regional_year=inflate.findViewById(R.id.rv_regional_year);
        rv_film_library_cover=inflate.findViewById(R.id.rv_film_library_cover);
    }
    private  void initAdapter(){
        moviceClassData.clear();
        moviceClassData.add(new TagBean("电视",true));
        moviceClassData.add(new TagBean("电影",false));
        moviceClassData.add(new TagBean("综艺",false));
        moviceClassData.add(new TagBean("动漫",false));
        regionalClassData.clear();
        regionalClassData.add(new TagBean("全部",true));
        regionalClassData.add(new TagBean("内地",false));
        regionalClassData.add(new TagBean("香港",false));
        regionalClassData.add(new TagBean("台湾",false));
        regionalClassData.add(new TagBean("美国",false));
        regionalClassData.add(new TagBean("韩国",false));
        regionalClassData.add(new TagBean("日本",false));
        regionalTagData.clear();
        regionalTagData.add(new TagBean("全部",true));
        regionalTagData.add(new TagBean("爱情",false));
        regionalTagData.add(new TagBean("都市",false));
        regionalTagData.add(new TagBean("喜剧",false));
        regionalTagData.add(new TagBean("武侠",false));
        regionalTagData.add(new TagBean("历史",false));
        regionalTagData.add(new TagBean("奇幻",false));
        regionalEvaluatueData.clear();
        regionalEvaluatueData.add(new TagBean("最热",true));
        regionalEvaluatueData.add(new TagBean("最新",false));
        regionalEvaluatueData.add(new TagBean("好评",false));
        regionalEvaluatueData.add(new TagBean("收藏",false));
        reginalYearData.clear();
        reginalYearData.add(new TagBean("全部",true));
        reginalYearData.add(new TagBean("2022",false));
        reginalYearData.add(new TagBean("2021",false));
        reginalYearData.add(new TagBean("2020",false));
        reginalYearData.add(new TagBean("2019",false));
        reginalYearData.add(new TagBean("2018",false));
        reginalYearData.add(new TagBean("2017",false));
        moviceClassAdapter=new FilmLibraryLabelAdapter(moviceClassData);
        regionalClassAdapter=new FilmLibraryLabelAdapter(regionalClassData);
        regionalTagAdapter=new FilmLibraryLabelAdapter(regionalTagData);
        regionalEvaluatueAdapter=new FilmLibraryLabelAdapter(regionalEvaluatueData);
        regionalYearAdapter=new FilmLibraryLabelAdapter(reginalYearData);
        rv_movie_class.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_movie_class.setAdapter(moviceClassAdapter);
        rv_regional_class.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_regional_class.setAdapter(regionalClassAdapter);
        rv_regional_tag.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_regional_tag.setAdapter(regionalTagAdapter);
        rv_regional_evaluate.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_regional_evaluate.setAdapter(regionalEvaluatueAdapter);
        rv_regional_year.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.HORIZONTAL,false));
        rv_regional_year.setAdapter(regionalYearAdapter);
        mData.clear();
        mData.add("哈利波特");
        mData.add("独行地球");
        mData.add("人生大事");
        mData.add("功夫熊猫3");
        filmLibraryCoverAdapter=new FilmLibraryCoverAdapter(mData);
        rv_film_library_cover.setLayoutManager(new GridLayoutManager(getActivity(),3));
        rv_film_library_cover.setAdapter(filmLibraryCoverAdapter);
    }
}
