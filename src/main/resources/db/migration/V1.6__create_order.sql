use svitsmachnogo;


create table user_profiles
(
    user_id      bigint not null,
    user_name    varchar(255),
    user_surname varchar(255),
    phone_number varchar(15),
    address      varchar(255),
    photo_url    varchar(255),
    constraint user_profile_user_id_PK primary key (user_id),
    constraint user_profile_users_user_id_FK foreign key (user_id) references users (user_id)
);

create table carts
(
    user_id               bigint not null,
    customer_name         varchar(64),
    customer_surname      varchar(64),
    customer_phone_number varchar(64),
    customer_address      varchar(255),
    constraint carts_user_id_PK primary key (user_id),
    constraint cart_user_profile_user_id_FK foreign key (user_id) references user_profiles (user_id)
);

create table wishlists
(
    user_id    bigint not null,
    product_id int    not null,
    constraint wishlists_user_id_product_id_PK primary key (user_id, product_id),
    constraint wishlists_user_profiles_user_id_FK foreign key (user_id) references user_profiles (user_id),
    constraint wishlists_product_id_FK foreign key (product_id) references products (product_id)
);

create table orders
(
    order_id              bigint auto_increment,
    user_id               bigint,
    order_comment         varchar(255),
    total_cost            int          not null,
    customer_name         varchar(64)  not null,
    customer_surname      varchar(64)  not null,
    customer_phone_number varchar(64)  not null,
    customer_address      varchar(255) not null,
    create_date           datetime     not null,
    pay_type              varchar(64)  not null,

#     constraint orders__pay_type_CHECK check (pay_type = 'CARD' or pay_type = 'POSTPAID'),
    constraint orders_order_id_PK primary key (order_id),
    constraint orders_user_profiles_user_id_FK foreign key (user_id) references user_profiles (user_id)

);

create table orders_packaging
(
    order_id   bigint not null,
    product_id int    not null,
    amount     int    not null,
    constraint orders_packaging__order_id__product_id__PK primary key (order_id, product_id, amount),
    constraint orders_packaging__orders_order_id_FK foreign key (order_id) references orders (order_id),
    constraint orders_packaging__orders_packaging__product_id__amount_FK foreign key (product_id, amount)
        references packaging (product_id, amount)
);

create table carts_packaging
(
    user_id    bigint not null,
    product_id int    not null,
    amount     int    not null,
    constraint carts_packaging__user_id__product_id__amount__PK primary key (user_id, product_id, amount),
    constraint carts_packaging__user_id_FK foreign key (user_id) references carts (user_id),
    constraint carts_packaging__product_id__amount_FK foreign key (product_id, amount)
        references packaging (product_id, amount)
);
insert into user_profiles(user_id)
values (1),
       (2);
insert into carts(user_id)
values (1),
       (2);