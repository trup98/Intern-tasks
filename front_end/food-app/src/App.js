import React from 'react';
import Navbar from "./Component/Navbar/Navbar";
import {Route, Routes} from "react-router-dom";
import Home from "./Page/Home/Home";
import Cart from "./Page/Cart/Cart";
import PlaceOrder from "./Page/PlaceOrder/PlaceOrder";
import Footer from "./Component/footer/footer";

const App = () => {
  return (
    <>
      <div className="app">
        <Navbar/>
        <Routes>
          <Route path="/" element={<Home/>}/>
          <Route path="/cart" element={<Cart/>}/>
          <Route path="/order" element={<PlaceOrder/>}/>
        </Routes>
      </div>
      <Footer/>
    </>
  );
};

export default App;
