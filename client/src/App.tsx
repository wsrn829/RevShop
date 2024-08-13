import "./App.css";
import Login from "./Auth/Login";
import Register from "./Auth/Register";
import { Routes, Route } from "react-router-dom";
import Home from "./Pages/Home";
import ProductDetails from "./Pages/ProductDetails";

const App = () => {
  return (
    <div className="App">
      <Routes>
        <Route path="/" element={<Login />} />
        <Route path="/register" element={<Register />} />
        <Route path="/home" element={<Home />} />
        
        {/* I use param so just follow this uri mapping : this note fro braxton dont deleted until braxton done his part */}
        <Route path="/products/details/:productId" element={<ProductDetails />} />
      </Routes>
    </div>
  );
};

export default App;
