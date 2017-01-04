
INSERT INTO `t_department` (department_id,parent_id,level,is_delete,department_name) VALUES (101,NULL,0,78,'根节点'),(102,101,1,78,'全国'),(103,102,2,78,'成都铁路局'),(3,103,3,78,'重庆车站');

insert into t_data_dict values ('RY000','根节点角色','RY');
insert into t_data_dict values ('RY001','全国类角色','RY');
insert into t_data_dict values ('RY002','路局类角色','RY');
insert into t_data_dict values ('RY003','车站类角色','RY');
insert into t_data_dict values ('RY004','车间类角色','RY');

insert into t_role (role_id,role_type_id,role_name) values (1,'RY000','系统管理员');
insert into t_role (role_id,role_type_id,role_name) values (2,'RY001','全国管理员');
insert into t_role (role_id,role_type_id,role_name) values (3,'RY001','全国查看员');
insert into t_role (role_id,role_type_id,role_name) values (4,'RY002','路局管理员');
insert into t_role (role_id,role_type_id,role_name) values (5,'RY002','路局查看员');
insert into t_role (role_id,role_type_id,role_name) values (6,'RY003','车站管理员');
insert into t_role (role_id,role_type_id,role_name) values (7,'RY003','车站查看员');
insert into t_role (role_id,role_type_id,role_name) values (8,'RY004','车间管理员');
insert into t_role (role_id,role_type_id,role_name) values (9,'RY004','车间查看员');

insert into t_user (user_id,is_delete,department_id,role_id,username,password) values (1,78,101,1,'admin','4280d89a5a03f812751f504cc10ee8a5');
insert into t_user (user_id,is_delete,department_id,role_id,username,password) values (2,78,3,6,'cqczgly','4280d89a5a03f812751f504cc10ee8a5');
insert into t_user (user_id,is_delete,department_id,role_id,username,password) values (3,78,3,7,'cqcz','4280d89a5a03f812751f504cc10ee8a5');

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (1,null,'根资源','',1,1,1);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (5,1,'车间反恐报表','',1,1,2);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (2,1,'车站反恐报表','',2,1,2);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (3,1,'基础数据管理','',3,1,2);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (4,1,'系统功能','',4,1,2);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (51,5,'未上报-反恐报表','report/securityForm/departmentSecurityFormFunc.do?command=listDepartmentNotConfirmSecurityForm','images/menu/security_form_manage.png',1,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (52,5,'已上报-反恐报表','report/securityForm/departmentSecurityFormFunc.do?command=listDepartmentConfirmSecurityForm','images/menu/security_form_manage.png',1,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (53,5,'已审核-反恐报表','report/securityForm/departmentSecurityFormFunc.do?command=listDepartmentVerifySecurityForm','images/menu/security_form_manage.png',1,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (21,2,'已导入-反恐报表','report/securityForm/stationSecurityFormFunc.do?command=listStationImportSecurityForm&startDateString=&endDateString=&departmentId=-1','images/menu/security_form_import.png',1,1,3);
insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (22,2,'未上报-反恐报表','report/securityForm/stationSecurityFormFunc.do?command=listStationNotConfirmSecurityForm&startDateString=&endDateString=&departmentId=-1','images/menu/security_form_manage.png',2,1,3);
insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (23,2,'已上报-反恐报表','report/securityForm/stationSecurityFormFunc.do?command=listStationConfirmSecurityForm&startDateString=&endDateString=&departmentId=-1','images/menu/security_form_manage.png',3,1,3);
insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (24,2,'已审核-反恐报表','report/securityForm/stationSecurityFormFunc.do?command=listStationVerifySecurityForm&startDateString=&endDateString=&departmentId=-1','images/menu/security_form_manage.png',4,1,3);
insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (25,2,'自定义统计维护','sta/staPeriodSecurity/customStaPeriodSecurityFunc.do?command=listCustomStaPeriodInfo&startDateString=&endDateString=','images/menu/statistics.png',5,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (31,3,'部门维护','basedata/department/departmentFunc.do?command=listDepartment','images/menu/department.png',1,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (32,3,'角色维护','basedata/role/roleFunc.do?command=listRole','images/menu/role.png',2,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (33,3,'菜单维护','basedata/resource/resourceFunc.do?command=listResource&resourceType=1&parentResourceId=1','images/menu/menu.png',3,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (34,3,'Action请求访问资源维护','basedata/resource/resourceFunc.do?command=listResource&resourceType=2&parentResourceId=101','images/menu/resource_be_requested.png',4,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (41,4,'修改密码','system/password/passwordFunc.do?command=updatePasswordPrepare','images/menu/password_manager.png',1,1,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,picture_url_path,order_number,resource_type,resource_level) values (42,4,'系统配置','system/systemConfig/systemConfigFunc.do?command=viewSystemConfig','images/menu/system_config.png',2,1,3);


insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (101,null,'根资源','',1,2,1);
insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (102,101,'反恐报表管理','',1,2,2);
insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (103,101,'基础数据管理','',2,2,2);
insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (104,101,'系统功能','',3,2,2);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (121,102,'车间反恐报表权限','/report/securityForm/departmentSecurityFormFunc.do',1,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (122,102,'车站反恐报表权限','/report/securityForm/stationSecurityFormFunc.do',2,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (123,102,'反恐统计权限','/sta/staPeriodSecurity/customStaPeriodSecurityFunc.do',3,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (131,103,'部门权限','/basedata/department/departmentFunc.do',1,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (132,103,'用户权限','/basedata/user/userFunc.do',2,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (133,103,'角色权限','/basedata/role/roleFunc.do',3,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (134,103,'资源权限','/basedata/resource/resourceFunc.do',4,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (141,104,'修改密码权限','/system/password/passwordFunc.do',1,2,3);

insert into t_resource (resource_id,parent_resource_id,resource_name,resource_url_path,order_number,resource_type,resource_level) values (142,104,'系统配置权限','/system/systemConfig/systemConfigFunc.do',2,2,3);


INSERT INTO `t_role_resource_authority` VALUES (1,1,2,0),(2,1,21,0),(3,1,22,0),(4,1,23,0),(5,1,24,0),(6,1,25,0),(7,1,3,16),(8,1,31,16),(9,1,32,16),(10,1,4,16),(11,1,41,16),(12,1,121,0),(13,1,122,0),(14,1,123,0),(15,1,131,47),(16,1,132,47),(17,1,133,47),(18,1,141,4),(19,6,2,16),(20,6,21,16),(21,6,22,0),(22,6,23,0),(23,6,24,16),(24,6,25,16),(25,6,3,16),(26,6,31,16),(27,6,32,0),(28,6,4,16),(29,6,41,16),(30,6,121,0),(31,6,122,46),(32,6,123,47),(33,6,131,47),(34,6,132,47),(35,6,133,0),(36,6,141,4),(37,7,2,16),(38,7,21,0),(39,7,22,0),(40,7,23,0),(41,7,24,16),(42,7,25,16),(43,7,3,16),(44,7,31,16),(45,7,32,0),(46,7,4,16),(47,7,41,16),(48,7,121,0),(49,7,122,2),(50,7,123,47),(51,7,131,34),(52,7,132,0),(53,7,133,0),(54,7,141,4),(55,1,33,16),(56,1,34,16),(57,1,134,47),(58,1,42,16),(59,1,142,38),(60,7,134,0),(61,7,142,0),(62,8,5,16),(63,8,51,16),(64,8,52,16),(65,8,53,16),(66,8,2,0),(67,8,21,0),(68,8,22,0),(69,8,23,0),(70,8,24,0),(71,8,25,0),(72,8,3,0),(73,8,31,0),(74,8,32,0),(75,8,33,0),(76,8,34,0),(77,8,4,16),(78,8,41,16),(79,8,42,0),(80,8,121,47),(81,8,122,0),(82,8,123,0),(83,8,131,0),(84,8,132,0),(85,8,133,0),(86,8,134,0),(87,8,141,4),(88,8,142,0);