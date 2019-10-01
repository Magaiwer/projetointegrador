insert into person(name, email, fone, birth_date) values ('Juca Bala',  'jucabala@juca.com', '(51) 9 98760309','1991-05-16 15:36:38');

insert into region(name, index) values ('Nordeste',5.0);
insert into region(name, index) values ('Sul',3.0);

insert into project(name, date, description, person_id, region_id) values('CASA JUCA BALA', '2019-10-01 15:36:38',' Sol forte na face norte',1,1);


insert into room(name, project_id) values('Quarto casal',1);

insert into face(name, room_id) values('norte',1);
insert into face(name, room_id) values('sul',1);

insert into layer(thickness, qfo,qft,m2, face_id) values(2.0, 0.0, 0.0, 0.0, 1);
insert into layer(thickness, qfo,qft,m2, face_id) values(2.0, 0.0, 0.0, 0.0, 1);
insert into layer(thickness, qfo,qft,m2, face_id) values(2.0, 0.0, 0.0, 0.0, 1);

insert into material(nome, condutividade_termica) values('argamassa', 10.0);
insert into material(nome, condutividade_termica) values('tijolo', 8.0);

insert into layer_material values(1,1);
insert into layer_material values(2,2);
insert into layer_material values(3,1);

