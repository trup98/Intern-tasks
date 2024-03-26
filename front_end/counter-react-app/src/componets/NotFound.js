import {Link} from "react-router-dom";

const NotFound = () => {
  return (
    <div className="not-found">
      <h2>Not Found</h2>
      <p>This page does not Exist</p>
      <Link to="/">Back To Home Page</Link>
    </div>
  );
}

export default NotFound;
