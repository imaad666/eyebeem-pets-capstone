import api from './client';

/**
 * Fetch products from backend and merge with local image URLs.
 * @param {Object} imageByProductId - map productId (1,2,3) to image src
 * @returns {Promise<Array<{ id, name, price, type, quantity, image }>>}
 */
export async function fetchProducts(imageByProductId = {}) {
  const list = await api.get('/api/products');
  return list.map((p) => ({
    id: p.productId,
    name: p.name,
    price: Number(p.price),
    type: p.type,
    quantity: p.quantity ?? 0,
    image: imageByProductId[p.productId] || null,
  }));
}
