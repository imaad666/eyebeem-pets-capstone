import api from './client';

/**
 * Get cart for user. Returns { userId, items: [{ id, productId, productName, unitPrice, quantity }], total }.
 */
export async function getCart(userId) {
  return api.get(`/api/cart?userId=${userId}`);
}

/**
 * Add to cart. Body: { userId, productId, quantity }.
 */
export async function addToCart(userId, productId, quantity = 1) {
  return api.post('/api/cart/items', { userId, productId, quantity });
}

/**
 * Remove one cart line by cart item id.
 */
export async function removeCartItem(userId, cartItemId) {
  return api.delete(`/api/cart/items/${cartItemId}?userId=${userId}`);
}

/**
 * Clear cart for user.
 */
export async function clearCart(userId) {
  return api.delete(`/api/cart?userId=${userId}`);
}
