insert into form(name, audit, description, entity_id) values ('Cadastro de Materiais', true, 'Cadastro e manutenção de materiais',6);
insert into form(name, audit, description, entity_id) values ('Cadastro de Superficie e absortancia', true, 'Cadastro e manutenção de superficies e absortancia',7);
insert into form(name, audit, description, entity_id) values ('Cadastro de Permissões', true, 'Cadastro e manutenção de permissões',8);

insert into permission (role, description, form_id) values('menuUser', 'Menu de usuários', 1);
insert into permission (role, description, form_id) values('menuGroup', 'Menu  Grupo de usuários', 2);
insert into permission (role, description, form_id) values('menuAudit', 'Menu de Auditoria', 3);
insert into permission (role, description, form_id) values('menuForm', 'Menu de Formulários do sistema', 4);
insert into permission (role, description, form_id) values('menuMaterial', 'Menu de materiais', 5);
insert into permission (role, description, form_id) values('menuMaterialAbsortancia', 'Menu de absortancia dos materiais', 6);
insert into permission (role, description, form_id) values('menuPermissions', 'Menu de permissões', 7);

insert into permission (role, description, form_id) values('btnNewUser', 'Cadastrar novo usuário', 1);
insert into permission (role, description, form_id) values('btnEditUser', 'Editar usuário', 1);
insert into permission (role, description, form_id) values('btnDeleteUser', 'Desativar usuário', 1);
insert into permission (role, description, form_id) values('btnSave', 'Salvar ', 1);
insert into permission (role, description, form_id) values('btnCancel', 'Cancelar', 1);
insert into permission (role, description, form_id) values('btnAddGroup', 'Adicionar grupo ao usuário', 1);

insert into permission (role, description, form_id) values('btnNewGroup', 'Cadastrar novo grupo de usuário', 2);
insert into permission (role, description, form_id) values('btnEditGroup', 'Editar grupo', 2);
insert into permission (role, description, form_id) values('btnDeleteGroup', 'Deletar grupo', 2);

insert into permission (role, description, form_id) values('btnNewForm', 'Cadastrar novo formulário', 4);
insert into permission (role, description, form_id) values('btnEditForm', 'Editar formulário', 4);
insert into permission (role, description, form_id) values('btnDeleteForm', 'Deletar formulário', 4);
insert into permission (role, description, form_id) values('btnAuditForm', 'Ativar e desativar auditoria de formulários', 4);

insert into permission (role, description, form_id) values('btnNewMaterial', 'Cadastrar novo material', 5);
insert into permission (role, description, form_id) values('btnEditMaterial', 'Editar material', 5);
insert into permission (role, description, form_id) values('btnDeleteMaterial', 'Deletar material', 5);

insert into permission (role, description, form_id) values('btnNewMaterialAbsortancia', 'Cadastrar nova absortancia do material', 6);
insert into permission (role, description, form_id) values('btnEditMaterialAbsortancia', 'Editar absortancia material', 6);
insert into permission (role, description, form_id) values('btnDeleteMaterialAbsortancia', 'Deletar absortancia do material', 6);

insert into permission (role, description, form_id) values('btnUpdatePermission', 'Ativar e desativar permissões de grupos', 7);


insert into group_permission values(1,1);
insert into group_permission values(1,2);
insert into group_permission values(1,3);
insert into group_permission values(1,4);
insert into group_permission values(1,5);
insert into group_permission values(1,6);
insert into group_permission values(1,7);
insert into group_permission values(1,8);
insert into group_permission values(1,9);
insert into group_permission values(1,10);
insert into group_permission values(1,11);
insert into group_permission values(1,12);
insert into group_permission values(1,13);
insert into group_permission values(1,14);
insert into group_permission values(1,15);
insert into group_permission values(1,16);
insert into group_permission values(1,17);
insert into group_permission values(1,18);
insert into group_permission values(1,19);
insert into group_permission values(1,20);
insert into group_permission values(1,21);
insert into group_permission values(1,22);
insert into group_permission values(1,23);
insert into group_permission values(1,24);
insert into group_permission values(1,25);
insert into group_permission values(1,26);
insert into group_permission values(1,27);

