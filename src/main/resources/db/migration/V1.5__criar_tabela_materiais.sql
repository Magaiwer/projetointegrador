CREATE TABLE material (
  id serial not null,
  nome varchar(150) not null,
  condutividade_termica numeric(5,2) not null,
  constraint pk_material primary key (id)
);
