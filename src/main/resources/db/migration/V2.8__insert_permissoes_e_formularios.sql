insert into form(name, audit, description, entity_id) values ('Dashboards', true, 'Dashboards',14);

insert into permission(role,description,form_id) values('menuDashboards','Dashboards',12);

insert into permission (role, description, form_id) values('btnAddFilter', 'Aplicar filtros', 12);
insert into permission (role, description, form_id) values('btnTotalProjects', 'Total de projetos', 12);
insert into permission (role, description, form_id) values('btnTotalClients', 'Total de clientes', 12);
insert into permission (role, description, form_id) values('btnAverageRooms', 'Media de comodos por projeto', 12);
insert into permission (role, description, form_id) values('btnClearFilters', 'LimparFiltros', 12);

insert into group_permission values(1,35);
insert into group_permission values(1,36);
insert into group_permission values(1,37);
insert into group_permission values(1,38);
insert into group_permission values(1,39);
insert into group_permission values(1,40);

