delete from employee;
delete from department;

insert into department (department_name, created, updated, id) values ('Отдел кадров', NOW(), NOW(), 1);
insert into department (department_name, created, updated, id) values ('Хозяйственный отдел', NOW(), NOW(), 2);