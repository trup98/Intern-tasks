import {createContext, useContext, useEffect, useState} from "react";
import {food_list} from "../assets/assets";

export const StoreContext = createContext(null);

const StoreContextProvider = (props) => {

  const [cartItem, setCartItem] = useState({});
  const addToCart = (itemId) => {
    if (!cartItem[itemId]) {
      setCartItem(prevState => ({...prevState, [itemId]: 1}))
    } else {
      setCartItem(prevState => ({...prevState, [itemId]: prevState[itemId] + 1}))
    }
  }
  const removeForCart = (itemId) => {
    setCartItem(prevState => ({...prevState, [itemId]: prevState[itemId] - 1}))
  }

  useEffect(() => {
    console.log(cartItem);
  }, [cartItem]);

  const contextValue = {
    food_list,
    cartItem,
    setCartItem,
    addToCart,
    removeForCart
  }

  return (
    <StoreContext.Provider value={contextValue}>
      {props.children}
    </StoreContext.Provider>
  )

}

export default StoreContextProvider;
export const useStoreContext = () => {
  return useContext(StoreContext);
};
