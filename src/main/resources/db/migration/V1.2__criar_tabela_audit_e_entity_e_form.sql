CREATE TABLE AUDIT (
  id serial not null,
  created_at timestamp not null,
  new_value varchar not null,
  command varchar not null,
  user_id BIGINT not null,
  constraint pk_audit primary key (id),
  constraint fk_audit_userid foreign key (user_id) references usuario

);

CREATE TABLE ENTITY (
  id serial not null,
  name varchar (100) not null,
  target varchar (100) not null,
  constraint pk_entity primary key (id)
);

CREATE TABLE FORM (
  id serial not null,
  name varchar (100) not null,
  audit boolean,
  entity_id BIGINT not null,
  constraint pk_form primary key (id),
  constraint fk_form_entityid foreign key (entity_id) references entity
);