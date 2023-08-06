use svitsmachnogo;

create table categories
(
    category_id       int auto_increment,
    name              varchar(255) unique not null,
    url_name          varchar(255) unique not null,
    fill_picture_url  varchar(255) unique not null,
    hover_picture_url varchar(255) unique not null,
    constraint categories_category_id_PK primary key (category_id)
);

create table products
(
    product_id       int auto_increment,
    category_id      int,
    article          int unique    not null,
    name             varchar(255)  not null,
    description      varchar(1024) not null,
    country_producer varchar(255),
    exist            boolean       not null default true,
    priority_score   int           not null default 0,
    rating           double (2, 1) ,
    review_count int ,
    discount_percent int ,
    create_date datetime not null ,
    constraint products_priority_score_check check ( priority_score between -1 and 16),
    constraint products_discount_percent_check check ( discount_percent between -1 and 101),
    constraint products_categories_category_id_FK foreign key(category_id) references categories(category_id),
    constraint products_product_id_PK primary key (product_id)
);

create table pictures
(
    picture_id int auto_increment,
    product_id int                 not null,
    url_path   varchar(255) unique not null,
    constraint pictures_picture_id primary key (picture_id),
    constraint pictures_products_product_id_FK foreign key (product_id) references products (product_id)
);



