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

	private void oldCode() {
		/**
		 * SELECT
	`c`.`gs_id` AS `gs_id`,
	`c`.`gs_name` AS `gs_name`,
	concat(
		round(
			(
				sum(`c`.`tp_capital_cost`) * 100
			),
			2
		),
		'%'
	) AS `tp_capital_cost`,
	concat(
		round(
			(
				sum(`c`.`lm_capital_cost`) * 100
			),
			2
		),
		'%'
	) AS `lm_capital_cost`,
	concat(
		round(
			(
				sum(`c`.`ty_capital_cost`) * 100
			),
			2
		),
		'%'
	) AS `ty_capital_cost`
FROM
	(
		SELECT
			substring_index(`a`.`sysid`, '_', 2) AS `gs_id`,
			(
				CASE substring_index(`a`.`sysid`, '_', 2)
				WHEN 'ryan_dz' THEN
					'大众财富'
				WHEN 'ryan_jtcw' THEN
					'集团理财'
				WHEN 'ryan_rongzi' THEN
					'资产管理'
				ELSE
					'方泽金服'
				END
			) AS `gs_name`,
			0 AS `tp_capital_cost`,
			(
				sum(
					(
						(
							(
								(`b`.`profit_rate` / 100) * `a`.`contract_amount`
							) * ifnull(if(b.term<12,b.term,12), 1)
						) / 12
					)
				) / sum(
					(
						(
							`a`.`contract_amount` * ifnull(if(b.term<12,b.term,12), 1)
						) / 12
					)
				)
			) AS `lm_capital_cost`,
			0 AS `ty_capital_cost`
		FROM
			(
				(
					(
						SELECT
							`dw_db`.`m_fact_inmoney_finance`.`sysid` AS `sysid`,
							`dw_db`.`m_fact_inmoney_finance`.`entertime` AS `entertime`,
							`dw_db`.`m_fact_inmoney_finance`.`product_id` AS `product_id`,
							`dw_db`.`m_fact_inmoney_finance`.`contract_amount` AS `contract_amount`
						FROM
							`dw_db`.`m_fact_inmoney_finance`
						WHERE
							(
								date_format(
									`dw_db`.`m_fact_inmoney_finance`.`entertime`,
									'%Y-%m'
								) = date_format(
									(
										(curdate() - INTERVAL 1 DAY) - INTERVAL 1 MONTH
									),
									'%Y-%m'
								)
							)
					)
				) `a`
				LEFT JOIN (
					SELECT
						`dw_db`.`m_dim_product_finance`.`sysid` AS `sysid`,
						`dw_db`.`m_dim_product_finance`.`id` AS `id`,
						(
							CASE
							WHEN (
								`dw_db`.`m_dim_product_finance`.`if_current` = '1'
							) THEN
								1
							WHEN (
								`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xx_hq'
							) THEN
								1
							WHEN (
								`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xs_hq'
							) THEN
								1
							ELSE
								`dw_db`.`m_dim_product_finance`.`limit_time`
							END
						) AS `term`,
						`dw_db`.`m_dim_product_finance`.`profit_rate` AS `profit_rate`
					FROM
						`dw_db`.`m_dim_product_finance`
				) `b` ON (
					(
						(`a`.`product_id` = `b`.`id`)
						AND (`a`.`sysid` = `b`.`sysid`)
					)
				)
			)
		GROUP BY
			`gs_id`,
			`gs_name`
		UNION ALL
			SELECT
				substring_index(`a`.`sysid`, '_', 2) AS `gs_id`,
				(
					CASE substring_index(`a`.`sysid`, '_', 2)
					WHEN 'ryan_dz' THEN
						'大众财富'
					WHEN 'ryan_jtcw' THEN
						'集团理财'
					WHEN 'ryan_rongzi' THEN
					'资产管理'
					ELSE
						'方泽金服'
					END
				) AS `gs_name`,
				(
					sum(
						(
							(
								(
									(`b`.`profit_rate` / 100) * `a`.`contract_amount`
								) * ifnull(if(b.term<12,b.term,12), 1)
							) / 12
						)
					) / sum(
						(
							(
								`a`.`contract_amount` * ifnull(if(b.term<12,b.term,12), 1)
							) / 12
						)
					)
				) AS `tp_capital_cost`,
				0 AS `lm_capital_cost`,
				0 AS `ty_capital_cost`
			FROM
				(
					(
						(
							SELECT
								`dw_db`.`m_fact_inmoney_finance`.`sysid` AS `sysid`,
								`dw_db`.`m_fact_inmoney_finance`.`entertime` AS `entertime`,
								`dw_db`.`m_fact_inmoney_finance`.`product_id` AS `product_id`,
								`dw_db`.`m_fact_inmoney_finance`.`contract_amount` AS `contract_amount`
							FROM
								`dw_db`.`m_fact_inmoney_finance`
							WHERE
								(
									date_format(
										`dw_db`.`m_fact_inmoney_finance`.`entertime`,
										'%Y-%m'
									) = date_format(
										(curdate() - INTERVAL 1 DAY),
										'%Y-%m'
									)
								)
						)
					) `a`
					LEFT JOIN (
						SELECT
							`dw_db`.`m_dim_product_finance`.`sysid` AS `sysid`,
							`dw_db`.`m_dim_product_finance`.`id` AS `id`,
							(
								CASE
								WHEN (
									`dw_db`.`m_dim_product_finance`.`if_current` = '1'
								) THEN
									1
								WHEN (
									`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xx_hq'
								) THEN
									1
								WHEN (
									`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xs_hq'
								) THEN
									1
								ELSE
									`dw_db`.`m_dim_product_finance`.`limit_time`
								END
							) AS `term`,
							`dw_db`.`m_dim_product_finance`.`profit_rate` AS `profit_rate`
						FROM
							`dw_db`.`m_dim_product_finance`
					) `b` ON (
						(
							(`a`.`product_id` = `b`.`id`)
							AND (`a`.`sysid` = `b`.`sysid`)
						)
					)
				)
			GROUP BY
				`gs_id`,
				`gs_name`
			UNION ALL
				SELECT
					substring_index(`a`.`sysid`, '_', 2) AS `gs_id`,
					(
						CASE substring_index(`a`.`sysid`, '_', 2)
						WHEN 'ryan_dz' THEN
							'大众财富'
						WHEN 'ryan_jtcw' THEN
							'集团理财'
						WHEN 'ryan_rongzi' THEN
							'资产管理'
						ELSE
							'方泽金服'
						END
					) AS `gs_name`,
					0 AS `tp_capital_cost`,
					0 AS `lm_capital_cost`,
					(
						sum(
							(
								(
									(
										(`b`.`profit_rate` / 100) * `a`.`contract_amount`
									) * ifnull(if(b.term<12,b.term,12), 1)
								) / 12
							)
						) / sum(
							(
								(
									`a`.`contract_amount` * ifnull(if(b.term<12,b.term,12), 1)
								) / 12
							)
						)
					) AS `ty_capital_cost`
				FROM
					(
						(
							(
								SELECT
									`dw_db`.`m_fact_inmoney_finance`.`sysid` AS `sysid`,
									`dw_db`.`m_fact_inmoney_finance`.`entertime` AS `entertime`,
									`dw_db`.`m_fact_inmoney_finance`.`product_id` AS `product_id`,
									`dw_db`.`m_fact_inmoney_finance`.`contract_amount` AS `contract_amount`
								FROM
									`dw_db`.`m_fact_inmoney_finance`
								WHERE
									(
										date_format(
											`dw_db`.`m_fact_inmoney_finance`.`entertime`,
											'%Y'
										) = date_format(
											(curdate() - INTERVAL 1 DAY),
											'%Y'
										)
									)
							)
						) `a`
						LEFT JOIN (
							SELECT
								`dw_db`.`m_dim_product_finance`.`sysid` AS `sysid`,
								`dw_db`.`m_dim_product_finance`.`id` AS `id`,
								(
									CASE
									WHEN (
										`dw_db`.`m_dim_product_finance`.`if_current` = '1'
									) THEN
										1
									WHEN (
										`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xx_hq'
									) THEN
										1
									WHEN (
										`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xs_hq'
									) THEN
										1
									ELSE
										`dw_db`.`m_dim_product_finance`.`limit_time`
									END
								) AS `term`,
								`dw_db`.`m_dim_product_finance`.`profit_rate` AS `profit_rate`
							FROM
								`dw_db`.`m_dim_product_finance`
						) `b` ON (
							(
								(`a`.`product_id` = `b`.`id`)
								AND (`a`.`sysid` = `b`.`sysid`)
							)
						)
					)
				GROUP BY
					`gs_id`,
					`gs_name`
				UNION ALL
					SELECT
						'total' AS `gs_id`,
						'合计' AS `gs_name`,
						0 AS `tp_capital_cost`,
						(
							sum(
								(
									(
										(
											(`b`.`profit_rate` / 100) * `a`.`contract_amount`
										) * ifnull(if(b.term<12,b.term,12), 1)
									) / 12
								)
							) / sum(
								(
									(
										`a`.`contract_amount` * ifnull(if(b.term<12,b.term,12), 1)
									) / 12
								)
							)
						) AS `lm_capital_cost`,
						0 AS `ty_capital_cost`
					FROM
						(
							(
								(
									SELECT
										`dw_db`.`m_fact_inmoney_finance`.`sysid` AS `sysid`,
										`dw_db`.`m_fact_inmoney_finance`.`entertime` AS `entertime`,
										`dw_db`.`m_fact_inmoney_finance`.`product_id` AS `product_id`,
										`dw_db`.`m_fact_inmoney_finance`.`contract_amount` AS `contract_amount`
									FROM
										`dw_db`.`m_fact_inmoney_finance`
									WHERE
										(
											date_format(
												`dw_db`.`m_fact_inmoney_finance`.`entertime`,
												'%Y-%m'
											) = date_format(
												(
													(curdate() - INTERVAL 1 DAY) - INTERVAL 1 MONTH
												),
												'%Y-%m'
											)
										)
								)
							) `a`
							LEFT JOIN (
								SELECT
									`dw_db`.`m_dim_product_finance`.`sysid` AS `sysid`,
									`dw_db`.`m_dim_product_finance`.`id` AS `id`,
									(
										CASE
										WHEN (
											`dw_db`.`m_dim_product_finance`.`if_current` = '1'
										) THEN
											1
										WHEN (
											`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xx_hq'
										) THEN
											1
										WHEN (
											`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xs_hq'
										) THEN
											1
										ELSE
											`dw_db`.`m_dim_product_finance`.`limit_time`
										END
									) AS `term`,
									`dw_db`.`m_dim_product_finance`.`profit_rate` AS `profit_rate`
								FROM
									`dw_db`.`m_dim_product_finance`
							) `b` ON (
								(
									(`a`.`product_id` = `b`.`id`)
									AND (`a`.`sysid` = `b`.`sysid`)
								)
							)
						)
					UNION ALL
						SELECT
							'total' AS `gs_id`,
							'合计' AS `gs_name`,
							(
								sum(
									(
										(
											(
												(`b`.`profit_rate` / 100) * `a`.`contract_amount`
											) * ifnull(if(b.term<12,b.term,12), 1)
										) / 12
									)
								) / sum(
									(
										(
											`a`.`contract_amount` * ifnull(if(b.term<12,b.term,12), 1)
										) / 12
									)
								)
							) AS `tp_capital_cost`,
							0 AS `lm_capital_cost`,
							0 AS `ty_capital_cost`
						FROM
							(
								(
									(
										SELECT
											`dw_db`.`m_fact_inmoney_finance`.`sysid` AS `sysid`,
											`dw_db`.`m_fact_inmoney_finance`.`entertime` AS `entertime`,
											`dw_db`.`m_fact_inmoney_finance`.`product_id` AS `product_id`,
											`dw_db`.`m_fact_inmoney_finance`.`contract_amount` AS `contract_amount`
										FROM
											`dw_db`.`m_fact_inmoney_finance`
										WHERE
											(
												date_format(
													`dw_db`.`m_fact_inmoney_finance`.`entertime`,
													'%Y-%m'
												) = date_format(
													(curdate() - INTERVAL 1 DAY),
													'%Y-%m'
												)
											)
									)
								) `a`
								LEFT JOIN (
									SELECT
										`dw_db`.`m_dim_product_finance`.`sysid` AS `sysid`,
										`dw_db`.`m_dim_product_finance`.`id` AS `id`,
										(
											CASE
											WHEN (
												`dw_db`.`m_dim_product_finance`.`if_current` = '1'
											) THEN
												1
											WHEN (
												`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xx_hq'
											) THEN
												1
											WHEN (
												`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xs_hq'
											) THEN
												1
											ELSE
												`dw_db`.`m_dim_product_finance`.`limit_time`
											END
										) AS `term`,
										`dw_db`.`m_dim_product_finance`.`profit_rate` AS `profit_rate`
									FROM
										`dw_db`.`m_dim_product_finance`
								) `b` ON (
									(
										(`a`.`product_id` = `b`.`id`)
										AND (`a`.`sysid` = `b`.`sysid`)
									)
								)
							)
						UNION ALL
							SELECT
								'total' AS `gs_id`,
								'合计' AS `gs_name`,
								0 AS `tp_capital_cost`,
								0 AS `lm_capital_cost`,
								(
									sum(
										(
											(
												(
													(`b`.`profit_rate` / 100) * `a`.`contract_amount`
												) * ifnull(if(b.term<12,b.term,12), 1)
											) / 12
										)
									) / sum(
										(
											(
												`a`.`contract_amount` * ifnull(if(b.term<12,b.term,12), 1)
											) / 12
										)
									)
								) AS `ty_capital_cost`
							FROM
								(
									(
										(
											SELECT
												`dw_db`.`m_fact_inmoney_finance`.`sysid` AS `sysid`,
												`dw_db`.`m_fact_inmoney_finance`.`entertime` AS `entertime`,
												`dw_db`.`m_fact_inmoney_finance`.`product_id` AS `product_id`,
												`dw_db`.`m_fact_inmoney_finance`.`contract_amount` AS `contract_amount`
											FROM
												`dw_db`.`m_fact_inmoney_finance`
											WHERE
												(
													date_format(
														`dw_db`.`m_fact_inmoney_finance`.`entertime`,
														'%Y'
													) = date_format(
														(curdate() - INTERVAL 1 DAY),
														'%Y'
													)
												)
										)
									) `a`
									LEFT JOIN (
										SELECT
											`dw_db`.`m_dim_product_finance`.`sysid` AS `sysid`,
											`dw_db`.`m_dim_product_finance`.`id` AS `id`,
											(
												CASE
												WHEN (
													`dw_db`.`m_dim_product_finance`.`if_current` = '1'
												) THEN
													1
												WHEN (
													`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xx_hq'
												) THEN
													1
												WHEN (
													`dw_db`.`m_dim_product_finance`.`sysid` = 'ryan_fzlc_xs_hq'
												) THEN
													1
												ELSE
													`dw_db`.`m_dim_product_finance`.`limit_time`
												END
											) AS `term`,
											`dw_db`.`m_dim_product_finance`.`profit_rate` AS `profit_rate`
										FROM
											`dw_db`.`m_dim_product_finance`
									) `b` ON (
										(
											(`a`.`product_id` = `b`.`id`)
											AND (`a`.`sysid` = `b`.`sysid`)
										)
									)
								)
	) `c`
GROUP BY
	`c`.`gs_id`,
	`c`.`gs_name`
		 * 
		 * 
		 */
	}
	

}
