package com.tokenassistant.market;

public interface IOkex_Parameter {

	public enum Markets_Enum implements IOkex_Parameter {
		OKEX("okex", "okex");
		// 成员变量
		private String keyWord;
		private String ShowName;

		// 构造方法
		private Markets_Enum(String keyWord, String ShowName) {
			this.keyWord = keyWord;
			this.ShowName = ShowName;
		}

		public String getKeyWord() {
			return keyWord;
		}

		public String getShowName() {
			return ShowName;
		}

	}

	public enum Symbols_Enum implements IOkex_Parameter {

		BTC_USD("btc_usd", "比特币_美元"), ETH_USD("eth_usd", "以太坊_美元"), LTC_USD("ltc_usd", "莱特币_美元"), ETC_USD("etc_usd",
				"以太经典_美元");
		// 成员变量
		private String keyWord;
		private String ShowName;

		// 构造方法
		private Symbols_Enum(String keyWord, String ShowName) {
			this.keyWord = keyWord;
			this.ShowName = ShowName;
		}

		public String getKeyWord() {
			return keyWord;
		}

		public String getShowName() {
			return ShowName;
		}

	}

	/**
	 * 期货结算时间：this_week,next_week,quarter
	 *
	 */
	public enum Contract_types_Enum implements IOkex_Parameter {
		THIS_WEEK("this_week", "当周期货"), NEXT_WEEK("next_week", "次周期货"), QUARTER("quarter", "季度期货");

		private String keyWord;
		private String showName;

		private Contract_types_Enum(String keyWord, String showName) {
			this.keyWord = keyWord;
			this.showName = showName;
		}

		public String getKeyWord() {
			return keyWord;
		}

		public String getShowName() {
			return showName;
		}

	}

	/**
	 * K线周期
	 *
	 */
	public enum Types_Enum implements IOkex_Parameter {
		MIN5("5min", "5分钟线"), MIN15("15min", "15分钟线"), HOUR1("1hour", "1小时线"), HOUR4("1hour", "4小时线"), DAY1("1day",
				"日线");

		private String keyWord;
		private String showName;

		private Types_Enum(String keyWord, String showName) {
			this.keyWord = keyWord;
			this.showName = showName;
		}

		public String getKeyWord() {
			return keyWord;
		}

		public String getShowName() {
			return showName;
		}
	}
	
	public enum Ktype_Enum implements IOkex_Parameter {
		BOLL("boll", "布林线");

		private String keyWord;
		private String showName;

		private Ktype_Enum(String keyWord, String showName) {
			this.keyWord = keyWord;
			this.showName = showName;
		}

		public String getKeyWord() {
			return keyWord;
		}

		public String getShowName() {
			return showName;
		}
	}

}
