package Constants;

import org.apache.spark.sql.SQLContext;

public interface Constants {
	String JDBC_DRIVER="JDBC_DRIVER";
	String JDBC_USER="JDBC_USER";
	String JDBC_URL="JDBC_URL";
	String JDBC_PASS="JDBC_PASS";
	String JDBC_DATASOURCE_POOL_SIZE = "JDBC_DATASOURCE_POOL_SIZE";
	String SPARK_APP_NAME = "SPARK_APP_NAME";
	String SPARK_LOCAL = "SPARK_LOCAL";
	
	String PARAM_START_DATE = "PARAM_START_DATE";
	String PARAM_END_DATE = "PARAM_END_DATE";

}
