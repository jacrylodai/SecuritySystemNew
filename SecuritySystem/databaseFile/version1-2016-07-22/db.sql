/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2016/7/22 14:48:21                           */
/*==============================================================*/


drop table if exists t_common_form;

drop table if exists t_common_sta;

drop index index_data_dict_category on t_data_dict;

drop table if exists t_data_dict;

drop index index_parent_id on t_department;

drop table if exists t_department;

drop table if exists t_keyun_form;

drop table if exists t_keyun_sta;

drop index index_resource_url_path on t_resource;

drop table if exists t_resource;

drop table if exists t_role;

drop index index_resource_id on t_role_resource_authority;

drop table if exists t_role_resource_authority;

drop index index_report_time on t_security_form;

drop index index_end_date on t_security_form;

drop index index_start_date on t_security_form;

drop index index_department_id on t_security_form;

drop table if exists t_security_form;

drop index index_sta_time on t_sta_period_info;

drop index index_creator_department_id on t_sta_period_info;

drop table if exists t_sta_period_info;

drop index index_sta_period_info_id on t_sta_period_security;

drop table if exists t_sta_period_security;

drop table if exists t_user;

/*==============================================================*/
/* Table: t_common_form                                         */
/*==============================================================*/
create table t_common_form
(
   common_form_id       bigint not null auto_increment,
   security_machine_check_num_string varchar(400),
   security_machine_trouble_num int,
   check_dangerous_object_num_string varchar(300),
   zhanqu_check_num     int,
   cctv_check_num       int,
   cctv_trouble_num     int,
   equipment_check_num  int,
   equipment_trouble_num int,
   militiaman_check_num int,
   trainning_people_num int,
   practice_people_num  int,
   other_work_info      varchar(1000),
   primary key (common_form_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Table: t_common_sta                                          */
/*==============================================================*/
create table t_common_sta
(
   common_sta_id        bigint not null auto_increment,
   security_machine_check_num_string varchar(400),
   all_security_machine_check_num bigint,
   security_machine_trouble_num int,
   check_dangerous_object_num_string varchar(300),
   zhanqu_check_num     int,
   cctv_check_num       int,
   cctv_trouble_num     int,
   equipment_check_num  int,
   equipment_trouble_num int,
   militiaman_check_num int,
   trainning_people_num int,
   practice_people_num  int,
   primary key (common_sta_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Table: t_data_dict                                           */
/*==============================================================*/
create table t_data_dict
(
   data_dict_id         char(5) not null,
   data_dict_name       varchar(30),
   data_dict_category   char(2),
   primary key (data_dict_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Index: index_data_dict_category                              */
/*==============================================================*/
create index index_data_dict_category on t_data_dict
(
   data_dict_category
);

/*==============================================================*/
/* Table: t_department                                          */
/*==============================================================*/
create table t_department
(
   department_id        bigint not null auto_increment,
   parent_id            bigint,
   level                smallint,
   is_delete            int,
   department_name      varchar(50),
   primary key (department_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Index: index_parent_id                                       */
/*==============================================================*/
create index index_parent_id on t_department
(
   parent_id
);

/*==============================================================*/
/* Table: t_keyun_form                                          */
/*==============================================================*/
create table t_keyun_form
(
   keyun_form_id        bigint not null auto_increment,
   period_number_of_passengers bigint,
   square_special_people_num int,
   square_check_people_num int,
   square_dangerous_object_num_string varchar(300),
   special_code_people_num_string varchar(230),
   yanzheng_using_fake_paper_num int,
   arrest_people_num    int,
   fine_people_num      int,
   study_people_num     int,
   sale_using_fake_paper_num int,
   sale_special_people_num int,
   wating_hall_check_people_num int,
   waiting_hall_xianza_people_num int,
   jianpiao_weisui_people_num int,
   special_train_identity_wrong_people_num int,
   special_train_identity_wrong_info varchar(2000),
   primary key (keyun_form_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Table: t_keyun_sta                                           */
/*==============================================================*/
create table t_keyun_sta
(
   keyun_sta_id         bigint not null auto_increment,
   period_number_of_passengers bigint,
   square_special_people_num int,
   square_check_people_num bigint,
   square_dangerous_object_num_string varchar(300),
   special_code_people_num_string varchar(230),
   yanzheng_using_fake_paper_num int,
   arrest_people_num    int,
   fine_people_num      int,
   study_people_num     int,
   sale_using_fake_paper_num int,
   sale_special_people_num int,
   wating_hall_check_people_num int,
   waiting_hall_xianza_people_num int,
   jianpiao_weisui_people_num int,
   special_train_identity_wrong_people_num int,
   primary key (keyun_sta_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Table: t_resource                                            */
/*==============================================================*/
create table t_resource
(
   resource_id          bigint not null auto_increment,
   parent_resource_id   bigint,
   resource_name        varchar(50),
   resource_url_path    varchar(150),
   picture_url_path     varchar(150),
   order_number         int,
   resource_type        int comment '1-menu,2-actionResource',
   resource_level       int comment '1-root,2-top_resource,3-sub_resource',
   primary key (resource_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Index: index_resource_url_path                               */
/*==============================================================*/
create index index_resource_url_path on t_resource
(
   resource_url_path
);

/*==============================================================*/
/* Table: t_role                                                */
/*==============================================================*/
create table t_role
(
   role_id              bigint not null auto_increment,
   role_type_id         char(5),
   role_name            varchar(50),
   primary key (role_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Table: t_role_resource_authority                             */
/*==============================================================*/
create table t_role_resource_authority
(
   role_resource_authority_id bigint not null auto_increment,
   role_id              bigint,
   resource_id          bigint,
   authority_code       int,
   primary key (role_resource_authority_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Index: index_resource_id                                     */
/*==============================================================*/
create index index_resource_id on t_role_resource_authority
(
   resource_id
);

/*==============================================================*/
/* Table: t_security_form                                       */
/*==============================================================*/
create table t_security_form
(
   security_form_id     bigint not null auto_increment,
   department_id        bigint,
   report_user_id       bigint,
   keyun_form_id        bigint,
   common_form_id       bigint,
   station_id           bigint,
   state                int comment 'N-未确认.C-已确认.V-已审核',
   start_date           char(25),
   end_date             char(25),
   last_days            smallint,
   report_time          char(25),
   primary key (security_form_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Index: index_department_id                                   */
/*==============================================================*/
create index index_department_id on t_security_form
(
   department_id
);

/*==============================================================*/
/* Index: index_start_date                                      */
/*==============================================================*/
create index index_start_date on t_security_form
(
   start_date
);

/*==============================================================*/
/* Index: index_end_date                                        */
/*==============================================================*/
create index index_end_date on t_security_form
(
   end_date
);

/*==============================================================*/
/* Index: index_report_time                                     */
/*==============================================================*/
create index index_report_time on t_security_form
(
   report_time
);

/*==============================================================*/
/* Table: t_sta_period_info                                     */
/*==============================================================*/
create table t_sta_period_info
(
   sta_period_info_id   bigint not null auto_increment,
   sta_user_id          bigint,
   creator_department_id bigint,
   sta_type             int comment 'W-周统计类.M-月统计类.C-自定义统计',
   start_date           char(25),
   end_date             char(25),
   last_days            int,
   sta_time             char(25),
   primary key (sta_period_info_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Index: index_creator_department_id                           */
/*==============================================================*/
create index index_creator_department_id on t_sta_period_info
(
   creator_department_id
);

/*==============================================================*/
/* Index: index_sta_time                                        */
/*==============================================================*/
create index index_sta_time on t_sta_period_info
(
   sta_time
);

/*==============================================================*/
/* Table: t_sta_period_security                                 */
/*==============================================================*/
create table t_sta_period_security
(
   sta_period_security_id bigint not null auto_increment,
   sta_department_id    bigint,
   sta_period_info_id   bigint,
   common_sta_id        bigint,
   keyun_sta_id         bigint,
   estimate_sta_days    int,
   actual_sta_days      int,
   absent_days_string   varchar(5000),
   primary key (sta_period_security_id)
)
ENGINE=InnoDB;

/*==============================================================*/
/* Index: index_sta_period_info_id                              */
/*==============================================================*/
create index index_sta_period_info_id on t_sta_period_security
(
   sta_period_info_id
);

/*==============================================================*/
/* Table: t_user                                                */
/*==============================================================*/
create table t_user
(
   user_id              bigint not null auto_increment,
   is_delete            int,
   department_id        bigint,
   role_id              bigint,
   username             varchar(30),
   password             varchar(40),
   contact_people       varchar(30),
   mobile_phone         varchar(30),
   office_phone         varchar(30),
   primary key (user_id)
)
ENGINE=InnoDB;

alter table t_department add constraint FK_Reference_5 foreign key (parent_id)
      references t_department (department_id) on delete restrict on update restrict;

alter table t_resource add constraint FK_Reference_15 foreign key (parent_resource_id)
      references t_resource (resource_id) on delete restrict on update restrict;

alter table t_role add constraint FK_Reference_21 foreign key (role_type_id)
      references t_data_dict (data_dict_id) on delete restrict on update restrict;

alter table t_role_resource_authority add constraint FK_Reference_19 foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

alter table t_role_resource_authority add constraint FK_Reference_20 foreign key (resource_id)
      references t_resource (resource_id) on delete restrict on update restrict;

alter table t_security_form add constraint FK_Reference_11 foreign key (common_form_id)
      references t_common_form (common_form_id) on delete restrict on update restrict;

alter table t_security_form add constraint FK_Reference_14 foreign key (station_id)
      references t_department (department_id) on delete restrict on update restrict;

alter table t_security_form add constraint FK_Reference_7 foreign key (keyun_form_id)
      references t_keyun_form (keyun_form_id) on delete restrict on update restrict;

alter table t_security_form add constraint FK_Reference_8 foreign key (department_id)
      references t_department (department_id) on delete restrict on update restrict;

alter table t_security_form add constraint FK_Reference_9 foreign key (report_user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_sta_period_info add constraint FK_Reference_16 foreign key (sta_user_id)
      references t_user (user_id) on delete restrict on update restrict;

alter table t_sta_period_info add constraint FK_Reference_25 foreign key (creator_department_id)
      references t_department (department_id) on delete restrict on update restrict;

alter table t_sta_period_security add constraint FK_Reference_18 foreign key (sta_period_info_id)
      references t_sta_period_info (sta_period_info_id) on delete restrict on update restrict;

alter table t_sta_period_security add constraint FK_Reference_22 foreign key (common_sta_id)
      references t_common_sta (common_sta_id) on delete restrict on update restrict;

alter table t_sta_period_security add constraint FK_Reference_23 foreign key (keyun_sta_id)
      references t_keyun_sta (keyun_sta_id) on delete restrict on update restrict;

alter table t_sta_period_security add constraint FK_Reference_24 foreign key (sta_department_id)
      references t_department (department_id) on delete restrict on update restrict;

alter table t_user add constraint FK_Reference_10 foreign key (department_id)
      references t_department (department_id) on delete restrict on update restrict;

alter table t_user add constraint FK_Reference_17 foreign key (role_id)
      references t_role (role_id) on delete restrict on update restrict;

