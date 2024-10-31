CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    email VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(50) NOT NULL,
    role VARCHAR(50) NOT NULL
);

CREATE TABLE product (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    description VARCHAR(255),
    stock INT NOT NULL
);

CREATE TABLE purchase (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    total DECIMAL(10, 2) NOT NULL,
    purchase_date DATE NOT NULL
);

CREATE TABLE purchase_detail (
    purchase_id INT REFERENCES purchase(id),
    product_id INT REFERENCES product(id),
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (purchase_id, product_id)
);

CREATE TABLE inventory (
    id SERIAL PRIMARY KEY,
    product_id INT REFERENCES product(id),
    movement_type VARCHAR(50),
    quantity INT NOT NULL,
    date DATE NOT NULL
);

CREATE TABLE promotion (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    discount_percentage DECIMAL(5, 2) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE product_promotion (
    promotion_id INT REFERENCES promotion(id) ON DELETE CASCADE,
    product_id INT REFERENCES product(id) ON DELETE CASCADE,
    PRIMARY KEY (promotion_id, product_id)
);

CREATE TABLE sale (
    id SERIAL PRIMARY KEY,
    user_id INT REFERENCES users(id),
    total DECIMAL(10, 2) NOT NULL,
    sale_date DATE NOT NULL
);

CREATE TABLE sale_detail (
    sale_id INT REFERENCES sale(id),
    product_id INT REFERENCES product(id),
    quantity INT NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    PRIMARY KEY (sale_id, product_id)
);

CREATE TABLE payment (
    id SERIAL PRIMARY KEY,
    sale_id INT REFERENCES sale(id),
    payment_date DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    method VARCHAR(50) NOT NULL
);

-- Insert sample data into users
INSERT INTO users (name, email, password, role) VALUES
('Alice Smith', 'alice@example.com', 'password123', 'CLIENT'),
('Bob Brown', 'bob@example.com', 'password456', 'CLIENT'),
('Admin User', 'admin@example.com', 'adminpass', 'ADMIN');

-- Insert sample data into product
INSERT INTO product (name, price, description, stock) VALUES
('Jeans Classic', 29.99, 'Classic fit jeans', 100),
('Skinny Jeans', 39.99, 'Skinny fit jeans', 50),
('Bootcut Jeans', 34.99, 'Bootcut style jeans', 75);

-- Insert sample data into promotion
INSERT INTO promotion (name, discount_percentage, start_date, end_date) VALUES
('Spring Sale', 15.00, '2024-03-01', '2024-03-31'),
('Summer Discount', 20.00, '2024-06-01', '2024-06-30');

-- Insert sample data into product_promotion
INSERT INTO product_promotion (promotion_id, product_id) VALUES
(1, 1),
(1, 2),
(2, 3);

-- Insert sample data into purchase
INSERT INTO purchase (user_id, total, purchase_date) VALUES
(1, 59.98, '2024-01-15'),
(2, 34.99, '2024-02-20');

-- Insert sample data into purchase_detail
INSERT INTO purchase_detail (purchase_id, product_id, quantity, price) VALUES
(1, 1, 2, 29.99),
(2, 3, 1, 34.99);

-- Insert sample data into inventory
INSERT INTO inventory (product_id, movement_type, quantity, date) VALUES
(1, 'Ingreso', 100, '2024-01-01'),
(2, 'Ingreso', 50, '2024-01-01'),
(3, 'Ingreso', 75, '2024-01-01');

-- Insert sample data into sale
INSERT INTO sale (user_id, total, sale_date) VALUES
(1, 29.99, '2024-01-20'),
(2, 69.98, '2024-01-25');

-- Insert sample data into sale_detail
INSERT INTO sale_detail (sale_id, product_id, quantity, price) VALUES
(1, 1, 1, 29.99),
(2, 2, 1, 39.99),
(2, 3, 1, 29.99);

-- Insert sample data into payment
INSERT INTO payment (sale_id, payment_date, amount, method) VALUES
(1, '2024-01-20', 29.99, 'Credit Card'),
(2, '2024-01-25', 69.98, 'PayPal');
