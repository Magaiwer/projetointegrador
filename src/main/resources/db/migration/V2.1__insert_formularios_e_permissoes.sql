insert into form(name, audit, description, entity_id) values ('Cadastro de Pessoas', true, 'Cadastro e manutenção de pessoas',12);
insert into form(name, audit, description, entity_id) values ('Cadastro de Regiões', true, 'Cadastro e manutenção de regiões',13);
insert into form(name, audit, description, entity_id) values ('Cadastro de Projeto', true, 'Cadastro e manutenção de projetos',14);

insert into permission(role,description,form_id) values('menuProject','Menu de projetos',10);
insert into permission(role,description,form_id) values('btnNextProject','Avançar no cadastro de projetos',10);
insert into permission(role,description,form_id) values('btnPreviousProject','Retornar no cadastro de projetos',10);


insert into group_permission values(1,28);
insert into group_permission values(1,29);
insert into group_permission values(1,30);