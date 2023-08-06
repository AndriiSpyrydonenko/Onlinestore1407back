use svitsmachnogo;

create table gift_sets
(
    gift_set_id      int auto_increment,
    url_picture      varchar(255) not null,
    discount_percent int          not null default 0,
    constraint gift_sets_discount_percent_CHECK check ( discount_percent between -1 and 101),
    constraint gift_sets_gift_set_id_FK primary key (gift_set_id)

);