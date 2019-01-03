package BigDataAnalysis;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import jdbc.JDBCHelper;

/**
*   ����ʱ�䣺2018��10��25������3:36:08
*   ��Ŀ���ƣ�test  
*@author zyk  
*   ˵����
*/
public class TableView {

	public Table getTable() {
		return myTable;
	}
	
	public Base myBase;
	private Table myTable;
	private BuildSQL myBuildSQL;
	private ResultSet resultSet; 
	
	public TableView(String _tableName,Base _base) throws SQLException {
		myBase = _base;
		myBuildSQL = new BuildSQL(_base);
		myBuildSQL.create(_tableName);
		myTable=myBuildSQL.myTable;
		resultSet = getTableResultSet(false);
	}
	
	public void kill() {
		try {
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
		
//	public void getConlumnName() throws IOException {
//		myBase.printPage("<tr class=\"ColumnName\">");
//		for(String fieldName:myTable.fieldDict.keySet()) {
//			myBase.printPage("<td>" + myTable.fieldDict.get(fieldName).fieldAlias + "</td>" );
//		}
//		myBase.printPage("</tr>");
//
//		myBase.printPage("<br/>");
//	}
	
	public void getTableEditBanner() throws IOException {
		myBase.printPage("<div>��ǰ��ͼ :  "+myTable.tableAlias+" ("+myTable.tableName+")</div>");
		myBase.printPage("<form action=\"TableEdit.jsp\" method=\"get\">");
		myBase.printPage(" <input style=\"display:none\"  type=\"text\" name=\"Table\" value=\""+ myTable.tableName +"\" />");
		myBase.printPage(" <input style=\"display:none\"  type=\"text\" name=\"TableAction\" value=\"Add\" />");
		myBase.printPage(" <input type=\"submit\" value=\"��Ӽ�¼\" />");
		myBase.printPage("</form>");

	}
	
	
//	public void getLeftMenu() throws IOException, SQLException {
//		TableView myTableView = new TableView("Links",myBase);
//		LinkedHashMap<String,String> myLinks = new LinkedHashMap<String,String>();
//		//myLinks.put(myTableView.getSingleColumn(_Column), value)
//		
//		
//		myBase.printPage("<div>վ�㣺"+myTable.tableName+"</div>");
//		myBase.printPage("<form action=\"TableEdit.jsp\" method=\"get\">");
//		myBase.printPage(" <input style=\"display:none\"  type=\"text\" name=\"Table\" value=\""+ myTable.tableName +"\" />");
//		myBase.printPage(" <input style=\"display:none\"  type=\"text\" name=\"TableAction\" value=\"Add\" />");
//		myBase.printPage(" <input type=\"submit\" value=\"��Ӽ�¼\" />");
//		myBase.printPage("</form>");
//
//	}
	
	public ResultSet getTableResultSet(Boolean _isTableViewPage) {
		myBuildSQL.isTableViewPage = _isTableViewPage;
		JDBCHelper myAdb =  JDBCHelper.getInstance();
		return myAdb.getQueryRs(myBuildSQL.getSQL());
		
	}
	public void printTableLines() throws SQLException, IOException {
		myBase.printPage("<div class=\"table_head\">");
		myBase.printPage("<div class=\"table_row_column_name\">");
		for(String fieldName:myTable.fieldDict.keySet()) {
			myBase.printPage("<div class=\"column_name\">" + myTable.fieldDict.get(fieldName).fieldAlias + "</div>" );
		}
		myBase.printPage("</div>");
		myBase.printPage("</div>");
		//myBase.printPage("<div class=\"TableRecordSet\">");
		myBase.printPage("<div class=\"table_content\">");

		while (resultSet.next()) {
			myBase.printPage("<div class=\"table_row\">");
			for(String tableField:myTable.fieldDict.keySet()) {
				myBase.printPage("<div class=\"column\">");
				myBase.printPage(Base.Format(resultSet.getString(tableField), myTable.fieldDict.get(tableField).DataType, myTable.fieldDict.get(tableField).dataLength));
				myBase.printPage("</div>");
			}
			myBase.printPage("</div>");
		}
		myBase.printPage("</div>");
	}
	
	public List<String> getSingleColumn(String _Column) throws SQLException {
		ResultSet _resultSet = getTableResultSet(false);
		List<String> myList = new ArrayList<String>();
		while (_resultSet.next()) {
			myList.add(_resultSet.getString(_Column));
		}
		return myList;
	}
	
	
	public void buildPageSearch() throws IOException, SQLException {
		TableView myTableView = new TableView("Tables",myBase);
		List<String> myTables = myTableView.getSingleColumn("TableName");
		//myBase.printPage("<h1>�𰲽��ڴ�������ͼƽ̨</h1>");
		myBase.printPage("<div id=\"TableName\" >" );
		myBase.printPage("	<form name=\"TableSearch\" action=\"TableView.jsp\" method=\"get\" >");
		myBase.printPage("		<input id=\"TableNameSearch\" list=\"Tables\" type=\"Text\" name=\"Table\"/>");
		myBase.printPage("<datalist id=\"Tables\">");
		for(String tableName:myTables) {
			myBase.printPage("  <option value=\"" + tableName + "\" >");
		}
//		this.printPage("  <option value=\"Tables\">");
//		this.printPage("  <option value=\"TableFields\">");
//		this.printPage("  <option value=\"dm_new_customer\">");
		myBase.printPage("</datalist>");
		myBase.printPage("		<input id=\"submit\" type=\"submit\" value=\"����ͼ\" />");
		myBase.printPage("	</form>");
		myBase.printPage("</div>" );
	}
	
	public void printTable() throws SQLException, IOException {
		myBase.printPage("<table class=\"tableborder\">");
		getTableEditBanner();
		printTableLines();
		myBase.printPage("</table>");

	}
	
	public void printTableEdit() throws SQLException, IOException {
		myBase.printPage("<table>");
		getTableEditBanner();
		printTableLines();
		myBase.printPage("</table>");

	}

	public void testPrint() {
		myTable.testPrint();
	}

}
