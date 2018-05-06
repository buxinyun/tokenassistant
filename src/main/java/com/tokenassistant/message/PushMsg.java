package com.tokenassistant.message;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.tokenassistant.http.HttpUtilManager;
import com.tokenassistant.market.IOkex_Parameter.Contract_types_Enum;
import com.tokenassistant.market.IOkex_Parameter.Ktype_Enum;
import com.tokenassistant.market.IOkex_Parameter.Markets_Enum;
import com.tokenassistant.market.IOkex_Parameter.Symbols_Enum;
import com.tokenassistant.market.IOkex_Parameter.Types_Enum;

public class PushMsg {

	private Logger logger = Logger.getLogger(this.getClass());

	private Markets_Enum markets_Enum = Markets_Enum.OKEX;
	private Symbols_Enum symbols_Enum;
	private Contract_types_Enum contract_types_Enum;
	private Types_Enum types_Enum;
	private Ktype_Enum ktype_Enum;
	private String content = "";

	/**
	 * 上涨
	 */
	public void pushUp() {
		content = "【上涨】 " + markets_Enum.getShowName() + "-" + symbols_Enum.getShowName()
				+ contract_types_Enum.getShowName() + " " + types_Enum.getShowName() + " " + content
				+ ",请密切关注行情走向,做好风险控制.";

		Logger logger = Logger.getLogger(this.getClass());
		logger.info(content);

		pushMsg();
	}

	/**
	 * 下跌
	 */
	public void pushDown() {
		content = "【下跌】 " + markets_Enum.getShowName() + "-" + symbols_Enum.getShowName()
				+ contract_types_Enum.getShowName() + " " + types_Enum.getShowName() + " " + content
				+ ",请密切关注行情走向,做好风险控制.";

		logger.info(content);

		pushMsg();
	}

	public void pushNone() {
		content = "【横盘】 " + markets_Enum.getShowName() + "-" + symbols_Enum.getShowName()
				+ contract_types_Enum.getShowName() + " " + types_Enum.getShowName() + " " + content
				+ ",请密切关注行情走向,做好风险控制.";

		logger.info(content);
	}

	private void pushMsg() {
		HttpUtilManager httpUtilManager = HttpUtilManager.getInstance();
		JSONObject jsonObject = new JSONObject();

		jsonObject.put("market", markets_Enum.getKeyWord());
		jsonObject.put("symbol", symbols_Enum.getKeyWord());
		jsonObject.put("ktype", ktype_Enum.getKeyWord());
		jsonObject.put("type", types_Enum.getKeyWord());
		jsonObject.put("contractType", contract_types_Enum.getKeyWord());
		jsonObject.put("content", content);

		String result = "";
		try {
			result = httpUtilManager.requestHttpPost("http://auth.u-ico.com/", "indicator/pushNotice",
					jsonObject.toString());
		} catch (IOException e) {

			e.printStackTrace();
		}
		System.out.println(result);
	}

	public Markets_Enum getMarkets_Enum() {
		return markets_Enum;
	}

	public void setMarkets_Enum(Markets_Enum markets_Enum) {
		this.markets_Enum = markets_Enum;
	}

	public Symbols_Enum getSymbols_Enum() {
		return symbols_Enum;
	}

	public void setSymbols_Enum(Symbols_Enum symbols_Enum) {
		this.symbols_Enum = symbols_Enum;
	}

	public Contract_types_Enum getContract_types_Enum() {
		return contract_types_Enum;
	}

	public void setContract_types_Enum(Contract_types_Enum contract_types_Enum) {
		this.contract_types_Enum = contract_types_Enum;
	}

	public Types_Enum getTypes_Enum() {
		return types_Enum;
	}

	public void setTypes_Enum(Types_Enum types_Enum) {
		this.types_Enum = types_Enum;
	}

	public Ktype_Enum getKtype_Enum() {
		return ktype_Enum;
	}

	public void setKtype_Enum(Ktype_Enum ktype_Enum) {
		this.ktype_Enum = ktype_Enum;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
