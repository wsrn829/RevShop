import React, { useEffect, useState } from 'react'
import axios from 'axios'
import '../CSS/productDetails.css'
import { useParams } from 'react-router-dom';
import { Product ,User , Category } from '../Interface/types';
  

function ProductDetails() {
    //this is the param for route in app.tsx we basically grabbing the productId
    const { productId } = useParams<{ productId: string }>();
     const [product, setProduct] = useState<Product | null>(null);
     const [User , setUser] = useState<User | null >(null);

     useEffect(() => {
            axios.get(`http://localhost:7777/products/${productId}`)
                .then(response => {
                    console.log(response.data);
                    setProduct(response.data);
                })
                .catch(error => {
                    console.error("Error fetching product details: ", error);
                });
     }, [productId]); 
    
    return (
        <div className='product-details-page'>
            <div className='product-info-container'>
                <img className='productimage' src={product?.img_url} />  
                <div className='product-info'>
                    <p>{product ? product.name : 'Product is nameless'}</p>
                    <p>{product? product.seller.firstName: 'seller is uknown'}</p>
                    <p>{product? product.price: 'priceless'}</p>
                    <p>{product? product.stock: 'infinity null'}</p>
                    <button>BUY NOW</button>
                </div>
            </div>
            <div className='product-description'>
                <h1>description</h1>
                <p>{product? product.description: 'Bling bang bling bang bling bang born....'}</p>
                </div>
            <div className='product-reviews-container'>
                {/* This going to be mapping later */}
                <p>review 1</p>
                <p>review 2</p>
            </div>
            <button className='product-review-button'>add review</button>
        </div>
    )
}

export default ProductDetails