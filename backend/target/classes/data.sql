-- Seed data (run after schema.sql; safe to run multiple times with INSERT IGNORE)

-- Products (match frontend: e-CAT, e-DOG, e-HAMSTER)
INSERT IGNORE INTO inventory (product_id, name, price, type, quantity) VALUES
  (1, 'e-CAT', 99.99, 'FELINE', 10),
  (2, 'e-DOG', 129.99, 'CANINE', 10),
  (3, 'e-HAMSTER', 49.99, 'RODENT', 10);

-- Optional: sample user for testing
INSERT IGNORE INTO users (id, name, email) VALUES
  (1, 'Test User', 'test@eyebeem.com');
