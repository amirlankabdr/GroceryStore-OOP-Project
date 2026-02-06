CREATE TABLE product (
                         product_id SERIAL PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         price DECIMAL(10,2) NOT NULL,
                         category VARCHAR(50) NOT NULL,
                         product_type VARCHAR(20) NOT NULL,
                         extra_value INTEGER NOT NULL
);