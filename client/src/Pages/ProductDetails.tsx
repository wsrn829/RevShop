import React, { useEffect, useState } from 'react'
import axios from 'axios'
import '../CSS/productDetails.css'

// in progress
// interface Product {
//     productId: number;
//     seller: string; // change later
//     name: string;
//     description: string;
//     price: number;
//     stock: number;
//     thresholdStock: number | null;
//     img_url: string;
//     category: string; // change later
// }

// interface Props {     productId: number; }    // maybe change?    

// function ProductDetails(props: Props) {
function ProductDetails() {
    // grab product by its id, through the products page by clicking on it
    // display product's name, picture, seller info, price, description, (maybe stock)
        //  IF LOGGED IN AS SELLER: stock, 
    // work with others: reviews

    // const [product, setProduct] = useState<Product | null>(null);

    // useEffect(() => {
    //     axios.get(`http://localhost:7777/products/${props.productId}`)
    //         .then(response => {
    //             console.log(response);
    //             console.log(response.data);
    //             setProduct(response.data);
    //         })
    //         .catch(error => {
    //             console.error("error fetching product details: ", error);
    //         })
    // }, [props.productId]); // maybe change?
    
    return (
        <div className='product-details-page'>
            {/* {product && <h1>product.name</h1>} */}
            <div className='product-info-container'>
                <img className='puppy' src='https://www.petlandflorida.com/wp-content/uploads/2022/04/shutterstock_1290320698-1-scaled.jpg' /> 
                <div className='product-info'>
                    <p>Name</p>
                    <p>Seller Info</p>
                    <p>Price</p>
                    <p>stock?</p>
                    <button>BUY NOW</button>
                </div>
            </div>
            <div className='product-description'>description</div>
            <div className='product-reviews-container'>
                <p>review 1</p>
                <p>review 2</p>
            </div>
            <button className='product-review-button'>add review</button>
        </div>
    )
}

export default ProductDetails