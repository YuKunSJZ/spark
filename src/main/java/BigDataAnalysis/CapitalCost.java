package BigDataAnalysis;

import jdbc.JDBCHelper;

public class CapitalCost {
	
	private void ryandzCost() {
		//capitalcost_company_bi
		JDBCHelper mydb = JDBCHelper.getInstance();
		
		String sql = "delete from dm_index.capitalcost_company_bi\r\n" + 
				"; ";
		mydb.executeSql(sql.toString());
		
		sql = "";
		sql += "insert into dm_index.capitalcost_company_bi "
				+ "select \r\n" + 
				" a.sysid\r\n" + 
				",a.sysname\r\n" + 
				",concat((case when cur_month_contract = 0 then 0 else round(cur_month_cost/cur_month_contract*100,2) end),'%')\r\n" + 
				",concat((case when cur_year_contract=0 then 0 else round(cur_year_cost/cur_year_contract*100,2) end),'%')\r\n" + 
				",concat((case when last_month_contract=0 then 0 else round(last_month_cost/last_month_contract*100,2) end),'%')\r\n" + 
				"from dm_db.dm_company a\r\n" + 
				"left join \r\n" + 
				"(\r\n" + 
				"select \r\n" + 
				"a.sysid\r\n" + 
				",sum(case when date_format(stock_date,'%Y-%m') = date_format(curdate(),'%Y-%m') then contract_amount*profit_rate/100 else 0 end) as cur_month_cost \r\n" + 
				",sum(case when date_format(stock_date,'%Y-%m') = date_format(curdate(),'%Y-%m') then contract_amount else 0 end) as cur_month_contract\r\n" + 
				",sum(case when date_format(stock_date,'%Y-%m') = date_format(date_add(curdate(),interval -1 month),'%Y-%m') then contract_amount*profit_rate/100 else 0 end) as last_month_cost \r\n" + 
				",sum(case when date_format(stock_date,'%Y-%m') = date_format(date_add(curdate(),interval -1 month),'%Y-%m') then contract_amount else 0 end) as last_month_contract\r\n" + 
				",sum(case when date_format(stock_date,'%Y') = date_format(curdate(),'%Y') then contract_amount*profit_rate/100 else 0 end) as cur_year_cost \r\n" + 
				",sum(case when date_format(stock_date,'%Y') = date_format(curdate(),'%Y') then contract_amount else 0 end) as cur_year_contract\r\n" + 
				"from dm_db.day_stock_in_agent_product a \r\n" + 
				"left join dw_db.m_dim_product_finance b on a.product_id = b.id  and a.sysid=b.sysid\r\n" + 
				"group by \r\n" + 
				"a.sysid\r\n" + 
				") b on a.sysid=b.sysid\r\n" + 
				";";

		mydb.executeSql(sql.toString());
	}
	
	public void run() {
		ryandzCost();
	}

}
