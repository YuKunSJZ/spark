package BigDataAnalysis;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import jdbc.JDBCHelper;

public class SparkTask {
	public static void main(String[] args) {
		//HttpHelper.sendHttp("http://localhost:8080/sparktask/SparkTasks.jsp");
		FundCompany  myFundCompany = new FundCompany();
		myFundCompany.load2ODS();
		myFundCompany.close();
//		getTableList();
		
		
		}
	
	private static void getTableList() {
		String sql = ""
				+ "select "
				+ "* "
				+ "from admin_menu "
				+ "limit 10 ";
		
		JDBCHelper mydb = JDBCHelper.getInstance();
		ResultSet myRs = mydb.getQueryRs(sql);
		try {
			while(myRs.next()) {
				System.out.println(myRs.getString("title"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
