import React from 'react';
import { Link } from 'react-router-dom';
import { useApp } from '../context/useApp';
import { products } from '../data/products';

const productImageStyle = {
  height: '200px',
  border: '2px solid black',
  objectFit: 'cover',
  width: '100%',
  display: 'block',
};

export default function HomePage() {
  const { handleAddToCart } = useApp();

  return (
    <>
      <section className="container" style={{ marginBottom: '4rem' }} aria-labelledby="hero-heading">
        <div style={{
          backgroundColor: '#000',
          color: '#fff',
          padding: '4rem 2rem',
          textAlign: 'center',
          border: '2px solid black',
          boxShadow: '8px 8px 0px 0px #000',
        }}>
          <h2 id="hero-heading" style={{ fontSize: '2.5rem', marginBottom: '1rem', color: '#fff' }}>ADOPT YOUR ROBOT COMPANION</h2>
          <p style={{ fontFamily: '"VT323", monospace', fontSize: '1.5rem', letterSpacing: '2px' }}>
            100% ELECTRIC. 0% SHEDDING.
          </p>
        </div>
      </section>

      <div className="container" style={{ flex: 1, marginBottom: '4rem' }}>
        <div style={{ display: 'grid', gridTemplateColumns: 'repeat(3, 1fr)', gap: '2rem' }}>
          {products.map((product) => (
            <div key={product.id} className="retro-container" style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
              <div style={{ height: '200px', overflow: 'hidden', border: '2px solid black' }}>
                <img src={product.image} alt={product.name} style={productImageStyle} />
              </div>
              <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <div>
                  <h3 style={{ fontSize: '1rem', margin: '0 0 0.5rem 0' }}>{product.name}</h3>
                  <span style={{ fontSize: '1.2rem' }}>${product.price}</span>
                </div>
              </div>
              <button type="button" style={{ width: '100%', marginTop: 'auto' }} onClick={() => handleAddToCart(product)}>
                ADD TO CART
              </button>
              <Link to="/products" style={{ textAlign: 'center', fontSize: '0.8rem' }}>View all products</Link>
            </div>
          ))}
        </div>
      </div>
    </>
  );
}
