package com.thrai.THRAI.Connection;

/**
 * Created by fahim on 3/15/18.
 */

public abstract class Request {
    public abstract String getOptCode();

    public String getURL(){
        return "http://www.thrai2app.com/thaiapp/api/";
    }
}
