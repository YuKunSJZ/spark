package BigDataAnalysis;

import jdbc.JDBCHelper;

public class FlowIn {
	private String Company = "";
	private void setCompany(String _companyName) {
		Company = _companyName;
	}
	private void ryanziguanFlowIn() {
		setCompany("ryan_rongzi");
		String sql = "";
		sql = "delete from dm_db.day_stock_in_agent_product where date(stock_date) = date_add(curdate(),interval -1 day) and sysid='" + Company + "' and stock_type='flow_in'\r\n" + 
				";";
		JDBCHelper mydb = JDBCHelper.getInstance();
		mydb.executeSql(sql.toString());
		
		sql = "";
		sql="insert into dm_db.day_stock_in_agent_product\r\n" + 
				"select\r\n" + 
				" '" + Company + "' as sysid\r\n" + 
				",date(begin_date) as stock_date\r\n" + 
				",'flow_in'\r\n" + 
				",sum(product_benjin) as contract_amount\r\n" + 
				" ,user_uid as agent_id\r\n" + 
				",NULL as agent_name\r\n" + 
				" ,product_uid as product_id\r\n" + 
				",NULL as hq_lv\r\n" + 
				"from ods_db.ods_rongzi_crm_hetong a where\r\n" + 
				"has_zhuantou_flag = 0 and has_chezi_flag = 0 and status in (0, 1) -- and edit_flag=0 -- edit_flag审核通过之后才算通过，与学亮沟通。\r\n" + 
				"and date(begin_date) = date_add(curdate(),interval -1 day)  -- 统计日当天\r\n" + 
				"group by\r\n" + 
				"  user_id \r\n" + 
				" ,product_uid \r\n"
				+ ",date(begin_date) ";
		mydb.executeSql(sql.toString());
	}

	private void ryandzFlowIn() {
		setCompany("ryan_dz");
		String sql = "";
		sql = "delete from dm_db.day_stock_in_agent_product where stock_date = date_add(curdate(),interval -1 day) and sysid='" + Company + "' and stock_type='flow_in'\r\n" + 
				";";
		JDBCHelper mydb = JDBCHelper.getInstance();
		mydb.executeSql(sql.toString());
		
		sql = "";
		sql = "insert into dm_db.day_stock_in_agent_product\r\n" + 
				"select \r\n" + 
				"'ryan_dz' as sysid\r\n" + 
				", stock_date\r\n" + 
				",'flow_in'\r\n" + 
				",sum(a.amount) as contract_amount\r\n" + 
				",agent_id\r\n" + 
				",NULL AS agent_name\r\n" + 
				",product_id\r\n" + 
				",case when c.contract_id is not null then c.htfxlv1 else null end as hq_lv\r\n" + 
				"from (select amount,product_id,id as contract_id,agent_id,date(begin_time) as stock_date from dw_db.m_fact_contract_finance where sysid ='ryan_dz' and date(begin_time) = date_add(curdate(),interval -1 day)) a\r\n" + 
				"left join (select contract_id, htfxlv htfxlv1 from dw_db.hq_lv where flag=1)  c on a.contract_id = c.contract_id \r\n" + 
				"group by \r\n" + 
				" product_id\r\n" + 
				",agent_id\r\n" + 
				" ,case when c.contract_id is not null then c.htfxlv1 else null end \r\n" + 
				",stock_date\r\n" + 
				";";
		mydb.executeSql(sql.toString());

	}
	
	public void run() {
		ryandzFlowIn();
		ryanziguanFlowIn();
	}

}
