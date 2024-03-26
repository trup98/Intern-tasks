let URL = "https://cat-fact.herokuapp.com/facts";

const factPara = document.querySelector("#fact");
const btnPara = document.querySelector("#btn");

const getFacts = async () => {
  // get the data
  let response = await fetch(URL);
  console.log(response);
  // convert response to a javascript object
  let data = await response.json();
  factPara.innerHTML = data[0].text;
};

btnPara.addEventListener("click", getFacts);
