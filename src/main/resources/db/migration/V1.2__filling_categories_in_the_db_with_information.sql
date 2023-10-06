use svitsmachnogo;



insert into categories(category_id, name, fill_picture_url, hover_picture_url)
value ('grocery' ,'Бакалія', 'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Bakalija+Status%3DFill.svg',
       'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Bakalija+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('dried_fruits', 'Сухофрукти' , 'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Dry+fruits+Status%3DFill.svg',
           'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Dry+fruits++Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('honey', 'Мед' , 'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Honey+Status%3DFill.svg',
           'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Honey+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('nuts', 'Горіхи' , 'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Nuts+Status%3DFill.svg',
           'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Nuts+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('oil', 'Олія' , 'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Oil+Status%3DFill.svg',
           'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Oil+Status%3DHover.svg');
insert into categories(category_id, name, fill_picture_url, hover_picture_url)
    value ('spices', 'Спеції' , 'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Spices++Status%3DFill.svg',
           'https://vidronuts.s3.eu-north-1.amazonaws.com/category/Spices+Status%3DHover.svg');

insert into gift_sets(url_picture, discount_percent)
value ('https://vidronuts.s3.eu-north-1.amazonaws.com/category/form_for_pic.png' , 50);