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

CREATE OR REPLACE FUNCTION dif_dates_day(date, date) RETURNS integer AS $$
    BEGIN
        RETURN (SELECT
                  (SELECT count(*) - 1
                   FROM generate_series(date1, date2, '1 DAY')) AS meses
                FROM (
                      VALUES ($1::date,
                              $2::date)) g (date1, date2));
        END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION dif_dates_year(date, date) RETURNS integer AS $$
    BEGIN
        RETURN (SELECT
                  (SELECT count(*) - 1
                   FROM generate_series(date1, date2, '1 YEAR')) AS meses
                FROM (
                      VALUES ($1::date,
                              $2::date)) g (date1, date2));
        END;
$$ LANGUAGE plpgsql;

CREATE OR REPLACE FUNCTION dif_dates_month(date, date) RETURNS integer AS $$
    BEGIN
        RETURN (SELECT
                  (SELECT count(*) - 1
                   FROM generate_series(date1, date2, '1 MONTH')) AS meses
                FROM (
                      VALUES ($1::date,
                              $2::date)) g (date1, date2));
        END;
$$ LANGUAGE plpgsql;

CREATE TYPE type_projects AS (
	id INT,
	name VARCHAR,
	description VARCHAR,
	date VARCHAR,
	person_id INT,
	region_id INT,
	person_name VARCHAR,
	person_email VARCHAR,
	person_fone VARCHAR,
	region_name VARCHAR
);


CREATE OR REPLACE FUNCTION projects(person_id INTEGER) RETURNS SETOF type_projects AS $$
DECLARE
	data_projects type_projects;
BEGIN
	FOR data_projects IN SELECT project.id,
				                project.name,
				                project.description,
				                project.date,
				                project.person_id,
				                project.region_id,
				                person.name,
				                person.email,
				                person.fone,
				                region.name
		                   FROM project,
				                person,
				                region
		                  WHERE project.person_id = person.id
		                    AND project.region_id = region.id
		                    AND project.person_id = $1 LOOP
		RETURN NEXT data_projects;
	END LOOP;
	RETURN;
END;
$$ LANGUAGE 'plpgsql';