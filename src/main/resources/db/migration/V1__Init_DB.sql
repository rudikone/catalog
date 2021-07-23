create sequence if not exists hibernate_sequence start 1 increment 1;

    create table if not exists department (
       id int8 not null,
        department_name varchar(255),
        persist_date timestamp,
        primary key (id)
    );

    create table if not exists employee (
       id int8 not null,
        first_name varchar(255),
        last_name varchar(255),
        persist_date timestamp,
        phone_number varchar(255),
        position varchar(255),
        rank varchar(255),
        department_id int8,
        primary key (id)
    );

    create table if not exists moderator (
       id int8 not null,
        login varchar(255),
        password varchar(255),
        primary key (id)
    );

    create table if not exists moderators_roles (
       moderator_id int8 not null,
        role_id int4 not null,
        primary key (moderator_id, role_id)
    );

    create table if not exists role (
       id int4 not null,
        role varchar(255),
        primary key (id)
    );

    alter table if exists employee
       add constraint FKbejtwvg9bxus2mffsm3swj3u9
       foreign key (department_id)
       references department;

    alter table if exists moderators_roles
       add constraint FKheej6v65bldq2cqa61dccyvyp
       foreign key (role_id)
       references role;

    alter table if exists moderators_roles
       add constraint FKrcncguwx65rlpi5u5vjdy8g6h
       foreign key (moderator_id)
       references moderator;