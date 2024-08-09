import React, { useEffect, useState } from 'react'
import axios from 'axios'
import '../CSS/productDetails.css'
import { useParams } from 'react-router-dom';

 interface Product {
     productId: number;
     seller: string; // change later into seller: User;
     name: string;
     description: string;
     price: number;
     stock: number;
    img_url: string;
     category: string; // change later category: Category;
}

 interface Props {    
     productId: number; 
    }    // maybe change?    

function ProductDetails(props: Props) {
    // grab product by its id, through the products page by clicking on it
    // display product's name, picture, seller info, price, description, (maybe stock)
        //  IF LOGGED IN AS SELLER: stock, 
    // work with others: reviews


    //this is the param for route in app.tsx
    const { productId } = useParams<{ productId: string }>();
     const [product, setProduct] = useState<Product | null>(null);

     useEffect(() => {
         axios.get(`http://localhost:7777/products/${props.productId}`)
             .then(response => {
                 console.log(response);
                 console.log(response.data);
                 setProduct(response.data);
             })
             .catch(error => {
                 console.error("error fetching product details: ", error);
             })
     }, [props.productId]); // maybe change?
    
    return (
        <div className='product-details-page'>
            <div className='product-info-container'>
                <img className='puppy' src='https://www.petlandflorida.com/wp-content/uploads/2022/04/shutterstock_1290320698-1-scaled.jpg' /> 
                <div className='product-info'>
                    <p>{product ? product.name : 'Product is nameless'}</p>
                    <p>{product? product.seller: 'seller is uknown'}</p>
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