import './App.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import {BrowserRouter, useRoutes} from "react-router-dom";
import {ToastContainer} from "react-toastify";
import 'react-toastify/dist/ReactToastify.css';
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
