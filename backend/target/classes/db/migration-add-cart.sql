-- Run this only if you already have inventory, users, orders, order_items and need to add cart support.

CREATE TABLE IF NOT EXISTS cart (
  id INT PRIMARY KEY AUTO_INCREMENT,
  user_id INT NOT NULL UNIQUE,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS cart_items (
  id INT PRIMARY KEY AUTO_INCREMENT,
  cart_id INT NOT NULL,
  product_id INT NOT NULL,
  quantity INT NOT NULL DEFAULT 1,
  FOREIGN KEY (cart_id) REFERENCES cart(id) ON DELETE CASCADE,
  UNIQUE KEY (cart_id, product_id)
);

-- If your inventory table uses id (not product_id) as PK, add a unique key on product_id for cart_items FK:
-- ALTER TABLE inventory ADD UNIQUE KEY (product_id);
