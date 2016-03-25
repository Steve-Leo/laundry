package com.ruanku.web;

import java.awt.Font;
import java.util.List;
import java.util.Map;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.axis.DateTickUnit;
import org.jfree.chart.labels.ItemLabelAnchor;
import org.jfree.chart.labels.ItemLabelPosition;
import org.jfree.chart.labels.StandardXYItemLabelGenerator;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYItemRenderer;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.chart.title.TextTitle;
import org.jfree.data.time.Month;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.ui.TextAnchor;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.ruanku.dao.OrderDao;
import com.ruanku.dao.impl.OrderDaoImpl;
import com.ruanku.model.Shop;
import com.ruanku.model.Statistics;

public class ShopStatisticsReport extends ActionSupport {

	private static final long serialVersionUID = 2L;

	private JFreeChart chart;
	private Shop shop;
	private String data;

	public JFreeChart getChart() {
		return chart;
	}

	public void setChart(JFreeChart chart) {
		this.chart = chart;
	}

	@Override
	public String execute() throws Exception {
		Map session = ActionContext.getContext().getSession();
		data = (String) session.get("data");
		shop = (Shop) session.get("shop");
		OrderDao dao = new OrderDaoImpl();
		List<Statistics> slist = dao.queryShopStatisticsByYear(shop.getId(),
				data);
		// 访问量统计
		TimeSeries timeSeries = new TimeSeries(shop.getShopname() + data
				+ "年度收益情况", Month.class);
		// 添加数据
		// 记录月份的i
		int j = 0;
		for (int i = 1; i <= 12; i++) {

			int len = slist.size();
			if (j < len) {
				Statistics st = slist.get(j);
				String mon = st.getYearMonth();
				if (mon.equals(i + "")) {
					timeSeries.add(new Month(i, Integer.parseInt(data)),
							st.getSumTotal());
					j++;
				} else {
					timeSeries.add(new Month(i, Integer.parseInt(data)), 0.0);
				}

			} else {
				timeSeries.add(new Month(i, Integer.parseInt(data)), 0.0);
			}

		}

		// 定义时间序列的集合
		TimeSeriesCollection lineDataset = new TimeSeriesCollection();
		lineDataset.addSeries(timeSeries);
		// lineDataset.addSeries(timeSeries2);

		chart = ChartFactory.createTimeSeriesChart("收益统计时间折线图", "年份", "收益",
				lineDataset, true, true, true);

		// 设置主标题
		chart.setTitle(new TextTitle(shop.getShopname() + data + "年度收益情况统计图",
				new Font("隶书", Font.ITALIC, 15)));
		// 设置子标题
		// TextTitle subtitle = new TextTitle("2013年度", new Font("黑体",
		// Font.BOLD, 12));
		// chart.addSubtitle(subtitle);
		chart.setAntiAlias(true);

		// 设置时间轴的范围。
		XYPlot plot = (XYPlot) chart.getPlot();
		DateAxis dateaxis = (DateAxis) plot.getDomainAxis();
		dateaxis.setDateFormatOverride(new java.text.SimpleDateFormat("M月"));
		dateaxis.setTickUnit(new DateTickUnit(DateTickUnit.MONTH, 1));

		// 设置曲线是否显示数据点
		XYLineAndShapeRenderer xylinerenderer = (XYLineAndShapeRenderer) plot
				.getRenderer();
		xylinerenderer.setBaseShapesVisible(true);

		// 设置曲线显示各数据点的值
		XYItemRenderer xyitem = plot.getRenderer();
		xyitem.setBaseItemLabelsVisible(true);
		xyitem.setBasePositiveItemLabelPosition(new ItemLabelPosition(
				ItemLabelAnchor.OUTSIDE12, TextAnchor.BASELINE_CENTER));
		xyitem.setBaseItemLabelGenerator(new StandardXYItemLabelGenerator());
		xyitem.setBaseItemLabelFont(new Font("Dialog", 1, 12));
		plot.setRenderer(xyitem);
		System.out.println("-----------zhixingwangcheng---------");
		session.remove("data");
		return SUCCESS;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

}
