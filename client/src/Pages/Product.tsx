import axios from "axios"
import React, { useEffect } from "react"
import { useState } from "react"

interface Products{
    categoryId:number
    priceNumber:number 
    productId:number 
    sellerId:number 
    stock:number 
    createdAt:string
    description:string 
    name:string
}
function Product(){
   
    let [product,setProduct] = useState(Array<Products>)
    
    useEffect(() => {
    async function allProducts(){
        const url = "http://localhost:7777/products/test";
        
        try {
            let response = await axios.get(url, {
                method: 'get',
                headers: {
                    'Content-Type': 'application/json'
                }
            });
    
           console.log(response.data);
           setProduct(response.data)
           
        } catch (error: any) {
            console.error(error.message);
        }
       
    }
    allProducts()
}, []);

return(
    <>{
        product.map((p,index = 0)=>{
        return(
         <div key = {index}>
          <p>Product: {p.productId} </p>
          <p>Name: { p.name}</p>
          <p> Product Description: {p.description}</p>
         </div>
        )
        })
    }
 
    </>
)
}


export default Product