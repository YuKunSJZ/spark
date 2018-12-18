package BigDataAnalysis;

import java.util.Properties;

import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;

import Constants.Constants;
import config.ConfigrationManager;

public class FundCompany extends Company{
	JavaSparkContext javasc;
	private Properties dataSourceConnProp;
	private Properties dataDestConnProp;
	public FundCompany(JavaSparkContext _javasc) {
		setConnProp();
		javasc = _javasc;
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
		dataSourceConnProp.put("jdbc_url", ConfigrationManager.getProperty(Constants.JDBC_URL));
		dataSourceConnProp.put("user", ConfigrationManager.getProperty(Constants.JDBC_USER));
		dataSourceConnProp.put("password", ConfigrationManager.getProperty(Constants.JDBC_PASS));
		dataSourceConnProp.put("driver", ConfigrationManager.getProperty(Constants.JDBC_DRIVER));
		
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
	protected SQLContext write2Mysql(Dataset<Row> ds2Write, String toTableName) {
		SQLContext sqlContext = getSQLContext(javasc.sc());
		ds2Write.write().mode(SaveMode.Append).jdbc(dataDestConnProp.getProperty("jdbc_url"), toTableName, dataDestConnProp);
		return sqlContext;
	}
	
	@Override
	protected void load2ODS() {
		insertStockCnt();		
	}
	
	public void insertStockCnt() {
		String souceTable = "m_fact_stock_finance_end_month";
		String write2Table= "dm_db.dm_customer_cnt_in_agent_product";
		
		//this.setDataSourcePath("D:\\spark\\datasorce\\m_fact_stock_finance_end_month.csv");
		SQLContext mySQLcontext = registerSourceTable(ConfigrationManager.getProperty(Constants.JDBC_URL), souceTable);
				
		Dataset<Row> dsStockCustomerCountAgg = mySQLcontext.sql(""
				+ "select "
				+ " sysid "
				+ ",'2018-01-19' as stock_date "
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
}
