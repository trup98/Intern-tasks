import React from "react";
import './App.css';
import Header from "./Header";
import AddContact from "./AddContact";
import ContactList from "./ContactList";

function App() {
  const contact = [{
    id: "1",
    name: "user",
    email: "user@gmail"
  },
    {
      id: "2",
      name: "admin",
      email: "admin@gmail"
    }];
  return (
    <div className="ui container">
      <Header/>
      <AddContact/>
      <ContactList contact={contact}/>
    </div>
  );
}

export default App;
