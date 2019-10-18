alter table component_material add column resistance decimal ;

alter table component_material drop column resistence;

update material set glass = false;