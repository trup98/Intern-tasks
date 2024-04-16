import React from 'react';

const Person = ({person}) => {
  return (
    <div>
      <h3>Name {person.name} Hobby {person.hobby}</h3>
    </div>
  );
};

export default Person;
