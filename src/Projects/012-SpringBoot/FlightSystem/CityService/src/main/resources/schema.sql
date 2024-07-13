create table if not exists cities (
	city_id bigserial primary key,
	name varchar(250) not null
);

create table if not exists airports (
	airport_id bigserial primary key,
	name varchar(250) not null,
	city_id bigint references cities(city_id) not null
);

create table if not exists flights (
	flight_id varchar(20) primary key,
	departure_airport_id bigint references airports(airport_id) not null,
	destination_airport_id bigint references airports(airport_id) not null,
	departure_date_time timestamp not null,
	return_date_time timestamp not null,
	price real not null	
);

truncate table flights restart identity cascade;
truncate table airports restart identity cascade;
truncate table cities restart identity cascade;

drop procedure if exists sp_delete_city_by_id;
create or replace procedure sp_delete_city_by_id(bigint)
language plpgsql
as
'
    begin
        delete from cities where city_id = $1;
    end

';

drop function if exists insert_city;
create or replace function insert_city(varchar(250))
returns bigint
as
'    begin
        insert into cities (name) values ($1);

        return currval($$cities_city_id_seq$$::regclass);
    end
' language plpgsql;


drop function if exists find_all_cities;
create or replace function find_all_cities()
returns table (id bigint, city_name varchar(250))
as
'
    begin
        return query select * from cities;
    end
' language plpgsql;

drop function if exists find_city_by_id;
create or replace function find_city_by_id(bigint)
returns table (id bigint, city_name varchar(250))
as
'
    begin
        return query select * from cities where city_id = $1;
    end
' language plpgsql;


drop function if exists find_city_by_name;
create or replace function find_city_by_name(varchar(250))
returns table (id bigint, city_name varchar(250))
as
'
    begin
        return query select * from cities where name = $1;
    end
' language plpgsql;


