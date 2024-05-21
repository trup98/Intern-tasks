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
import {Role} from "./pages/Role";
import {AddRole} from "./pages/rolePage/AddRole";
import {EditRole} from "./pages/rolePage/EditRole";

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
            <Route path="/role" element={<Role/>}/>
            <Route path="/role/add" element={<AddRole/>}/>
            <Route path="/editRole" element={<EditRole/>}/>

          </Route>
        </Routes>
      </Router>

    </div>
  );
}

export default App;
