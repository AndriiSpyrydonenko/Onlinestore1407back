use svitsmachnogo;


insert into categories(category_id, name, fill_picture_url, hover_picture_url)
value ('grocery' ,'Бакалія', 'https://storage.googleapis.com/svsmach/categories/Bakalija%20Status%3DFill.svg',
       'https://storage.googleapis.com/svsmach/categories/Bakalija+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('dried_fruits', 'Сухофрукти' , 'https://storage.googleapis.com/svsmach/categories/Dry%20fruits%20Status%3DFill.svg',
           'https://storage.googleapis.com/svsmach/categories/Dry%20fruits%20%20Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('honey', 'Мед' , 'https://storage.googleapis.com/svsmach/categories/Honey%20Status%3DFill.svg',
           'https://storage.googleapis.com/svsmach/categories/Honey%20Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('nuts', 'Горіхи' , 'https://storage.googleapis.com/svsmach/categories/Nuts%20Status%3DFill.svg',
           'https://storage.googleapis.com/svsmach/categories/Nuts%20Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('oil', 'Олія' , 'https://storage.googleapis.com/svsmach/categories/Oil%20Status%3DFill.svg',
           'https://storage.googleapis.com/svsmach/categories/Oil%20Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('spices', 'Спеції' , 'https://storage.googleapis.com/svsmach/categories/Spices%20%20Status%3DFill.svg',
           'https://storage.googleapis.com/svsmach/categories/Spices%20Status%3DHover.svg');

insert into gift_sets(url_picture, discount_percent)
value ('https://storage.googleapis.com/svsmach/categories/Form_for_pic.png' , 50);