alter table t_common_form add column trainning_count int;
alter table t_common_form add column practice_count int;
alter table t_common_sta add column trainning_count int;
alter table t_common_sta add column practice_count int;

update t_common_form set trainning_count=0 where trainning_count is null;
update t_common_form set trainning_count=1 where trainning_people_num>0;
update t_common_form set practice_count=0 where practice_count is null;
update t_common_form set practice_count=1 where practice_people_num>0;

update t_common_sta set trainning_count=0 where trainning_count is null;
update t_common_sta set practice_count=0 where practice_count is null;