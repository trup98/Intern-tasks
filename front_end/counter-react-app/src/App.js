import Navbar from "./componets/navbar";
import Home from "./componets/home";
import List from "./componets/List";
import {BrowserRouter as Router, Route, Switch} from "react-router-dom";
import CreateBlog from "./componets/CreateBlog";
import BlogDetail from "./componets/BlogDetail";
import NotFound from "./componets/NotFound";

function App() {
  return (
    <Router>
      <div className="App">
        <Navbar/>
        <div className="content">
          <Switch>
            <Route exact path="/">
              <List/>
            </Route>
            <Route path="/create">
              <CreateBlog/>
            </Route>
            <Route path="/blogs/:id">
              <BlogDetail/>
            </Route>
            <Route path="*">
              <NotFound/>
            </Route>
          </Switch>
          {/*<Home/>*/}
        </div>
      </div>
    </Router>
  );
}

export default App;
