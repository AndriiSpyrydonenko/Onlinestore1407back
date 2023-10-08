use svitsmachnogo;

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 123456, 'Квасоля червона', true, 10, 4.2, 50, 20, sysdate(), 'Україна',
           'Склад: Квасоля червона "Рубін" - вона є чудовою альтернативою м''ясу для вегетаріанців і тих, хто поститься. Червона квасоля містить до 25% білка, який за своєю харчовою цінністю не поступається білку м''яса. До того ж білок квасолі засвоюється на 70 – 80%.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 548712, 'Кіноа Біла', true, 11, 3.2, 278, 0, sysdate(), 'Перу',
           'Склад: Кіноа Біла - зернова культура з сімейства мареві, яка росте на гірських схилах Анд. Батьківщиною білої кіноа прийнято вважати берега озера Тітікака в Південній Америці.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 234567, 'Кіноа Червона', false, 1, 4.8, 10, 20, sysdate(), 'Перу',
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
    value ('dried_fruits', 124875, 'Журавлина сушена без цукру', true, 0, 4.2, 100, 50, sysdate(), 'Канада', 'Склад: Журавлина сушена без цукра, ягода журавлини використовують у лікуванні захворювань серцево-судинної системи.
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
    value ('dried_fruits', 90075, 'Чорнослив копчений без кісточки', true, 3, 4.2, 100, 40, sysdate(), 'Україна',
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
(category_id, article, name, exist, is_gift_set, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 222222, 'Кеш’ю сирий', true, true, 0, 4.2, 100, 0, sysdate(), 'В’єтнам', 'Склад: Кеш’ю сирий - багаті білками и вуглеводами, вітамінами А, В2, В1 і залізом, містять цинк, фосфор, кальцій.
Енергетична цінність 1 г кеш’ю — приблизно 5,5 ккал (70 % яких припадає на жири)
Вживання кеш’ю в їжу знижує артеріальний тиск і рівень тригліцеридів, а регулярне вживання знижує ризик ішемічної хвороби серця. Горіх допомагає контролювати «поганий» холестерин і рівень цукру в крові. Заміна 10 % загальної добової калорійності сирим кеш''ю знижує рівень інсуліну в крові в пацієнтів з цукровим діабетом другого типу
');

insert into products
(category_id, article, name, exist, is_gift_set, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 333333, 'Кеш’ю смажений ', true, true, 0, 4.2, 100, 0, sysdate(), 'В’єтнам',
           'Склад: Кеш’ю смажений за складом дуже близький до кеш’ю сирому, а тому багато в чому повторює його корисні властивості. Як і свіжий плід, він посилює імунітет, нормалізує діяльність серця й судин, зменшує рівень шкідливого холестерину в крові, захищає клітини від дії вільних радикалів, омолоджує організм і живить його корисними речовинами (вітамінами Е, В, А, РР; мінералами магнієм, калієм, кальцієм, залізом; амінокислотами триптофаном, гліцином, лізоном; поліненасиченими Омега-3 кислотами та ін.).');

insert into products
(category_id, article, name, exist, is_gift_set, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 44444, 'Заправка для борщу', true, true, 0, 4.2, 100, 0, sysdate(), 'Україна',
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

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 5557681, 'Перець Чорний Горошок', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Перець горошком з плодів рослини Piper nigrum. Круглої форми. Чорного кольору з коричневим відтінком');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 4658941, 'Перець Чорний Мелений', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Приправа порошкоподібна із плодів рослини Piper nigrum. Темно-сірого кольору');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 3645761, 'Приправа для Ухи', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: морква, петрушка, кріп, перець чорний мелений, коріандр, лаврове листя мелене, базилік, помідор мелений, паприка, часник.');

insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 4576751, 'Суміш 10-ти овочів', true, 0, 4.2, 100, 0, sysdate(), 'Україна',
           'Склад: Морква, Цибуля сушена мелена, помідор, паприка, петрушка, селера, часник, цибуля порей, Сіль, Перець');

-- Запит 1
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 123406, 'Квасоля червона', true, 0, 4.2, 50, 0, '2022-03-15 14:30:00', 'Україна',
           'Ця квасоля - справжня смакота для вашого столу.');

-- Запит 2
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 654321, 'Вишневі сушені', true, 0, 3.5, 30, 0, '2021-12-10 09:45:00', 'Польща',
           'Солодкі та соковиті вишні в сушеному вигляді.');

-- Запит 3
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 987654, 'Мед багатоцвітковий', true, 0, 4.8, 75, 0, '2022-05-20 17:15:00', 'Україна',
           'Натуральний мед з різних видів квітів.');

-- Запит 4
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 222252, 'Горіхи грецькі', true, 0, 4.0, 60, 0, '2023-02-05 21:30:00', 'Греція',
           'Смачні та корисні горіхи для вашого здорового харчування.');

-- Запит 5
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 333433, 'Оливкова олія', true, 0, 4.5, 40, 0, '2021-11-01 18:00:00', 'Іспанія',
           'Перша віджимка оливкової олії для справжніх гурманів.');

-- Запит 6
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 789012, 'Гречка', true, 0, 4.6, 65, 0, '2022-04-05 10:15:00', 'Україна',
           'Смачна та корисна гречка для сніданку або обіду.');

-- Запит 7
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 456789, 'Родзинки', true, 0, 4.3, 45, 0, '2021-12-05 12:30:00', 'США',
           'Солодкі та соковиті родзинки для вашого десерту.');

-- Запит 8
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 988254, 'Мед липовий', true, 0, 4.9, 80, 0, '2023-01-20 14:25:00', 'Україна',
           'Ароматний липовий мед з найкращих садів.');

-- Запит 9
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 144433, 'Мигдаль', true, 0, 4.7, 70, 0, '2021-10-15 16:45:00', 'Іспанія',
           'Спробуйте смачні мигдалі для здорового перекусу.');

-- Запит 10
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 490536, 'Рапсова олія', true, 0, 4.4, 55, 0, '2022-02-22 20:20:00', 'Україна',
           'Корисна рапсова олія для приготування смачних страв.');
-- Запит 11
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 135792, 'Перець чорний', true, 0, 4.8, 50, 0, '2023-03-10 11:30:00', 'Індія',
           'Ароматний чорний перець для ваших страв.');

-- Запит 12
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 246810, 'Рис басматі', true, 0, 4.7, 45, 0, '2022-05-05 14:00:00', 'Індія',
           'Кращий рис для смачних пілафів і суші.');

-- Запит 13
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 357951, 'Фініки', true, 0, 4.5, 60, 0, '2021-11-11 17:10:00', 'Іран',
           'Солодкі фініки для ваших десертів і закусок.');

-- Запит 14
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 468024, 'Мед акацієвий', true, 0, 4.9, 70, 0, '2022-12-22 08:45:00', 'Польща',
           'Ароматний акацієвий мед з натуральних джерел.');

-- Запит 15
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 579135, 'Фундук', true, 0, 4.6, 55, 0, '2023-01-01 12:00:00', 'Україна',
           'Корисні фундуки для сніданку або перекусу.');

-- Запит 16
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 680247, 'Оливкова олія Extra Virgin', true, 0, 4.9, 75, 0, '2022-04-18 16:30:00', 'Іспанія',
           'Екстра вірджин оливкова олія для вашого здорового харчування.');

-- Запит 17
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 791358, 'Куркума', true, 0, 4.7, 65, 0, '2021-10-20 19:15:00', 'Індія',
           'Куркума для приготування ароматних страв.');

-- Запит 18
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 802469, 'Цукор', true, 0, 4.5, 50, 0, '2023-03-05 10:00:00', 'Україна',
           'Цукор для солодких десертів і чаю.');

-- Запит 19
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 913580, 'Червоний кавун', true, 0, 4.6, 55, 0, '2021-12-01 13:20:00', 'Туреччина',
           'Солодкий червоний кавун для ваших закусок.');

-- Запит 20
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 101692, 'Мед липовий', true, 0, 4.8, 60, 0, '2022-05-15 11:45:00', 'Україна',
           'Ароматний липовий мед для вашого чаю.');

-- Запит 21
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('nuts', 112803, 'Мигдаль', true, 0, 4.7, 40, 0, '2022-08-25 09:20:00', 'США',
           'Смачні мигдалі для перекусу.');

-- Запит 22
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 121914, 'Соняшникова олія', true, 0, 4.5, 35, 0, '2021-09-30 16:45:00', 'Україна',
           'Соняшникова олія для вашого столу.');

-- Запит 23
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 135025, 'Паприка', true, 0, 4.6, 60, 0, '2023-02-12 14:30:00', 'Угорщина',
           'Паприка для гострих страв.');

-- Запит 24
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 146136, 'Гречка', true, 0, 4.7, 70, 0, '2021-10-10 11:10:00', 'Україна',
           'Смачна гречка для сніданків.');

-- Запит 25
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 157247, 'Сушені абрикоси', true, 0, 4.8, 45, 0, '2022-12-05 18:20:00', 'Туреччина',
           'Сушені абрикоси для вашого перекусу.');

-- Запит 26
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 168358, 'Цукор', true, 0, 4.6, 55, 0, '2022-03-15 10:40:00', 'Україна',
           'Цукор для приготування солодощів.');

-- Запит 27
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 179469, 'Курага', true, 0, 4.7, 75, 0, '2021-11-18 17:15:00', 'Узбекистан',
           'Солодка курага для перекусу.');

-- Запит 28
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 190580, 'Липовий мед', true, 0, 4.9, 85, 0, '2023-05-20 08:30:00', 'Україна',
           'Липовий мед для вашого здоров\'я.');

-- Запит 29
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 201691, 'Оливкова олія', true, 0, 4.8, 60, 0, '2022-06-23 12:55:00', 'Іспанія',
           'Оливкова олія для салатів.');

-- Запит 30
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 212802, 'Куркума', true, 0, 4.7, 70, 0, '2021-12-28 14:10:00', 'Індія',
           'Куркума для страв.');

-- Запит 31
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 223913, 'Кукурудза', true, 0, 4.6, 45, 0, '2022-04-25 09:20:00', 'Україна',
           'Солодка кукурудза для ваших страв.');

-- Запит 32
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 235024, 'Чорнослив', true, 0, 4.5, 65, 0, '2021-11-05 15:30:00', 'Туреччина',
           'Солодкий чорнослив для перекусу.');

-- Запит 33
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 246135, 'Гірський мед', true, 0, 4.7, 75, 0, '2023-02-10 07:45:00', 'Україна',
           'Гірський мед для вашого здоров\'я.');

-- Запит 34
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 257246, 'Соняшникова олія', true, 0, 4.9, 90, 0, '2022-08-15 11:10:00', 'Україна',
           'Соняшникова олія для салатів.');

-- Запит 35
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 268357, 'Перець чорний', true, 0, 4.8, 80, 0, '2021-12-31 13:25:00', 'Вєтнам',
           'Чорний перець для страв.');

-- Запит 36
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 279468, 'Шоколадка', true, 0, 4.6, 55, 10, '2023-07-05 16:45:00', 'Україна',
           'Найсолодший шоколад для задоволення.');

-- Запит 37
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 290579, 'Суниці', true, 0, 4.7, 70, 0, '2022-05-12 10:00:00', 'Україна',
           'Смачні суниці для перекусу.');

-- Запит 38
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 301680, 'Мед гірський', true, 0, 4.9, 88, 0, '2021-09-20 08:30:00', 'Україна',
           'Натуральний гірський мед.');

-- Запит 39
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 312791, 'Оливкова олія', true, 0, 4.7, 65, 15, '2023-01-07 12:15:00', 'Іспанія',
           'Оливкова олія для приготування.');

-- Запит 40
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 323802, 'Куркума', true, 0, 4.8, 74, 0, '2022-07-18 14:00:00', 'Індія',
           'Куркума для спецій.');

-- Запит 41
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 334913, 'Гречка', true, 0, 4.6, 55, 5, '2023-06-29 16:30:00', 'Україна',
           'Смачна гречка для смачних обідів.');

-- Запит 42
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 346024, 'Фініки', true, 0, 4.7, 67, 0, '2021-12-14 11:40:00', 'Іран',
           'Солодкі фініки для перекусу.');

-- Запит 43
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 357135, 'Мед трюфельний', true, 0, 4.9, 90, 0, '2022-03-07 08:20:00', 'Бельгія',
           'Трюфельний мед для справжніх гурманів.');

-- Запит 44
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 368246, 'Кунжутна олія', true, 0, 4.8, 78, 0, '2021-08-25 13:10:00', 'Індія',
           'Кунжутна олія для смачних салатів.');

-- Запит 45
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 379357, 'Тмін', true, 0, 4.6, 64, 0, '2023-04-14 15:55:00', 'Марокко',
           'Тмін для особливих страв.');

-- Запит 46
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('grocery', 390468, 'Козинаки', true, 0, 4.7, 70, 5, '2022-09-30 10:05:00', 'Україна',
           'Солодкі козинаки для перекусу.');

-- Запит 47
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('dried_fruits', 401579, 'Манго', true, 0, 4.6, 62, 0, '2023-05-17 11:15:00', 'Тайланд',
           'Солодке манго для вас.');

-- Запит 48
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('honey', 412680, 'Акацієвий мед', true, 0, 4.9, 85, 0, '2022-11-12 08:40:00', 'Польща',
           'Акацієвий мед для гурманів.');

-- Запит 49
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('oil', 423791, 'Рицинова олія', true, 0, 4.8, 76, 0, '2021-10-28 13:35:00', 'Індія',
           'Рицинова олія для догляду за волоссям.');

-- Запит 50
insert into products
(category_id, article, name, exist, priority_score, rating, review_count, discount_percent, create_date,
 country_producer, description)
    value ('spices', 434902, 'Сіль', true, 0, 4.7, 60, 0, '2022-12-19 15:50:00', 'Україна',
           'Сіль для основних страв.');



insert into pictures
    (product_id, url_path)
    value (1, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%9A%D0%B2%D0%B0%D1%81%D0%BE%D0%BB%D1%8F+%D1%87%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0.jpg');

insert into pictures
    (product_id, url_path)
    value (2, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%9A%D1%96%D0%BD%D0%BE%D0%B0+%D0%B1%D1%96%D0%BB%D0%B0.jpg');

insert into pictures
    (product_id, url_path)
    value (3, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%9A%D1%96%D0%BD%D0%BE%D0%B0+%D1%87%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0.jpg');

insert into pictures
    (product_id, url_path)
    value (4, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%A7%D0%B5%D1%87%D0%B5%D0%B2%D0%B8%D1%86%D1%8F+%D0%A2%D1%83%D1%80%D0%B5%D1%87%D1%87%D0%B8%D0%BD%D0%B0.jpg');

insert into pictures
    (product_id, url_path)
    value (5, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%A7%D0%B5%D1%87%D0%B5%D0%B2%D0%B8%D1%86%D1%8F+%D0%A3%D0%BA%D1%80%D0%B0%D1%97%D0%BD%D0%B0.jpg');

insert into pictures
    (product_id, url_path)
    value (6, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%A7%D1%96%D0%B0+%D0%B1%D1%96%D0%BB%D0%B0.jpg');

insert into pictures
    (product_id, url_path)
    value (7, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%9A%D1%96%D0%BD%D0%BE%D0%B0+%D1%87%D0%B5%D1%80%D0%B2%D0%BE%D0%BD%D0%B0.jpg');

insert into pictures
    (product_id, url_path)
    value (8, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%9A%D1%83%D1%80%D0%B0%D0%B3%D0%B0+%D0%B4%D0%B6%D0%B0%D0%BC%D0%B1%D0%BE.jpg');

insert into pictures
    (product_id, url_path)
    value (9, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D1%84%D1%96%D0%BD%D1%96%D0%BA+%D0%B2+%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D1%86%D1%96_1.jpg');

insert into pictures
    (product_id, url_path)
    value (9, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D1%84%D1%96%D0%BD%D1%96%D0%BA+%D0%B2+%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D1%86%D1%96_1.jpg');

insert into pictures
    (product_id, url_path)
    value (9, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D1%84%D1%96%D0%BD%D1%96%D0%BA+%D0%B2+%D0%BA%D0%BE%D1%80%D0%BE%D0%B1%D1%86%D1%96.jpg');

insert into pictures
    (product_id, url_path)
    value (10, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D1%87%D0%BE%D1%80%D0%BD%D0%BE%D1%81%D0%BB%D0%B8%D0%B2+%D0%B2%D1%8F%D0%BB%D0%B5%D0%BD%D0%B8%D0%B9.jpg');

insert into pictures
    (product_id, url_path)
    value (11, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D1%87%D0%BE%D1%80%D0%BD%D0%BE%D1%81%D0%BB%D0%B8%D0%B2+%D0%BA%D0%BE%D0%BF%D1%87%D0%B5%D0%BD%D0%B8%D0%B9.jpg');

insert into pictures
    (product_id, url_path)
    value (12, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/caf7d1fde344b318f1bc619ac84104af.jpg');

insert into pictures
    (product_id, url_path)
    value (13, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%9C%D0%B0%D0%BA%D0%B0%D0%B4%D0%B0%D0%BC%D1%96.jpg');

insert into pictures
    (product_id, url_path)
    value (13, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%9C%D0%B0%D0%BA%D0%B0%D0%B4%D0%B0%D0%BC%D1%96%2B+%D1%87%D0%BB%D1%8E%D1%87%D0%B8%D0%BA.jpg');

insert into pictures
    (product_id, url_path)
    value (14, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/IMG_0123.jpg');

insert into pictures
    (product_id, url_path)
    value (14, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/IMG_0123%2B.jpg');

insert into pictures
    (product_id, url_path)
    value (14, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/IMG_0123%2B%2B.jpg');

insert into pictures
    (product_id, url_path)
    value (15, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%BA%D0%B5%D1%88%D1%8E+%D0%BD%D0%B5+%D1%81%D0%BC%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9+2.jpg');

insert into pictures
    (product_id, url_path)
    value (15, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%BA%D0%B5%D1%88%D1%8E+%D0%BD%D0%B5+%D1%81%D0%BC%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9.jpg');

insert into pictures
    (product_id, url_path)
    value (16, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%BA%D0%B5%D1%88%D1%8E+%D1%81%D0%BC%D0%B0%D0%B6%D0%B5%D0%BD%D0%B8%D0%B9.jpg');

insert into pictures
    (product_id, url_path)
    value (17, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/dressing_for_borscht.jpg');

insert into pictures
    (product_id, url_path)
    value (18, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%BF%D0%B5%D1%80%D0%B5%D1%86%D1%8C+%D1%87%D0%BE%D1%80%D0%BD%D0%B8%D0%B9+%D0%B3%D0%BE%D1%80%D0%BE%D1%88%D0%BE%D0%BA.jpg');

insert into pictures
    (product_id, url_path)
    value (19, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%BF%D0%B5%D1%80%D0%B5%D1%86%D1%8C+%D1%87%D0%BE%D1%80%D0%BD%D0%B8%D0%B9+%D0%BC%D0%B5%D0%BB%D0%B5%D0%BD%D0%B8%D0%B9.jpg');

insert into pictures
    (product_id, url_path)
    value (20, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%BF%D1%80%D0%B8%D0%BF%D1%80%D0%B0%D0%B2%D0%B0+%D0%B4%D0%BB%D1%8F+%D1%83%D1%85%D0%B8.jpg');

insert into pictures
    (product_id, url_path)
    value (21, 'https://vidronuts.s3.eu-north-1.amazonaws.com/products/%D0%A1%D0%BC%D0%B5%D1%81%D1%8C+10+%D0%BE%D0%B2%D0%BE%D1%87%D0%B5%D0%B9+110%D0%B3%D1%80.jpg');

insert into pictures
    (product_id, url_path)
    value (22, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/black_pepper.jpg');

insert into pictures
    (product_id, url_path)
    value (23, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/grind_black_pepper.jpg');

insert into pictures
    (product_id, url_path)
    value (24, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/for_fish.jpg');

insert into pictures
    (product_id, url_path)
    value (25, 'https://svitsmachnogo.s3.eu-central-1.amazonaws.com/products/vegetables_mix.jpg');

INSERT INTO packaging(product_id, amount, cost)
VALUES (1, '200', 159);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (1, '500', 688);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (1, '1000', 283);
INSERT INTO packaging(product_id, amount, cost)
VALUES (2, '200', 436);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (2, '500', 322);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (2, '1000', 812);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (3, '200', 768);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (3, '500', 865);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (3, '1000', 112);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (4, '200', 853);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (4, '500', 925);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (4, '1000', 563);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (5, '200', 137);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (5, '500', 245);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (5, '1000', 587);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (6, '200', 913);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (6, '500', 517);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (6, '1000', 199);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (7, '200', 122);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (7, '500', 734);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (7, '1000', 607);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (8, '200', 617);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (8, '500', 843);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (8, '1000', 743);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (9, '200', 723);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (9, '500', 950);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (9, '1000', 546);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (10, '200', 856);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (10, '500', 382);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (10, '1000', 615);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (11, '200', 215);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (11, '500', 530);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (11, '1000', 314);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (12, '200', 415);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (12, '500', 381);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (12, '1000', 209);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (13, '200', 816);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (13, '500', 295);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (13, '1000', 968);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (14, '200', 762);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (14, '500', 872);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (14, '1000', 502);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (15, '200', 214);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (15, '500', 108);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (15, '1000', 921);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (16, '200', 244);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (16, '500', 292);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (16, '1000', 690);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (17, '200', 810);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (17, '500', 854);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (17, '1000', 409);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (18, '200', 245);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (18, '500', 543);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (18, '1000', 829);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (19, '200', 208);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (19, '500', 369);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (19, '1000', 800);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (20, '200', 581);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (20, '500', 237);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (20, '1000', 993);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (21, '200', 287);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (21, '500', 241);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (21, '1000', 256);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (22, '200', 917);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (22, '500', 609);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (22, '1000', 682);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (23, '200', 455);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (23, '500', 202);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (23, '1000', 956);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (24, '200', 738);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (24, '500', 802);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (24, '1000', 122);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (25, '200', 758);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (25, '500', 949);
# INSERT INTO packaging(product_id, amount, cost)
# VALUES (25, '1000', 590);

UPDATE products
SET number_of_orders = FLOOR(RAND() * 100) + 1
WHERE product_id BETWEEN 1 AND 75;

-- Початкове значення id
SET @product_id = 3;

-- Генерація 150 запитів
DELIMITER //
CREATE PROCEDURE InsertPackagingData()
BEGIN
    DECLARE i INT DEFAULT 1;

    WHILE i <= 75
        DO
            INSERT INTO packaging(product_id, amount, cost)
            VALUES (@product_id, '200', FLOOR(RAND() * (1000 - 100 + 1) + 100));

            INSERT INTO packaging(product_id, amount, cost)
            VALUES (@product_id, '500', FLOOR(RAND() * (1000 - 100 + 1) + 100));

            INSERT INTO packaging(product_id, amount, cost)
            VALUES (@product_id, '1000', FLOOR(RAND() * (1000 - 100 + 1) + 100));

            SET @product_id = @product_id + 1;
            SET i = i + 1;
        END WHILE;
END //
DELIMITER ;

-- Виклик процедури для генерації запитів
CALL InsertPackagingData();

# UPDATE products p
# SET min_price = (select min(pac.cost) from packaging pac where pac.product_id = p.product_id)
# where p.product_id > 0;

UPDATE products p
SET min_price = (select pac.cost
                 from packaging pac
                 where pac.product_id = p.product_id
                   and amount = (select min(amount) from packaging pg where pg.product_id = p.product_id))
where p.product_id > 0;