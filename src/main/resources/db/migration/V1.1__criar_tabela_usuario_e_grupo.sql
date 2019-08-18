CREATE TABLE USUARIO (
  id serial not null,
  nome varchar (100),
  login varchar (100) not null,
  senha text not null,
  ativo boolean not null,
  constraint pk_usuario primary key (id)

);

CREATE TABLE GRUPO (
  id serial not null,
  nome varchar (100) not null,
  constraint pk_grupo primary key (id)
);

  /*senha 1234*/
INSERT INTO USUARIO(nome, login, senha, ativo) values('Administrador','admin@admin.com','$2a$10$12Bm5xzAVYzi.fvHIxreqeR.ajqGazIcHPQKzAUtgKYfZa8axbsR2',true );