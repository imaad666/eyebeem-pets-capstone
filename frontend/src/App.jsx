import { useState } from 'react'
import './App.css'

function App() {
  const [count, setCount] = useState(0)

  return (
    <div className="app">
      <header className="header">
        <h1 className="logo" data-testid="logo">EYE BEE M</h1>
        <p className="tagline">RETRO ROBOT PETS</p>
      </header>

      <nav className="nav">
        <button data-testid="nav-home">HOME</button>
        <button data-testid="nav-catalog">CATALOG</button>
        <button data-testid="nav-cart">CART</button>
        <button data-testid="nav-login">LOGIN</button>
      </nav>

      <main className="hero">
        <div className="hero-content">
          <h2 className="hero-title">WELCOME TO THE FUTURE</h2>
          <p className="hero-text">Collect rare digital companions from the past</p>
          <button className="cta-button" data-testid="btn-shop">SHOP NOW</button>
        </div>
        
        <div className="pet-showcase">
          <div className="pet-card">
            <div className="pixel-art">🤖</div>
            <p>e-CAT</p>
          </div>
          <div className="pet-card">
            <div className="pixel-art">🤖</div>
            <p>e-DOG</p>
          </div>
          <div className="pet-card">
            <div className="pixel-art">🤖</div>
            <p>e-BIRD</p>
          </div>
        </div>
      </main>

      <footer className="footer">
        <p>&copy; 2026 EYE BEE M PETS. All rights reserved.</p>
      </footer>
    </div>
  )
}

export default App
