// block variable;
// let number = 45;
// block variables;
// const word = "Tony";

// console.log(number)
// console.log(word)

// Objects
// const student = {
//     studentName: "tony",
//     rollNo: 48,
//     address: "home",
//     marks: 7486
// }

// for (let i in student) {
//     console.log("Key: "+i,"Value: "+student[i]);
// }

// ternary operators

// let age = 18;
// console.log(age >= 18 ? "vote" : "can't vote");

// alert("Hello ")
// let userInput = prompt("Enter A Number:");
// if (userInput % 5 === 0) {
//     console.log("Number is divide By 5: " + userInput)
// }

// for (let i = 0; i <= 10; i++) {
//     console.log("Hello");
// }

// let string = "this should be meaning full message";

// for (let i of string) {
//     console.log(i);
// }

let evenNumbers = 0;
let oddNumbers = 0;
for (let i = 1; i <= 100; i++) {
  if (i % 2 === 0) {
    evenNumbers += i;
  } else {
    oddNumbers += i;
  }
}

//              String and string methods

let str = "          simple word";
let simpleWord = "SIMPLE WORD";
//  it will never change it's original value it's return a new value;

console.log(str.toUpperCase());
// time will remove spaces from stating and ending not from in between;
console.log(str.trim());

console.log(simpleWord.toLowerCase());

console.log(simpleWord.slice(1, 4));

console.log(str.concat(simpleWord));

console.log(str.replace("          ", ""));

let markArray = [4, 5, 8, 77, 94, 6];

markArray.push(48322);
markArray.pop();
markArray.toString();
markArray.splice(0, 0, 1);

// for (let i in markArray) {
//     console.log(markArray[i])
// }
for (let i of markArray) {
  console.log(i);
}

// unShift() = push()
// shift() = pop()

//             functions

function sumFunction(x, y) {
  let sum = x + y;
  return sum;
}

let result = sumFunction(3, 6);
console.log(result);

const arrowFunction = (a, b) => {
  console.log(a + b);
};

console.log("@@@@");

//              Arrays and arrays methods

//  callback functions is a functions passed as a argument to another functions
let numberArray = [2, 3, 4, 5, 6];

// their are three parameter in foraEach functions (value,index,array)

let squareNumber = (value) => {
  console.log(value * value);
};
numberArray.forEach(squareNumber);

//  other way to write the forEach function;

// numberArray.forEach((value) => {
//   console.log(value * value);
// });

//  map function
// map method is also similar to a forEach functions, forEach functions don't return a new array , and map functions return a new arrays;
// also same with forEach functions three parameters(value,index,array)

console.log("map function:::::::::::::::::");

let squareOfNumber = numberArray.map((value) => {
  return value * value;
});

console.log(squareOfNumber);

// filter method
// create a new arrays of elements that give true for conditions or filters.
console.log("filter method:::::::::::::::");
let evenNumberArrays = numberArray.filter((value) => {
  return value % 2 === 0;
});
console.log(evenNumberArrays);

// reduce method
// Perform some operations & reduce the array to single value. it returns that single value.
console.log("reduce functions:::::::::::::::::");
const outPut = numberArray.reduce((previous, current) => {
  return previous > current ? previous : current;
  //   return result + current;
});
console.log(outPut);

// objects
console.log("objects:::::::::::::::::");
let student = {
  fullName: "tav",
  mark: 47,
  printMark: function () {
    console.log("Marks", this.mark);
  },
};

teacher = {
  salary: 48321,
};
// for injecting a object into another object;
teacher.__proto__ = student;

console.log("settimeout::::::::::::::::");

setTimeout(() => {
  console.log("hello");
}, 5000);

// callback hell;
console.log("callback hell:::::::::::::");
function getData(getId, getNextData) {
  setTimeout(() => {
    console.log("Data::::" + getId);
    if (getNextData) {
      getNextData();
    }
  }, 2000);
}
getData(1, () => {
  getData(2, () => {});
});

// promises
console.log("promises:::::::::::::::::");

function getData(getId) {
  return new Promise((resolve, reject) => {
    setTimeout(() => {
      console.log("Data::::" + getId);
      resolve("successful");
    }, 2000);
  });
}

async function getAllData() {
  await getData(1);
  await getData(2);
}

getData(1)
  .then((res) => {
    return getData(2);
  })
  .then((res) => {
    console.log(res);
  });
// promises with then and catch function

const getPromises = () => {
  return new Promise((resolve, reject) => {
    console.log("Promises");
    resolve("Successful");
    // reject("Error");
  });
};

let promise = getPromises();
promise.then((result) => {
  console.log("promise fulfilled!", result);
});
promise.catch((error) => {
  console.log("promises cant fulfilled got some error!", error);
});
