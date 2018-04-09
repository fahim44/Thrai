package com.thrai.THRAI.ModelClasses;

import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;

/**
 * Created by fahim on 3/14/18.
 */

public abstract class Plant implements Serializable{
    private long id;
    private String plant_name,scientific_name,eng_name,local_name,family,botany,usage,referent;
    private List<String> images;
    private HashSet<String> tags;


    public void setId(long id) {
        this.id = id;
    }

    public void setPlant_name(String plant_name) {
        this.plant_name = plant_name;
    }

    public void setScientific_name(String scientific_name) {
        this.scientific_name = scientific_name;
    }

    public void setEng_name(String eng_name) {
        this.eng_name = eng_name;
    }

    public void setLocal_name(String local_name) {
        this.local_name = local_name;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public void setBotany(String botany) {
        this.botany = botany;
    }

    public void setUsage(String usage) {
        this.usage = usage;
    }

    public void setReferent(String referent) {
        this.referent = referent;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public void setTags(HashSet<String> tags) {
        this.tags = tags;
    }

    public long getId() {
        return id;
    }

    public String getPlant_name() {
        return plant_name;
    }

    public String getScientific_name() {
        return scientific_name;
    }

    public String getEng_name() {
        return eng_name;
    }

    public String getLocal_name() {
        return local_name;
    }

    public String getFamily() {
        return family;
    }

    public String getBotany() {
        return botany;
    }

    public String getUsage() {
        return usage;
    }

    public String getReferent() {
        return referent;
    }

    public List<String> getImages() {
        return images;
    }

    public HashSet<String> getTags() {
        return tags;
    }

    public abstract SpannableString getString();

    protected void setSpan(SpannableString spannableString,String mainSt,String subSt){
        spannableString.setSpan(new StyleSpan(android.graphics.Typeface.BOLD_ITALIC),
                mainSt.indexOf(subSt),mainSt.indexOf(subSt)+subSt.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
    }
}
