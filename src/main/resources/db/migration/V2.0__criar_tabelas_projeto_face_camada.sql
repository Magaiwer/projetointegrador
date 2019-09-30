CREATE TABLE PERSON (
  id serial not null,
  name varchar (100),
  email text not null,
  fone text not null,
  birth_date timestamp,
  constraint pk_person primary key (id)

);

CREATE TABLE REGION (
  id serial not null,
  name varchar (100),
  index DECIMAL not null,
  constraint pk_region primary key (id)
);

CREATE TABLE PROJECT (
  id serial not null,
  name varchar (100) not null,
  date timestamp not null,
  description text,
  person_id BIGINT not null,
  region_id BIGINT not null,
  constraint pk_project primary key (id),
  constraint fk_person_project foreign key(person_id) references PERSON,
  constraint fk_region_project foreign key(region_id) references REGION
);

CREATE TABLE ROOM (
  id serial not null,
  name varchar (100),
  project_id BIGINT not null,
  constraint pk_room primary key (id),
  constraint fk_project_room foreign key(project_id) references PROJECT
);

CREATE TABLE FACE (
  id serial not null,
  name varchar (100),
  room_id BIGINT not null,
  constraint pk_face primary key (id),
  constraint fk_room_face foreign key(room_id) references ROOM
);

CREATE TABLE LAYER (
  id serial not null,
  thickness decimal not null,
  qfo decimal ,
  qft decimal,
  m2 decimal,
  face_id BIGINT not null,
  constraint pk_layer primary key (id),
  constraint fk_face_layer foreign key(face_id) references FACE
);


CREATE TABLE LAYER_MATERIAL (
  layer_id BIGINT  not null,
  material_id BIGINT not null,
  constraint fk_layer_material foreign key (layer_id) references LAYER,
  constraint fk_material_layer foreign key (material_id) references MATERIAL,
  constraint pk_layer_material primary key (layer_id,material_id)
);

alter table material add column glass boolean;
