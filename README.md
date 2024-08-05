# Project 2 (capstone) - RevShop E-commerce SPA
The RevShop project aims to develop a secure, user-friendly, and versatile e-commerce application for both buyers and sellers. The core functionalities for buyers include browsing products, 
adding products to a cart, checkout, and payment processing. Sellers can add products, manage inventory, and fulfill orders. The project's completion will be demonstrated through a cloud-hosted 
working version, technical presentation, and associated diagrams. 

## Delivery
Final delivery of the project will require:
 - A portable container of your Spring server hosted in the cloud
 - A react SPA hosted in the cloud
 - Source code for both client and server in the project repo
 - An architectural diagram of the client/server application
 - An entity relationship diagram of the database
 - A demonstration of the app

## Demonstration
 - [ ] Register new seller
 - [ ] Register new buyer
 - [ ] Log in as buyer/seller & show newly acquired cookie
 - [ ] View products (paginated)
   - [ ] Each product shows: name, image, price
   - [ ] Filter product view by category or keyword search
 - [ ] View a product's details including: name, description, price, user reviews
 - [ ] Add product to cart
 - [ ] Remove product from cart
 - [ ] Adjust quantity of product in cart
 - [ ] Checkout (fake - simulated checkout workflow)
 - [ ] Receive email notification with purchase details
 - [ ] Review a product
 - [ ] View user order history (list of all orders placed by user, paginated)
 - [ ] View seller's inventory of products
 - [ ] Edit seller's inventory of products, add items, remove items, modify stock
 - [ ] View seller order history (list of all orders placed with seller, paginated)
 - [ ] Receive email notification with details when a purchase is made


## Dates
 - Code Freeze: EOB 8/21/24
 - Due: 8/23/24
 - Presentations: 8/23/24 - 10:30 AM ET

## Technologies
 - Java
 - SQL
 - REST
 - HTTP
 - JUnit
 - Mockito
 - Spring
 - React
 - JWT
 - Docker
 - Jenkins
 - AWS
  - RDS
  - EC2
  - S3

## User Stories
#### As a buyer, I should be able to:
 - Register on the platform with email/username and password
 - Login to the application using email/username and password
 - View product details including image, price, description, and user reviews
 - Browse products by category and/or keywords
 - Add or remove products from the cart and provide quantity
 - Get email notifications when an order is placed
 - View order history
 - Review products
 - Make a simulated payment in a fake checkout workflow

#### As a seller, I should be able to:  
 - Register as a seller with email/username, password, and business details
 - Login into the application using email/username and password
 - Manage inventory of products (stock should decrease with purchases, and seller accounts should be able to increase it)
 - Add new products with price and description
 - See placed orders
 - Receive email notifications when a user places an order

#### Stretch Goals:
 - Site hosted in the cloud, and API running on cloud server for demonstration
 - Working CICD pipeline for continuous delivery during development
 - Admin functionality - Edit and delete buyer and seller user accounts as well as products
 - Sellers get email notifications when their inventory quantity is less than a threshold. (Seller sets the threshold value)
 - Buyers can keep a wishlist of items, like a separate cart for later
 - Buyers get email notifications about inventory quantities changing (sold out items back in stock, item in cart/wishlist no longer in stock)
 - Buyers get email notifications about price changes (items in cart/wishlist change in price)
 - AI customer service chat integration


## Non-functional Requirements
 - Adhere to REST guiding principles
   - Uniform Interface
   - Client-Server
   - Stateless Session Management
   - Cacheability
   - 3-Tier server
 - All resource representations must be transmitted as JSON request/response bodies
 - Adhere to SOLID principles, most importantly the Single Responsibility Principle
 - Encrypt passwords for storage
 - Implement encrypted bearer tokens for session management (JWT cookies)
 - Use Spring framework, and the Core, Web, and Data modules for the server
 - Use React library for the SPA client
 - Validate and sanitize all user inputs in the client
 - Paginate any very large collection of items
