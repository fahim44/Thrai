package com.thrai.THRAI.Connection;

import android.content.Context;
import android.os.AsyncTask;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;

/**
 * Created by fahim on 3/15/18.
 */

public class ServerHandlerAsyncTask extends AsyncTask<Void,Void,Response> {

    ServerInterface serverInterface;
    Request req;
    Response rsp;
    Context context;

    public ServerHandlerAsyncTask(Context context,Request req,Response rsp, ServerInterface serverInterface){
        this.context = context;
        this.req = req;
        this.rsp = rsp;
        this.serverInterface = serverInterface;
    }

    @Override
    protected Response doInBackground(Void... voids) {
            try {
                OkHttpClient client = new OkHttpClient();
                MediaType mt = MediaType.parse("application/json; charset=utf-8");
                RequestBody body = RequestBody.create(mt, "");
                okhttp3.Request request = (new okhttp3.Request.Builder()).url(req.getURL()).addHeader("optcode", req.getOptCode()).post(body).build();
                okhttp3.Response response = client.newCall(request).execute();
                rsp.setJSON(response.body().string());
                return rsp;
            } catch (Exception e) {
                cancel(true);
                return null;
            }
    }

    @Override
    protected void onPostExecute(Response response) {
        super.onPostExecute(response);
        if(response != null){
            serverInterface.onResponse(response);
        }
    }
}
