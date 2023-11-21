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
    constraint cart_user_id_product_id_PK primary key (user_id, product_id),
    constraint cart_users_user_id_FK foreign key (user_id) references users (user_id),
    constraint cart_product_id_FK foreign key (product_id) references products (product_id)
);

create table orders
(
    order_id      bigint auto_increment not null,
    user_id       bigint                not null,
    order_comment varchar(255),
    create_date   datetime              not null,
    pay_type      varchar(64)           not null, # maybe need other type !!!
    constraint orders_order_id_PK primary key (order_id),
    constraint orders_users_user_id_FK foreign key (user_id) references users (user_id)
);

create table orders_products
(
    order_id   bigint not null,
    product_id int    not null,
    amount     int    not null,
    constraint orders_products__order_id__product_id__PK primary key (order_id, product_id, amount),
    constraint orders_products__orders_order_id_FK foreign key (order_id) references orders (order_id),
    constraint orders_products__products__product_id__amount_FK foreign key (product_id, amount)
        references packaging (product_id)
);