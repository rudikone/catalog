insert into role (id, role)
values (1, 'ROLE_ADMIN');

insert into moderator (id, login, password)
values (1, 'admin', '$2y$12$F4GdRHWT8iOVSSbNlCvWoOH7BWYeFEQ9958kEezv6Hg.7T.HqqQAq');

insert into moderators_roles (moderator_id, role_id)
values (1, 1);