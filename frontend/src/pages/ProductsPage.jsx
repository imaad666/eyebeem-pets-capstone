import React, { useState, useMemo } from 'react';
import { useApp } from '../context/useApp';

const productImageStyle = {
  height: '200px',
  border: '2px solid black',
  objectFit: 'cover',
  width: '100%',
  display: 'block',
};

export default function ProductsPage() {
  const { products, productsLoading, productsError, handleAddToCart } = useApp();
  const [search, setSearch] = useState('');
  const [typeFilter, setTypeFilter] = useState('');

  const filtered = useMemo(() => {
    return products.filter((p) => {
      const matchSearch = !search.trim() || p.name.toLowerCase().includes(search.toLowerCase());
      const matchType = !typeFilter || p.type === typeFilter;
      return matchSearch && matchType;
    });
  }, [search, typeFilter, products]);

  const types = [...new Set(products.map((p) => p.type))];

  return (
    <div className="container" style={{ marginBottom: '4rem' }}>
      <h2 style={{ fontSize: '1.2rem', marginBottom: '1rem' }}>PRODUCTS</h2>
      {productsLoading && <p className="retro-container">Loading products…</p>}
      {productsError && <p className="retro-container" style={{ color: 'red' }}>Could not load products. Showing fallback.</p>}
      <div className="retro-container" style={{ marginBottom: '2rem', display: 'flex', flexDirection: 'column', gap: '1rem' }}>
        <label htmlFor="product-search" style={{ fontSize: '0.9rem' }}>Search</label>
        <input id="product-search" type="text" placeholder="Search by name..." value={search} onChange={(e) => setSearch(e.target.value)} aria-label="Search products" />
        <label htmlFor="product-type" style={{ fontSize: '0.9rem' }}>Type</label>
        <select id="product-type" value={typeFilter} onChange={(e) => setTypeFilter(e.target.value)} style={{ fontFamily: 'inherit', padding: '0.5rem', border: '2px solid black' }} aria-label="Filter by type">
          <option value="">All</option>
          {types.map((t) => (
            <option key={t} value={t}>{t}</option>
          ))}
        </select>
      </div>
      <div style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(280px, 1fr))', gap: '2rem' }}>
        {filtered.length === 0 ? (
          <p className="retro-container" style={{ gridColumn: '1 / -1' }}>No products match your filters.</p>
        ) : (
          filtered.map((product) => (
            <div key={product.id} className="retro-container" style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
              <div style={{ height: '200px', overflow: 'hidden', border: '2px solid black' }}>
                {product.image && <img src={product.image} alt={product.name} style={productImageStyle} />}
              </div>
              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <div>
                  <h3 style={{ fontSize: '1rem', margin: '0 0 0.5rem 0' }}>{product.name}</h3>
                  <span style={{ fontSize: '1.2rem' }}>${product.price}</span>
                </div>
              </div>
              <button type="button" style={{ width: '100%', marginTop: 'auto' }} onClick={() => handleAddToCart(product)}>ADD TO CART</button>
            </div>
          ))
        )}
      </div>
    </div>
  );
}
