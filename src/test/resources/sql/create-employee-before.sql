delete from employee;

insert into employee (department_id, first_name, last_name, created, updated, phone_number, position, rank, id)
        values (1, 'Коля', 'Колин', NOW(), NOW(), '11-11', 'Разработчик', 'Младший', 1);

insert into employee (department_id, first_name, last_name, created, updated, phone_number, position, rank, id)
        values (1, 'Ваня', 'Ванин', NOW(), NOW(), '22-22', 'Разработчик', 'Старший', 2);