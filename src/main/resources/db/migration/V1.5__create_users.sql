use svitsmachnogo;

create table users
(
    user_id   bigint auto_increment,
    user_name varchar(30)  not null,
    password  varchar(255) not null,
    email     varchar(255) unique,
    constraint users_user_id_PK primary key (user_id)
);

create table roles
(
    role_id   int auto_increment,
    role_name varchar(58) not null,
    constraint roles_role_id_PK primary key (role_id)
);

create table users_roles
(
    user_id bigint not null,
    role_id int    not null,
    constraint users_roles_user_id_role_id_PK primary key (user_id, role_id),
    constraint users_roles_users_user_id_FK foreign key (user_id) references users (user_id),
    constraint users_roles_roles_role_id_FK foreign key (role_id) references roles (role_id)
);

insert into roles(role_name)
values ('ROLE_USER'),
       ('ROLE_ADMIN');

insert into users(user_name, password, email)
values ('user', '$2a$10$MGVe/7h9Ycg/EljjjlVRj.EuUKUy1LR5jMI9O81SSlYRcDSCyWzm6', 'user@gmail.com'),
       ('admin', '$2a$10$MGVe/7h9Ycg/EljjjlVRj.EuUKUy1LR5jMI9O81SSlYRcDSCyWzm6', 'admin@gmail.com');

insert into users_roles(user_id, role_id)
values (1, 1),
       (2, 2);