package com.thrai.THRAI.Connection;

import com.thrai.THRAI.ModelClasses.LocalVegitable;
import com.thrai.THRAI.ModelClasses.Plant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by fahim on 3/15/18.
 */

public class LocalVegRes extends Response {
    private List<Plant> vegitables=null;
    private HashSet<String> all_tags = new HashSet<>();

    public List<Plant> getVegitables() {
        return vegitables;
    }

    @Override
    public void setJSON(String st) {
        vegitables = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(st);
            for(int i=0;i<arr.length();i++){
                JSONObject obj = arr.getJSONObject(i);

                LocalVegitable veg = new LocalVegitable();
                veg.setId(obj.getLong("localveg_id"));
                veg.setPlant_name(obj.getString("plant_name"));
                veg.setScientific_name(obj.getString("scientific_name"));
                veg.setEng_name(obj.getString("eng_name"));
                veg.setLocal_name(obj.getString("local_name"));

                String tags = obj.getString("tags");
                String[] tags_arr = tags.split(",");
                HashSet<String> tags_set = new HashSet<>();
                for (String t :
                        tags_arr) {
                    t = t.trim();
                    all_tags.add(t);
                    tags_set.add(t);
                }
                veg.setTags(tags_set);

                veg.setFamily(obj.getString("family"));
                veg.setBotany(obj.getString("botany"));
                veg.setUsage(obj.getString("useg"));
                veg.setCaution(obj.getString("caution"));
                veg.setPlace(obj.getString("place"));
                veg.setReferent(obj.getString("referent"));
                veg.setDescription(obj.getString("description"));

                List<String> images = new ArrayList<>();
                JSONArray img_arr = obj.getJSONArray("images");
                for(int j=0;j<img_arr.length();j++){
                    String q = img_arr.getString(j);
                    images.add(imageBaseUrl() + q);
                }
                veg.setImages(images);

                vegitables.add(veg);

            }
        } catch (JSONException var4) {
        }
    }

    public HashSet<String> getAll_tags() {
        return all_tags;
    }
}
