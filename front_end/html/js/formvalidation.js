const userName = document.getElementById("user-name");
const password = document.getElementById("password");
const form = document.getElementById("form");
const error = document.getElementById("error");


form.addEventListener("submit", (e) => {
  let message = [];
  if (userName.value === "" || userName.value == null || password.value === "" || password.value == null) {
    message.push("Empty filed");
  }
  if(message.length>0){
    e.preventDefault();
    error.innerText = message.join(',')  
  }
  
});
