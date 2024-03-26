const person = {
  userName: "hari",
  // defining function inside a object before ES6;
  walk: function () {},
  // defining function inside a object after ES6;
  talk() {
    console.log(this);
  },
};

// different ways to access this property

person.talk();
person["userName"] = "some input from html";

// function in javascript are objects
//  with the bind method we can set the value of this permanently
//  when we call bind on talk function we get a new talk function
//  in that new function the value of this is based on the argument we passed to the bind method
const talk = person.talk.bind(person);
talk();

// arrow functions

const jobs = [
  { id: 1, isActive: true },
  { id: 2, isActive: true },
  { id: 3, isActive: false },
];

// without arrow function
const activeJob = jobs.filter(function (job) {
  return job.isActive;
});

// with arrow function
const activeJobs = jobs.filter((job) => job.isActive);
console.log(activeJobs);

// spread operator

const first = [1, 32, 4];
const second = [2, 5, 4];

// before spread operator how we concat two array;
const combined = first.concat(second);

// after spread operator we concat;
const combineSpread = [...first, "first", ...second, "second"];

console.log(combineSpread);

// spread on objects

const firstObject = { job: "nothing" };
const secondObject = { value: "what" };

// combining objects and adding another value in new object
const combinedObjects = {
  ...firstObject,
  ...secondObject,
  location: "no Where",
};
console.log(combinedObjects);
