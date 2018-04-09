package com.thrai.THRAI.ModelClasses;

import java.io.Serializable;
import java.util.List;

/**
 * Created by fahim on 4/8/18.
 */
public class MedicalBook implements Serializable {

    private long id;
    private String book_name;
    private List<Long> page_no;
    private List<String> original_pics;
    private List<String> copy_pics;
    private List<String> translated_articles;

    public long getId() {
        return id;
    }

    public String getBook_name() {
        return book_name;
    }

    public List<Long> getPage_no() {
        return page_no;
    }

    public List<String> getOriginal_pics() {
        return original_pics;
    }

    public List<String> getCopy_pics() {
        return copy_pics;
    }

    public List<String> getTranslated_articles() {
        return translated_articles;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public void setPage_no(List<Long> page_no) {
        this.page_no = page_no;
    }

    public void setOriginal_pics(List<String> original_pics) {
        this.original_pics = original_pics;
    }

    public void setCopy_pics(List<String> copy_pics) {
        this.copy_pics = copy_pics;
    }

    public void setTranslated_articles(List<String> translated_articles) {
        this.translated_articles = translated_articles;
    }
}
