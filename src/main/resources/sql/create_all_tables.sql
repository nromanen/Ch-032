CREATE TABLE product 
(
    id BIGINT NOT NULL AUTO_INCREMENT (1) PRIMARY KEY,
    name CHAR(30),
    price DOUBLE,
    available BOOLEAN
);

CREATE TABLE dish 
(
    id BIGINT NOT NULL AUTO_INCREMENT (1) PRIMARY KEY,
    name CHAR(30),
    dish_group CHAR(30)
);

CREATE TABLE dish_content 
(
    id BIGINT NOT NULL AUTO_INCREMENT (1) PRIMARY KEY,
	dish_id BIGINT,
	ingredient_index BIGINT,
	product_id BIGINT,
	quantity INT,
	FOREIGN KEY (dish_id) REFERENCES dish(id),
	FOREIGN KEY (product_id) REFERENCES product(id)
);

