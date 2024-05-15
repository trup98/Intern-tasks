import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Route, Routes} from "react-router-dom";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {User} from "./pages/User";
import {LoginPage} from "./pages/LoginPage";
import PrivateRoute from "./components/PrivateRoute";

function App() {
  return (
    <div className="App">

      <BrowserRouter>
        <ToastContainer/>
        <Routes>
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/" element={<PrivateRoute/>}>
            <Route path="user" element={<User/>}/>
          </Route>
        </Routes>
      </BrowserRouter>

    </div>
  );
}

export default App;
