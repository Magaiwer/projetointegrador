insert into entity( name, target) values ( 'user', 'User');
insert into entity( name, target) values ( 'group', 'Group');
insert into entity( name, target) values ( 'user_group', 'User Group');
insert into entity( name, target) values ( 'audit', 'Audit');
insert into entity( name, target) values ( 'form', 'Form');

insert into form(name, audit, description, entity_id) values ('Cadastro de Usuário', true, 'Cadastro e manutenção de usuário',1);
insert into form(name, audit, description, entity_id) values ('Cadastro de Grupo', true, 'Cadastro e manutenção de grupos de usuário',2);
insert into form(name, audit, description, entity_id) values ('Consulta de Auditoria', true, 'Consulta das ações executadas no sistema',4);
insert into form(name, audit, description, entity_id) values ('Cadastro de formulários', true, 'Cadastro e manutenção de formulários',5);


