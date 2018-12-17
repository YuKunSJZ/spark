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

public abstract class Company {
	public String companyName;
	public JavaSparkContext javasc;

	public abstract void loadData2ODS();
	public abstract void loadData2DW();
	public abstract void makeDataMarket();

	protected SQLContext getSQLContext(SparkContext sc) {
		boolean local = ConfigrationManager.getPropertyBoolean(Constants.SPARK_LOCAL);
		if(local) {
			return SQLContext.getOrCreate(sc);
		}else {
			return new SQLContext(sc);
		}
	}
	
	protected Properties getConnProp() {
		Properties connectionProperties = new Properties();
		connectionProperties.put("jdbc_url", ConfigrationManager.getProperty(Constants.JDBC_URL));
		connectionProperties.put("user", ConfigrationManager.getProperty(Constants.JDBC_USER));
		connectionProperties.put("password", ConfigrationManager.getProperty(Constants.JDBC_PASS));
		connectionProperties.put("driver", ConfigrationManager.getProperty(Constants.JDBC_DRIVER));
		return connectionProperties;
	}
	
	protected  SQLContext registerSourceTable(String sourceStr,String tableName){
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
	
	protected SQLContext write2Mysql(Dataset<Row> ds2Write,String toTableName){
		SQLContext sqlContext = getSQLContext(javasc.sc());
		Properties myConnProp = getConnProp();		
		ds2Write.write().mode(SaveMode.Append).jdbc(myConnProp.getProperty("jdbc_url"), toTableName, myConnProp);
		return sqlContext;
	} 
	
	
	

}
