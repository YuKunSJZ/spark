package fz.spark;
import java.util.Properties;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import Constants.Constants;
import config.ConfigrationManager;

public class SparkMySQL {
	public static void main(String[] args) {
		JavaSparkContext sparkContext = new JavaSparkContext(new SparkConf().setAppName("SparkMysql").setMaster("local"));
		SQLContext sqlContext = SQLContext.getOrCreate(JavaSparkContext.toSparkContext(sparkContext));
		readMySQLTest(sqlContext);
		//sparkContext.stop();
	}

	private static void readMySQL(SQLContext sqlContext) {
		String url = ConfigrationManager.getProperty(Constants.JDBC_URL);
		String table = "tables";
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", ConfigrationManager.getProperty(Constants.JDBC_USER));
		connectionProperties.put("password", ConfigrationManager.getProperty(Constants.JDBC_PASS));
		connectionProperties.put("driver", ConfigrationManager.getProperty(Constants.JDBC_DRIVER));

		System.out.println("tables");

		Dataset<Row> jdbcRs = sqlContext.read().jdbc(url, table,connectionProperties).select("*");
		
		System.out.println(jdbcRs.count());
		jdbcRs.show();

		jdbcRs.show(50);

	}
	
	private static void readMySQLTest(SQLContext sqlContext) {
		String url = ConfigrationManager.getProperty(Constants.JDBC_URL);
		String table = "tables";
		Properties connectionProperties = new Properties();
		connectionProperties.put("user", ConfigrationManager.getProperty(Constants.JDBC_USER));
		connectionProperties.put("password", ConfigrationManager.getProperty(Constants.JDBC_PASS));
		connectionProperties.put("driver", ConfigrationManager.getProperty(Constants.JDBC_DRIVER));
	

		System.out.println("tables");

		Dataset<Row> jdbcRs = sqlContext.sql("select * from tables");
		
		System.out.println(jdbcRs.count());
		jdbcRs.show();

		jdbcRs.show(50);

	}

}
