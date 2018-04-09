package com.thrai.THRAI.ModelClasses;

import android.text.SpannableString;

import java.util.Iterator;

/**
 * Created by fahim on 3/14/18.
 */

public class ThaiHub extends Plant {
    private String chemical,c_tags,activity,toxicity;

    public void setChemical(String chemical) {
        this.chemical = chemical;
    }

    public void setC_tags(String c_tags) {
        this.c_tags = c_tags;
    }

    public void setActivity(String activity) {
        this.activity = activity;
    }

    public void setToxicity(String toxicity) {
        this.toxicity = toxicity;
    }

    public String getChemical() {
        return chemical;
    }

    public String getC_tags() {
        return c_tags;
    }

    public String getActivity() {
        return activity;
    }

    public String getToxicity() {
        return toxicity;
    }

    @Override
    public SpannableString getString() {
        String st = "Scientific Name:    " + getScientific_name()
                + "\n\nEnglish Name:    " + getEng_name()
                + "\n\nLocal Name:    " + getLocal_name()
                + "\n\nFamily:    " + getFamily()
                + "\n\nBotany:    " + getBotany()
                + "\n\nUsage:    " + getUsage()
                + "\n\nReferent:    " + getReferent()

                + "\n\nChemical:    " + getChemical()
                + "\n\nC-tags:    " + getC_tags()
                + "\n\nActivity:    " + getActivity()
                + "\n\nToxicity:    " + getToxicity()

                + "\n\nTags:    ";

        Iterator<String> iterator = getTags().iterator();
        StringBuilder builder = new StringBuilder();
        if(iterator.hasNext())
            builder.append(iterator.next());

        while (iterator.hasNext()){
            builder.append(", ");
            builder.append(iterator.next());
        }

        st += builder.toString();

        SpannableString spannablecontent=new SpannableString(st);
        setSpan(spannablecontent,st,"Scientific Name:");
        setSpan(spannablecontent,st,"English Name:");
        setSpan(spannablecontent,st,"Local Name:");
        setSpan(spannablecontent,st,"Family:");
        setSpan(spannablecontent,st,"Botany:");
        setSpan(spannablecontent,st,"Usage:");
        setSpan(spannablecontent,st,"Referent:");
        setSpan(spannablecontent,st,"Chemical:");
        setSpan(spannablecontent,st,"C-tags:");
        setSpan(spannablecontent,st,"Activity:");
        setSpan(spannablecontent,st,"Toxicity:");
        setSpan(spannablecontent,st,"Tags:");

        return spannablecontent;
    }
}
