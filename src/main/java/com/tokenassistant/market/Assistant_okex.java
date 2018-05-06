package com.tokenassistant.market;

import java.util.ArrayList;
import java.util.List;

import com.tokenassistant.kline.KlineManager;
import com.tokenassistant.kline.KlinePoint;
import com.tokenassistant.market.IOkex_Parameter.Contract_types_Enum;
import com.tokenassistant.market.IOkex_Parameter.Symbols_Enum;
import com.tokenassistant.market.IOkex_Parameter.Types_Enum;
import com.tokenassistant.message.PushMsg;
import com.tokenassistant.ticker.FutureClient;

public class Assistant_okex {

	// 推送消息
	PushMsg pushMsg = new PushMsg();

	// 期货行情
	FutureClient futureClient = new FutureClient();

	// 指标分析系统
	KlineManager klineManager = new KlineManager();

	public void start() {

		for (Symbols_Enum symbols_Enum : Symbols_Enum.values()) {

			for (Contract_types_Enum contract_types_Enum : Contract_types_Enum.values()) {

				for (Types_Enum types_Enum : Types_Enum.values()) {
					// System.out.println(symbols_Enum.getKeyWord() + " " +
					// contract_types_Enum.getKeyWord() + " "
					// + types_Enum.getKeyWord());
	
					// 获取期货行情
					List<KlinePoint> Klines = new ArrayList<KlinePoint>();
					futureClient.future_kline(symbols_Enum.getKeyWord(), contract_types_Enum.getKeyWord(),
							types_Enum.getKeyWord(), Klines);

					// 行情输入指标分析，获取分析结果
					pushMsg.setSymbols_Enum(symbols_Enum);
					pushMsg.setContract_types_Enum(contract_types_Enum);
					pushMsg.setTypes_Enum(types_Enum);

					klineManager.inputKlines(Klines, pushMsg);

				}
			}
		}
	}
}
