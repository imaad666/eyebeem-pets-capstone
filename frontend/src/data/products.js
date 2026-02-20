import ecatImg from './ecat.png';
import edogImg from './edog.png';
import ehamImg from './eham.png';

/** Map productId -> image src for merging with API product data */
export const productImages = {
  1: ecatImg,
  2: edogImg,
  3: ehamImg,
};

/** Static fallback when API is unavailable */
export const products = [
  { id: 1, name: 'e-CAT', price: 99.99, type: 'FELINE', image: ecatImg },
  { id: 2, name: 'e-DOG', price: 129.99, type: 'CANINE', image: edogImg },
  { id: 3, name: 'e-HAMSTER', price: 49.99, type: 'RODENT', image: ehamImg },
];
