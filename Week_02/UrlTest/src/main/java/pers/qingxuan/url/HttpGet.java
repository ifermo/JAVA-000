/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package pers.qingxuan.url;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

import java.util.concurrent.Callable;

public class HttpGet implements Callable<String> {
    private final String url;

    public HttpGet(String url) {
        this.url = url;
    }

    @Override
    public String call() throws Exception {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url(url)
                .build();

        try (Response response = client.newCall(request).execute()) {
            ResponseBody body = response.body();
            if (body != null) {
                return body.string();
            }
            return null;
        }
    }
}
