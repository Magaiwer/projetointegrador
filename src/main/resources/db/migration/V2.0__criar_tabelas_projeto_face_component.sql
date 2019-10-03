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
  transmittance_average decimal,
  room_id BIGINT not null,
  constraint pk_face primary key (id),
  constraint fk_room_face foreign key(room_id) references ROOM
);

CREATE TABLE COMPONENT (
  id serial not null,
  name varchar(45) not null,
  total_resistence decimal ,
  transmittance decimal,
  qfo decimal ,
  qft decimal,
  m2 decimal,
  face_id BIGINT not null,
  constraint pk_component primary key (id),
  constraint fk_face_component foreign key(face_id) references FACE
);


CREATE TABLE COMPONENT_MATERIAL (
  component_id BIGINT  not null,
  material_id BIGINT not null,
  thickness decimal ,
  resistence decimal,
  constraint fk_component_material foreign key (component_id) references COMPONENT,
  constraint fk_material_component foreign key (material_id) references MATERIAL,
  constraint pk_component_material primary key (component_id,material_id)
);

alter table material add column glass boolean;
