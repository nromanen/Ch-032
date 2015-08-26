<!-- IF YOU CREATE FIRST TIME USE DEFAULT VALUE POSTGRES 9.3 PORT=5433-->
CREATE USER root WITH PASSWORD 'root' CREATEDB;
CREATE DATABASE "orphanagemenu"
  WITH OWNER "root"
  ENCODING 'UTF8';
<!--AFTER THAT RECONNECT WITH NEW USER NAME "root"-->

<!--IF YOU ALREADY HAVE USER "root"-->
DROP DATABASE orphanagemenu CASCADE;
CREATE DATABASE "orphanagemenu"
  WITH OWNER "root"
  ENCODING 'UTF8';

<!--IF YOU CREATES TABLES IN SQL SHELL - -->
<!--BEFORE CREATING TABLES CHANGE ENCODING-->
set client_encoding='WIN866';
  
CREATE TABLE dimension (
  id 	bigserial NOT NULL,
  name 	character varying(255),
  CONSTRAINT dimension_pkey PRIMARY KEY (id));
 
CREATE TABLE product (
  id bigserial NOT NULL,
  name character varying(255),
  dimension_id bigserial NOT NULL,
  CONSTRAINT product_pkey PRIMARY KEY (id),
  CONSTRAINT fk_product_dimension FOREIGN KEY (dimension_id)
      REFERENCES dimension (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE );

CREATE TABLE warehouse (
  id                    bigserial PRIMARY KEY ,
  product_id            bigserial REFERENCES product(id),
  product_quantity      double precision NOT NULL);

CREATE TABLE consumption_type (
  id        				bigserial PRIMARY KEY, 
  name      				text NOT NULL, 
  is_active 				boolean NOT NULL,
  orderby 	int NOT NULL);

CREATE TABLE age_category (
  id bigserial NOT NULL,
  is_active boolean,
  name character varying(255),
  CONSTRAINT age_category_pkey PRIMARY KEY (id));
  
CREATE TABLE product_weight (
  id bigserial NOT NULL,
  standart_product_quantity double precision,
  age_category_id bigserial NOT NULL,
  product_id bigint NOT NULL,
  CONSTRAINT product_weight_pkey PRIMARY KEY (age_category_id, product_id),
  CONSTRAINT fk_product_weight_age_category FOREIGN KEY (age_category_id)
      REFERENCES age_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_product_weight_product FOREIGN KEY (product_id)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE CASCADE );

CREATE TABLE daily_menu (
  id           bigserial PRIMARY KEY, 
  date         date NOT NULL, 
  is_accepted  boolean NOT NULL);
CREATE INDEX daily_menu_idx_name ON daily_menu (date);

CREATE TABLE submenu (
  id                   bigserial PRIMARY KEY, 
  child_quantity       int NOT NULL,
  daily_menu_id        bigint REFERENCES daily_menu(id),
  consumption_type_id  bigint REFERENCES consumption_type(id),
  age_category_id      bigint REFERENCES age_category(id)
);
CREATE INDEX submenu_idx_daily_menu_id ON submenu (daily_menu_id, consumption_type_id, age_category_id);

CREATE TABLE dish (
  id           bigserial PRIMARY KEY, 
  name         text NOT NULL,
  is_available boolean NOT NULL
 );

CREATE TABLE submenu_has_dish (
  submenu_id        bigint REFERENCES submenu(id),
  dish_id           bigint REFERENCES dish(id)
 );
 CREATE INDEX submenu_has_dish_idx_submenu_id ON submenu_has_dish (submenu_id);

CREATE TABLE component (
  id          bigserial UNIQUE, 
  dish_id     bigint REFERENCES dish(id),
  product_id  bigint REFERENCES product(id),
CONSTRAINT component_pkey PRIMARY KEY (id, dish_id, product_id)
);

CREATE TABLE component_weight (
  id                          bigserial PRIMARY KEY, 
  standart_component_quantity double precision NOT NULL,
  component_id                bigint REFERENCES component(id),
  age_category_id             bigint REFERENCES  age_category(id)
);
CREATE INDEX component_weight_idx_component_id ON component_weight (component_id);

CREATE TABLE fact_product_quantity (
  id                     bigserial PRIMARY KEY, 
  fact_product_quantity  double precision NOT NULL,
  submenu_id            bigint REFERENCES submenu(id),
  component_weight_id    bigint REFERENCES component_weight(id)
);
CREATE INDEX fact_productu_quantity_idx_submenu_id ON fact_product_quantity (submenu_id, component_weight_id);

CREATE TABLE user_account (
  id         bigserial PRIMARY KEY, 
  login      text NOT NULL UNIQUE, 
  first_name text NOT NULL,
  last_name  text NOT NULL,
  password   text NOT NULL,
  email      text NOT NULL);

CREATE TABLE role (
  id                bigserial PRIMARY KEY, 
  name              text NOT NULL UNIQUE);
  
CREATE TABLE user_account_has_role (
    user_account_id     bigint REFERENCES user_account(id),
    role_id             bigint REFERENCES role(id),
CONSTRAINT user_account_has_role_pkey PRIMARY KEY (user_account_id, role_id));

INSERT INTO consumption_type(
           id, name, is_active, orderby)
    VALUES (1, 'Снiданок', true, 1);
INSERT INTO consumption_type(
           id, name, is_active, orderby)
    VALUES (2, 'Обiд', true, 2);
INSERT INTO consumption_type(
           id, name, is_active, orderby)
    VALUES (3, 'Пiдвечiрок', true, 3);
INSERT INTO consumption_type(
           id, name, is_active, orderby)
    VALUES (4, 'Вечеря', true, 4);
    
    
INSERT INTO dimension(
           id, name)
    VALUES (1, 'гр.');
INSERT INTO dimension(
           id, name)
    VALUES (2, 'мг.');
    
INSERT INTO role(
            name)
    VALUES ('Administrator');

INSERT INTO role(
            name)
    VALUES ('Operator');

INSERT INTO user_account(login, first_name, last_name, password, email)
    VALUES ('admin', 'Olexii', 'Deer', 'admin', 'admin@admin.com');

INSERT INTO user_account_has_role(user_account_id, role_id)
    VALUES (
		(SELECT id FROM user_account WHERE login = 'admin'),
		(SELECT id FROM role WHERE name = 'Administrator')
		);
		
INSERT INTO user_account(login, first_name, last_name, password, email)
    VALUES ('operator', 'Volodya', 'Back', 'operator', 'operator@operator.com');
		
INSERT INTO user_account_has_role(user_account_id, role_id)
    VALUES (
		(SELECT id FROM user_account WHERE login = 'operator'),
		(SELECT id FROM role WHERE name = 'Operator')
		);
		
INSERT INTO user_account(login, first_name, last_name, password, email)
    VALUES ('jack', 'Jack', 'Back', 'jackjack', 'operator@operator.com');
		
INSERT INTO user_account_has_role(user_account_id, role_id)
    VALUES (
		(SELECT id FROM user_account WHERE login = 'jack'),
		(SELECT id FROM role WHERE name = 'Operator')
		);
    
		
INSERT INTO age_category(
            id, name, is_active)
    VALUES (1, '3-5р.', true);
INSERT INTO age_category(
           id, name, is_active)
    VALUES (2, '6-9р.', true);
INSERT INTO age_category(
           id, name, is_active)
    VALUES (3, '10-12р.', true);
INSERT INTO age_category(
           id, name, is_active)
    VALUES (4, '13-18р.', true);


INSERT INTO product(
            id, name, dimension_id)
    VALUES (1, 'Чай чорний', 1);
INSERT INTO product(
           id, name, dimension_id)
    VALUES (2, 'Молоко', 2);
INSERT INTO product(
           id, name, dimension_id)
    VALUES (3, 'Картопля', 1);
INSERT INTO product(
           id, name, dimension_id)
    VALUES (4, 'Курка', 1);
INSERT INTO product(
           id, name, dimension_id)
    VALUES (5, 'Буряк', 1);
INSERT INTO product(
           id, name, dimension_id)
    VALUES (6, 'Вівсяні пластівці', 1);
    
    
INSERT INTO warehouse(
            product_id, product_quantity)
    VALUES (1, 0);
INSERT INTO warehouse(
             product_id, product_quantity)
    VALUES (2, 0);
INSERT INTO warehouse(
              product_id, product_quantity)
    VALUES (3, 0);
INSERT INTO warehouse(
             product_id, product_quantity)
    VALUES (4, 0);
INSERT INTO warehouse(
            product_id, product_quantity)
    VALUES (5, 0);
INSERT INTO warehouse(
             product_id, product_quantity)
    VALUES (6, 0);

    
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (10, 1, 1);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (15, 2, 1);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (20, 3, 1);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (25, 4, 1);

INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (50, 1, 2);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (100, 2, 2);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (150, 3, 2);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (200, 4, 2);
    
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (200, 1, 3);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (250, 2, 3);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (300, 3, 3);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (350, 4, 3);

INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (100, 1, 4);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (150, 2, 4);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (200, 3, 4);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (250, 4, 4);

INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (100, 1, 5);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (150, 2, 5);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (200, 3, 5);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (250, 4, 5);
    
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (100, 1, 6);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (150, 2, 6);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (200, 3, 6);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (250, 4, 6);
 
    
INSERT INTO dish(
           id, name, is_available)
    VALUES (1, 'Каша Вівсяна', true);
INSERT INTO dish(
            id, name, is_available)
    VALUES (2, 'Картопля з куркою', true);
INSERT INTO dish(
           id, name, is_available)
    VALUES (3, 'Борщ', true);
INSERT INTO dish(
            id, name, is_available)
    VALUES (4, 'Чай', true);

    
INSERT INTO component(
            id, dish_id, product_id)
    VALUES (1, 1, 2);
INSERT INTO component(
            id, dish_id, product_id)
    VALUES (2, 1, 6);
INSERT INTO component(
            id, dish_id, product_id)
    VALUES (3, 2, 3);
INSERT INTO component(
            id, dish_id, product_id)
    VALUES (4, 2, 4);
INSERT INTO component(
            id, dish_id, product_id)
    VALUES (5, 3, 5);
INSERT INTO component(
            id, dish_id, product_id)
    VALUES (6, 3, 3);
INSERT INTO component(
            id, dish_id, product_id)
    VALUES (7, 4, 1);
    
    
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (1, 1, 1, 150);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (2, 1, 2, 200);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (3, 1, 3, 250);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (4, 1, 4, 300);
    
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (5, 2, 1, 50);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (6, 2, 2, 100);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (7, 2, 3, 150);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (8, 2, 4, 200);
    
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (9, 3, 1, 150);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (10, 3, 2, 200);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (11, 3, 3, 250);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (12, 3, 4, 300);
    
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (13, 4, 1, 100);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (14, 4, 2, 150);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (15, 4, 3, 200);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (16, 4, 4, 250);
    
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (17, 5, 1, 500);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (18, 5, 2, 100);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (19, 5, 3, 150);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (20, 5, 4, 200);

INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (21, 6, 1, 100);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (22, 6, 2, 150);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (23, 6, 3, 200);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (24, 6, 4, 250);
    
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (25, 7, 1, 10);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (26, 7, 2, 15);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (27, 7, 3, 20);
INSERT INTO component_weight(
            id, component_id, age_category_id,  standart_component_quantity)
    VALUES (28, 7, 4, 25);
    

INSERT INTO daily_menu(
            id, date, is_accepted)
    VALUES (1, '2015-10-09', true);
INSERT INTO daily_menu(
            id, date, is_accepted)
    VALUES (2, '2015-10-10', true);
    
    
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (1, 20, 1, 1, 1);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (2, 25, 1, 1, 2);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (3, 30, 1, 1, 3);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (4, 35, 1, 1, 4);
    
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (5, 10, 1, 2, 1);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (6, 15, 1, 2, 2);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (7, 20, 1, 2, 3);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (8, 25, 1, 2, 4);
    
    
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (1, 4);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (2, 4);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (3, 4);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (4, 4);

INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (5, 1);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (6, 1);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (7, 1);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (8, 1);
    
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (5, 3);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (6, 3);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (7, 3);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (8, 3);
    
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (5, 2);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (6, 2);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (7, 2);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (8, 2);
    

INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (1, 5, 1, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (2, 6, 2, 250);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (3, 7, 3, 300);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (4, 8, 4, 350);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (5, 5, 5, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (6, 6, 6, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (7, 7, 7, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (8, 8, 8, 250);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (9, 5, 9, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (10, 6, 10, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (11, 7, 11, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (12, 8, 12, 250);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (13, 5, 13, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (14, 6, 14, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (15, 7, 15, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (16, 8, 16, 250);
  
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (17, 5, 17, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (18, 6, 18, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (19, 7, 19, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (20, 8, 20, 250);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (21, 5, 21, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (22, 6, 22, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (23, 7, 23, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (24, 8, 24, 250);
  
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (25, 1, 25, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (26, 2, 26, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (27, 3, 27, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (28, 4, 28, 250);
    
    
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (9, 20, 2, 1, 1);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (10, 25, 2, 1, 2);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (11, 30, 2, 1, 3);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (12, 35, 2, 1, 4);
    
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (13, 10, 2, 2, 1);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (14, 15, 2, 2, 2);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (15, 20, 2, 2, 3);
INSERT INTO submenu(
            id, child_quantity, daily_menu_id, consumption_type_id, age_category_id)
    VALUES (16, 25, 2, 2, 4);
    
    
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (13, 4);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (14, 4);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (15, 4);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (16, 4);

INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (9, 1);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (10, 1);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (11, 1);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (12, 1);
    
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (9, 3);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (10, 3);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (11, 3);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (12, 3);
    
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (9, 2);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (10, 2);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (11, 2);
INSERT INTO submenu_has_dish(
            submenu_id, dish_id)
    VALUES (12, 2);
    

INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (29, 9, 1, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (30, 10, 2, 250);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (31, 11, 3, 300);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (32, 12, 4, 350);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (33, 9, 5, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (34, 10, 6, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (35, 11, 7, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (36, 12, 8, 250);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (37, 9, 9, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (38, 10, 10, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (39, 11, 11, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (40, 12, 12, 250);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (41, 9, 13, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (42, 10, 14, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (43, 11, 15, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (44, 12, 16, 250);
  
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (45, 9, 17, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (46, 10, 18, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (47, 11, 19, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (48, 12, 20, 250);
    
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (49, 9, 21, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (50, 10, 22, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (51, 11, 23, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (52, 12, 24, 250);
  
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (53, 13, 25, 100);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (54, 14, 26, 150);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (55, 15, 27, 200);
INSERT INTO fact_product_quantity(
            id, submenu_id, component_weight_id, fact_product_quantity)
    VALUES (56, 16, 28, 250);

    
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