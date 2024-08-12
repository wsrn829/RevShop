export interface User {
    userId: number;
    username: string;
    email: string;
    firstName?: string;
    lastName?: string;
    type: string;
    businessDetails?: string;
    banned: boolean;

}

export interface Product {
    productId: number;
    seller: User; 
    name: string;
    description: string;
    price: number;
    stock: number;
   img_url: string;
    category: Category; // change later category: Category;
}

export interface Category {
    categoryId: number;  
    name: string;        
    products: Product[]; 
}