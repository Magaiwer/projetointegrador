
CREATE TABLE PERMISSION (
  id serial not null,
  role text not null,
  description text,
  form_id BIGINT,
  constraint fk_form_id_permission foreign key (form_id) references form,
  constraint pk_permission primary key (id)

);

CREATE TABLE GROUP_PERMISSION (
  group_id BIGINT  not null,
  permission_id BIGINT not null,
  constraint fk_group_permission foreign key (group_id) references public.group,
  constraint fk_permission_group foreign key (permission_id) references permission,
  constraint pk_group_permission primary key (group_id,permission_id)
);


