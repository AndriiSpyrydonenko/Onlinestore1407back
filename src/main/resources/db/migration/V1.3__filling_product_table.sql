use svitsmachnogo;

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 123456, 'Квасоля червона', true, 0, 4.2, 50, 0, sysdate(), 'Україна',
           'Склад: Квасоля червона "Рубін" - вона є чудовою альтернативою м''ясу для вегетаріанців і тих, хто поститься. Червона квасоля містить до 25% білка, який за своєю харчовою цінністю не поступається білку м''яса. До того ж білок квасолі засвоюється на 70 – 80%.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 548712, 'Кіноа Біла', true, 0, 3.2, 278, 0, sysdate(), 'Перу',
           'Склад: Кіноа Біла - зернова культура з сімейства мареві, яка росте на гірських схилах Анд. Батьківщиною білої кіноа прийнято вважати берега озера Тітікака в Південній Америці.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 234567, 'Кіноа Червона', false, 1, 4.8, 10, 0, sysdate(), 'Перу',
           'Склад: Кіноа Червона - зернова культура з сімейства мареві, яка росте на гірських схилах Анд. Батьківщиною білої кіноа прийнято вважати берега озера Тітікака в Південній Америці. Поряд з картоплею і кукурудзою кіноа входила в трійку головних продуктів харчування Індіанців.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 345667, 'Сочевиця (Туреччина)', true, 3, 4.2, 100, 0, sysdate(), 'Туреччина',
           'Склад: плоди сочевиці сирі. Сочевиця – однорідна рослина роду сочевиця, родини бобових. Форма зернятка кругла, плоска.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 566778, 'Сочевиця (Україна)', true, 2, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: плоди сочевиці сирі. Сочевиця – однорідна рослина роду сочевиця, родини бобових. Форма зернятка кругла, плоска.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 122113, 'Чіа Біла', true, 8, 4.2, 100, 0, sysdate(), 'США',
           'Склад: Насіння Білої Чіа - це високопоживний продукт, що містить білки з повним складом незамінних амінокислот, поліненасичені жирні кислоти сімейства омега-3 і омега-6, харчові розчинні і нерозчинні волокна, вітаміни і мінерали');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 124875, 'Журавлина сушена без цукру', true, 0, 4.2, 100, 0, sysdate(), 'Канада', 'Склад: Журавлина сушена без цукра, ягода журавлини використовують у лікуванні захворювань серцево-судинної системи.
Вживання журавлини в їжу позитивно позначається на настрої. Вона рятує від депресій і нервових розладів. Журавлина також стимулює вироблення шлункового соку, а тому особливо ефективна для лікування гастриту зі зниженою кислотністю.
');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 233257, 'Курага Джамбо', true, 6, 4.2, 100, 40, sysdate(), 'Турція', 'Склад: Сорт Джамбо вирізняється найвищою концентрацією калію — приблизно 9% від денної норми. курага сорту Джамбо максимально схожа на свіжий абрикос. Її смак не нудотно солодкий, а з приємною, освіжною кислинкою
Щоб відчути максимум користі для здоров’я, вживати курагу Джамбо потрібно невеликими порціями щодня — приблизно по 2-3 плоди.
');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 465674, 'Фініки натуральні Мазафаті ', true, 0, 4.2, 100, 0, sysdate(), 'Іран', 'Склад: Фініки — їстівні плоди фінікової пальми. З давніх-давен використовується людиною як високоцінний харчовий продукт. У продаж зазвичай надходять як сухофрукти. Найпопулярніший сорт фінікової пальми — «мазафаті» — культивується в промислових масштабах в Ірані.
Фініки Мазафаті — один із найвдаліших і найпопулярніших сортів сухофрукту з Ірану. Цей продукт справедливо вважається одним із найсмачніших видів у всьому світі. Має характерний оксамитовий чорний колір, самі плоди — досить великі, мають м’ясисту структуру.
');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 70006, 'Чорнослив в’ялений ', false, 4, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Чорнослив в''ялений без кісточки — це поширений сухофрукт, відомий своїми корисними якостями, у ньому збережені корисні речовини, наявні у свіжому фрукті. Крім того, мінеральні речовини містяться в концентрованому вигляді. У цих сухофруктах є вітаміни: РР, А, В1, В2, С. Вони містять фруктозу, глюкозу, сахарозу, магній, натрій, кальцій, залізо, фосфор, калій, органічні кислоти, пектини, харчові волокна, білки, крохмаль.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 90075, 'Чорнослив копчений без кісточки', true, 3, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: За розміром вони стандартні та мають приємний, м''який і зрозуміло копчений смак. Попри процес коптування, вони пристойно соковиті. Копчені плоди сливи не втрачають своїх цінних властивостей, майже повністю зберігаючи свій вітамінно-мінеральний склад. Він є одним із найбільш доступних сухофруктів у наших регіонах. Він містить вітаміни: В1, В2, С, PP, Е, А, бета каротин; мінерали: залізо, фосфор, калій, натрій, магній, кальцій.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 57689, 'Волоський горіх «Половинки»', true, 5, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Особливістю волоського горіха є те, що його хімічний склад змінюється залежно від ступеня зрілості плоду, при цьому в засушеному вигляді він зберігає всі свої корисні властивості. Для нього характерна наявність жирів у діапазоні 45-77%, білків 8-21%, вуглеводів-до 10% і води до 5%. Його калорійність складає 648 ккал на 100 грам продукту. В ядрах волоського горіха виявлені вільні амінокислоти (аспарагін, валін, глутамін, гістидин, серин, фенілаланін і цистин), провітамін А, вітаміни Е, РР, К, група В, а також мінеральні речовини і мікроелементи (йод, кальцій, калій, кобальт, магній, залізо, цинк, фосфор). Що стосується вітаміну С, що входить до складу волоського горіха, то згідно з науковими дослідженнями у великій кількості він зустрічається тільки в не зрілих плодах, при дозріванні ж його зміст зводиться до мінімуму.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 294566, 'Горіх макадамія', true, 0, 4.2, 100, 0, sysdate(), 'Китай',
           'Склад: Горіх макадамія - унікальний горіх, що містить велику кількість селену - природного антиоксиданту, цинку - важливого мікроелемента для чоловічого здоров''я - і марганцю, необхідного для нормального функціонування ферментів. За смаком макадамія нагадує лісовий горіх і чудово поєднується з овочевими салатами, шоколадом та карамеллю. Однак, слід пам''ятати, що макадамія - це досить висококалорійний продукт, тому його слід їсти потроху (4-5 горіхів на день).');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 111111, 'Almonds Alesto', true, 9, 4.2, 100, 20, sysdate(), 'Німеччина', 'Almonds Alesto — це не тільки смачні, але ще й корисні горіхи. До складу мигдалю входять мононасичені жири, цим мигдаль і відрізняється від усіх інших горіхів. Мононасичені жири допомагають під час виведення холестерину з організму людини.У разі регулярного вживання мигдалю у вас нормалізується сон, ви станете спокійнішими. Це допоможе почуватися бадьорим і відпочилим.

Виробник  Alesto
Тип Мигдаль
Фасовка	Пакет
Вага (кг) 0.2');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 222222, 'Кеш’ю сирий', true, 0, 4.2, 100, 0, sysdate(), 'В’єтнам', 'Склад: Кеш’ю сирий - багаті білками и вуглеводами, вітамінами А, В2, В1 і залізом, містять цинк, фосфор, кальцій.
Енергетична цінність 1 г кеш’ю — приблизно 5,5 ккал (70 % яких припадає на жири)
Вживання кеш’ю в їжу знижує артеріальний тиск і рівень тригліцеридів, а регулярне вживання знижує ризик ішемічної хвороби серця. Горіх допомагає контролювати «поганий» холестерин і рівень цукру в крові. Заміна 10 % загальної добової калорійності сирим кеш''ю знижує рівень інсуліну в крові в пацієнтів з цукровим діабетом другого типу
');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 333333, 'Кеш’ю смажений ', true, 0, 4.2, 100, 0, sysdate(), 'В’єтнам',
           'Склад: Кеш’ю смажений за складом дуже близький до кеш’ю сирому, а тому багато в чому повторює його корисні властивості. Як і свіжий плід, він посилює імунітет, нормалізує діяльність серця й судин, зменшує рівень шкідливого холестерину в крові, захищає клітини від дії вільних радикалів, омолоджує організм і живить його корисними речовинами (вітамінами Е, В, А, РР; мінералами магнієм, калієм, кальцієм, залізом; амінокислотами триптофаном, гліцином, лізоном; поліненасиченими Омега-3 кислотами та ін.).');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 44444, 'Заправка для борщу', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Томат різаний, Буряк, Морква, Цибуля, Петрушка, Часник , Паприка');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 555768, 'Перець Чорний Горошок', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Перець горошком з плодів рослини Piper nigrum. Круглої форми. Чорного кольору з коричневим відтінком');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 465894, 'Перець Чорний Мелений', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Приправа порошкоподібна із плодів рослини Piper nigrum. Темно-сірого кольору');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 364576, 'Приправа для Ухи', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: морква, петрушка, кріп, перець чорний мелений, коріандр, лаврове листя мелене, базилік, помідор мелений, паприка, часник.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 457675, 'Суміш 10-ти овочів', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Морква, Цибуля сушена мелена, помідор, паприка, петрушка, селера, часник, цибуля порей, Сіль, Перець');



insert into pictures
    (product_id, url_path)
    value (1, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/red_beans.jpg');

insert into pictures
    (product_id, url_path)
    value (2, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/white_quinoa.jpg');

insert into pictures
    (product_id, url_path)
    value (3, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/red_quinoa.jpg');

insert into pictures
    (product_id, url_path)
    value (4, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/%D1%82urkish_lentils.jpg');

insert into pictures
    (product_id, url_path)
    value (5, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/ukrain_lentils.jpg');

insert into pictures
    (product_id, url_path)
    value (6, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/white_chia.jpg');

insert into pictures
    (product_id, url_path)
    value (7, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/cranberry.jpg');

insert into pictures
    (product_id, url_path)
    value (8, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/dried_apricots.jpg');

insert into pictures
    (product_id, url_path)
    value (9, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/date_fruit.jpg');

insert into pictures
    (product_id, url_path)
    value (9, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/date_fruit2.jpg');

insert into pictures
    (product_id, url_path)
    value (9, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/date_fruit3.jpg');

insert into pictures
    (product_id, url_path)
    value (10, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/prunes.jpg');

insert into pictures
    (product_id, url_path)
    value (11, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/smoked_prunes.jpg');

insert into pictures
    (product_id, url_path)
    value (12, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/walnut_halves.jpg');

insert into pictures
    (product_id, url_path)
    value (13, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/macadamia.jpg');

insert into pictures
    (product_id, url_path)
    value (13, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/macadamia_with_key.jpg');

insert into pictures
    (product_id, url_path)
    value (14, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/alesto.jpg');

insert into pictures
    (product_id, url_path)
    value (14, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/alesto_with_heap.jpg');

insert into pictures
    (product_id, url_path)
    value (14, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/alesto_with_big_heap.jpg');

insert into pictures
    (product_id, url_path)
    value (15, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/cashew.jpg');

insert into pictures
    (product_id, url_path)
    value (15, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/cashew2.jpg');

insert into pictures
    (product_id, url_path)
    value (16, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/roasted_cashew.jpg');

insert into pictures
    (product_id, url_path)
    value (17, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/dressing_for_borscht.jpg');

insert into pictures
    (product_id, url_path)
    value (18, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/black_pepper.jpg');

insert into pictures
    (product_id, url_path)
    value (19, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/grind_black_pepper.jpg');

insert into pictures
    (product_id, url_path)
    value (20, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/for_fish.jpg');

insert into pictures
    (product_id, url_path)
    value (21, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/vegetables_mix.jpg');

insert into packaging(product_id, unit, cost)
    value (1, '200г', 230);

insert into packaging(product_id, unit, cost)
    value (1, '500г', 345);

insert into packaging(product_id, unit, cost)
    value (1, '1кг', 450);

insert into packaging(product_id, unit, cost)
    value (2, '200г', 125);

insert into packaging(product_id, unit, cost)
    value (2, '500г', 235);

insert into packaging(product_id, unit, cost)
    value (2, '1кг', 345);

insert into packaging(product_id, unit, cost)
    value (3, '200г', 345);

insert into packaging(product_id, unit, cost)
    value (3, '500г', 455);

insert into packaging(product_id, unit, cost)
    value (3, '1кг', 670);

insert into packaging(product_id, unit, cost)
    value (4, '200г', 567);

insert into packaging(product_id, unit, cost)
    value (4, '500г', 678);

insert into packaging(product_id, unit, cost)
    value (4, '1кг', 890);

insert into packaging(product_id, unit, cost)
    value (5, '200г', 234);

insert into packaging(product_id, unit, cost)
    value (5, '500г', 345);

insert into packaging(product_id, unit, cost)
    value (5, '1кг', 567);

insert into packaging(product_id, unit, cost)
    value (6, '200г', 670);

insert into packaging(product_id, unit, cost)
    value (6, '500г', 780);

insert into packaging(product_id, unit, cost)
    value (6, '1кг', 800);

insert into packaging(product_id, unit, cost)
    value (7, '200г', 356);

insert into packaging(product_id, unit, cost)
    value (7, '500г', 456);

insert into packaging(product_id, unit, cost)
    value (7, '1кг', 556);

insert into packaging(product_id, unit, cost)
    value (8, '200г', 545);

insert into packaging(product_id, unit, cost)
    value (8, '500г', 720);

insert into packaging(product_id, unit, cost)
    value (8, '1кг', 854);

insert into packaging(product_id, unit, cost)
    value (9, '1шт', 80);

insert into packaging(product_id, unit, cost)
    value (9, '3шт', 200);

insert into packaging(product_id, unit, cost)
    value (9, '5шт', 380);

insert into packaging(product_id, unit, cost)
    value (10, '200г', 600);

insert into packaging(product_id, unit, cost)
    value (10, '500г', 650);

insert into packaging(product_id, unit, cost)
    value (10, '1кг', 700);

insert into packaging(product_id, unit, cost)
    value (11, '200г', 250);

insert into packaging(product_id, unit, cost)
    value (11, '500г', 500);

insert into packaging(product_id, unit, cost)
    value (11, '1кг', 700);

insert into packaging(product_id, unit, cost)
    value (12, '200г', 250);

insert into packaging(product_id, unit, cost)
    value (12, '500г', 500);

insert into packaging(product_id, unit, cost)
    value (12, '1кг', 700);

insert into packaging(product_id, unit, cost)
    value (13, '200г', 130);

insert into packaging(product_id, unit, cost)
    value (13, '500г', 260);

insert into packaging(product_id, unit, cost)
    value (13, '1кг', 400);

insert into packaging(product_id, unit, cost)
    value (14, '1шт', 45);

insert into packaging(product_id, unit, cost)
    value (14, '5шт', 200);

insert into packaging(product_id, unit, cost)
    value (14, '10шт', 400);

insert into packaging(product_id, unit, cost)
    value (15, '200г', 400);

insert into packaging(product_id, unit, cost)
    value (15, '500г', 800);

insert into packaging(product_id, unit, cost)
    value (15, '1кг', 1000);

insert into packaging(product_id, unit, cost)
    value (16, '200г', 250);

insert into packaging(product_id, unit, cost)
    value (16, '500г', 600);

insert into packaging(product_id, unit, cost)
    value (16, '1кг', 900);

insert into packaging(product_id, unit, cost)
    value (17, '200г', 50);

insert into packaging(product_id, unit, cost)
    value (17, '500г', 750);

insert into packaging(product_id, unit, cost)
    value (17, '1кг', 1000);

insert into packaging(product_id, unit, cost)
    value (18, '200г', 400);

insert into packaging(product_id, unit, cost)
    value (18, '500г', 600);

insert into packaging(product_id, unit, cost)
    value (18, '1кг', 800);

insert into packaging(product_id, unit, cost)
    value (19, '200г', 500);

insert into packaging(product_id, unit, cost)
    value (19, '500г', 700);

insert into packaging(product_id, unit, cost)
    value (19, '1кг', 1010);

insert into packaging(product_id, unit, cost)
    value (20, '200г', 600);

insert into packaging(product_id, unit, cost)
    value (20, '500г', 900);

insert into packaging(product_id, unit, cost)
    value (20, '1кг', 1200);

insert into packaging(product_id, unit, cost)
    value (21, '200г', 300);

insert into packaging(product_id, unit, cost)
    value (21, '500г', 600);

insert into packaging(product_id, unit, cost)
    value (21, '1кг', 800);