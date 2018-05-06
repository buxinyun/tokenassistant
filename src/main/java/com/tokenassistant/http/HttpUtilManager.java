package com.tokenassistant.http;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpUtilManager {

	private static HttpUtilManager instance = new HttpUtilManager();

	public static HttpUtilManager getInstance() {
		return instance;
	}

	// private static OkHttpClient httpClient = new OkHttpClient.Builder()
	// .proxy(new Proxy(Proxy.Type.HTTP, new InetSocketAddress("",
	// 9666))).build();

	private static OkHttpClient httpClient = new OkHttpClient();

	public static OkHttpClient getHttpClient() {
		return httpClient;
	}

	public static void setHttpClient(OkHttpClient httpClient) {
		HttpUtilManager.httpClient = httpClient;
	}

	public static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");

	public String requestHttpGet(String url_prex, String url, String param) throws IOException {

		url = url_prex + url;
		if (param != null && !param.equals("")) {
			if (url.endsWith("?")) {
				url = url + param;
			} else {
				url = url + "?" + param;
			}
		}

		Request request = new Request.Builder().url(url).build();
		Response response = httpClient.newCall(request).execute();
		return response.body().string(); // 返回的是string 类型，json的mapper可以直接处理
	}

	public String requestHttpPost(String url_prex, String url, String json) throws IOException {

		MediaType JSON = MediaType.parse("application/json;charset=utf-8");

		RequestBody requestBody = RequestBody.create(JSON, json);
		Request request = new Request.Builder().url(url_prex + url).post(requestBody).build();
		Response response = httpClient.newCall(request).execute();
		return response.body().string();

	}

}
