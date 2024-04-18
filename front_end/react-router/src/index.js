import React from 'react';
import ReactDOM from 'react-dom/client';
import './index.css';
import reportWebVitals from './reportWebVitals';
import {BrowserRouter as Router, Route, Routes, Navigate} from 'react-router-dom';
import Home from "./components/Home";
import Learn, {CourseId} from "./components/Learn";
import {Course} from "./components/Learn";
import {Bundle} from "./components/Learn";
import Dashboard from "./components/Dashboard";


const root = ReactDOM.createRoot(document.getElementById('root'));

root.render(
  <Router>
    <Routes>
      <Route path="/" element={<Home/>}/>
      <Route path="/myapps" element={<Navigate replace to={"/learn"}/>}/>
      <Route path="/learn" element={<Learn/>}>
        <Route path="course" element={<Course/>}>
          <Route path=":courseId" element={<CourseId/>}/>
        </Route>
        <Route path="bundle" element={<Bundle/>}/>
      </Route>
      <Route path="/dashboard" element={<Dashboard/>}/>
    </Routes>
  </Router>
);


reportWebVitals();
