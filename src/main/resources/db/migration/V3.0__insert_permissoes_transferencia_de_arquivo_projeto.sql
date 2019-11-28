insert into form(name, audit, description, entity_id) values ('Envio de arquivo  servidor', true, 'Envio de arquivos para o servidor',14);

insert into permission(role,description,form_id) values('btnSendFile','Enviar arquivo servidor',13);
insert into permission(role,description,form_id) values('btnSelectFile','Selecionar arquivo a ser enviado para o servidor',13);
insert into permission(role,description,form_id) values('menuTransfer','Transfêrencia de arquivo',13);

insert into group_permission values(1,43);
insert into group_permission values(1,44);
insert into group_permission values(1,45);

