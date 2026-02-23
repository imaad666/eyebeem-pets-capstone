-- CI seed data: minimal products and users for tests
INSERT INTO inventory (product_id, name, price, type, quantity) VALUES
(1, 'e-CAT', 99.99, 'FELINE', 5),
(2, 'e-DOG', 129.99, 'CANINE', 9),
(3, 'e-HAMSTER', 49.99, 'RODENT', 9);

INSERT INTO users (name, email) VALUES
('Test User', 'test@eyebeem.com'),
('Jane Doe', 'jane@eyebeem.com'),
('imaad', 'imaad@imaad.com'),
('aryan', 'aryan@aryan.com'),
('petlover', 'petlover@pet.com');

-- Orders (aligned with db test/orders.json)
INSERT INTO orders (user_id, total, placed_at) VALUES
(1, 99.99, NOW()),
(3, 129.99, NOW()),
(4, 399.96, NOW()),
(5, 49.99, NOW());

-- Order items (aligned with db test/orderitems.json)
INSERT INTO order_items (order_id, product_id, product_name, quantity, unit_price) VALUES
(1, 1, 'e-CAT', 1, 99.99),
(2, 2, 'e-DOG', 1, 129.99),
(3, 1, 'e-CAT', 4, 99.99),
(4, 3, 'e-HAMSTER', 1, 49.99);
