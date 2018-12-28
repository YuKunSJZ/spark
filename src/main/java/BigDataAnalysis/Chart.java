package BigDataAnalysis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.sun.tools.javac.code.Attribute.Array;

import net.sf.json.JSONObject;


public class Chart {
	/*
	"title": {
	"text": "瑞安金融各公司存量金额(单位：万元)"
},
"tooltip": {},
"legend": {
	"data": ["销量", "收入1"]
},
"xAxis": {
	"data": ["衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"]
},
"yAxis": {},
"series": [{
	"name": "销量",
	"type": "bar",
	"data": [5, 20, 36, 10, 10, 20]
}, {
	"name": "收入1",
	"type": "bar",
	"data": [5, 20, 36, 10, 10, 20]
}]
		*/
	    private LinkedHashMap<String,Object> chartParam = new LinkedHashMap<String,Object>();
		
		private void gettitle() {
			LinkedHashMap<String,Object> text = new LinkedHashMap<String,Object>();
			text.put("text", "瑞安金融各公司存量金额(单位：万元)");
			chartParam.put("title", text);
		}
		
		private void gettooltip() {
			chartParam.put("tooltip", new Object());
		}
		
		private void getlegend() {
			LinkedHashMap<String,Object> data = new LinkedHashMap<String,Object>();
			String[] tempData = {"销量", "收入1"};
			data.put("data", tempData);
			chartParam.put("legend", data);
		}
		
		private void getxAxis() {
			LinkedHashMap<String,Object> data = new LinkedHashMap<String,Object>();
			String[] tempData = {"衬衫", "羊毛衫", "雪纺衫", "裤子", "高跟鞋", "袜子"};
			data.put("data", tempData);
			chartParam.put("xAxis", data);
		}
		
		private void getyAxis() {
			chartParam.put("yAxis", new Object());
		}
		
		private void getseries() {
			LinkedHashMap<String,Object> myData1 = new LinkedHashMap<String,Object>();
			Integer[] data1 = {5, 20, 36, 10, 10, 20};
			myData1.put("name", "销量");
			myData1.put("type", "bar");
			myData1.put("data", data1);
			
			LinkedHashMap<String,Object> myData2 = new LinkedHashMap<String,Object>();
			Integer[] data2 = {5, 20, 36, 10, 10, 20};
			myData2.put("name", "收入1");
			myData2.put("type", "bar");
			myData2.put("data", data2);
			
			List<Map<String,Object>> myData = new ArrayList<Map<String,Object>>();
			myData.add(myData1);
			myData.add(myData2);
			chartParam.put("series", myData);
		}
		
		public String getChartParam() {
			gettitle();
			gettooltip();
			getlegend();
			getxAxis();
			getyAxis();
			getseries();
			
			//return chartParam;
			return JSONObject.fromObject(chartParam).toString();
			
			
			
			
			
		}
		



}
