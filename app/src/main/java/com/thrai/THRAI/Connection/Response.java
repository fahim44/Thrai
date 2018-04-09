package com.thrai.THRAI.Connection;

/**
 * Created by fahim on 3/15/18.
 */

public abstract class Response {

    public String imageBaseUrl(){
        return "http://www.thrai2app.com/thaiapp/api/";

    }

    public abstract void setJSON(String st);
}
