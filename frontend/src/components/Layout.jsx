import React from 'react';
import { Outlet, Link, useNavigate } from 'react-router-dom';
import { useApp } from '../context/useApp';

const headerStyles = {
  borderBottom: '2px solid black',
  padding: '1.5rem 0',
  marginBottom: '2rem',
};

const overlayBackdrop = {
  position: 'fixed',
  inset: 0,
  backgroundColor: 'rgba(0,0,0,0.4)',
  display: 'flex',
  alignItems: 'center',
  justifyContent: 'center',
  zIndex: 20,
};

export default function Layout() {
  const navigate = useNavigate();
  const app = useApp();
  const {
    user,
    cart,
    orders,
    authForm,
    setAuthForm,
    isCartOpen,
    isOrdersOpen,
    isAuthOpen,
    cartItemCount,
    cartTotal,
    handleRemoveFromCart,
    handleClearCart,
    handlePlaceOrder,
    handleAuthSubmit,
    handleLogout,
    openAuth,
    openCart,
    openOrders,
    closeCart,
    closeOrders,
    closeAuth,
  } = app;

  const onPlaceOrder = () => {
    handlePlaceOrder();
    navigate('/orders');
  };

  return (
    <div className="retro-wrapper" style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column', position: 'relative' }}>
      <header style={headerStyles}>
        <div className="container" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '1rem' }}>
          <nav style={{ display: 'flex', alignItems: 'center', gap: '1rem' }} aria-label="Main navigation">
            <Link to="/" style={{ display: 'flex', alignItems: 'center', gap: '0.5rem', textDecoration: 'none', color: 'inherit' }}>
              <span style={{ fontSize: '1.5rem' }}>👁️ 🐝 Ⓜ️</span>
              <h1 style={{ margin: 0, fontSize: '1.2rem', letterSpacing: '-1px' }}>EYE BEE M Pets</h1>
            </Link>
          </nav>
          <div style={{ display: 'flex', alignItems: 'center', gap: '1rem', flexWrap: 'wrap' }}>
            <Link to="/products" className="nav-link-button" style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem', padding: '0.75rem 1rem', textDecoration: 'none' }}>
              PRODUCTS
            </Link>
            <button type="button" onClick={openAuth} style={{ fontSize: '0.7rem', padding: '0.75rem 1rem' }} aria-label={user ? `User: ${user.name}` : 'Login or sign up'}>
              {user ? `USER: ${user.name.toUpperCase()}` : 'USER LOGIN / SIGN UP'}
            </button>
            <button type="button" onClick={openCart} style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem' }} aria-label={`Cart: ${cartItemCount} items`}>
              CART [{cartItemCount}]
            </button>
            <button type="button" onClick={openOrders} style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem' }} aria-label={`Orders: ${orders.length}`}>
              ORDERS [{orders.length}]
            </button>
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

      {/* Cart Overlay */}
      {isCartOpen && (
        <div style={overlayBackdrop} onClick={closeCart} role="dialog" aria-modal="true" aria-labelledby="cart-title">
          <div
            className="retro-container"
            style={{ maxWidth: '600px', width: '90%', maxHeight: '80vh', overflowY: 'auto', backgroundColor: '#fff' }}
            onClick={(e) => e.stopPropagation()}
          >
            <h2 id="cart-title" style={{ fontSize: '1.2rem' }}>CART</h2>
            {cart.length === 0 ? (
              <p>Your cart is empty. <Link to="/products">Shop products</Link></p>
            ) : (
              <>
                <ul style={{ listStyle: 'none', marginBottom: '1rem' }}>
                  {cart.map((item) => (
                    <li key={item.product.id} style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', marginBottom: '0.5rem' }}>
                      <span>{item.product.name} x {item.quantity}</span>
                      <span>${(item.quantity * item.product.price).toFixed(2)}</span>
                      <button type="button" style={{ fontSize: '0.6rem', padding: '0.5rem 0.75rem' }} onClick={() => handleRemoveFromCart(item.product.id)}>REMOVE</button>
                    </li>
                  ))}
                </ul>
                <div style={{ display: 'flex', justifyContent: 'space-between', marginBottom: '1rem' }}>
                  <strong>TOTAL:</strong>
                  <span>${cartTotal.toFixed(2)}</span>
                </div>
                <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', gap: '0.5rem', flexWrap: 'wrap' }}>
                  <Link to="/cart" style={{ fontSize: '0.7rem' }}>View full cart</Link>
                  <div style={{ display: 'flex', gap: '0.5rem' }}>
                    <button type="button" style={{ fontSize: '0.7rem', padding: '0.6rem 1rem', backgroundColor: '#fff', color: '#000' }} onClick={handleClearCart}>CLEAR CART</button>
                    <button type="button" style={{ fontSize: '0.7rem', padding: '0.6rem 1rem' }} onClick={onPlaceOrder}>PLACE ORDER</button>
                  </div>
                </div>
                {!user && (
                  <p style={{ marginTop: '1rem', fontSize: '0.8rem' }}>Pro tip: log in or sign up so your robot companions remember you.</p>
                )}
              </>
            )}
          </div>
        </div>
      )}

      {/* Orders Overlay */}
      {isOrdersOpen && (
        <div style={overlayBackdrop} onClick={closeOrders} role="dialog" aria-modal="true" aria-labelledby="orders-title">
          <div
            className="retro-container"
            style={{ maxWidth: '700px', width: '95%', maxHeight: '80vh', overflowY: 'auto', backgroundColor: '#fff' }}
            onClick={(e) => e.stopPropagation()}
          >
            <h2 id="orders-title" style={{ fontSize: '1.2rem' }}>ORDERS</h2>
            {orders.length === 0 ? (
              <p>No orders yet. Fill that cart!</p>
            ) : (
              <div style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                {orders.map((order) => (
                  <div key={order.id} className="retro-container" style={{ boxShadow: 'none' }}>
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
                  </div>
                ))}
              </div>
            )}
          </div>
        </div>
      )}

      {/* Auth Overlay */}
      {isAuthOpen && (
        <div style={overlayBackdrop} onClick={closeAuth} role="dialog" aria-modal="true" aria-labelledby="auth-title">
          <div
            className="retro-container"
            style={{ maxWidth: '480px', width: '90%', backgroundColor: '#fff' }}
            onClick={(e) => e.stopPropagation()}
          >
            <h2 id="auth-title" style={{ fontSize: '1.1rem' }}>{user ? 'USER PROFILE' : 'LOGIN / SIGN UP'}</h2>
            {user ? (
              <>
                <p style={{ marginBottom: '1rem' }}>Logged in as: <strong>{user.name}</strong> ({user.email})</p>
                <button type="button" style={{ fontSize: '0.8rem', padding: '0.6rem 1rem' }} onClick={handleLogout}>LOG OUT</button>
              </>
            ) : (
              <form onSubmit={handleAuthSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
                <p style={{ fontSize: '0.8rem' }}>One account for all your digital pets. No backend, just vibes.</p>
                <label htmlFor="auth-name" style={{ display: 'flex', flexDirection: 'column', gap: '0.25rem', fontSize: '0.9rem' }}>
                  NAME
                  <input id="auth-name" type="text" value={authForm.name} onChange={(e) => setAuthForm((prev) => ({ ...prev, name: e.target.value }))} aria-label="Your name" />
                </label>
                <label htmlFor="auth-email" style={{ display: 'flex', flexDirection: 'column', gap: '0.25rem', fontSize: '0.9rem' }}>
                  EMAIL
                  <input id="auth-email" type="email" value={authForm.email} onChange={(e) => setAuthForm((prev) => ({ ...prev, email: e.target.value }))} aria-label="Your email" />
                </label>
                <button type="submit" style={{ marginTop: '0.5rem', fontSize: '0.8rem', padding: '0.7rem 1rem' }}>LOGIN / SIGN UP</button>
              </form>
            )}
          </div>
        </div>
      )}
    </div>
  );
}
