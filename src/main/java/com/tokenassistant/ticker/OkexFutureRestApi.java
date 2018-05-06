package com.tokenassistant.ticker;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import org.apache.log4j.Logger;

import com.tokenassistant.http.HttpUtilManager;

public class OkexFutureRestApi implements IFutureRestApi {

	private String secret_key;

	private String api_key;

	private String url_prex;

	/**
	 * 构造函数：仅传入地址
	 * 
	 * @param url_prex
	 */
	public OkexFutureRestApi(String url_prex) {
		this.url_prex = url_prex;
	}

	/**
	 * 构造函数：需要传入Apikey,SecretKey
	 * 
	 * @param url_prex
	 * @param api_key
	 * @param secret_key
	 */
	public OkexFutureRestApi(String url_prex, String api_key, String secret_key) {
		this.api_key = api_key;
		this.secret_key = secret_key;
		this.url_prex = url_prex;
	}

	/**
	 * 期货行情URL
	 */
	private final String FUTURE_TICKER_URL = "/api/v1/future_ticker.do";

	private final String FUTURE_KLINE_URL = "/api/v1/future_kline.do";

	public String future_ticker(String symbol, String contractType) {
		HttpUtilManager httpUtilManager = HttpUtilManager.getInstance();
		String param = "";
		param = "symbol=" + symbol + "&contract_type=" + contractType + "&size=40";

		String result = "";
		try {
			result = httpUtilManager.requestHttpGet(url_prex, FUTURE_TICKER_URL, param);
		} catch (IOException e) {
			e.printStackTrace();
		}
		// System.out.println(result);
		return result;
	}

	public String future_kline(String symbol, String contract_type, String type) {
		HttpUtilManager httpUtilManager = HttpUtilManager.getInstance();
		String param = "";
		param = "symbol=" + symbol + "&type=" + type + "&contract_type=" + contract_type;

		String result = "";
		try {
			result = httpUtilManager.requestHttpGet(url_prex, FUTURE_KLINE_URL, param);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}

	public String getSecret_key() {
		return secret_key;
	}

	public void setSecret_key(String secret_key) {
		this.secret_key = secret_key;
	}

	public String getUrl_prex() {
		return url_prex;
	}

	public void setUrl_prex(String url_prex) {
		this.url_prex = url_prex;
	}

	String baseUrl = "https://www.okex.cn";
	String queryUrl = "/api/v1/kline.do?";
	String symbol = "symbol=btc_cny";
	String type = "type=1min";

	String apiKey = "acc0c249-c5a5-40aa-a398-c978bb832ffb";
	String Secretkey = "3725F86BA84B6CF9292C3949C497D490";

	Logger logger = Logger.getLogger(this.getClass());

	public String requestUrl = "";

	public void makeSign() {

		String signStr = "api_key=" + apiKey + "&" + symbol + "&" + type + "&secret_key=" + Secretkey;

		logger.info(signStr);

		String signature = MD5(signStr);
		requestUrl = baseUrl + queryUrl + "api_key=" + apiKey + "&" + symbol + "&" + type + "&secret_key=" + Secretkey
				+ "&sign=" + signature;

		logger.info(requestUrl);
	}

	private String MD5(String s) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] bytes = md.digest(s.getBytes("utf-8"));
			return toHex(bytes);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private static String toHex(byte[] bytes) {

		final char[] HEX_DIGITS = "0123456789ABCDEF".toCharArray();
		StringBuilder ret = new StringBuilder(bytes.length * 2);
		for (int i = 0; i < bytes.length; i++) {
			ret.append(HEX_DIGITS[(bytes[i] >> 4) & 0x0f]);
			ret.append(HEX_DIGITS[bytes[i] & 0x0f]);
		}
		return ret.toString();
	}

	public String SHA256(byte[] data, byte[] key) {
		try {
			SecretKeySpec signingKey = new SecretKeySpec(key, "HmacSHA256");
			Mac mac = Mac.getInstance("HmacSHA256");
			mac.init(signingKey);
			return byte2hex(mac.doFinal(data));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static String byte2hex(byte[] b) {
		StringBuilder hs = new StringBuilder();
		String stmp;
		for (int n = 0; b != null && n < b.length; n++) {
			stmp = Integer.toHexString(b[n] & 0XFF);
			if (stmp.length() == 1)
				hs.append('0');
			hs.append(stmp);
		}
		return hs.toString().toUpperCase();
	}

}
