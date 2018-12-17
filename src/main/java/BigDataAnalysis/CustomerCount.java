package BigDataAnalysis;

import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

import Constants.Constants;
import config.BigDataTask;
import config.ConfigrationManager;

public class CustomerCount {
	JavaSparkContext javasc;
	private String souceTable = "";
	private String write2Table="";
	private String dataSourcePath = "";
	
	public String getDataSourcePath() {
		return dataSourcePath;
	}

	public void setDataSourcePath(String dataSourcePath) {
		this.dataSourcePath = dataSourcePath;
	}

	public String getSouceTable() {
		return souceTable;
	}

	public void setSouceTable(String souceTable) {
		this.souceTable = souceTable;
	}

	public String getWrite2Table() {
		return write2Table;
	}

	public void setWrite2Table(String write2Table) {
		this.write2Table = write2Table;
	}
	
	public CustomerCount(JavaSparkContext _javasc) {
		javasc=_javasc;
	}
	
	private Properties getConnProp() {
		Properties connectionProperties = new Properties();
		connectionProperties.put("jdbc_url", ConfigrationManager.getProperty(Constants.JDBC_URL));
		connectionProperties.put("user", ConfigrationManager.getProperty(Constants.JDBC_USER));
		connectionProperties.put("password", ConfigrationManager.getProperty(Constants.JDBC_PASS));
		connectionProperties.put("driver", ConfigrationManager.getProperty(Constants.JDBC_DRIVER));
		return connectionProperties;
	}
	
	private SQLContext getSQLContext(SparkContext sc) {
		boolean local = ConfigrationManager.getPropertyBoolean(Constants.SPARK_LOCAL);
		if(local) {
			return SQLContext.getOrCreate(sc);
		}else {
			return new SQLContext(sc);
		}
	}
	
	private  SQLContext registerSourceTable(String sourceStr,String tableName){
		SQLContext sqlContext = getSQLContext(javasc.sc());
		Dataset<Row> stockDF;
		
		if (sourceStr.contains("jdbc")) {
			Properties myConnProp = getConnProp();
			stockDF= sqlContext.read().jdbc(myConnProp.getProperty("jdbc_url"), tableName, myConnProp);
		}else {
			stockDF= sqlContext.read().json(sourceStr);
		}

		stockDF.createOrReplaceTempView(tableName);
		return sqlContext;
	}
	

	private SQLContext write2Mysql(Dataset<Row> ds2Write,String toTableName){
		SQLContext sqlContext = getSQLContext(javasc.sc());
		Properties myConnProp = getConnProp();		
		ds2Write.write().mode(SaveMode.Append).jdbc(myConnProp.getProperty("jdbc_url"), toTableName, myConnProp);
		return sqlContext;
	}


	
	private void getbigdataIdcodeAggWrite2Mysql(BigDataTask task){
		String startDate=task.TaskParameter.get(ConfigrationManager.getProperty(Constants.PARAM_START_DATE));
		String endDate=task.TaskParameter.get(ConfigrationManager.getProperty(Constants.PARAM_END_DATE));
		
		souceTable = "m_fact_stock_finance_end_month";
		write2Table = "bigdataIdcodeAgg";

		this.setDataSourcePath("D:\\spark\\datasorce\\m_fact_stock_finance_end_month.csv");
		SQLContext mySQLcontext = registerSourceTable(dataSourcePath, souceTable);
		
		Dataset<Row> dsBigdataIdcodeAgg = mySQLcontext.sql("select "
				+ "current_date as stock_date "
				+ ",idcode "
				+ ",max(contract_amount) max_amount "
				+ ",min(contract_amount) min_amount "
				+ ",count(1) as cnt "
				+ "from " + souceTable + " " 
				+"group by idcode ");
		write2Mysql(dsBigdataIdcodeAgg,write2Table);
	}
	
	public void insertStockCnt() {
		souceTable = "m_fact_stock_finance_end_month";
		write2Table= "dm_db.dm_customer_cnt_in_agent_product";
		
		this.setDataSourcePath("D:\\spark\\datasorce\\m_fact_stock_finance_end_month.csv");
		SQLContext mySQLcontext = registerSourceTable(dataSourcePath, souceTable);
				
		Dataset<Row> dsStockCustomerCountAgg = mySQLcontext.sql(""
				+ "select "
				+ " sysid "
				+ ",'2018-02-28' as stock_date "
				+ ",'all_stock_cnt' as stock_type"
				+ ",count(distinct idcode) as customer_cnt "
				+ ",agent_id "
				+ ",agent_id as agent_name "
				+ ",product_id "
				+ ",0 as hq_lv "
				+ "from " + souceTable + " "
				+ "where date(time) = '2018-02-28'  "
				+ "group by "
				+ " sysid "
				+ ",agent_id "
				+ ",product_id ");
		write2Mysql(dsStockCustomerCountAgg, write2Table);
	}
	
	public void run(BigDataTask task) {
		insertStockCnt();
		//getbigdataIdcodeAggWrite2Mysql(task);
	}

	
	
}

	
	

	
	
