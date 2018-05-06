package com.tokenassistant.kline;

import java.util.List;

import com.tokenassistant.market.IOkex_Parameter.Ktype_Enum;
import com.tokenassistant.message.PushMsg;

public class KlineManager {

	Kline_boll kline_boll = new Kline_boll();

	public void inputKlines(List<KlinePoint> klines, PushMsg pushMsg) {

		pushMsg.setKtype_Enum(Ktype_Enum.BOLL);
		kline_boll.calculation(klines, pushMsg);

	}
}
