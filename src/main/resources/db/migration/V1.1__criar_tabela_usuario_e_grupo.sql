CREATE TABLE PUBLIC.USER (
  id serial not null,
  name varchar (100),
  login varchar (100) not null,
  password text not null,
  active boolean not null,
  constraint pk_user primary key (id)

);

CREATE TABLE PUBLIC.GROUP (
  id serial not null,
  name varchar (100) not null,
  constraint pk_group primary key (id)
);

CREATE TABLE USER_GROUP (
  user_id BIGINT  not null,
  group_id BIGINT not null,
  constraint fk_user_group foreign key (user_id) references public.user,
  constraint fk_group_user foreign key (group_id) references public.group,
  constraint pk_user_group primary key (user_id,group_id)
);

  /*senha 1234*/
INSERT INTO PUBLIC.USER(name, login, password, active) values('Administrador','admin@admin.com','$2a$10$12Bm5xzAVYzi.fvHIxreqeR.ajqGazIcHPQKzAUtgKYfZa8axbsR2',true );

INSERT INTO PUBLIC.GROUP(name) values('Administrador');

INSERT INTO USER_GROUP values(1,1);