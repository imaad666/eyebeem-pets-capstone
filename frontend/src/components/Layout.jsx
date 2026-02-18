import React from 'react';
import { Outlet, Link } from 'react-router-dom';
import { useApp } from '../context/useApp';

const headerStyles = {
  borderBottom: '2px solid black',
  padding: '1.5rem 0',
  marginBottom: '2rem',
};

export default function Layout() {
  const { user, cartItemCount, orders } = useApp();

  return (
    <div className="retro-wrapper" style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column', position: 'relative' }}>
      <header style={headerStyles}>
        <div className="container" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '1rem' }}>
          <nav style={{ display: 'flex', alignItems: 'center', gap: '1rem' }} aria-label="Main navigation">
            <Link to="/" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', textDecoration: 'none', color: 'inherit' }}>
              <span style={{ fontSize: '1.5rem' }}>👁️ 🐝 Ⓜ️</span>
              <h1 style={{ margin: 0, fontSize: '1.2rem', letterSpacing: '-1px' }}>Pets</h1>
            </Link>
          </nav>
          <div style={{ display: 'flex', alignItems: 'center', gap: '1rem', flexWrap: 'wrap' }}>
            <Link to="/products" className="nav-link-button" style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem', padding: '0.75rem 1rem', textDecoration: 'none' }}>
              PRODUCTS
            </Link>
            <Link to="/login" className="nav-link-button" style={{ fontSize: '0.7rem', padding: '0.75rem 1rem', textDecoration: 'none' }} aria-label={user ? `User: ${user.name}` : 'Login or sign up'}>
              {user ? `USER: ${user.name.toUpperCase()}` : 'USER LOGIN / SIGN UP'}
            </Link>
            <Link to="/cart" className="nav-link-button" style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem', padding: '0.75rem 1rem', textDecoration: 'none' }} aria-label={`Cart: ${cartItemCount} items`}>
              CART [{cartItemCount}]
            </Link>
            <Link to="/checkout" className="nav-link-button" style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem', padding: '0.75rem 1rem', textDecoration: 'none' }}>
              CHECKOUT
            </Link>
            <Link to="/orders" className="nav-link-button" style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem', padding: '0.75rem 1rem', textDecoration: 'none' }} aria-label={`Orders: ${orders.length}`}>
              ORDERS [{orders.length}]
            </Link>
          </div>
        </div>
      </header>

      <main style={{ flex: 1 }}>
        <Outlet />
      </main>

      <footer style={{ borderTop: '2px solid black', padding: '2rem 0', textAlign: 'center' }}>
        <p style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.7rem' }}>
          &copy; 2026 EYE BEE M PETS. ALL RIGHTS RESERVED.
        </p>
      </footer>
    </div>
  );
}
