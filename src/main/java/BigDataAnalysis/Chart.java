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
	"text": "�𰲽��ڸ���˾�������(��λ����Ԫ)"
},
"tooltip": {},
"legend": {
	"data": ["����", "����1"]
},
"xAxis": {
	"data": ["����", "��ë��", "ѩ����", "����", "�߸�Ь", "����"]
},
"yAxis": {},
"series": [{
	"name": "����",
	"type": "bar",
	"data": [5, 20, 36, 10, 10, 20]
}, {
	"name": "����1",
	"type": "bar",
	"data": [5, 20, 36, 10, 10, 20]
}]
		*/
	    private LinkedHashMap<String,Object> chartParam = new LinkedHashMap<String,Object>();
		
		private void gettitle() {
			LinkedHashMap<String,Object> text = new LinkedHashMap<String,Object>();
			text.put("text", "�𰲽��ڸ���˾�������(��λ����Ԫ)");
			chartParam.put("title", text);
		}
		
		private void gettooltip() {
			chartParam.put("tooltip", new Object());
		}
		
		private void getlegend() {
			LinkedHashMap<String,Object> data = new LinkedHashMap<String,Object>();
			String[] tempData = {"����", "����1"};
			data.put("data", tempData);
			chartParam.put("legend", data);
		}
		
		private void getxAxis() {
			LinkedHashMap<String,Object> data = new LinkedHashMap<String,Object>();
			String[] tempData = {"����", "��ë��", "ѩ����", "����", "�߸�Ь", "����"};
			data.put("data", tempData);
			chartParam.put("xAxis", data);
		}
		
		private void getyAxis() {
			chartParam.put("yAxis", new Object());
		}
		
		private void getseries() {
			LinkedHashMap<String,Object> myData1 = new LinkedHashMap<String,Object>();
			Integer[] data1 = {5, 20, 36, 10, 10, 20};
			myData1.put("name", "����");
			myData1.put("type", "bar");
			myData1.put("data", data1);
			
			LinkedHashMap<String,Object> myData2 = new LinkedHashMap<String,Object>();
			Integer[] data2 = {5, 20, 36, 10, 10, 20};
			myData2.put("name", "����1");
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
