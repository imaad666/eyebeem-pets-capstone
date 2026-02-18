import React, { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { useApp } from '../context/useApp';

export default function CheckoutPage() {
  const { cart, cartTotal, user, handlePlaceOrder } = useApp();
  const navigate = useNavigate();
  const [address, setAddress] = useState({ street: '', city: '', zip: '' });
  const [step, setStep] = useState('address'); // 'address' | 'payment' | 'success'
  const [paymentMethod, setPaymentMethod] = useState('card');

  const canProceed = cart.length > 0 && address.street.trim() && address.city.trim() && address.zip.trim();

  const handleProceedToPayment = (e) => {
    e.preventDefault();
    if (!canProceed) return;
    setStep('payment');
  };

  const handlePay = (e) => {
    e.preventDefault();
    if (cart.length === 0) return;
    handlePlaceOrder();
    setStep('success');
    setTimeout(() => navigate('/orders'), 1500);
  };

  if (cart.length === 0 && step !== 'success') {
    return (
      <div className="container" style={{ marginBottom: '4rem' }}>
        <div className="retro-container">
          <p>Your cart is empty. Add items before checkout.</p>
          <Link to="/products" style={{ display: 'inline-block', marginTop: '1rem', fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem' }}>SHOP PRODUCTS</Link>
        </div>
      </div>
    );
  }

  if (step === 'success') {
    return (
      <div className="container" style={{ marginBottom: '4rem', textAlign: 'center' }}>
        <div className="retro-container">
          <h2 style={{ fontSize: '1.2rem' }}>ORDER CONFIRMED</h2>
          <p>Redirecting to orders...</p>
        </div>
      </div>
    );
  }

  if (step === 'payment') {
    return (
      <div className="container" style={{ marginBottom: '4rem' }}>
        <h2 style={{ fontSize: '1.2rem', marginBottom: '1rem' }}>CHECKOUT – PAYMENT</h2>
        <div className="retro-container" style={{ marginBottom: '1rem' }}>
          <p><strong>Total:</strong> ${cartTotal.toFixed(2)}</p>
          <p style={{ fontSize: '0.9rem' }}>Shipping to: {address.street}, {address.city} {address.zip}</p>
        </div>
        <form onSubmit={handlePay} className="retro-container" style={{ marginBottom: '1rem' }}>
          <h3 style={{ fontSize: '0.9rem', marginBottom: '0.5rem' }}>PAYMENT METHOD</h3>
          <label style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '0.5rem' }}>
            <input type="radio" name="method" value="card" checked={paymentMethod === 'card'} onChange={() => setPaymentMethod('card')} aria-label="Credit card" />
            <span>Credit Card (simulated)</span>
          </label>
          <label style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', marginBottom: '0.5rem' }}>
            <input type="radio" name="method" value="paypal" checked={paymentMethod === 'paypal'} onChange={() => setPaymentMethod('paypal')} aria-label="PayPal" />
            <span>PayPal (simulated)</span>
          </label>
          {user && <p style={{ fontSize: '0.8rem', marginTop: '0.5rem' }}>Charging: {user.email}</p>}
          <button type="submit" style={{ marginTop: '1rem' }}>PAY ${cartTotal.toFixed(2)}</button>
        </form>
        <button type="button" style={{ fontSize: '0.7rem' }} onClick={() => setStep('address')}>BACK TO ADDRESS</button>
      </div>
    );
  }

  return (
    <div className="container" style={{ marginBottom: '4rem' }}>
      <h2 style={{ fontSize: '1.2rem', marginBottom: '1rem' }}>CHECKOUT</h2>
      <div className="retro-container" style={{ marginBottom: '2rem' }}>
        <h3 style={{ fontSize: '0.9rem', marginBottom: '0.5rem' }}>ORDER SUMMARY</h3>
        <ul style={{ listStyle: 'none', marginBottom: '1rem' }}>
          {cart.map((item) => (
            <li key={item.product.id} style={{ display: 'flex', justifyContent: 'space-between' }}>
              <span>{item.product.name} x {item.quantity}</span>
              <span>${(item.quantity * item.product.price).toFixed(2)}</span>
            </li>
          ))}
        </ul>
        <div style={{ display: 'flex', justifyContent: 'space-between' }}>
          <strong>TOTAL:</strong>
          <span>${cartTotal.toFixed(2)}</span>
        </div>
      </div>
      <form onSubmit={handleProceedToPayment} className="retro-container" style={{ marginBottom: '1rem' }}>
        <h3 style={{ fontSize: '0.9rem', marginBottom: '0.5rem' }}>SHIPPING ADDRESS</h3>
        <label htmlFor="checkout-street" style={{ display: 'block', marginBottom: '0.25rem', fontSize: '0.9rem' }}>Street</label>
        <input id="checkout-street" type="text" value={address.street} onChange={(e) => setAddress((a) => ({ ...a, street: e.target.value }))} required aria-required="true" style={{ width: '100%', marginBottom: '0.75rem' }} />
        <label htmlFor="checkout-city" style={{ display: 'block', marginBottom: '0.25rem', fontSize: '0.9rem' }}>City</label>
        <input id="checkout-city" type="text" value={address.city} onChange={(e) => setAddress((a) => ({ ...a, city: e.target.value }))} required aria-required="true" style={{ width: '100%', marginBottom: '0.75rem' }} />
        <label htmlFor="checkout-zip" style={{ display: 'block', marginBottom: '0.25rem', fontSize: '0.9rem' }}>ZIP</label>
        <input id="checkout-zip" type="text" value={address.zip} onChange={(e) => setAddress((a) => ({ ...a, zip: e.target.value }))} required aria-required="true" style={{ width: '100%', marginBottom: '0.75rem' }} />
        {user && <p style={{ fontSize: '0.8rem', marginBottom: '0.5rem' }}>Shipping to: {user.name} ({user.email})</p>}
        <button type="submit" style={{ marginTop: '0.5rem' }} disabled={!canProceed}>PROCEED TO PAYMENT</button>
      </form>
      <Link to="/cart" style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.7rem' }}>BACK TO CART</Link>
    </div>
  );
}
