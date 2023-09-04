use
    svitsmachnogo;

create table categories
(
    category_id       varchar(255) unique not null,
    name              varchar(255) unique not null,
    fill_picture_url  varchar(255) unique not null,
    hover_picture_url varchar(255) unique not null,
    constraint categories_category_id_PK primary key (category_id)
);

create table gift_sets
(
    gift_set_id      int auto_increment,
    url_picture      varchar(255) not null,
    discount_percent int          not null default 0,
    constraint gift_sets_discount_percent_CHECK check ( discount_percent between -1 and 101),
    constraint gift_sets_gift_set_id_FK primary key (gift_set_id)

);

create table products
(
    product_id       int auto_increment,
    category_id      varchar(255),
    article          int unique    not null,
    name             varchar(255)  not null,
    description      varchar(1024) not null,
    country_producer varchar(255),
    exist            boolean       not null default true,
    is_gift_set      boolean       not null default false,
    priority_score   int           not null default 0,
    rating           double(2, 1),
    review_count     int,
    discount_percent int           not null default 0,
    create_date      datetime      not null,
    unit             varchar(255)  not null default 'г',
    constraint products_priority_score_CHECK check ( priority_score between -1 and 16),
    constraint products_discount_percent_CHECK check ( discount_percent between -1 and 101),
    constraint products_categories_category_id_FK foreign key (category_id) references categories (category_id),
    constraint products_product_id_PK primary key (product_id)
);

create table pictures
(
    picture_id int auto_increment,
    product_id int          not null,
    url_path   varchar(255) not null,
    constraint pictures_picture_id_PK primary key (picture_id),
    constraint pictures_products_product_id_FK foreign key (product_id) references products (product_id)
);

create table packaging
(
    product_id int    not null,
    amount     int not null,
    cost       double    not null default 100,
    constraint packaging_products_product_id_FK foreign key (product_id) references products (product_id),
    constraint packaging_packaging_id_PK primary key (product_id, amount)
);



