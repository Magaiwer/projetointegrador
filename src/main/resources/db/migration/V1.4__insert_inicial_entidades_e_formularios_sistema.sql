insert into entity(id, name, target) values (1, 'usuario', 'Usuario');
insert into entity(id, name, target) values (2, 'grupo', 'Grupo');
insert into entity(id, name, target) values (3, 'audit', 'Audit');
insert into entity(id, name, target) values (4, 'form', 'Form');

insert into form(name, audit, description, entity_id) values ('Cadastro de Usuario', true, 'Cadastro e manutenção de usuário',1);
insert into form(name, audit, description, entity_id) values ('Cadastro de grupo', true, 'Cadastro e manutenção de grupo de usuário',2);
insert into form(name, audit, description, entity_id) values ('Consulta de Auditoria', true, 'Consulta das ações executadas no sistema',3);
insert into form(name, audit, description, entity_id) values ('Cadastro de formulários', true, 'Cadastro e manutenção de formulários',4);
