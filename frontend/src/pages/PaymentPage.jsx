import React, { useState } from 'react';
import { useNavigate, useLocation } from 'react-router-dom';
import { useApp } from '../context/useApp';

export default function PaymentPage() {
  const { cart, cartTotal, user, handlePlaceOrder } = useApp();
  const navigate = useNavigate();
  const location = useLocation();
  const address = location.state?.address || { street: '', city: '', zip: '' };
  const [method, setMethod] = useState('card');
  const [paid, setPaid] = useState(false);

  const handlePay = (e) => {
    e.preventDefault();
    if (cart.length === 0) return;
    handlePlaceOrder();
    setPaid(true);
    setTimeout(() => navigate('/orders'), 1500);
  };

  if (cart.length === 0 && !paid) {
    return (
      <div className="container" style={{ marginBottom: '4rem' }}>
        <div className="retro-container">
          <p>No items to pay for. Start from cart or checkout.</p>
          <button type="button" style={{ marginTop: '1rem' }} onClick={() => navigate('/cart')}>GO TO CART</button>
        </div>
      </div>
    );
  }

  if (paid) {
    return (
      <div className="container" style={{ marginBottom: '4rem', textAlign: 'center' }}>
        <div className="retro-container">
          <h2 style={{ fontSize: '1.2rem', color: 'green' }}>PAYMENT SUCCESSFUL</h2>
          <p>Redirecting to orders...</p>
        </div>
      </div>
    );
  }

  return (
    <div className="container" style={{ marginBottom: '4rem' }}>
      <h2 style={{ fontSize: '1.2rem', marginBottom: '1rem' }}>PAYMENT</h2>
      <div className="retro-container" style={{ marginBottom: '1rem' }}>
        <p><strong>Total:</strong> ${cartTotal.toFixed(2)}</p>
        {address?.city && <p style={{ fontSize: '0.9rem' }}>Shipping to: {address.street}, {address.city} {address.zip}</p>}
      </div>
      <form onSubmit={handlePay} className="retro-container" style={{ marginBottom: '1rem' }}>
        <h3 style={{ fontSize: '0.9rem', marginBottom: '0.5rem' }}>PAYMENT METHOD</h3>
        <label style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '0.5rem' }}>
          <input type="radio" name="method" value="card" checked={method === 'card'} onChange={() => setMethod('card')} aria-label="Credit card" />
          <span>Credit Card (simulated)</span>
        </label>
        <label style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '0.5rem' }}>
          <input type="radio" name="method" value="paypal" checked={method === 'paypal'} onChange={() => setMethod('paypal')} aria-label="PayPal" />
          <span>PayPal (simulated)</span>
        </label>
        {user && <p style={{ fontSize: '0.8rem', marginTop: '0.5rem' }}>Charging: {user.email}</p>}
        <button type="submit" style={{ marginTop: '1rem' }}>PAY ${cartTotal.toFixed(2)}</button>
      </form>
      <button type="button" style={{ fontSize: '0.7rem' }} onClick={() => navigate('/checkout')}>BACK TO CHECKOUT</button>
    </div>
  );
}
