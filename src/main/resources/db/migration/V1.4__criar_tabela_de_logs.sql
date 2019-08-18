CREATE TABLE LOG (
  id serial not null,
  create_at timestamp not null,
  content text,
  constraint pk_log primary key (id)
);