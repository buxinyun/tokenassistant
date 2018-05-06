package com.tokenassistant.kline;

/**
 * 
 * [ 1440308760000, 时间戳 233.38, 开 233.38, 高 233.27, 低 233.37, 收 186, 交易量
 * 79.70234956 交易量转化BTC或LTC数量 ]
 * 
 * @author Administrator
 *
 */
public class KlinePoint {
	private double unixTime;
	private double open;
	private double high;
	private double low;
	private double last;
	private double vol;
	private double volCoin;

	public double getVolCoin() {
		return volCoin;
	}

	public void setVolCoin(double volCoin) {
		this.volCoin = volCoin;
	}

	public double getUnixTime() {
		return unixTime;
	}

	public void setUnixTime(double unixTime) {
		this.unixTime = unixTime;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getLast() {
		return last;
	}

	public void setLast(double last) {
		this.last = last;
	}

	public double getVol() {
		return vol;
	}

	public void setVol(double vol) {
		this.vol = vol;
	}

}
