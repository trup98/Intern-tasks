import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, Route, Routes, useRoutes} from "react-router-dom";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
import {User} from "./pages/User";
import {LoginPage} from "./pages/LoginPage";
import PrivateRoutes from "./components/PrivateRoutes";
import {AddUser} from "./pages/AddUser";
import {EditUser} from "./pages/EditUser";
import {Role} from "./pages/Role";
import {AddRole} from "./pages/rolePage/AddRole";
import {EditRole} from "./pages/rolePage/EditRole";
import {PageNotFound} from "./components/PageNotFound";
import routes from "./routes/routes";


function App() {
  return (
    <div className="App">


      <BrowserRouter>
        <ToastContainer/>
        <AppRouter/>
      </BrowserRouter>

    </div>
  );
}

function AppRouter() {
  let element = useRoutes(routes)
  return element;
}

export default App;
