import React from 'react';
import Person from "./Person";

const NameList = () => {
  const person = [{
    id: 1,
    name: "tony",
    hobby: "sleeping"
  }, {
    id: 2,
    name: "clark",
    hobby: "working"

  }]
  const personList = person.map(person => <Person person={person}/>)
  return (
    <div>
      {personList}
    </div>
  );
};

export default NameList;
