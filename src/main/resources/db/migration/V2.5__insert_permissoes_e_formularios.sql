insert into form(name, audit, description, entity_id) values ('Cadastro de Projetos', true, 'Cadastro e manutenção de projetos',14);

insert into permission (role, description, form_id) values('btnNewProject', 'Cadastrar Projetos', 10);
insert into permission (role, description, form_id) values('btnEditProject', 'Editar Projetos', 10);
insert into permission (role, description, form_id) values('btnDeleteProject', 'Deletar Projetos', 10);
insert into permission (role, description, form_id) values('btnDetailProject', 'Visualizar detalhes do projeto', 10);

insert into group_permission values(1,31);
insert into group_permission values(1,32);
insert into group_permission values(1,33);
insert into group_permission values(1,34);

