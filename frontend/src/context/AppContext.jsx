import React, { useState, useEffect, useCallback } from 'react';
import { AppContext } from './appContext';
import { fetchProducts } from '../api/products';
import { productImages, products as staticProducts } from '../data/products';
import * as authApi from '../api/auth';
import * as cartApi from '../api/cart';
import * as ordersApi from '../api/orders';

function mapApiCartToState(apiCart) {
  if (!apiCart?.items?.length) return [];
  return apiCart.items.map((i) => ({
    product: {
      id: i.productId,
      name: i.productName,
      price: Number(i.unitPrice),
    },
    quantity: i.quantity,
    cartItemId: i.id,
  }));
}

function mapOrderHistoryToState(orders) {
  if (!orders?.length) return [];
  return orders.map((o) => ({
    id: o.id,
    items: (o.items || []).map((i) => ({
      product: {
        id: i.productId,
        name: i.productName,
        price: Number(i.unitPrice),
      },
      quantity: i.quantity,
    })),
    total: Number(o.total),
    placedAt: o.placedAt ? new Date(o.placedAt).toLocaleString() : '',
    user: null,
  }));
}

export function AppProvider({ children }) {
  const [products, setProducts] = useState([]);
  const [productsLoading, setProductsLoading] = useState(true);
  const [productsError, setProductsError] = useState(null);
  const [cart, setCart] = useState([]);
  const [orders, setOrders] = useState([]);
  const [user, setUser] = useState(null);
  const [isCartOpen, setIsCartOpen] = useState(false);
  const [isOrdersOpen, setIsOrdersOpen] = useState(false);
  const [isAuthOpen, setIsAuthOpen] = useState(false);
  const [authForm, setAuthForm] = useState({ name: '', email: '' });

  const cartItemCount = cart.reduce((sum, item) => sum + item.quantity, 0);
  const cartTotal = cart.reduce((sum, item) => sum + item.quantity * item.product.price, 0);

  // Load products from API on mount
  useEffect(() => {
    let cancelled = false;
    setProductsLoading(true);
    setProductsError(null);
    fetchProducts(productImages)
      .then((list) => {
        if (!cancelled) setProducts(list);
      })
      .catch((err) => {
        if (!cancelled) {
          setProductsError(err.message);
          setProducts(staticProducts);
        }
      })
      .finally(() => {
        if (!cancelled) setProductsLoading(false);
      });
    return () => { cancelled = true; };
  }, []);

  const loadCartForUser = useCallback(async (userId) => {
    try {
      const apiCart = await cartApi.getCart(userId);
      setCart(mapApiCartToState(apiCart));
    } catch {
      setCart([]);
    }
  }, []);

  const loadOrdersForUser = useCallback(async (userId) => {
    try {
      const history = await ordersApi.getOrderHistory(userId);
      setOrders(mapOrderHistoryToState(history));
    } catch {
      setOrders([]);
    }
  }, []);

  const handleAddToCart = useCallback(
    async (product) => {
      if (user?.id) {
        try {
          await cartApi.addToCart(user.id, product.id, 1);
          await loadCartForUser(user.id);
        } catch (e) {
          console.error('Add to cart failed', e);
        }
        return;
      }
      setCart((prev) => {
        const existing = prev.find((item) => item.product.id === product.id);
        if (existing) {
          return prev.map((item) =>
            item.product.id === product.id ? { ...item, quantity: item.quantity + 1 } : item
          );
        }
        return [...prev, { product, quantity: 1 }];
      });
    },
    [user?.id, loadCartForUser]
  );

  const handleRemoveFromCart = useCallback(
    async (productIdOrItem) => {
      const productId = typeof productIdOrItem === 'object' ? productIdOrItem.product?.id : productIdOrItem;
      const item = typeof productIdOrItem === 'object' ? productIdOrItem : cart.find((i) => i.product.id === productIdOrItem);

      if (user?.id && item?.cartItemId) {
        try {
          await cartApi.removeCartItem(user.id, item.cartItemId);
          await loadCartForUser(user.id);
        } catch (e) {
          console.error('Remove from cart failed', e);
        }
        return;
      }
      setCart((prev) =>
        prev
          .map((i) =>
            i.product.id === productId ? { ...i, quantity: i.quantity - 1 } : i
          )
          .filter((i) => i.quantity > 0)
      );
    },
    [user?.id, cart, loadCartForUser]
  );

  const handleClearCart = useCallback(async () => {
    if (user?.id) {
      try {
        await cartApi.clearCart(user.id);
        setCart([]);
      } catch (e) {
        console.error('Clear cart failed', e);
      }
      return;
    }
    setCart([]);
  }, [user?.id]);

  const handlePlaceOrder = useCallback(
    async () => {
      if (cart.length === 0) return;
      if (user?.id) {
        try {
          await ordersApi.placeOrder(user.id);
          await loadOrdersForUser(user.id);
          await loadCartForUser(user.id);
          setCart([]);
          setIsCartOpen(false);
        } catch (e) {
          console.error('Place order failed', e);
        }
        return;
      }
      const newOrder = {
        id: Date.now(),
        items: [...cart],
        total: cartTotal,
        placedAt: new Date().toLocaleString(),
        user: null,
      };
      setOrders((prev) => [newOrder, ...prev]);
      setCart([]);
      setIsCartOpen(false);
    },
    [cart, cartTotal, user?.id, loadCartForUser, loadOrdersForUser]
  );

  const handleAuthSubmit = useCallback(
    async (event) => {
      event.preventDefault();
      if (!authForm.name.trim() || !authForm.email.trim()) return;
      try {
        const userData = await authApi.login(authForm.name.trim(), authForm.email.trim());
        setUser({ id: userData.id, name: userData.name, email: userData.email });
        setIsAuthOpen(false);
        await loadCartForUser(userData.id);
        await loadOrdersForUser(userData.id);
      } catch (e) {
        console.error('Login failed', e);
      }
    },
    [authForm.name, authForm.email, loadCartForUser, loadOrdersForUser]
  );

  const handleLogout = useCallback(() => {
    setUser(null);
    setCart([]);
    setOrders([]);
    setIsAuthOpen(false);
  }, []);

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
    products,
    productsLoading,
    productsError,
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
