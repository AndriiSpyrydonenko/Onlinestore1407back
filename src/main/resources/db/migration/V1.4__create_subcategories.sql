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
insert into subcategories value ('mediterranean_herbs', 'nuts', 'Середземноморські трави', 'Смак');
insert into subcategories value ('contains_sugar', 'dried_fruits', 'Містить цукор', 'Наявність цукру');
insert into subcategories value ('without_sugar', 'dried_fruits', 'Без цукру', 'Наявність цукру');
insert into subcategories value ('sprinkled_with_sugar', 'dried_fruits', 'Посипаний цукром', 'Наявність цукру');
insert into subcategories value ('dried', 'dried_fruits', 'Сушені', 'Тип обробки');
insert into subcategories value ('naturally_dried', 'dried_fruits', 'Натурально сушені', 'Тип обробки');
insert into subcategories value ('Vyaleni', 'dried_fruits', 'В`ялені', 'Тип обробки');

insert into products_subcategories value (12, 'roast');
insert into products_subcategories value (13, 'in_the_shell');
insert into products_subcategories value (14, 'roast');
insert into products_subcategories value (15, 'are_not_roast');
insert into products_subcategories value (16, 'roast');
insert into products_subcategories value (12, 'salty');
insert into products_subcategories value (13, 'mediterranean_herbs');
insert into products_subcategories value (14, 'cheese');
insert into products_subcategories value (15, 'paprika');
insert into products_subcategories value (29, 'roast');
insert into products_subcategories value (29, 'salty');
insert into products_subcategories value (34, 'salty');
insert into products_subcategories value (34, 'in_the_shell');
insert into products_subcategories value (40, 'salty');
insert into products_subcategories value (40, 'are_not_roast');
insert into products_subcategories value (46, 'paprika');
insert into products_subcategories value (46, 'caramel');
insert into products_subcategories value (46, 'roast');

insert into products_subcategories value (7, 'contains_sugar');
insert into products_subcategories value (7, 'dried');
insert into products_subcategories value (8, 'contains_sugar');
insert into products_subcategories value (8, 'naturally_dried');
insert into products_subcategories value (9, 'contains_sugar');
insert into products_subcategories value (9, 'naturally_dried');
insert into products_subcategories value (10, 'without_sugar');
insert into products_subcategories value (10, 'dried');
insert into products_subcategories value (11, 'sprinkled_with_sugar');
insert into products_subcategories value (11, 'naturally_dried');
insert into products_subcategories value (27, 'dried');
insert into products_subcategories value (27, 'without_sugar');
insert into products_subcategories value (32, 'sprinkled_with_sugar');
insert into products_subcategories value (32, 'dried');
insert into products_subcategories value (32, 'Vyaleni');
insert into products_subcategories value (32, 'without_sugar');
insert into products_subcategories value (38, 'sprinkled_with_sugar');
insert into products_subcategories value (38, 'naturally_dried');
insert into products_subcategories value (44, 'dried');
insert into products_subcategories value (44, 'without_sugar');
insert into products_subcategories value (50, 'Vyaleni');
insert into products_subcategories value (50, 'sprinkled_with_sugar');
insert into products_subcategories value (52, 'dried');
insert into products_subcategories value (52, 'sprinkled_with_sugar');
insert into products_subcategories value (57, 'Vyaleni');
insert into products_subcategories value (57, 'sprinkled_with_sugar');
insert into products_subcategories value (62, 'sprinkled_with_sugar');
insert into products_subcategories value (62, 'naturally_dried');
insert into products_subcategories value (67, 'Vyaleni');
insert into products_subcategories value (67, 'sprinkled_with_sugar');
insert into products_subcategories value (72, 'dried');
insert into products_subcategories value (72, 'without_sugar');
