package BigDataAnalysis;

import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SQLContext;

import Constants.Constants;
import config.ConfigrationManager;

public class FundCompany extends Company{
	public FundCompany(JavaSparkContext jsc) {
		javasc = jsc;
	}

	@Override
	public void loadData2ODS() {
		// TODO Auto-generated method stub
		insertStockCnt();
	}

	@Override
	public void loadData2DW() {
		
		
	}

	@Override
	public void makeDataMarket() {
		// TODO Auto-generated method stub
		
	}
	
	public void insertStockCnt() {
		String souceTable = "m_fact_stock_finance_end_month";
		String write2Table= "dm_db.dm_customer_cnt_in_agent_product";
		
		//this.setDataSourcePath("D:\\spark\\datasorce\\m_fact_stock_finance_end_month.csv");
		String dataSourcePath = ConfigrationManager.getProperty(Constants.JDBC_URL);
		SQLContext mySQLcontext = registerSourceTable(dataSourcePath, souceTable);
				
		Dataset<Row> dsStockCustomerCountAgg = mySQLcontext.sql(""
				+ "select "
				+ " sysid "
				+ ",'2018-01-21' as stock_date "
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
