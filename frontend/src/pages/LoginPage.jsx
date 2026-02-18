import React from 'react';
import { useApp } from '../context/useApp';

export default function LoginPage() {
  const { user, authForm, setAuthForm, handleAuthSubmit, handleLogout } = useApp();

  if (user) {
    return (
      <div className="container" style={{ maxWidth: '480px', marginTop: '2rem' }}>
        <div className="retro-container">
          <h2 style={{ fontSize: '1.1rem' }}>USER PROFILE</h2>
          <p style={{ marginBottom: '1rem' }}>Logged in as: <strong>{user.name}</strong> ({user.email})</p>
          <button type="button" style={{ fontSize: '0.8rem', padding: '0.6rem 1rem' }} onClick={handleLogout}>LOG OUT</button>
        </div>
      </div>
    );
  }

  return (
    <div className="container" style={{ maxWidth: '480px', marginTop: '2rem' }}>
      <div className="retro-container">
        <h2 style={{ fontSize: '1.1rem' }}>LOGIN / SIGN UP</h2>
        <form onSubmit={handleAuthSubmit} style={{ display: 'flex', flexDirection: 'column', gap: '0.75rem' }}>
          <p style={{ fontSize: '0.8rem' }}>One account for all your digital pets. No backend, just vibes.</p>
          <label htmlFor="login-name" style={{ display: 'flex', flexDirection: 'column', gap: '0.25rem', fontSize: '0.9rem' }}>
            NAME
            <input id="login-name" type="text" value={authForm.name} onChange={(e) => setAuthForm((prev) => ({ ...prev, name: e.target.value }))} aria-required="true" aria-label="Your name" />
          </label>
          <label htmlFor="login-email" style={{ display: 'flex', flexDirection: 'column', gap: '0.25rem', fontSize: '0.9rem' }}>
            EMAIL
            <input id="login-email" type="email" value={authForm.email} onChange={(e) => setAuthForm((prev) => ({ ...prev, email: e.target.value }))} aria-required="true" aria-label="Your email" />
          </label>
          <button type="submit" style={{ marginTop: '0.5rem', fontSize: '0.8rem', padding: '0.7rem 1rem' }}>LOGIN / SIGN UP</button>
        </form>
      </div>
    </div>
  );
}
