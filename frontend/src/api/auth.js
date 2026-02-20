import api from './client';

/**
 * Login or register. Returns user with id, name, email, orderHistory.
 */
export async function login(name, email) {
  return api.post('/api/users/login', { name, email });
}

/**
 * Get user profile and order history.
 */
export async function getUser(userId) {
  return api.get(`/api/users/${userId}`);
}
