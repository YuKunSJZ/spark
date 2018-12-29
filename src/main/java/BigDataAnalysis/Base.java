package BigDataAnalysis;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Base {
	public HttpServletRequest request;
	public HttpServletResponse response;
	public String CustomerName="test_db";
	
	public enum DataTypeEnum{
		INTEGER,
		STRING,
		DATETIME,
		LINK,
		PICTRUE
	}
	
	public enum StriptType{
		JAVASCRIPT,
		CSS
	}
	
	public Base(HttpServletRequest _request,HttpServletResponse _response) {
		request = _request;
		response = _response;
	}
	
	
	public void printPage(String _string) {
		try {
			this.response.getWriter().print(_string);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static String Format(String _value,DataTypeEnum _dataType,int _dataLength) {
		String formatValue = "";
		switch(_dataType) {
		case INTEGER:
			formatValue = "" + _value+ "";
			break;
		case LINK:
			formatValue = "<a href=\"http://www.baidu.com\">"+ _value +"</a>";
			break;
			
		default:
			formatValue = _value;
		}
		return  formatValue;
	}
	
	public void importScript(String _location,StriptType _scriptType) throws IOException {
		switch(_scriptType) {
		case CSS:
			this.printPage("<link rel=\"stylesheet\" type=\"text/css\" href=\"" + _location + "\">");
			break;
		case JAVASCRIPT:
			this.printPage("<script language=\"javascript\" src=\"" + _location + "\"></script>");
			break;
		}
	}

	public String  getDotLine(int _counter) {
		String strLine="";
		for (int i=0;i<_counter;i++){
			strLine+=("-");
		}
		
		return strLine;
	}
}