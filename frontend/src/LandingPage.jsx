import React from 'react';

const LandingPage = () => {
    // Mock Data
    const products = [
        { id: 1, name: 'e-CAT', price: 99.99, type: 'FELINE' },
        { id: 2, name: 'e-DOG', price: 129.99, type: 'CANINE' },
        { id: 3, name: 'e-HAMSTER', price: 49.99, type: 'RODENT' },
    ];

    return (
        <div className="retro-wrapper" style={{ minHeight: '100vh', display: 'flex', flexDirection: 'column' }}>

            {/* HEADER */}
            <header style={{
                borderBottom: '2px solid black',
                padding: '1.5rem 0',
                marginBottom: '2rem'
            }}>
                <div className="container" style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center', flexWrap: 'wrap', gap: '1rem' }}>
                    {/* Logo */}
                    <div style={{ display: 'flex', alignItems: 'center', gap: '0.5rem' }}>
                        <span style={{ fontSize: '1.5rem' }}>👁️ 🐝 Ⓜ️</span>
                        <h1 style={{ margin: 0, fontSize: '1.2rem', letterSpacing: '-1px' }}>EYE BEE M Pets</h1>
                    </div>

                    {/* Right Side */}
                    <div style={{ display: 'flex', alignItems: 'center', gap: '1rem' }}>
                        <input type="text" placeholder="SEARCH..." />
                        <a href="#" style={{ fontFamily: '"Press Start 2P", monospace', fontSize: '0.8rem' }}>CART [0]</a>
                    </div>
                </div>
            </header>

            {/* HERO SECTION */}
            <section className="container" style={{ marginBottom: '4rem' }}>
                <div style={{
                    backgroundColor: '#000',
                    color: '#fff',
                    padding: '4rem 2rem',
                    textAlign: 'center',
                    border: '2px solid black',
                    boxShadow: '8px 8px 0px 0px #000' // Extra pop for hero
                }}>
                    <h2 style={{ fontSize: '2.5rem', marginBottom: '1rem', color: '#fff' }}>ADOPT YOUR ROBOT COMPANION</h2>
                    <p style={{ fontFamily: '"VT323", monospace', fontSize: '1.5rem', letterSpacing: '2px' }}>
                        100% ELECTRIC. 0% SHEDDING.
                    </p>
                </div>
            </section>

            {/* PRODUCT GRID */}
            <main className="container" style={{ flex: 1, marginBottom: '4rem' }}>
                <div style={{
                    display: 'grid',
                    gridTemplateColumns: 'repeat(3, 1fr)',
                    gap: '2rem'
                }}>
                    {products.map((product) => (
                        <div key={product.id} className="retro-container" style={{ display: 'flex', flexDirection: 'column', gap: '1rem' }}>
                            {/* Image Placeholder */}
                            <div style={{
                                height: '200px',
                                backgroundColor: '#e0e0e0',
                                border: '2px solid black',
                                display: 'flex',
                                alignItems: 'center',
                                justifyContent: 'center',
                                backgroundImage: 'radial-gradient(#000 1px, transparent 0)', // Dither effect
                                backgroundSize: '4px 4px',
                                opacity: 0.5
                            }}>
                                <span style={{ fontFamily: '"Press Start 2P"', fontSize: '3rem' }}>?</span>
                            </div>

                            {/* Info */}
                            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                                <div>
                                    <h3 style={{ fontSize: '1rem', margin: '0 0 0.5rem 0' }}>{product.name}</h3>
                                    <span style={{ fontSize: '1.2rem' }}>${product.price}</span>
                                </div>
                            </div>

                            {/* Button */}
                            <button style={{ width: '100%', marginTop: 'auto' }}>
                                BUY NOW
                            </button>
                        </div>
                    ))}
                </div>
            </main>

            {/* FOOTER */}
            <footer style={{ borderTop: '2px solid black', padding: '2rem 0', textAlign: 'center' }}>
                <p style={{ fontFamily: '"Press Start 2P"', fontSize: '0.7rem' }}>
                    &copy; 2026 EYE BEE M PETS. ALL RIGHTS RESERVED.
                </p>
            </footer>

        </div>
    );
};

export default LandingPage;
