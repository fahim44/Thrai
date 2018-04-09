package com.thrai.THRAI.ModelClasses;

import android.text.SpannableString;

import java.util.Iterator;

/**
 * Created by fahim on 3/14/18.
 */

public class LocalVegitable extends Plant {
    private String caution,place,description;

    public void setCaution(String caution) {
        this.caution = caution;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCaution() {
        return caution;
    }

    public String getPlace() {
        return place;
    }

    public String getDescription() {
        return description;
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

                + "\n\nCaution:    " + getCaution()
                + "\n\nPlace:    " + getPlace()
                + "\n\nDescription:    " + getDescription()

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
        setSpan(spannablecontent,st,"Caution:");
        setSpan(spannablecontent,st,"Place:");
        setSpan(spannablecontent,st,"Description:");
        setSpan(spannablecontent,st,"Tags:");


        return spannablecontent;
    }


}
