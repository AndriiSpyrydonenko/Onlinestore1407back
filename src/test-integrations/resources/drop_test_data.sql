-- ALTER TABLE products DROP CONSTRAINT products_categories_category_id_FK;
-- ALTER TABLE pictures DROP CONSTRAINT pictures_products_product_id_FK;
-- ALTER TABLE packaging DROP CONSTRAINT packaging_products_product_id_FK;
-- ALTER TABLE subcategories DROP CONSTRAINT subcategories_categories_category_id_FK;
-- ALTER TABLE products_subcategories DROP CONSTRAINT products_subcategories_products_product_id_FK;
-- ALTER TABLE products_subcategories DROP CONSTRAINT products_subcategories_subcategories_subcategory_id_FK;
-- ALTER TABLE users_roles DROP CONSTRAINT users_roles_users_user_id_FK;
-- ALTER TABLE users_roles DROP CONSTRAINT users_roles_roles_role_id_FK;
-- ALTER TABLE user_profiles DROP CONSTRAINT user_profile_users_user_id_FK;
-- ALTER TABLE carts DROP CONSTRAINT cart_user_profile_user_id_FK;
-- ALTER TABLE wishlists DROP CONSTRAINT wishlists_user_profiles_user_id_FK;
-- ALTER TABLE wishlists DROP CONSTRAINT wishlists_product_id_FK;
-- ALTER TABLE orders DROP CONSTRAINT orders_user_profiles_user_id_FK;
-- ALTER TABLE orders_packaging DROP CONSTRAINT orders_packaging__orders_order_id_FK;
-- ALTER TABLE orders_packaging DROP CONSTRAINT orders_packaging__orders_packaging__product_id__amount_FK;
-- ALTER TABLE carts_packaging DROP CONSTRAINT carts_packaging__user_id_FK;
-- ALTER TABLE carts_packaging DROP CONSTRAINT carts_packaging__product_id__amount_FK;
--
--
-- DROP TABLE orders_packaging;
-- DROP TABLE carts_packaging;
-- DROP TABLE users_roles;
-- DROP TABLE gift_sets;
-- DROP TABLE pictures;
-- DROP TABLE products_subcategories;
-- DROP TABLE wishlists;
-- DROP TABLE orders;
-- DROP TABLE carts;
-- DROP TABLE user_profiles;
-- DROP TABLE users;
-- DROP TABLE roles;
-- DROP TABLE products;
-- DROP TABLE subcategories;
-- DROP TABLE categories;
-- DROP TABLE packaging;

DROP ALL OBJECTS ; --(facepalm)