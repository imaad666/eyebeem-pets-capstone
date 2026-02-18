import React from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useApp } from '../context/useApp';

export default function CartPage() {
  const navigate = useNavigate();
  const { cart, cartTotal, handleRemoveFromCart, handleClearCart } = useApp();

  return (
    <div className="container" style={{ marginBottom: '4rem' }}>
      <h2 style={{ fontSize: '1.2rem', marginBottom: '1rem' }}>CART</h2>
      {cart.length === 0 ? (
        <div className="retro-container">
          <p>Your cart is empty.</p>
          <Link to="/products" style={{ display: 'inline-block', marginTop: '1rem', fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem' }}>SHOP PRODUCTS</Link>
        </div>
      ) : (
        <>
          <ul style={{ listStyle: 'none', marginBottom: '1rem' }}>
            {cart.map((item) => (
              <li key={item.product.id} className="retro-container" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '0.5rem' }}>
                <span>{item.product.name} x {item.quantity}</span>
                <span>${(item.quantity * item.product.price).toFixed(2)}</span>
                <button type="button" style={{ fontSize: '0.6rem', padding: '0.5rem 0.75rem' }} onClick={() => handleRemoveFromCart(item.product.id)}>REMOVE</button>
              </li>
            ))}
          </ul>
          <div className="retro-container" style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '1rem' }}>
            <strong>TOTAL:</strong>
            <span>${cartTotal.toFixed(2)}</span>
          </div>
          <div style={{ display: 'flex', gap: '0.5rem', flexWrap: 'wrap' }}>
            <button type="button" style={{ fontSize: '0.7rem', padding: '0.6rem 1rem', backgroundColor: '#fff', color: '#000' }} onClick={handleClearCart}>CLEAR CART</button>
            <button type="button" style={{ fontSize: '0.7rem', padding: '0.6rem 1rem' }} onClick={() => navigate('/checkout')}>GO TO CHECKOUT</button>
          </div>
        </>
      )}
    </div>
  );
}
