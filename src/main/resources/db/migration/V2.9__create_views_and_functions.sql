create or replace view view_client AS (SELECT DISTINCT person.id, person.name, person.email, person.fone FROM person, project WHERE project.person_id = person.id);
create or replace view view_average_rooms AS (select avg(room.id) AS average FROM project, room WHERE room.project_id = project.id);
create or replace view view_rooms_project AS (SELECT DISTINCT room.id, room.name, project.id AS project_id FROM room, project WHERE room.project_id = project.id);
create or replace view view_projects_client AS (select person.id, person.name, count(project.id) AS count_projects FROM person, project WHERE project.person_id = person.id GROUP BY 1, 2);

CREATE OR REPLACE FUNCTION remove_acento(text)
RETURNS text AS $BODY$
SELECT TRANSLATE($1,'áàãâäÁÀÃÂÄéèêëÉÈÊËíìîïÍÌÎÏóòõôöÓÒÕÔÖúùûüÚÙÛÜñÑçÇÿýÝ','aaaaaAAAAAeeeeEEEEiiiiIIIIoooooOOOOOuuuuUUUUnNcCyyY')
$BODY$
LANGUAGE sql IMMUTABLE STRICT
COST 100;
ALTER FUNCTION remove_acento(text)
OWNER TO postgres;

