create table t_user(
	id int not null auto_increment,
	username varchar(20) not null,
	loginacct varchar(50) not null,
	userpswd varchar(20) not null,
	email varchar(100),
	createtime varchar(50),
	primary key(id)
);

insert into t_user(username,loginacct,userpswd,email,createtime)
	values('admin','admin','admin','admin@admin.com','2020-05-04 00:00:00');

create table t_user_role(
	urid int not null auto_increment,
	userid int not null,
	roleid int not null,
	primary key(urid)
);

insert into t_user_role(userid,roleid) values(1,1);

create table t_role(
	id int not null auto_increment,
	name varchar(50) not null,
	primary key(id)
);

insert into t_role(id,name) values(1,'管理员');

create table t_role_permission(
	rpid int not null auto_increment,
	roleid int not null,
	permissionid int not null,
	primary key(rpid)
);

insert into t_role_permission(roleid,permissionid) values(1,1);
insert into t_role_permission(roleid,permissionid) values(1,2);
insert into t_role_permission(roleid,permissionid) values(1,3);
insert into t_role_permission(roleid,permissionid) values(1,4);
insert into t_role_permission(roleid,permissionid) values(1,5);
insert into t_role_permission(roleid,permissionid) values(1,6);

create table t_permission(
	id int not null auto_increment,
	name varchar(50) default null,
	url varchar(256) default null,
	pid int not null,
	open boolean not null default 1,
	checked boolean not null default 0,
	icon varchar(100) default null,
	primary key(id)	
);

insert into t_permission(id,name,url,pid) values(1,'系统菜单',null,0);
insert into t_permission(id,name,url,pid,icon) values(2,'控制面板','/main',1,'glyphicon glyphicon-dashboard');
insert into t_permission(id,name,url,pid,icon) values(3,'RBAC管理',null,1,'glyphicon glyphicon glyphicon-tasks');


insert into t_permission(id,name,url,pid,icon) values(4,'用户管理','/user/index',3,'glyphicon glyphicon-user');
insert into t_permission(id,name,url,pid,icon) values(5,'角色管理','/role/index',3,'glyphicon glyphicon-king');
insert into t_permission(id,name,url,pid,icon) values(6,'权限管理','/permission/index',3,'glyphicon glyphicon-lock');