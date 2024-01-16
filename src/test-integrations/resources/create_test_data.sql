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
    rating           double,
    review_count     int           not null default 0,
    number_of_orders int           not null default 0,
    discount_percent int           not null default 0,
    create_date      datetime      not null,
    min_price        double        not null default 0,
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
    amount     int    not null,
    cost       double not null default 100,
    constraint packaging_products_product_id_FK foreign key (product_id) references products (product_id),
    constraint packaging_packaging_id_PK primary key (product_id, amount)
);


insert into categories(category_id, name, fill_picture_url, hover_picture_url)
values ('grocery', 'Бакалія', 'https://storage.googleapis.com/svsmach/categories/Bakalija%20Status%3DFill.svg',
        'https://storage.googleapis.com/svsmach/categories/Bakalija%20Status%3DHover.svg'),
       ('dried_fruits', 'Сухофрукти',
        'https://storage.googleapis.com/svsmach/categories/Dry%20fruits%20Status%3DFill.svg',
        'https://storage.googleapis.com/svsmach/categories/Dry%20fruits%20%20Status%3DHover.svg'),
       ('honey', 'Мед', 'https://storage.googleapis.com/svsmach/categories/Honey%20Status%3DFill.svg',
        'https://storage.googleapis.com/svsmach/categories/Honey%20Status%3DHover.svg'),
       ('nuts', 'Горіхи', 'https://storage.googleapis.com/svsmach/categories/Nuts%20Status%3DFill.svg',
        'https://storage.googleapis.com/svsmach/categories/Nuts%20Status%3DHover.svg'),
       ('oil', 'Олія', 'https://storage.googleapis.com/svsmach/categories/Oil%20Status%3DFill.svg',
        'https://storage.googleapis.com/svsmach/categories/Oil%20Status%3DHover.svg'),
       ('spices', 'Спеції', 'https://storage.googleapis.com/svsmach/categories/Spices%20%20Status%3DFill.svg',
        'https://storage.googleapis.com/svsmach/categories/Spices%20Status%3DHover.svg');

insert into gift_sets(url_picture, discount_percent)
values ('https://storage.googleapis.com/svsmach/categories/Form_for_pic.png', 50);


insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
values ('grocery', 123456, 'Квасоля червона', true, 0, 4.2, 50, 20, now(), 'Україна',
        'Склад: Квасоля червона "Рубін" - вона є чудовою альтернативою м''ясу для вегетаріанців і тих, хто поститься. Червона квасоля містить до 25% білка, який за своєю харчовою цінністю не поступається білку м''яса. До того ж білок квасолі засвоюється на 70 – 80%.'),
       ('grocery', 548712, 'Кіноа Біла', true, 0, 3.2, 278, 0, now(), 'Перу',
        'Склад: Кіноа Біла - зернова культура з сімейства мареві, яка росте на гірських схилах Анд. Батьківщиною білої кіноа прийнято вважати берега озера Тітікака в Південній Америці.'),
       ('grocery', 234567, 'Кіноа Червона', false, 1, 4.8, 10, 20, now(), 'Перу',
        'Склад: Кіноа Червона - зернова культура з сімейства мареві, яка росте на гірських схилах Анд. Батьківщиною білої кіноа прийнято вважати берега озера Тітікака в Південній Америці. Поряд з картоплею і кукурудзою кіноа входила в трійку головних продуктів харчування Індіанців.'),
       ('grocery', 345667, 'Сочевиця (Туреччина)', true, 3, 4.2, 100, 0, now(), 'Туреччина',
        'Склад: плоди сочевиці сирі. Сочевиця – однорідна рослина роду сочевиця, родини бобових. Форма зернятка кругла, плоска.'),
       ('grocery', 566778, 'Сочевиця (Україна)', true, 2, 4.2, 100, 0, now(), 'Україна',
        'Склад: плоди сочевиці сирі. Сочевиця – однорідна рослина роду сочевиця, родини бобових. Форма зернятка кругла, плоска.'),
       ('grocery', 122113, 'Чіа Біла', true, 8, 4.2, 100, 0, now(), 'США',
        'Склад: Насіння Білої Чіа - це високопоживний продукт, що містить білки з повним складом незамінних амінокислот, поліненасичені жирні кислоти сімейства омега-3 і омега-6, харчові розчинні і нерозчинні волокна, вітаміни і мінерали'),
       ('dried_fruits', 124875, 'Журавлина сушена без цукру', true, 0, 4.2, 100, 50, now(), 'Канада', 'Склад: Журавлина сушена без цукра, ягода журавлини використовують у лікуванні захворювань серцево-судинної системи.
Вживання журавлини в їжу позитивно позначається на настрої. Вона рятує від депресій і нервових розладів. Журавлина також стимулює вироблення шлункового соку, а тому особливо ефективна для лікування гастриту зі зниженою кислотністю.
'),
       ('dried_fruits', 233257, 'Курага Джамбо', true, 6, 4.2, 100, 40, now(), 'Турція', 'Склад: Сорт Джамбо вирізняється найвищою концентрацією калію — приблизно 9% від денної норми. курага сорту Джамбо максимально схожа на свіжий абрикос. Її смак не нудотно солодкий, а з приємною, освіжною кислинкою
Щоб відчути максимум користі для здоров’я, вживати курагу Джамбо потрібно невеликими порціями щодня — приблизно по 2-3 плоди.
'),
       ('dried_fruits', 465674, 'Фініки натуральні Мазафаті ', true, 0, 4.2, 100, 0, now(), 'Іран', 'Склад: Фініки — їстівні плоди фінікової пальми. З давніх-давен використовується людиною як високоцінний харчовий продукт. У продаж зазвичай надходять як сухофрукти. Найпопулярніший сорт фінікової пальми — «мазафаті» — культивується в промислових масштабах в Ірані.
Фініки Мазафаті — один із найвдаліших і найпопулярніших сортів сухофрукту з Ірану. Цей продукт справедливо вважається одним із найсмачніших видів у всьому світі. Має характерний оксамитовий чорний колір, самі плоди — досить великі, мають м’ясисту структуру.
'),
       ('dried_fruits', 70006, 'Чорнослив в’ялений ', false, 4, 4.2, 100, 0, now(), 'Україна',
        'Склад: Чорнослив в''ялений без кісточки — це поширений сухофрукт, відомий своїми корисними якостями, у ньому збережені корисні речовини, наявні у свіжому фрукті. Крім того, мінеральні речовини містяться в концентрованому вигляді. У цих сухофруктах є вітаміни: РР, А, В1, В2, С. Вони містять фруктозу, глюкозу, сахарозу, магній, натрій, кальцій, залізо, фосфор, калій, органічні кислоти, пектини, харчові волокна, білки, крохмаль.');



insert into pictures
    (product_id, url_path)
values (1,
        'https://storage.googleapis.com/svsmach/products/%D0%9A%D1%96%D0%BD%D0%BE%D0%B0%20%D1%87%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0.jpg'),
       (2,
        'https://storage.googleapis.com/svsmach/products/%D0%9A%D1%96%D0%BD%D0%BE%D0%B0%20%D0%B1%D1%96%D0%BB%D0%B0.jpg'),
       (3,
        'https://storage.googleapis.com/svsmach/products/%D0%9A%D1%96%D0%BD%D0%BE%D0%B0%20%D1%87%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0.jpg'),
       (4,
        'https://storage.googleapis.com/svsmach/products/%D0%A7%D0%B5%D1%87%D0%B5%D0%B2%D0%B8%D1%86%D1%8F%20%D0%A2%D1%83%D1%80%D0%B5%D1%87%D1%87%D0%B8%D0%BD%D0%B0.jpg'),
       (5,
        'https://storage.googleapis.com/svsmach/products/%D0%A7%D0%B5%D1%87%D0%B5%D0%B2%D0%B8%D1%86%D1%8F%20%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B0.jpg'),
       (6, 'https://storage.googleapis.com/svsmach/products/%D0%A7%D1%96%D0%B0%20%D0%B1%D1%96%D0%BB%D0%B0.jpg'),
       (7,
        'https://storage.googleapis.com/svsmach/products/%D0%9A%D1%96%D0%BD%D0%BE%D0%B0%20%D1%87%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0.jpg'),
       (8,
        'https://storage.googleapis.com/svsmach/products/%D0%9A%D1%83%D1%80%D0%B0%D0%B3%D0%B0%20%D0%B4%D0%B6%D0%B0%D0%BC%D0%B1%D0%BE.jpg'),
       (9,
        'https://storage.googleapis.com/svsmach/products/%D1%84%D1%96%D0%BD%D1%96%D0%BA%20%D0%B2%20%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D1%86%D1%96_1.jpg'),
       (9,
        'https://storage.googleapis.com/svsmach/products/%D1%84%D1%96%D0%BD%D1%96%D0%BA%20%D0%B2%20%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D1%86%D1%96_1.jpg'),
       (9,
        'https://storage.googleapis.com/svsmach/products/%D1%84%D1%96%D0%BD%D1%96%D0%BA%20%D0%B2%20%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D1%86%D1%96.jpg'),
       (10,
        'https://storage.googleapis.com/svsmach/products/%D1%87%D0%BE%D1%80%D0%BD%D0%BE%D1%81%D0%BB%D0%B8%D0%B2%20%D0%B2%D1%8F%D0%BB%D0%B5%D0%BD%D0%B8%D0%B9.jpg');

INSERT INTO packaging(product_id, amount, cost)
VALUES (1, 200, 159),
       (1, 500, 688),
       (1, 1000, 283),
       (2, 200, 436),
       (2, 500, 322),
       (2, 1000, 812),
       (3, 200, 768),
       (3, 500, 865),
       (3, 1000, 112),
       (4, 200, 853),
       (4, 500, 925),
       (4, 1000, 563),
       (5, 200, 137),
       (5, 500, 245),
       (5, 1000, 587),
       (6, 200, 913),
       (6, 500, 517),
       (6, 1000, 199),
       (7, 200, 122),
       (7, 500, 734),
       (7, 1000, 607),
       (8, 200, 617),
       (8, 500, 843),
       (8, 1000, 743),
       (9, 200, 723),
       (9, 500, 950),
       (9, 1000, 546),
       (10, 200, 856),
       (10, 500, 382),
       (10, 1000, 615);


UPDATE products p
SET min_price = (select pac.cost
                 from packaging pac
                 where pac.product_id = p.product_id
                   and amount = (select min(amount) from packaging pg where pg.product_id = p.product_id))
where p.product_id > 0;


create table subcategories
(
    subcategory_id varchar(255),
    category_id    varchar(255),
    name           varchar(255) not null,
    title          varchar(255) not null,
    constraint subcategories_subcategory_id_PK primary key (subcategory_id),
    constraint subcategories_categories_category_id_FK foreign key (category_id) references categories (category_id)
);

create table products_subcategories
(
    product_id     int,
    subcategory_id varchar(255),
    constraint products_subcategories_product_id_subcategory_id_PK
        primary key (product_id, subcategory_id),
    constraint products_subcategories_products_product_id_FK
        foreign key (product_id) references products (product_id),
    constraint products_subcategories_subcategories_subcategory_id_FK
        foreign key (subcategory_id) references subcategories (subcategory_id)
);

insert into subcategories
values ('roast', 'nuts', 'Смажені', 'Тип обробки'),
       ('are_not_roast', 'nuts', 'Сирі', 'Тип обробки'),
       ('in_the_shell', 'nuts', 'В шкарлупі', 'Тип обробки'),
       ('blanched', 'nuts', 'Бланшовані', 'Тип обробки'),
       ('salty', 'nuts', 'Солені', 'Смак'),
       ('cheese', 'nuts', 'Сир', 'Смак'),
       ('paprika', 'nuts', 'Паприка', 'Смак'),
       ('caramel', 'nuts', 'Карамель', 'Смак'),
       ('mediterranean_herbs', 'nuts', 'Середземноморські трави', 'Смак'),
       ('contains_sugar', 'dried_fruits', 'Містить цукор', 'Наявність цукру'),
       ('without_sugar', 'dried_fruits', 'Без цукру', 'Наявність цукру'),
       ('sprinkled_with_sugar', 'dried_fruits', 'Посипаний цукром', 'Наявність цукру'),
       ('dried', 'dried_fruits', 'Сушені', 'Тип обробки'),
       ('naturally_dried', 'dried_fruits', 'Натурально сушені', 'Тип обробки'),
       ('Vyaleni', 'dried_fruits', 'В`ялені', 'Тип обробки');



insert into products_subcategories
values (7, 'contains_sugar'),
       (7, 'dried'),
       (8, 'contains_sugar'),
       (8, 'naturally_dried'),
       (9, 'contains_sugar'),
       (9, 'naturally_dried'),
       (10, 'without_sugar'),
       (10, 'dried');


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
    customer_name         varchar(255) not null,
    customer_surname      varchar(255) not null,
    customer_phone_number varchar(255) not null,
    customer_address      varchar(255) not null,
    create_date           datetime     not null,
    pay_type              varchar(255) not null,
    constraint orders_order_id_PK primary key (order_id),
    constraint orders_user_profiles_user_id_FK foreign key (user_id) references user_profiles (user_id)
);

create table orders_packaging
(
    id              bigint auto_increment,
    order_id        bigint,
    amount_of_units int not null,
    product_id      int not null,
    amount          int not null,
    constraint orders_packaging__order_id__product_id__PK primary key (id),
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