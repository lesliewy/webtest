package cn.wy.biz.http.okhttp;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

import javax.annotation.Nullable;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.*;
import okio.BufferedSink;

/**
 * Created by leslie on 2020/3/27.
 */
public class OkHttp1 {

    public static void main(String[] args) {
        // asyncGet();
        // syncGet();
        // syncGetCookie();
//        getMenuTreeHttp();

        // asyncPost();
        // asyncPostStream();
    }

    /**
     * 异步get
     */
    private static void asyncGet() {
        String url = "http://wwww.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).get()// 默认就是GET请求，可以不写
                                                     .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure: " + e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse: " + response.body().string());
            }
        });
    }

    private static void syncGet() {
        String url = "http://wwww.baidu.com";
        OkHttpClient okHttpClient = new OkHttpClient();
        final Request request = new Request.Builder().url(url).build();
        final Call call = okHttpClient.newCall(request);
        Response response = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                System.out.println("response header: " + response.header("Date"));
                System.out.println("response: " + response.body().string());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 初始化Cookie管理器
    private static CookieJar cookieJar = new CookieJar() {

        // Cookie缓存区
        private final Map<String, List<Cookie>> cookiesMap = new HashMap<String, List<Cookie>>();

        @Override
        public void saveFromResponse(HttpUrl arg0, List<Cookie> arg1) {
        }

        @Override
        public List<Cookie> loadForRequest(HttpUrl arg0) {
            // TODO Auto-generated method stub
            List<Cookie> cookiesList = cookiesMap.get(arg0.host());
            // 注：这里不能返回null，否则会报NULLException的错误。
            // 原因：当Request 连接到网络的时候，OkHttp会调用loadForRequest()
            return cookiesList != null ? cookiesList : new ArrayList<Cookie>();
        }
    };

    private static String syncGetWithCookie(String url, String cookie) {
        // 两种方式添加cookie. 一种是header 中添加; 一种使用cookieJar管理;
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(1000, TimeUnit.MILLISECONDS).build();
        // OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(1000,
        // TimeUnit.MILLISECONDS).cookieJar(cookieJar).build();
        final Request request = new Request.Builder().url(url).header("Cookie", cookie).build();
        final Call call = okHttpClient.newCall(request);
        Response response = null;
        String result = null;
        try {
            response = call.execute();
            if (response.isSuccessful()) {
                System.out.println("response header: " + response.header("Date"));
                result = response.body().string();
                System.out.println("response: " + result);
            } else {
                System.out.println("code: " + response.code() + " message:" + response.message());
                return null;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }



    private static void asyncPost() {
        MediaType mediaType = MediaType.parse("text/x-markdown; charset=utf-8");
        String requestBody = "I am Jdqm.";
        Request request = new Request.Builder().url("https://api.github.com/markdown/raw").post(RequestBody.create(mediaType,
                                                                                                                   requestBody)).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(headers.name(i) + ":" + headers.value(i));
                }
                System.out.println("onResponse: " + response.body().string());
            }
        });
    }

    private static void asyncPostStream() {
        RequestBody requestBody = new RequestBody() {

            @Nullable
            @Override
            public MediaType contentType() {
                return MediaType.parse("text/x-markdown; charset=utf-8");
            }

            @Override
            public void writeTo(BufferedSink sink) throws IOException {
                sink.writeUtf8("I am Jdqm.");
            }
        };

        Request request = new Request.Builder().url("https://api.github.com/markdown/raw").post(requestBody).build();
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(headers.name(i) + ":" + headers.value(i));
                }
                System.out.println("onResponse: " + response.body().string());
            }
        });
    }

    private static void asyncPostForm() {
        OkHttpClient okHttpClient = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder().add("search", "Jurassic Park").build();
        Request request = new Request.Builder().url("https://en.wikipedia.org/w/index.php").post(requestBody).build();

        okHttpClient.newCall(request).enqueue(new Callback() {

            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println(response.protocol() + " " + response.code() + " " + response.message());
                Headers headers = response.headers();
                for (int i = 0; i < headers.size(); i++) {
                    System.out.println(headers.name(i) + ":" + headers.value(i));
                }
                System.out.println("onResponse: " + response.body().string());
            }
        });
    }

}
