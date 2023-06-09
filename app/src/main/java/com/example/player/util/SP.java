package com.example.player.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.player.App;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;

/**
 * 存
 * SP.getInstance().PutData(App.getContext(), "serviceId", s_id)
 * 取
 * String id = (String) SP.getInstance().GetData(App.getContext(), "serviceId", null);
 */
public class SP {
    private static SP spInstant = null;
    private static final String defaultModelName = "DefaultModelName";
    private final SharedPreferences preferences = App.getContext().getSharedPreferences(defaultModelName, Context.MODE_PRIVATE);
    private final SharedPreferences.Editor editor = preferences.edit();
    private SP() {

    }

    public static SP getInstance() {
        if (spInstant == null) {
            Sync();
        }
        return spInstant;
    }

    private static synchronized void Sync() {
        if (spInstant == null) {
            spInstant = new SP();
        }
    }

    /**
     * 保存List
     *
     * @param tag
     * @param datalist
     */
    public <T> void setDataList(String tag, List<T> datalist) {
        if (null == datalist || datalist.size() <= 0)
            return;
        Gson gson = new Gson();
        //转换成json数据，再保存
        String strJson = gson.toJson(datalist);
        editor.putString(tag, strJson);
        editor.commit();
    }

    /**
     * 获取List
     *
     * @param tag
     * @return
     */
    public <T> List<T> getDataList(String tag, Class<T> cls) {
        List<T> datalist=new ArrayList<T>();
        String strJson = preferences.getString(tag, null);
        if (null == strJson) {
            return null;
        }
        try {
            Gson gson = new Gson();
            JsonArray array = new JsonParser().parse(strJson).getAsJsonArray();
            for (JsonElement jsonElement : array) {
                datalist.add(gson.fromJson(jsonElement, cls));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datalist;
    }

    public void PutData(Context context, String key, Object value) {
        PutData(context, defaultModelName, key, value);
    }

    private void PutData(Context context, String defaultModelName, String key, Object value) {
        SharedPreferences preferences = context.getSharedPreferences(defaultModelName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else if (value instanceof String) {
            editor.putString(key, (String) value);
        } else {
            return;
        }
        editor.apply();
    }

    public Object GetData(Context context, String key, Object defaultValue) {
        return GetData(context, defaultModelName, key, defaultValue);
    }

    private Object GetData(Context context, String defaultModelName, String key, Object defaultValue) {
        SharedPreferences preferences = context.getSharedPreferences(defaultModelName, Context.MODE_PRIVATE);
        if (defaultValue instanceof Boolean) {
            return preferences.getBoolean(key, (Boolean) defaultValue);
        } else if (defaultValue instanceof Integer) {
            return preferences.getInt(key, (Integer) defaultValue);
        } else if (defaultValue instanceof Float) {
            return preferences.getFloat(key, (Float) defaultValue);
        } else if (defaultValue instanceof Long) {
            return preferences.getLong(key, (Long) defaultValue);
        } else if (defaultValue instanceof String) {
            return preferences.getString(key, (String) defaultValue);
        } else {
            return null;
        }
    }
}
