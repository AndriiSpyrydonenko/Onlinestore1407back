use svitsmachnogo;

create table carts
(
    user_id    bigint not null,
    product_id int    not null,
    amount     int    not null,
    cost       double,
    count      int,
    constraint cart_user_id_product_id_PK primary key (user_id, product_id, amount),
    constraint cart_users_user_id_FK foreign key (user_id) references users (user_id),
    constraint cart_product_id_FK foreign key (product_id, amount) references packaging (product_id, amount)
);

create table wishlists
(
    user_id    bigint not null,
    product_id int    not null,
    constraint wishlists_user_id_product_id_PK primary key (user_id, product_id),
    constraint wishlists_users_user_id_FK foreign key (user_id) references users (user_id),
    constraint wishlists_product_id_FK foreign key (product_id) references products (product_id)
);

create table orders
(
    order_id      bigint auto_increment ,
    user_id       bigint                not null,
    order_comment varchar(255),
    create_date   datetime              not null,
    pay_type      varchar(64)           not null ,

#     constraint orders__pay_type_CHECK check (pay_type = 'CARD' or pay_type = 'POSTPAID'),
    constraint orders_order_id_PK primary key (order_id),
    constraint orders_users_user_id_FK foreign key (user_id) references users (user_id)

);

create table orders_packaging
(
    order_id   bigint not null,
    product_id int    not null,
    amount     int    not null,
    constraint orders_packaging__order_id__product_id__PK primary key (order_id, product_id, amount),
    constraint orders_packaging__orders_order_id_FK foreign key (order_id) references orders (order_id),
    constraint orders_packaging__orders_packaging__product_id__amount_FK foreign key (product_id, amount)
        references packaging(product_id, amount)
);