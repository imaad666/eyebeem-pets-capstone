import React from 'react';
import { Link } from 'react-router-dom';
import { useApp } from '../context/useApp';

export default function OrdersPage() {
  const { orders } = useApp();

  return (
    <div className="container" style={{ marginBottom: '4rem' }}>
      <h2 style={{ fontSize: '1.2rem', marginBottom: '1rem' }}>ORDER HISTORY</h2>
      {orders.length === 0 ? (
        <div className="retro-container">
          <p>No orders yet. Fill that cart!</p>
          <Link to="/products" style={{ display: 'inline-block', marginTop: '1rem', fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem' }}>SHOP PRODUCTS</Link>
        </div>
      ) : (
        <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
          {orders.map((order) => (
            <div key={order.id} className="retro-container">
              <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '0.5rem' }}>
                <span>Order #{order.id}</span>
                <span>{order.placedAt}</span>
              </div>
              {order.user && (
                <p style={{ fontSize: '0.8rem', marginBottom: '0.5rem' }}>For: {order.user.name} ({order.user.email})</p>
              )}
              <ul style={{ listStyle: 'none', marginBottom: '0.5rem' }}>
                {order.items.map((item) => (
                  <li key={item.product.id} style={{ display: 'flex', justifyContent: 'space-between' }}>
                    <span>{item.product.name} x {item.quantity}</span>
                    <span>${(item.quantity * item.product.price).toFixed(2)}</span>
                  </li>
                ))}
              </ul>
              <div style={{ display: 'flex', justifyContent: 'space-between' }}>
                <strong>TOTAL:</strong>
                <span>${order.total.toFixed(2)}</span>
              </div>
              <p style={{ fontSize: '0.75rem', marginTop: '0.5rem' }}>Status: Confirmed (simulated)</p>
            </div>
          ))}
        </div>
      )}
    </div>
  );
}
