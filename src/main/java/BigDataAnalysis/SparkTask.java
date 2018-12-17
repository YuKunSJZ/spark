package BigDataAnalysis;

import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;
import org.apache.spark.sql.SaveMode;



import Constants.Constants;
import config.BigDataTask;
import config.ConfigrationManager;

public class SparkTask {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf()
				.setAppName(ConfigrationManager.getProperty(Constants.SPARK_APP_NAME))
				.setMaster("local");
		JavaSparkContext javasc = new JavaSparkContext(conf);
//		CustomerCount myCustomerCount = new CustomerCount(javasc);
//		
//		myCustomerCount.run(new BigDataTask("1"));
		FundCompany myFundCompany = new FundCompany(javasc);
		myFundCompany.insertStockCnt();
		javasc.close();
		
	}
}
