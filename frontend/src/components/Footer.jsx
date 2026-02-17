import React from 'react';

const Footer = () => {
    const styles = {
        footer: {
            padding: '4rem 2rem',
            textAlign: 'center',
            borderTop: '1px solid #eee',
            marginTop: '4rem',
            color: '#999',
            fontSize: '0.8rem',
        }
    };

    return (
        <footer style={styles.footer}>
            <p>&copy; 2026 Store_. All rights reserved.</p>
        </footer>
    );
};

export default Footer;
