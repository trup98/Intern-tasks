import {Link} from "react-router-dom";

// Inline Css
/*style={{
  color:"white",
    backgroundColor:"#f1356d",
    borderRadius:'8px'
}}*/
const Navbar = () => {
  return (
    <nav className="navbar">
      <h1>The Book Blog</h1>
      <div className="links">
        <Link to="/">Home</Link>
        <Link to="/create">Create New Blog</Link>
      </div>
    </nav>
  );
}

export default Navbar;
