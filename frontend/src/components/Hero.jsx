import React from 'react';

const Hero = () => {
    const styles = {
        section: {
            padding: '6rem 2rem',
            textAlign: 'center',
            maxWidth: '800px',
            margin: '0 auto',
        },
        title: {
            fontSize: '3.5rem',
            fontWeight: '300',
            marginBottom: '1rem',
            letterSpacing: '-1px',
            lineHeight: '1.2',
        },
        subtitle: {
            fontSize: '1.1rem',
            color: '#666',
            marginBottom: '2rem',
            lineHeight: '1.6',
        },
        button: {
            padding: '1rem 2.5rem',
            backgroundColor: '#000',
            color: '#fff',
            border: 'none',
            borderRadius: '0', // Sharp edges as requested "minimal"
            fontSize: '1rem',
            cursor: 'pointer',
        }
    };

    return (
        <section style={styles.section}>
            <h1 style={styles.title}>Essential items for the modern lifestyle.</h1>
            <p style={styles.subtitle}>
                Curated robotic companions designed for efficiency and aesthetics.<br />
                Experience the future, today.
            </p>
            <button style={styles.button}>Shop Now</button>
        </section>
    );
};

export default Hero;
