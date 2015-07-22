CREATE TABLE dimension (
  id           bigserial PRIMARY KEY, 
  name         text NOT NULL);
 
CREATE TABLE product (
  id             bigserial PRIMARY KEY, 
  name           text NOT NULL, 
  dimension_id   bigint REFERENCES dimension(id)
 );

CREATE TABLE warehouse (
  id                    bigint PRIMARY KEY REFERENCES product(id),
  product_quantity      int NOT NULL); 

CREATE TABLE consumption_type (
  id        bigserial PRIMARY KEY, 
  name      text NOT NULL, 
  is_active boolean NOT NULL);

CREATE TABLE age_category (
  id        bigserial PRIMARY KEY, 
  name      text NOT NULL, 
  is_active boolean NOT NULL);

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
  standart_component_quantity int NOT NULL,
  component_id                bigint REFERENCES component(id),
  age_category_id             bigint REFERENCES  age_category(id)
);
CREATE INDEX component_weight_idx_component_id ON component_weight (component_id);

CREATE TABLE fact_quantity_product (
  id                     bigserial PRIMARY KEY, 
  fact_product_quantity  int NOT NULL,
  sub_menu_id            bigint REFERENCES sub_menu(id),
  component_weight_id    bigint REFERENCES component_weight(id)
);
CREATE INDEX fact_quantity_productu_idx_sub_menu_id ON fact_quantity_product (sub_menu_id, component_weight_id);

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