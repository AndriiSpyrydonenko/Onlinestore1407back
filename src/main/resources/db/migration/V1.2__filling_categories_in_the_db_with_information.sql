use svitsmachnogo;



insert into categories(category_id, name, fill_picture_url, hover_picture_url)
value ('grocery' ,'Бакалія', 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Bakalija+Status%3DFill.svg',
       'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Bakalija+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('dried_fruits', 'Сухофрукти' , 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Dry+fruits+Status%3DFill.svg',
           'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Dry+fruits++Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('honey', 'Мед' , 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Honey+Status%3DFill.svg',
           'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Honey+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('nuts', 'Горіхи' , 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Nuts+Status%3DFill.svg',
           'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Nuts+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('oil', 'Олія' , 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Oil+Status%3DFill.svg',
           'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Oil+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('spices', 'Спеції' , 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Spices++Status%3DFill.svg',
           'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/categories/Spices+Status%3DHover.svg');

insert into gift_sets(url_picture, discount_percent)
value ('https://svitsmachnogo.space/apps/photos/api/v1/preview/156?etag=c4195f20515569d476a73f46f420dd81&x=512&y=512' , 50);