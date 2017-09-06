package com.shu.shust2.util;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Leo on 2017/9/5.
 */

public class OkConnect {

    private OkHttpClient client;

    public String run(String url) throws IOException {
        client = new OkHttpClient();
        Request request = new Request.Builder().url(url).build();
        Response response = client.newCall(request).execute();
        if (response.isSuccessful())
            return response.body().string();
        else
            return "error";
    }
}
