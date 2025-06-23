package com.ruoyi.common.utils.http;

import com.ruoyi.common.utils.uri.UriManagerUtils;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.Duration;

/**
 * 自定义请求工具类
 */
@Component
public class HttpRequestUtils {

    private static UriManagerUtils uriManagerUtils;

    public HttpRequestUtils(UriManagerUtils uriManagerUtils) {
        this.uriManagerUtils = uriManagerUtils;
    }

    /**
     * 发送post请求
     * @param route 路径
     * @param data 字节数据
     * @return 响应体
     * @throws IOException
     * @throws InterruptedException
     */
    public static String sendPostByBytes(String route, byte[] data) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .connectTimeout(Duration.ofSeconds(10))
                .build();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriManagerUtils.getPython() + route))
                .header("Content-Type", "application/json;charset=utf-8;")
                .POST(HttpRequest.BodyPublishers.ofByteArray(data))
                .timeout(Duration.ofSeconds(15))
                .build();

        HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }
}
