import React from 'react';
import LandingPage from './LandingPage';
import './App.css'; // Keep App.css for container if needed, but LandingPage handles its own layout

function App() {
  return (
    <div className="app-container">
      <LandingPage />
    </div>
  );
}

export default App;
