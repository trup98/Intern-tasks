import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter as Router, Route, Routes} from "react-router-dom";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {User} from "./pages/User";
import {LoginPage} from "./pages/LoginPage";
import PrivateRoute from "./components/PrivateRoute";
import {AddUser} from "./pages/AddUser";
import {EditUser} from "./pages/EditUser";

function App() {
  return (
    <div className="App">

      <Router>
        <ToastContainer/>
        <Routes>
          <Route path="/login" element={<LoginPage/>}/>
          <Route path="/" element={<PrivateRoute/>}>

            <Route path="user" element={<User/>}/>
            <Route path="/add" element={<AddUser/>}/>
            <Route path="/edit" element={<EditUser/>}/>

          </Route>
        </Routes>
      </Router>

    </div>
  );
}

export default App;
