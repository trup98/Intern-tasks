import {LoginPage} from "../pages/LoginPage";
import {PageNotFound} from "../components/PageNotFound";
import PrivateRoutes from "../components/PrivateRoutes";
import {User} from "../pages/User";
import {AddUser} from "../pages/AddUser";
import {EditUser} from "../pages/EditUser";
import {Role} from "../pages/Role";
import {AddRole} from "../pages/rolePage/AddRole";
import {EditRole} from "../pages/rolePage/EditRole";

const routes = [

  {path: "/login", element: <LoginPage/>},
  {path: "*", element: <PageNotFound/>},
  {
    path: "/",
    element: <PrivateRoutes/>,
    children: [
      {path: "/", element: <User/>},
      {path: "/add", element: <AddUser/>},
      {path: "/edit", element: <EditUser/>},
      {path: "/role", element: <Role/>},
      {path: "/role/add", element: <AddRole/>},
      {path: "/editRole", element: <EditRole/>}
    ]

  }

]

export default routes;
