import React from 'react';

const Navbar = () => {
    const styles = {
        nav: {
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'center',
            padding: '1.5rem 2rem',
            maxWidth: '1200px',
            margin: '0 auto',
        },
        logo: {
            fontSize: '1.5rem',
            fontWeight: 'bold',
            letterSpacing: '-0.5px',
        },
        links: {
            display: 'flex',
            gap: '2rem',
            fontSize: '0.9rem',
            color: '#666',
        }
    };

    return (
        <nav style={styles.nav}>
            <div style={styles.logo}>Store_</div>
            <div style={styles.links}>
                <span>Home</span>
                <span>Products</span>
                <span>Cart (0)</span>
            </div>
        </nav>
    );
};

export default Navbar;
