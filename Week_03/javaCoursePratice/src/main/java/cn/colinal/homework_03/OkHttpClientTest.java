package cn.colinal.homework_03;


import okhttp3.*;

import java.io.IOException;

public class OkHttpClientTest {


    public static final MediaType JSON
            = MediaType.get("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        try (Response response = client.newCall(request).execute()) {
            return response.body().string();
        }
    }

    public static void main(String[] args) throws IOException {
        new OkHttpClientTest().post("http://localhost:9999","{\"name\":\"张三\",\"age\":18}");
    }
}
