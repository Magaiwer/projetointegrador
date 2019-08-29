CREATE TABLE material_absortancia (
  id serial not null,
  superficie varchar(150) not null,
  alfa numeric(12,2),
  beta numeric(12,2),
  constraint pk_material_absortancia primary key (id)
);

