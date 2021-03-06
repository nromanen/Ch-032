INSERT INTO age_category (id, is_active, name) VALUES (1, true, '3-5р.'), (2, true, '6-9р.'), (3, true, '10-12р.'), (4, true, '13-18р.');
INSERT INTO consumption_type (id, name, is_active, orderby) VALUES (1, 'Снiданок', true, 1), (2, 'Обiд', true, 2), (3, 'Пiдвечiрок', true, 3), (4, 'Вечеря', true, 4);
INSERT INTO dimension (id, name) VALUES (1, 'гр.'), (2, 'мг.');
INSERT INTO role(name) VALUES ('Administrator'), ('Operator');
INSERT INTO user_account(login, first_name, last_name, password, email) VALUES ('admin', 'Olexii', 'Deer', 'admin', 'admin@admin.com');
INSERT INTO user_account_has_role(user_account_id, role_id) VALUES ((SELECT id FROM user_account WHERE login = 'admin'), (SELECT id FROM role WHERE name = 'Administrator'));
INSERT INTO user_account(login, first_name, last_name, password, email) VALUES ('operator', 'Volodya', 'Back', 'operator', 'operator@operator.com');
INSERT INTO user_account_has_role(user_account_id, role_id) VALUES ((SELECT id FROM user_account WHERE login = 'operator'), (SELECT id FROM role WHERE name = 'Operator'));
INSERT INTO user_account(login, first_name, last_name, password, email) VALUES ('jack', 'Jack', 'Back', 'jackjack', 'operator@operator.com');
INSERT INTO user_account_has_role(user_account_id, role_id) VALUES ((SELECT id FROM user_account WHERE login = 'jack'),	(SELECT id FROM role WHERE name = 'Operator'));
INSERT INTO product(id, name, dimension_id) VALUES (1, 'Чай чорний', 1), (2, 'Молоко', 2), (3, 'Картопля', 1), (4, 'Курка', 1), (5, 'Буряк', 1), (6, 'Вівсяні пластівці', 1), (8, 'Хліб', 1);
INSERT INTO warehouse (id, product_id, product_quantity) VALUES (3, 3, 0), (4, 4, 0), (5, 5, 0), (8, 8, 0), (2, 2, 30000), (1, 1, 2000);
INSERT INTO product_weight (id, standart_product_quantity, age_category_id, product_id) VALUES (1, 10, 1, 1), (2, 15, 2, 1), (3, 20, 3, 1), (4, 25, 4, 1), (5, 50, 1, 2), (6, 100, 2, 2), (7, 150, 3, 2), (8, 200, 4, 2), (9, 200, 1, 3), (10, 250, 2, 3), (11, 300, 3, 3), (12, 350, 4, 3), (13, 101, 1, 4), (14, 151, 2, 4), (15, 201, 3, 4), (16, 251, 4, 4), (17, 100, 1, 5), (18, 150, 2, 5), (19, 200, 3, 5), (20, 250, 4, 5), (21, 101, 1, 6), (22, 150, 2, 6), (23, 200, 3, 6), (24, 250, 4, 6), (26, 300, 3, 8), (27, 400, 4, 8), (28, 100, 1, 8), (29, 200, 2, 8);
INSERT INTO dish (id, name, is_available) VALUES (1, 'Каша Вівсяна', true), (2, 'Картопля з куркою', true), (3, 'Борщ', true), (4, 'Чай', true), (6, 'Хліб', true), (7, 'Каша пшенична', true);
INSERT INTO component (id, dish_id, product_id) VALUES (1, 1, 2), (2, 1, 6), (3, 2, 3), (4, 2, 4), (5, 3, 5), (6, 3, 3), (7, 4, 1), (9, 6, 8), (10, 7, 2);
INSERT INTO component_weight (id, standart_component_quantity, component_id, age_category_id) VALUES (1, 151, 1, 1), (2, 201, 1, 2), (3, 250, 1, 3), (4, 301, 1, 4), (5, 50, 2, 1), (6, 103, 2, 2), (7, 150, 2, 3), (8, 200, 2, 4), (9, 151, 3, 1), (10, 201, 3, 2), (11, 250, 3, 3), (12, 300, 3, 4), (13, 100, 4, 1), (14, 150, 4, 2), (15, 200, 4, 3), (16, 250, 4, 4), (17, 500, 5, 1), (18, 102, 5, 2), (19, 150, 5, 3), (20, 200, 5, 4), (21, 101, 6, 1), (22, 150, 6, 2), (23, 200, 6, 3), (24, 250, 6, 4), (25, 10, 7, 1), (26, 15, 7, 2), (27, 20, 7, 3), (28, 25, 7, 4), (30, 200, 9, 1), (31, 400, 9, 2), (32, 300, 9, 3), (33, 100, 9, 4), (34, 100, 10, 1), (35, 100, 10, 2), (36, 150, 10, 3), (37, 150, 10, 4);
INSERT INTO daily_menu (id, date, is_accepted) VALUES (14, '2015-12-10', true);
INSERT INTO submenu (id, child_quantity, daily_menu_id, consumption_type_id, age_category_id) VALUES (47, 20, 14, 1, 1), (48, 25, 14, 1, 2), (49, 30, 14, 1, 3), (50, 35, 14, 1, 4), (51, 10, 14, 2, 1), (52, 15, 14, 2, 2), (53, 20, 14, 2, 3), (54, 25, 14, 2, 4);
INSERT INTO fact_product_quantity (id, fact_product_quantity, submenu_id, component_weight_id) VALUES (82, 125, 47, 25), (83, 126, 48, 26), (84, 227, 49, 27), (85, 228, 50, 28), (86, 201, 51, 1), (87, 105, 51, 5), (88, 109, 51, 9), (89, 113, 51, 13), (90, 117, 51, 17), (91, 121, 51, 21), (92, 252, 52, 2), (93, 156, 52, 6), (94, 160, 52, 10), (95, 164, 52, 14), (96, 168, 52, 18), (97, 172, 52, 22), (98, 303, 53, 3), (99, 207, 53, 7), (100, 211, 53, 11), (101, 215, 53, 15), (102, 219, 53, 19), (103, 223, 53, 23), (104, 354, 54, 4), (105, 258, 54, 8), (106, 262, 54, 12), (107, 266, 54, 16), (108, 270, 54, 20), (109, 224, 54, 24);
SELECT pg_catalog.setval(pg_get_serial_sequence('component', 'id'), (SELECT MAX(id) FROM component)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('component_weight', 'id'), (SELECT MAX(id) FROM component_weight)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('daily_menu', 'id'), (SELECT MAX(id) FROM daily_menu)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('dish', 'id'), (SELECT MAX(id) FROM dish)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('fact_product_quantity', 'id'), (SELECT MAX(id) FROM fact_product_quantity)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('product', 'id'), (SELECT MAX(id) FROM product)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('product_weight', 'id'), (SELECT MAX(id) FROM product_weight)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('submenu', 'id'), (SELECT MAX(id) FROM submenu)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('user_account', 'id'), (SELECT MAX(id) FROM user_account)+1);
SELECT pg_catalog.setval(pg_get_serial_sequence('warehouse', 'id'), (SELECT MAX(id) FROM warehouse)+1);