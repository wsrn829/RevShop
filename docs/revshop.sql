ROLLBACK;

BEGIN TRANSACTION;

-- *************************************************************************************************
-- Drop all db objects in the proper order
-- *************************************************************************************************
DROP TABLE IF EXISTS Wishlist CASCADE;
DROP TABLE IF EXISTS Reviews CASCADE;
DROP TABLE IF EXISTS Cart CASCADE;
DROP TABLE IF EXISTS OrderItems CASCADE;
DROP TABLE IF EXISTS Orders CASCADE;
DROP TABLE IF EXISTS Products CASCADE;
DROP TABLE IF EXISTS Notifications CASCADE;
DROP TABLE IF EXISTS Users CASCADE;

-- *************************************************************************************************
-- Drop existing types
-- *************************************************************************************************
DO $$
BEGIN
    IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'notification_type') THEN
        DROP TYPE notification_type;
    END IF;
    IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'user_type') THEN
        DROP TYPE user_type;
    END IF;
    IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'order_status') THEN
        DROP TYPE order_status;
    END IF;
   IF EXISTS (SELECT 1 FROM pg_type WHERE typname = 'item_status') THEN
        DROP TYPE item_status;
    END IF;
END $$;

-- *************************************************************************************************
-- Create necessary ENUM types
-- *************************************************************************************************
CREATE TYPE notification_type AS ENUM ('ORDER_PLACED', 'SHIP_ITEM', 'STOCK_ALERT', 'PRICE_CHANGE');
CREATE TYPE user_type AS ENUM ('BUYER', 'SELLER', 'BOTH', 'ADMIN');
CREATE TYPE order_status AS ENUM ('PLACED', 'PARTIALLY_SHIPPED', 'SHIPPED', 'PARTIALLY_DELIVERED', 'DELIVERED', 'CANCELLED');
CREATE TYPE item_status AS ENUM ('PLACED', 'SHIPPED', 'DELIVERED', 'CANCELLED');

-- *************************************************************************************************
-- Create tables
-- *************************************************************************************************

CREATE TABLE Users (
    user_id SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    type user_type NOT NULL,
    business_details TEXT,
    is_active BOOLEAN DEFAULT true NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Categories (
    category_id SERIAL PRIMARY KEY,
    category_name VARCHAR(50)
);

CREATE TABLE Products (
    product_id SERIAL PRIMARY KEY,
    seller_id INTEGER NOT NULL REFERENCES Users(user_id),
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price DECIMAL(10, 2) NOT NULL,
    stock INTEGER NOT NULL DEFAULT 0,
    image_url TEXT,
    category_id INTEGER NOT NULL REFERENCES Categories(category_id)
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Orders (
    order_id SERIAL PRIMARY KEY,
    buyer_id INTEGER NOT NULL Users(user_id),
    total_amount DECIMAL(10, 2) NOT NULL,
    status order_status NOT NULL DEFAULT 'PLACED',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE OrderItems (
    order_item_id SERIAL PRIMARY KEY,
    order_id INTEGER NOT NULL Orders(order_id),
    product_id INTEGER NOT NULL Products(product_id),
    seller_id INTEGER NOT NULL Users(user_id),
    quantity INTEGER NOT NULL DEFAULT 1,
    price DECIMAL(10, 2) NOT NULL,
    status item_status NOT NULL DEFAULT 'PLACED'
);

--TODO: ask about Carts & Wishlists Tables

-- CREATE TABLE Carts (
--     cart_id SERIAL PRIMARY KEY,
--     buyer_id INTEGER NOT NULL REFERENCES Users(user_id),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

CREATE TABLE CartItems (
    cart_item_id SERIAL PRIMARY KEY,
    -- cart_id INTEGER NOT NULL REFERENCES Carts(cart_id)
    buyer_id INTEGER NOT NULL REFERENCES Users(user_id),
    product_id INTEGER NOT NULL REFERENCES Products(product_id),
    seller_id INTEGER NOT NULL Users(user_id),
    quantity INTEGER NOT NULL DEFAULT 1,
);

-- CREATE TABLE Wishlists (
--     wishlist_id SERIAL PRIMARY KEY,
--     buyer_id INTEGER NOT NULL REFERENCES Users(user_id),
--     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
-- );

CREATE TABLE WishlistItems (
    wishlist_item_id SERIAL PRIMARY KEY,
    -- wishlist_id INTEGER NOT NULL REFERENCES Wishlists(wishlist_id)
    buyer_id INTEGER NOT NULL REFERENCES Users(user_id),
    product_id INTEGER NOT NULL REFERENCES Products(product_id),
    seller_id INTEGER NOT NULL Users(user_id),
    quantity INTEGER NOT NULL DEFAULT 1
);

CREATE TABLE Reviews (
    review_id SERIAL PRIMARY KEY,
    product_id INTEGER NOT NULL Products(product_id),
    user_id INTEGER NOT NULL REFERENCES Users(user_id),
    rating INTEGER NOT NULL CHECK (rating BETWEEN 1 AND 5),
    comment TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE Notifications (
    notification_id SERIAL PRIMARY KEY,
    user_id INTEGER NOT NULL REFERENCES Users(user_id),
    type notification_type NOT NULL,
    content TEXT,
    read BOOLEAN DEFAULT FALSE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);



INSERT INTO Users (username, email, password, first_name, last_name, type, business_details, is_active, created_at)
VALUES ('admin_user', 'admin@example.com', 'password', 'Admin', 'User', 'ADMIN', 'Administrator account', true, CURRENT_TIMESTAMP);


COMMIT TRANSACTION;
