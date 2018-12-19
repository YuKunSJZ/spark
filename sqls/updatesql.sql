drop table if exists tasks;
create table tasks
(
 id integer auto_increment
,taskid integer
,taskname varchar(1000)
,taskdesc varchar(1000)
,timeupdated datetime not null default current_timestamp
,timecreated datetime not null default current_timestamp
,primary key (id)
,key ix_1 (taskid)
)
;

create table taskparameters
(
 id integer auto_increment
,taskid integer
,parameterid integer
,parametername varchar(1000)
,parameterdesc varchar(1000)
,timeupdated datetime not null default current_timestamp
,timecreated datetime not null default current_timestamp
,primary key (id)
,key ix_1 (taskid)
)
;

ALTER TABLE `test_db`.`taskparameters` 
ADD CONSTRAINT `taskparameters_tasks`
  FOREIGN KEY (`taskid`)
  REFERENCES `test_db`.`tasks` (`taskid`)
  ON DELETE NO ACTION
  ON UPDATE NO ACTION;

alter table taskparameters add column parametervalue varchar(1000) after parameterid;
insert into tasks (taskid,taskname,taskdesc)
values(1,'测试','测试');
;

insert into taskparameters (taskid,parametername,parametervalue)
values (1,'idcode','120222197906042214')
;
insert into taskparameters (taskid,parametername,parametervalue)
values (1,'PARAM_START_DATE','2018-11-01')
;

insert into taskparameters (taskid,parametername,parametervalue)
values (1,'PARAM_END_DATE','2018-11-15')
;


insert into test_db.tables
values
 ('admin_company','公司','guquan_zhengshi','','1')
,('admin_dept','部门','guquan_zhengshi','','1')
,('admin_menu','菜单','guquan_zhengshi','','1')
,('admin_operation_log','系统操作日志表','guquan_zhengshi','','1')
,('admin_permissions','权限表','guquan_zhengshi','','1')
,('admin_position','职位','guquan_zhengshi','','1')
,('admin_positionlv','职级','guquan_zhengshi','','1')
,('admin_role_menu','角色和目录对应表','guquan_zhengshi','','1')
,('admin_role_permissions','角色_权限表','guquan_zhengshi','','1')
,('admin_role_users','用户与角色对应表','guquan_zhengshi','','1')
,('admin_slider','轮播图','guquan_zhengshi','','1')
,('admin_user_permissions','权限表','guquan_zhengshi','','1')
,('admin_users','用户表','guquan_zhengshi','','1')
,('cash','现金流表','guquan_zhengshi','','1')
,('cert','合格者认证表','guquan_zhengshi','','1')
,('cert_gr','合格者认证_个人认证表','guquan_zhengshi','','1')
,('cert_gz','合格者认证_风险告知书表','guquan_zhengshi','','1')
,('cert_jg','合格者认证_机构认证表','guquan_zhengshi','','1')
,('contract','签约信息','guquan_zhengshi','','1')
,('fund','基金列表','guquan_zhengshi','','1')
,('fund_link','基金属性表','guquan_zhengshi','','1')
,('fund_link_sys','基金_基金属性关联表','guquan_zhengshi','','1')
,('fund_pro','基金_项目对应表','guquan_zhengshi','','1')
,('investor','投资客户表','guquan_zhengshi','','1')
,('investor_bank','投资者银行账户表','guquan_zhengshi','','1')
,('investor_fund_sys','投资者_基金对应表','guquan_zhengshi','','1')
,('pro_company','项目标的列表','guquan_zhengshi','','1')
,('pro_company_employee','标的公司联系人表','guquan_zhengshi','','1')
,('pro_cost','项目费用表','guquan_zhengshi','','1')
,('pro_file','项目文档-文件','guquan_zhengshi','','1')
,('pro_file_tag','项目文件-标签','guquan_zhengshi','','1')
,('pro_file_time','项目文件-时效','guquan_zhengshi','','1')
,('pro_file_type','项目文档-节点','guquan_zhengshi','','1')
,('pro_file_view_log','项目文件-操作日志','guquan_zhengshi','','1')
,('pro_filetag','项目文件标签','guquan_zhengshi','','1')
,('pro_filetype_dept','项目文档-部门','guquan_zhengshi','','1')
,('pro_filetype_position','项目文档-职位','guquan_zhengshi','','1')
,('pro_filetype_positionlv','项目文档-职级','guquan_zhengshi','','1')
,('pro_filetype_role','项目文档-角色','guquan_zhengshi','','1')
,('pro_filetype_time','项目文档-时效','guquan_zhengshi','','1')
,('pro_filetype_user','项目文档-用户','guquan_zhengshi','','1')
,('pro_hetong','项目合同表','guquan_zhengshi','','1')
,('pro_log','项目日志表','guquan_zhengshi','','1')
,('pro_pay','项目划款表','guquan_zhengshi','','1')
,('pro_pay_process','项目划款类别表','guquan_zhengshi','','1')
,('pro_process','审批流程表','guquan_zhengshi','','1')
,('pro_process_type','审批流程类别表','guquan_zhengshi','','1')
,('pro_project','项目表','guquan_zhengshi','','1')
,('pro_track','项目跟踪表','guquan_zhengshi','','1')
,('pro_user_team','用户_团队关联表','guquan_zhengshi','','1')
,('sys_link','行业，领域，地区二级联动表','guquan_zhengshi','','1')
;