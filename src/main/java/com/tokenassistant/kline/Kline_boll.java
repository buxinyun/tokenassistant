package com.tokenassistant.kline;

import java.text.SimpleDateFormat;
import java.util.List;

import com.tokenassistant.message.PushMsg;

public class Kline_boll {

	/**
	 * @param Klines
	 */
	public void calculation(List<KlinePoint> Klines, PushMsg pushMsg) {

		// 计算MA
		double[] ma = new double[Klines.size()];
		calculationMA(Klines, ma);
		// 计算 up,down 布林线
		double[] boll_up = new double[Klines.size()];
		double[] boll_down = new double[Klines.size()];
		calculationMD(Klines, ma, boll_up, boll_down);

		if (Klines.get(Klines.size() - 1).getLast() > boll_up[Klines.size() - 1]
				&& Klines.get(Klines.size() - 2).getLast() <= boll_up[Klines.size() - 2]) {
			SimpleDateFormat sformat = new SimpleDateFormat("MM-dd HH:mm:ss");
			String timestr = sformat.format(Klines.get(Klines.size() - 1).getUnixTime());

			pushMsg.setContent(timestr + " 现价:" + Klines.get(Klines.size() - 1).getLast() + ",突破布林线上轨,上轨价为:"
					+ String.format("%.3f", boll_up[Klines.size() - 1]));
			pushMsg.pushUp();
		} else if (Klines.get(Klines.size() - 1).getLast() < boll_down[Klines.size() - 1]
				&& Klines.get(Klines.size() - 2).getLast() >= boll_down[Klines.size() - 2]) {
			SimpleDateFormat sformat = new SimpleDateFormat("MM-dd HH:mm:ss");
			String timestr = sformat.format(Klines.get(Klines.size() - 1).getUnixTime());
			pushMsg.setContent(timestr + " 现价:" + Klines.get(Klines.size() - 1).getLast() + ",跌破布林线下轨,下轨价为:"
					+ String.format("%.3f", boll_down[Klines.size() - 1]));
			pushMsg.pushDown();
		} else {
			SimpleDateFormat sformat = new SimpleDateFormat("MM-dd HH:mm:ss");
			String timestr = sformat.format(Klines.get(Klines.size() - 1).getUnixTime());
			pushMsg.setContent(timestr + " 现价:" + Klines.get(Klines.size() - 1).getLast() + ",处于布林线中间，上轨价位:"
					+ String.format("%.3f", boll_up[Klines.size() - 1]) + " 下轨价位:"
					+ String.format("%.3f", boll_down[Klines.size() - 1]));
			pushMsg.pushNone();
		}
	}

	/**
	 * 计算20日的移动平均线
	 * 
	 * @return
	 */
	private void calculationMA(List<KlinePoint> Klines, double[] ma) {

		for (int i = 0; i < Klines.size(); i++) {

			double totalSum = 0;

			if (i < 20) {

				for (int j = 0; j <= i; j++) {
					KlinePoint klinePoint = Klines.get(j);
					totalSum = totalSum + klinePoint.getLast();

				}
				double average = totalSum / (i + 1);
				ma[i] = average;

			} else {
				for (int j = i - 19; j <= i; j++) {
					KlinePoint klinePoint = Klines.get(j);
					totalSum = totalSum + klinePoint.getLast();
				}
				double average = totalSum / 20;
				ma[i] = average;
			}
		}
	}

	/**
	 * 计算 MD
	 * 
	 * @param Klines
	 * @param ma
	 * @param boll_up
	 * @param boll_down
	 * @param pushMsg
	 */
	private void calculationMD(List<KlinePoint> Klines, double[] ma, double[] boll_up, double[] boll_down) {

		for (int i = 0; i < Klines.size(); i++) {

			double totalSum = 0;

			if (i < 20) {

				for (int j = 0; j <= i; j++) {
					KlinePoint klinePoint = Klines.get(j);
					totalSum = totalSum + (klinePoint.getLast() - ma[i]) * (klinePoint.getLast() - ma[i]);

				}
				double average = totalSum / (i + 1);
				double md = Math.sqrt(average);

				boll_up[i] = md * 2 + ma[i];
				boll_down[i] = ma[i] - md * 2;

			} else {
				for (int j = i - 19; j <= i; j++) {
					KlinePoint klinePoint = Klines.get(j);
					totalSum = totalSum + (klinePoint.getLast() - ma[i]) * (klinePoint.getLast() - ma[i]);
				}
				double average = totalSum / 20;
				double md = Math.sqrt(average);

				boll_up[i] = md * 2 + ma[i];
				boll_down[i] = ma[i] - md * 2;
			}
		}

	}

	/**
	 * 计算标准差MD MD=平方根(N-1)日的（C－MA）的两次方之和除以N
	 * 
	 * @return
	 */
	// private void calculationMD2(List<KlinePoint> Klines, double[] ma,
	// double[] boll_up, double[] boll_down) {
	//
	// for (int i = 0; i < Klines.size(); i++) {
	//
	// double totalSum = 0;
	//
	// if (i < 20) {
	//
	// for (int j = 0; j <= i; j++) {
	// KlinePoint klinePoint = Klines.get(j);
	// totalSum = totalSum + (klinePoint.getLast() - ma[i]) *
	// (klinePoint.getLast() - ma[i]);
	//
	// }
	// double average = totalSum / (i + 1);
	// double md = Math.sqrt(average);
	//
	// boll_up[i] = md * 2 + ma[i];
	// boll_down[i] = ma[i] - md * 2;
	//
	// } else {
	// for (int j = i - 19; j <= i; j++) {
	// KlinePoint klinePoint = Klines.get(j);
	// totalSum = totalSum + (klinePoint.getLast() - ma[i]) *
	// (klinePoint.getLast() - ma[i]);
	// }
	// double average = totalSum / 20;
	// double md = Math.sqrt(average);
	//
	// boll_up[i] = md * 2 + ma[i];
	// boll_down[i] = ma[i] - md * 2;
	// }
	// }
	// }

}
