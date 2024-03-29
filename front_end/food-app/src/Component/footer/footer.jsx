import React from 'react';
import './footer.css'
import {assets} from "../../assets/assets";

const Footer = () => {
  return (
    <div className="footer" id="footer">
      <div className="footer-content">
        <div className="footer-content-left">
          <img src={assets.logo}/>
          <p>Lorem ipsum, dolor sit amet consectetur adipisicing elit. Dolores expedita eius perspiciatis eum,
            repellendus rem cum nostrum numquam consectetur animi quas eligendi dolore culpa vel. Doloribus illum rerum
            eius similique!</p>
          <div className="footer-social-icons">
            <img src={assets.facebook_icon}/>
            <img src={assets.twitter_icon}/>
            <img src={assets.linkedin_icon}/>
          </div>
        </div>
        <div className="footer-content-center">
          <h2>COMPANY</h2>
          <ul>
            <li>Home</li>
            <li>About Us</li>
            <li>Delivery</li>
            <li>Privacy Policy</li>
          </ul>
        </div>
        <div className="footer-content-right">
          <h2>GET IN TOUCH</h2>
          <ul>
            <li>+91-966-290-2324</li>
            <li>comeHome@gmail.com</li>
          </ul>
        </div>
      </div>
      <hr/>
      <p className="copy-right">
        PrivacyPolicies.com © 2002 - 2024 All rights reserved
      </p>
    </div>
  );
};

export default Footer;
