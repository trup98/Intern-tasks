import Cookies from "js-cookie";

//store in cookies
export const doLogin = (response) => {
  const token = response.data.token;
  Cookies.set("token", token, {expires: new Date(Date.now() + 900000)});

}

// isLoggedIn
export const isAuthenticated = () => {
  let token = Cookies.get("token");
  if (token == null && token == undefined) return true;
  else return false;
}

// logout
export const doLogout = () => {
  Cookies.remove("token")

}

// get current user
export const getCurrentUser = () => {
  if (isAuthenticated()) {
    return Cookies.get("token");
  } else return false;
}
