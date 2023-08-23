use
    svitsmachnogo;

create table subcategories
(
    subcategory_id varchar(255),
    category_id    varchar(255),
    name           varchar(255) not null,
    title          varchar(255) not null,
    constraint subcategories_subcategory_id_PK primary key (subcategory_id),
    constraint subcategories_categories_category_id_FK foreign key (category_id) references svitsmachnogo.categories (category_id)
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

insert into subcategories value ('roast', 'nuts', 'Смажені', 'Тип обробки');
insert into subcategories value ('are_not_roast', 'nuts', 'Сирі', 'Тип обробки');
insert into subcategories value ('in_the_shell', 'nuts', 'В шкарлупі', 'Тип обробки');
insert into subcategories value ('blanched', 'nuts', 'Бланшовані', 'Тип обробки');
insert into subcategories value ('salty', 'nuts', 'Солені', 'Смак');
insert into subcategories value ('cheese', 'nuts', 'Сир', 'Смак');
insert into subcategories value ('paprika', 'nuts', 'Паприка', 'Смак');
insert into subcategories value ('caramel', 'nuts', 'Карамель', 'Смак');
insert into subcategories value ('mediterranean_herbs', 'nuts', 'Середземноморскі трави', 'Смак');

insert into products_subcategories value (12, 'roast');
insert into products_subcategories value (13, 'in_the_shell');
insert into products_subcategories value (14, 'roast');
insert into products_subcategories value (15, 'are_not_roast');
insert into products_subcategories value (16, 'roast');
insert into products_subcategories value (12, 'salty');
insert into products_subcategories value (13, 'mediterranean_herbs');
insert into products_subcategories value (14, 'cheese');
insert into products_subcategories value (15, 'paprika');
insert into products_subcategories value (16, 'salty');