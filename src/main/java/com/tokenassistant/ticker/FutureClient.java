package com.tokenassistant.ticker;

import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.tokenassistant.kline.KlinePoint;

/**
 * 期货 REST API 客户端请求
 * 
 * @author buxinyun
 *
 */
public class FutureClient {

	// OKCoin申请的apiKey
	String api_key = "acc0c249-c5a5-40aa-a398-c978bb832ffb";
	// OKCoin申请的secretKey
	String secret_key = "3725F86BA84B6CF9292C3949C497D490";
	// 注意：请求URL 国际站https://www.okcoin.com 国内站https://www.okcoin.cn
	String url_prex = "https://www.okex.cn";

	/**
	 * get请求无需发送身份认证,通常用于获取行情，市场深度等公共信息
	 */
	IFutureRestApi futureGetV1 = new OkexFutureRestApi(url_prex);

	/**
	 * post请求需发送身份认证，获取用户个人相关信息时，需要指定api_key,与secret_key并与参数进行签名，
	 * 此处对构造方法传入api_key与secret_key,在请求用户相关方法时则无需再传入， 发送post请求之前，程序会做自动加密，生成签名。
	 * 
	 */
	IFutureRestApi futurePostV1 = new OkexFutureRestApi(url_prex, api_key, secret_key);

	/**
	 * 获取期货实时行情
	 */
	public void future_ticker() {
		// 期货行情信息
		String result = futureGetV1.future_ticker("btc_usd", "this_week");
		System.out.println(result);
	}

	/**
	 * [ 1440308760000, 时间戳 233.38, 开 233.38, 高 233.27, 低 233.37, 收 186, 交易量
	 * 79.70234956 交易量转化BTC或LTC数量 ]
	 * 
	 * @param Klines
	 */
	public void future_kline(String symbol, String contract_type, String type, List<KlinePoint> Klines) {
		// 期货行情信息
		String klineStr = futureGetV1.future_kline(symbol, contract_type, type);

		// 字符串转换成Klines对象
		// 解析json 字符串
		JSONArray array = JSONArray.parseArray(klineStr);

		for (int i = 0; i < array.size(); i++) {

			KlinePoint klinePoint = new KlinePoint();

			// 转换成数组
			Double[] array_Klines = array.getObject(i, Double[].class);
			klinePoint.setUnixTime(array_Klines[0]);
			klinePoint.setOpen(array_Klines[1]);
			klinePoint.setHigh(array_Klines[2]);
			klinePoint.setLow(array_Klines[3]);
			klinePoint.setLast(array_Klines[4]);
			klinePoint.setVol(array_Klines[5]);
			klinePoint.setVolCoin(array_Klines[6]);

			Klines.add(klinePoint);
		}

	}

}
