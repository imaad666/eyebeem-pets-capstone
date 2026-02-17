import React from 'react';

const ProductCard = ({ product }) => {
    const styles = {
        card: {
            display: 'flex',
            flexDirection: 'column',
            gap: '1rem',
            padding: '1rem',
        },
        imageContainer: {
            backgroundColor: '#f5f5f5',
            aspectRatio: '1/1',
            width: '100%',
            display: 'flex',
            alignItems: 'center',
            justifyContent: 'center',
            color: '#ccc',
        },
        info: {
            display: 'flex',
            justifyContent: 'space-between',
            alignItems: 'baseline',
        },
        name: {
            fontSize: '1rem',
            fontWeight: '500',
        },
        price: {
            fontSize: '0.9rem',
            color: '#666',
        }
    };

    return (
        <div style={styles.card}>
            <div style={styles.imageContainer}>
                {/* Placeholder for now */}
                <span>{product.name}</span>
            </div>
            <div style={styles.info}>
                <span style={styles.name}>{product.name}</span>
                <span style={styles.price}>${product.price}</span>
            </div>
        </div>
    );
};

export default ProductCard;
