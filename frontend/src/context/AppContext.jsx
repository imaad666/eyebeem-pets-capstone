import React, { useState } from 'react';
import { AppContext } from './appContext';

export function AppProvider({ children }) {
  const [cart, setCart] = useState([]);
  const [orders, setOrders] = useState([]);
  const [user, setUser] = useState(null);
  const [isCartOpen, setIsCartOpen] = useState(false);
  const [isOrdersOpen, setIsOrdersOpen] = useState(false);
  const [isAuthOpen, setIsAuthOpen] = useState(false);
  const [authForm, setAuthForm] = useState({ name: '', email: '' });

  const cartItemCount = cart.reduce((sum, item) => sum + item.quantity, 0);
  const cartTotal = cart.reduce((sum, item) => sum + item.quantity * item.product.price, 0);

  const handleAddToCart = (product) => {
    setCart((prev) => {
      const existing = prev.find((item) => item.product.id === product.id);
      if (existing) {
        return prev.map((item) =>
          item.product.id === product.id
            ? { ...item, quantity: item.quantity + 1 }
            : item
        );
      }
      return [...prev, { product, quantity: 1 }];
    });
  };

  const handleRemoveFromCart = (productId) => {
    setCart((prev) =>
      prev
        .map((item) =>
          item.product.id === productId
            ? { ...item, quantity: item.quantity - 1 }
            : item
        )
        .filter((item) => item.quantity > 0)
    );
  };

  const handleClearCart = () => {
    setCart([]);
  };

  const handlePlaceOrder = () => {
    if (cart.length === 0) return;
    const newOrder = {
      id: Date.now(),
      items: [...cart],
      total: cartTotal,
      placedAt: new Date().toLocaleString(),
      user: user ? { name: user.name, email: user.email } : null,
    };
    setOrders((prev) => [newOrder, ...prev]);
    setCart([]);
    setIsCartOpen(false);
  };

  const handleAuthSubmit = (event) => {
    event.preventDefault();
    if (!authForm.name.trim() || !authForm.email.trim()) return;
    setUser({ ...authForm });
    setIsAuthOpen(false);
  };

  const handleLogout = () => {
    setUser(null);
    setIsAuthOpen(false);
  };

  const openAuth = () => {
    setIsAuthOpen(true);
    setIsCartOpen(false);
    setIsOrdersOpen(false);
  };

  const openCart = () => {
    setIsCartOpen(true);
    setIsOrdersOpen(false);
    setIsAuthOpen(false);
  };

  const openOrders = () => {
    setIsOrdersOpen(true);
    setIsCartOpen(false);
    setIsAuthOpen(false);
  };

  const closeCart = () => setIsCartOpen(false);
  const closeOrders = () => setIsOrdersOpen(false);
  const closeAuth = () => setIsAuthOpen(false);

  const value = {
    cart,
    orders,
    user,
    authForm,
    setAuthForm,
    isCartOpen,
    isOrdersOpen,
    isAuthOpen,
    cartItemCount,
    cartTotal,
    handleAddToCart,
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
  };

  return <AppContext.Provider value={value}>{children}</AppContext.Provider>;
}
