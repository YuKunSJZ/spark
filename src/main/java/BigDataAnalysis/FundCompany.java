package BigDataAnalysis;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

import Constants.Constants;
import config.ConfigrationManager;
import jdbc.JDBCHelper;

public class FundCompany extends Company{
	JavaSparkContext javasc;
	private Properties dataSourceConnProp;
	private Properties dataDestConnProp;
	private String sourceSchemaName;
	public FundCompany(JavaSparkContext _javasc) {
		setConnProp();
		javasc = _javasc;
		sourceSchemaName = "guquan_zhengshi";
	}
	


	@Override
	protected SQLContext getSQLContext(SparkContext sc) {
		boolean local = ConfigrationManager.getPropertyBoolean(Constants.SPARK_LOCAL);
		if(local) {
			return SQLContext.getOrCreate(sc);
		}else {
			return new SQLContext(sc);
		}
	}

	@Override
	protected void setConnProp() {
		dataSourceConnProp = new Properties();
		dataSourceConnProp.put("jdbc_url", ConfigrationManager.getProperty(Constants.COMPANY_FUND_JDBC));
		dataSourceConnProp.put("user", ConfigrationManager.getProperty(Constants.COMPANY_FUND_JDBC_USER));
		dataSourceConnProp.put("password", ConfigrationManager.getProperty(Constants.COMPANY_FUND_JDBC_PASS));
		dataSourceConnProp.put("driver", ConfigrationManager.getProperty(Constants.COMPANY_JDBC_DRIVER));
		
		dataDestConnProp = new Properties();
		dataDestConnProp.put("jdbc_url", ConfigrationManager.getProperty(Constants.JDBC_URL));
		dataDestConnProp.put("user", ConfigrationManager.getProperty(Constants.JDBC_USER));
		dataDestConnProp.put("password", ConfigrationManager.getProperty(Constants.JDBC_PASS));
		dataDestConnProp.put("driver", ConfigrationManager.getProperty(Constants.JDBC_DRIVER));
	}

	@Override
	protected SQLContext registerSourceTable(String sourceStr, String tableName) {
		SQLContext sqlContext = getSQLContext(javasc.sc());
		Dataset<Row> stockDF;
		
		if (sourceStr.contains("jdbc")) {
			stockDF= sqlContext.read().jdbc(dataSourceConnProp.getProperty("jdbc_url"), tableName, dataSourceConnProp);
		}else {
			stockDF= sqlContext.read().json(sourceStr);
		}

		stockDF.createOrReplaceTempView(tableName);
		return sqlContext;
	}

	@Override
	protected void write2Mysql(Dataset<Row> ds2Write, String toTableName) {
		write2Mysql(ds2Write,toTableName,SaveMode.Append);
	}
	
	protected void write2Mysql(Dataset<Row> ds2Write, String toTableName, SaveMode _saveMode) {
		try {
			ds2Write.write().mode(_saveMode).jdbc(dataDestConnProp.getProperty("jdbc_url"), toTableName, dataDestConnProp);
			task_log("*" + toTableName + " load successfully");
		} catch (Exception e) {
			task_log("*" + toTableName + " load failed");
		}
	}
	
	@Override
	protected void load2ODS() {
		Iterator<String> myIterator = getTableList().iterator();
		while(myIterator.hasNext()) {
			String tableName = myIterator.next();
			tableCopy(tableName,"ods_db." + sourceSchemaName.split("_")[0] + "_" + tableName,SaveMode.Overwrite);
		}
			
	}
	
	private void tableCopy(String _sourceTable,String _write2Table,SaveMode _saveMode) {
		String souceTable = _sourceTable;
		String write2Table= _write2Table;
		
		//this.setDataSourcePath("D:\\spark\\datasorce\\m_fact_stock_finance_end_month.csv");
		SQLContext mySQLcontext = registerSourceTable(ConfigrationManager.getProperty(Constants.JDBC_URL), souceTable);
				
		Dataset<Row> dssouceTable = mySQLcontext.sql(""
				+ "select "
				+ " * "
				+ "from " + souceTable + " ");
		
		write2Mysql(dssouceTable, write2Table,_saveMode);
	}
	
	private List<String> getTableList() {
		List<String> tableList = new ArrayList<String>();
		String sql = ""
				+ "select "
				+ "TableName "
				+ "from test_db.tables "
				+ "where TableSchema = '" + sourceSchemaName + "' "
				+ "and IfLoadODS = 1 "
				+ "order by TableName ";
		
		JDBCHelper mydb = JDBCHelper.getInstance();
		ResultSet myRs = mydb.getQueryRs(sql);
		try {
			while(myRs.next()) {
				tableList.add(myRs.getString("TableName"));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return tableList;
	}
	
	private void task_log(String logMessage) {
		System.out.println(logMessage);
	}
	
}
