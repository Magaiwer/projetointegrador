CREATE TABLE material_absortancia (
  id serial not null,
  superficie varchar(150) not null,
  alfa_ini numeric(12,2),
  alfa_fim numeric(12,2),
  beta_ini numeric(12,2),
  beta_fim numeric(12,2),
  constraint pk_material_absortancia primary key (id)
);

