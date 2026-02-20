import api from './client';

/**
 * Place order for user (uses current backend cart). Returns order summary.
 */
export async function placeOrder(userId) {
  return api.post(`/api/orders?userId=${userId}`);
}

/**
 * Get order history for user.
 */
export async function getOrderHistory(userId) {
  return api.get(`/api/orders/history?userId=${userId}`);
}
