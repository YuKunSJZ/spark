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