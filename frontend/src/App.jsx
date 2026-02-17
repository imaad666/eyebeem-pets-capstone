import React from 'react';
import Navbar from './components/Navbar';
import Hero from './components/Hero';
import ProductCard from './components/ProductCard';
import Footer from './components/Footer';
import './App.css';

function App() {
  const products = [
    { id: 1, name: 'Model X-1', price: 299 },
    { id: 2, name: 'Rover Bot', price: 450 },
    { id: 3, name: 'Drone Mini', price: 199 },
    { id: 4, name: 'Servo Arm', price: 150 },
    { id: 5, name: 'Sensor Pack', price: 89 },
    { id: 6, name: 'Logic Core', price: 599 },
  ];

  const styles = {
    grid: {
      display: 'grid',
      gridTemplateColumns: 'repeat(auto-fill, minmax(280px, 1fr))',
      gap: '3rem',
      padding: '4rem 2rem',
      maxWidth: '1200px',
      margin: '0 auto',
    },
    sectionTitle: {
      textAlign: 'center',
      fontSize: '0.9rem',
      textTransform: 'uppercase',
      letterSpacing: '2px',
      color: '#999',
      marginTop: '4rem',
    }
  };

  return (
    <div>
      <Navbar />
      <Hero />

      <h3 style={styles.sectionTitle}>Latest Arrivals</h3>

      <main style={styles.grid}>
        {products.map(product => (
          <ProductCard key={product.id} product={product} />
        ))}
      </main>

      <Footer />
    </div>
  );
}

export default App;
