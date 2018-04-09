package com.thrai.THRAI.Connection;

import com.thrai.THRAI.ModelClasses.MedicalBook;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fahim on 4/8/18.
 */
public class MedicalTextbookRes extends Response {
    private List<MedicalBook> books;

    public List<MedicalBook> getBooks() {
        return books;
    }

    @Override
    public String imageBaseUrl() {
        return "http://www.thrai2app.com/thaiapp/webportal/";
    }

    @Override
    public void setJSON(String st) {
        books = new ArrayList<>();
        try {
            JSONArray arr = new JSONArray(st);
            for(int i=0;i<arr.length();i++){
                JSONObject obj = arr.getJSONObject(i);

                MedicalBook book = new MedicalBook();

                List<Long> page_id = new ArrayList<>();
                List<String> original_img = new ArrayList<>();
                List<String> copy_img = new ArrayList<>();
                List<String> translated_text = new ArrayList<>();

                book.setId(obj.getLong("id"));
                book.setBook_name(obj.getString("textbook_name"));

                JSONArray details_arr = obj.getJSONArray("details");
                for(int j=0;j<details_arr.length();j++){
                    JSONArray page_arr = details_arr.getJSONArray(j);
                    for(int k=0;k<page_arr.length();k++){
                        String q = page_arr.getString(k);
                        if(k==0)
                            page_id.add(Long.parseLong(q));
                        else if(k==1)
                            original_img.add(imageBaseUrl()+q);
                        else if(k==2)
                            copy_img.add(imageBaseUrl()+q);
                        else if(k==3)
                            translated_text.add(q);

                    }
                }
                book.setPage_no(page_id);
                book.setOriginal_pics(original_img);
                book.setCopy_pics(copy_img);
                book.setTranslated_articles(translated_text);

                books.add(book);

            }
        } catch (JSONException var4) {
        }
    }
}
