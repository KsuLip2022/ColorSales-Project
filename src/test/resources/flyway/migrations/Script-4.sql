DROP TABLE IF EXISTS order_options;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS options;
DROP TABLE IF EXISTS brands;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
  customer_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_id varchar(40) NOT NULL,
  first_name varchar(45) NOT NULL, 
  last_name varchar(45) NOT NULL,
  phone varchar(20),
  PRIMARY KEY (customer_pk)
);

CREATE TABLE brands (
  brand_pk int unsigned NOT NULL AUTO_INCREMENT,
  brand_id enum('NEVSKAYA_PALITRA', 'WINSOR_&_NEWTON', 'CRAYOLA', 'CARAN_DACHE', 'ARTEZA', 'VAN_GOGH', 'SCHMINCKE') NOT NULL,
  type_paint varchar(40) NOT NULL,
  base_price decimal(9, 2) NOT NULL,
  PRIMARY KEY (brand_pk),
  UNIQUE KEY (brand_id, type_paint)
);

CREATE TABLE options (
  option_pk int unsigned NOT NULL AUTO_INCREMENT,
  option_id varchar(30) NOT NULL,
  category enum('WORKING_PLACE', 'BRUSHES', 'PAPER', 'STORAGE', 'PAINT') NOT NULL,
  manufacturer varchar(60) NOT NULL,
  name varchar(60) NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (option_pk),
  UNIQUE KEY (option_id)
);

CREATE TABLE orders (
  order_pk int unsigned NOT NULL AUTO_INCREMENT,
  customer_fk int unsigned NOT NULL,
  brand_fk int unsigned NOT NULL,
  price decimal(9, 2) NOT NULL,
  PRIMARY KEY (order_pk),
  FOREIGN KEY (customer_fk) REFERENCES customers (customer_pk) ON DELETE CASCADE,
  FOREIGN KEY (brand_fk) REFERENCES brands (brand_pk) ON DELETE CASCADE
);

CREATE TABLE order_options (
  option_fk int unsigned NOT NULL,
  order_fk int unsigned NOT NULL,
  FOREIGN KEY (option_fk) REFERENCES options (option_pk) ON DELETE CASCADE,
  FOREIGN KEY (order_fk) REFERENCES orders (order_pk) ON DELETE CASCADE
);
