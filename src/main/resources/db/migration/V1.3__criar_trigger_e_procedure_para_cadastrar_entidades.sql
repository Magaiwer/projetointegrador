/* Procedure para capturar o evento de create e tratar os dados para inserir na tabela de entidades*/
CREATE or replace FUNCTION catch_event_create()
    RETURNS event_trigger
    LANGUAGE plpgsql
AS $$
declare
    obj record;
	vtable_name text;
begin
	FOR obj IN SELECT pg_describe_object(classid, objid, objsubid) as table_info, object_type FROM pg_event_trigger_ddl_commands()
    loop
		IF obj.object_type = 'table' then
			vtable_name := SPLIT_PART(obj.table_info, ' ', 2);
        	EXECUTE register_entities(vtable_name);
		END IF;
    end loop;
end
$$;

/* Procedure para cadastrar as tabelas criadas na tabela de entidades*/
CREATE OR REPLACE FUNCTION register_entities(ptable_name text)
RETURNS VOID AS $$
DECLARE
	vtable_name text;
BEGIN
	IF ptable_name <> '' THEN
		vtable_name:= SPLIT_PART(ptable_name, '_', 1) || ' ' || SPLIT_PART(ptable_name, '_', 2) || ' ' || SPLIT_PART(ptable_name, '_', 3)
			         || ' ' || SPLIT_PART(ptable_name, '_', 4)|| ' ' || SPLIT_PART(ptable_name, '_', 5)|| ' ' || SPLIT_PART(ptable_name, '_', 6);
		INSERT INTO entity(name, target) VALUES (ptable_name, INITCAP(vtable_name));
	END IF;

END
$$ LANGUAGE plpgsql;

/* Trigger para capturar o envento de criação de tabelas no banco de dados*/
CREATE EVENT TRIGGER trigg_event_create_table
ON ddl_command_end WHEN TAG IN ('CREATE TABLE')
EXECUTE PROCEDURE catch_event_create();