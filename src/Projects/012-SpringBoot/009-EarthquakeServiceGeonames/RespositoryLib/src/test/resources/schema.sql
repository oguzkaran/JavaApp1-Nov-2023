drop table if exists earthquake_query_info;
drop table if exists region_info;

create table region_info (
	region_info_id bigserial primary key,
	east double precision not null,
	west double precision not null,
	north double precision not null,
	south double precision not null,
	query_datetime timestamp default(current_timestamp) not null
);

create table earthquake_info (
    earthquake_info_id bigserial primary key,
    region_info_id bigint references region_info(region_info_id) not null,
    datetime timestamp,
    depth double precision,
    latitude double precision,
    longitude double precision,
    earthquake_id varchar(250),
    magnitude double precision,
    distance varchar(250),
    country_code varchar(20),
    country_name varchar(250),
    locality varchar(250),
    street varchar(250),
    postal_code varchar(250)
);

create table earthquake_query_info (
    earthquake_query_info_id bigserial primary key,
    region_info_id bigint references region_info(region_info_id) not null,
    query_datetime timestamp default(current_timestamp) not null
);

--