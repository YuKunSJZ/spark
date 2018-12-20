package BigDataAnalysis;

import java.util.Properties;
import org.apache.spark.SparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;


public abstract class Company {
	protected abstract SQLContext getSQLContext(SparkContext sc);
	
	protected abstract void setConnProp();

	
	protected abstract SQLContext registerSourceTable(String sourceStr,String tableName);
	
	protected abstract void write2Mysql(Dataset<Row> ds2Write,String toTableName);
	
	protected abstract void load2ODS(); 
	
	protected abstract void makeMarketReport(); 
	
	protected abstract void create(); 
	protected abstract void close(); 

}
