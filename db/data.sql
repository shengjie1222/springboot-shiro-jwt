INSERT INTO `sys_role`(`id`,`name`,`code`) VALUES (1,'超级管理员','admin');
INSERT INTO `sys_permission`(`id`,`name`,`code`) VALUES (1,'超级管理员','admin');
INSERT INTO `sys_role_mid_per`(`role_id`,`permission_id`) VALUES (1,1);
INSERT INTO `sys_user`(`username`,`password`,`salt`,`role_id`) VALUES ('admin','admin','ethereal1222',1);