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
  CONSTRAINT fk_87kb83f20wdxw5rx9sgypw5vo FOREIGN KEY (dimension_id)
      REFERENCES dimension (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION );

CREATE TABLE warehouse (
  id                    bigserial PRIMARY KEY ,
  product_id            bigserial REFERENCES product(id),
  product_quantity      double precision NOT NULL);

CREATE TABLE consumption_type (
  id        				bigserial PRIMARY KEY, 
  name      				text NOT NULL, 
  is_active 				boolean NOT NULL,
  consumption_type_order 	int NOT NULL);

CREATE TABLE age_category (
  id bigserial NOT NULL,
  is_active boolean,
  name character varying(255),
  CONSTRAINT age_category_pkey PRIMARY KEY (id));
  
CREATE TABLE product_weight (
  standart_product_quantity double precision,
  age_category_id bigserial NOT NULL,
  product_id bigint NOT NULL,
  CONSTRAINT product_weight_pkey PRIMARY KEY (age_category_id, product_id),
  CONSTRAINT fk_7cvwxe1ypetlas5yd771es467 FOREIGN KEY (age_category_id)
      REFERENCES age_category (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION,
  CONSTRAINT fk_d3xlpx2okkwxgstt9pxsgeqr0 FOREIGN KEY (product_id)
      REFERENCES product (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION );

CREATE TABLE daily_menu (
  id           bigserial PRIMARY KEY, 
  date         date NOT NULL, 
  is_accepted  boolean NOT NULL);
CREATE INDEX daily_menu_idx_name ON daily_menu (date);

CREATE TABLE sub_menu (
  id                   bigserial PRIMARY KEY, 
  child_quantity       int NOT NULL,
  daily_menu_id        bigint REFERENCES daily_menu(id),
  consumption_type_id  bigint REFERENCES consumption_type(id),
  age_category_id      bigint REFERENCES age_category(id)
);
CREATE INDEX sub_menu_idx_daily_menu_id ON sub_menu (daily_menu_id, consumption_type_id, age_category_id);

CREATE TABLE meal (
  id           bigserial PRIMARY KEY, 
  name         text NOT NULL,
  is_available boolean NOT NULL
 );

CREATE TABLE meal_has_sub_menu (
  meal_id           bigint REFERENCES meal(id), 
  sub_menu_id       bigint REFERENCES sub_menu(id)
 );
 CREATE INDEX meal_has_sub_menu_idx_sub_menu_id ON meal_has_sub_menu (sub_menu_id);

CREATE TABLE component (
  id          bigserial UNIQUE, 
  meal_id     bigint REFERENCES meal(id),
  product_id  bigint REFERENCES product(id),
CONSTRAINT component_pkey PRIMARY KEY (id, meal_id, product_id)
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
  sub_menu_id            bigint REFERENCES sub_menu(id),
  component_weight_id    bigint REFERENCES component_weight(id)
);
CREATE INDEX fact_productu_quantity_idx_sub_menu_id ON fact_product_quantity (sub_menu_id, component_weight_id);

CREATE TABLE user_account (
  id         bigserial PRIMARY KEY, 
  login      text NOT NULL, 
  first_name text NOT NULL,
  last_name  text NOT NULL,
  password   text NOT NULL,
  email      text NOT NULL);

CREATE TABLE role (
  id                bigserial PRIMARY KEY, 
  name              text NOT NULL);
  
CREATE TABLE user_account_has_role (
    user_account_id     bigint REFERENCES user_account(id),
    role_id             bigint REFERENCES role(id),
CONSTRAINT user_account_has_role_pkey PRIMARY KEY (user_account_id, role_id));

INSERT INTO dimension(
            name)
    VALUES ('грам');
INSERT INTO dimension(
            name)
    VALUES ('міліграм');
    
INSERT INTO role(
            name)
    VALUES ('Administrator');

INSERT INTO role(
            name)
    VALUES ('Operator');

INSERT INTO user_account(login, first_name, last_name, password, email)
    VALUES ('admin', 'admin', 'admin', 'admin', 'admin@admin.admin');

INSERT INTO user_account_has_role(user_account_id, role_id)
    VALUES (
		(SELECT id FROM user_account WHERE login = 'admin'),
		(SELECT id FROM role WHERE name = 'Administrator')
		);
		
INSERT INTO user_account(login, first_name, last_name, password, email)
    VALUES ('operator', 'operator', 'operator', 'operator', 'operator@operator.operator');
		
INSERT INTO user_account_has_role(user_account_id, role_id)
    VALUES (
		(SELECT id FROM user_account WHERE login = 'operator'),
		(SELECT id FROM role WHERE name = 'Operator')
		);
    
INSERT INTO age_category(
            name, is_active)
    VALUES ('3-5р.', true);
INSERT INTO age_category(
            name, is_active)
    VALUES ('6-9р.', true);
INSERT INTO age_category(
            name, is_active)
    VALUES ('10-12р.', true);
INSERT INTO age_category(
            name, is_active)
    VALUES ('13-18р.', true);
 
INSERT INTO consumption_type(
            name, is_active, consumption_type_order)
    VALUES ('Снiданок', true, 1);
INSERT INTO consumption_type(
            name, is_active, consumption_type_order)
    VALUES ('Обiд', true, 2);
INSERT INTO consumption_type(
            name, is_active, consumption_type_order)
    VALUES ('Пiдвечiрок', true, 3);
INSERT INTO consumption_type(
            name, is_active, consumption_type_order)
    VALUES ('Вечеря', true, 4);

INSERT INTO product(
            name, dimension_id)
    VALUES ('морква', 1);
INSERT INTO product(
            name, dimension_id)
    VALUES ('молоко', 2);

INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (150, 1, 1);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (200, 2, 1);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (250, 3, 1);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (300, 4, 1);

INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (50, 1, 2);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (200, 2, 2);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (250, 3, 2);
INSERT INTO product_weight(
            standart_product_quantity, age_category_id, product_id)
    VALUES (300, 4, 2);

