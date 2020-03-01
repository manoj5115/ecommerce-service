DROP TABLE IF EXISTS ecom_product;
 
CREATE TABLE ecom_product (
  code INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  pointsCost INT NOT NULL
);
 
INSERT INTO ecom_product (code, name, pointsCost) VALUES
  ('101', 'Product-A', 20),
  ('102', 'Product-B', 45),
  ('201', 'Product-C', 70),
  ('301', 'Product-D', 10),
  ('302', 'Product-F', 510),
  ('401', 'Product-G', 55),
  ('502', 'Product-E', 110);


DROP TABLE IF EXISTS ecom_customer;
 
CREATE TABLE ecom_customer (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  adPoints INT NOT NULL
);
 
INSERT INTO ecom_customer (id, name, adPoints) VALUES
  ('1', 'James', 85),
  ('2', 'Laura', 20),
  ('3', 'Raj', 40),
  ('4', 'Bryan', 30),
  ('5', 'Britney', 200),
  ('6', 'Sara', 0),
  ('7', 'Adele', 500),
  ('8', 'Stephen', 10);