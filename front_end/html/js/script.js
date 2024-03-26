let mode = document.querySelector("#button");
let currentMode = "light";

mode.addEventListener("click", () => {
  if (currentMode === "light") {
    currentMode = "dark";
    document.querySelector("body").style.backgroundColor = "black";
    document.querySelector("p").style.backgroundColor = "white";
  } else {
    currentMode = "light";
    document.querySelector("body").style.backgroundColor = "white";
  }
  console.log(currentMode);
});
