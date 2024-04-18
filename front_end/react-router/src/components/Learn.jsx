import React from 'react';
import {Link, NavLink, Outlet, useNavigate, useParams} from "react-router-dom";

function Learn(props) {
  return (
    <div>
      <h1>Learn</h1>
      <h4>All course listed here</h4>
      <Link to={"/learn/course"} className="btn btn-primary">Course</Link>|
      <Link to={"/learn/bundle"} className="btn btn-success">Bundle</Link>
      <Outlet/>
    </div>
  );
}


export function Course(props) {
  const courseList = ["react", "javascript", "angular", "vue"]
  const randomList = courseList[Math.floor(Math.random() * courseList.length)];
  return (
    <div>
      <h1>Course List</h1>
      <h4>Course card</h4>
      <NavLink style={({isActive}) => {
        return {
          backgroundColor: isActive ? "white" : "yellow"
        }
      }} to={`/learn/course/${randomList}`}>
        {randomList}
      </NavLink>
      <Outlet/>
    </div>

  );
}


export function Bundle(props) {
  return (
    <div>
      <h1>Bundle List</h1>
      <h4>Bundle card</h4>
    </div>
  );
}

export function CourseId(props) {
  const {courseId} = useParams();
  const navigate = useNavigate();
  return (
    <div>
      <h4>Params is: {courseId}</h4>
      <button onClick={() => {
        navigate("/dashboard", {state: '1099'})
      }} className="btn btn-warning">Price
      </button>

      <Link to="/dashboard" state={"trying another way to pass data"}>Test </Link>
    </div>
  );
}

export default Learn;



