package BigDataAnalysis;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import Constants.Constants;
import config.ConfigrationManager;

public class SparkTask {
	public static void main(String[] args) {
		SparkConf conf = new SparkConf()
				.setAppName(ConfigrationManager.getProperty(Constants.SPARK_APP_NAME))
				.setMaster("local");
		JavaSparkContext javasc = new JavaSparkContext(conf);
		FundCompany  myFundCompany = new FundCompany(javasc);
		myFundCompany.load2ODS();
		javasc.close();
		
	}
}
