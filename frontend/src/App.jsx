import React, { Suspense, lazy } from 'react';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import { AppProvider } from './context/AppContext.jsx';
import Layout from './components/Layout';
import './App.css';

const HomePage = lazy(() => import('./pages/HomePage'));
const LoginPage = lazy(() => import('./pages/LoginPage'));
const ProductsPage = lazy(() => import('./pages/ProductsPage'));
const CartPage = lazy(() => import('./pages/CartPage'));
const CheckoutPage = lazy(() => import('./pages/CheckoutPage'));
const OrdersPage = lazy(() => import('./pages/OrdersPage'));

function App() {
  return (
    <BrowserRouter>
      <AppProvider>
        <div className="app-container">
          <Suspense fallback={<div className="container" style={{ padding: '2rem', textAlign: 'center' }}>Loading...</div>}>
            <Routes>
              <Route path="/" element={<Layout />}>
                <Route index element={<HomePage />} />
                <Route path="login" element={<LoginPage />} />
                <Route path="products" element={<ProductsPage />} />
                <Route path="cart" element={<CartPage />} />
                <Route path="checkout" element={<CheckoutPage />} />
                <Route path="orders" element={<OrdersPage />} />
              </Route>
            </Routes>
          </Suspense>
        </div>
      </AppProvider>
    </BrowserRouter>
  );
}

export default App;
