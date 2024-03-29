import React from 'react';
import ContactCard from "./ContactCard";

// function component
const ContactList = (props) => {
  const renderContactList = props.contact.map((contact) => {
    return (
      <ContactCard contact={contact}></ContactCard>
    )
  })
  return (
    <div className="ui celled list">{renderContactList}</div>
  );
};

export default ContactList;
