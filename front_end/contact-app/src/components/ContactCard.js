import React from 'react';
import User from '../images/user_456212.png';

const ContactCard = (props) => {
  // destructuring
  const {id, name, email} = props.contact;
  return (
    <div className="item">
      <img className="ui avatar image" src={User} alt="user"/>
      <div className="content">
        <div className="header">{name}</div>
        <div>{email}</div>
      </div>
      <i style={{float:"right",color:"red",marginBottom:"7px"}} className="trash alternate outline icon"></i>
    </div>
  );
};

export default ContactCard;
