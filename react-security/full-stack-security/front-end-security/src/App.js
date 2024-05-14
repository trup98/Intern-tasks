import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {Navbar} from "./components/Navbar";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import PrivateRoute from "./components/PrivateRoute";
import {User} from "./pages/User";
import {LoginPage} from "./pages/LoginPage";

function App() {
  return (
    <div className="App">

      <BrowserRouter>
        <ToastContainer/>
        <Routes>
          <Route path="/" element={<LoginPage/>}/>
          <Route path="/user" element={<User/>}/>
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
