import Cookies from "js-cookie";

//store in cookies
//token is valid for 15 min
export const doLogin = (response) => {
  const token = response.data.token;
  Cookies.set("accessToken", token, {expires: 1 / 1140});

}

// isLoggedIn
export const isAuthenticated = () => {
  let token = Cookies.get("accessToken");
  if (token == null && token == undefined) return true;
  else return false;
}

// logout
export const doLogout = () => {
  Cookies.remove("accessToken")
  Cookies.remove("refreshToken")

}

// get current user
export const getCurrentUser = () => {
  let token = Cookies.get("accessToken");
  if (token == null && token == undefined) return null;
  else return token;
}

// refreshToken will be valid for 30 min
export const refreshToken = () => {
  const refreshToken = Cookies.get("accessToken");
  if (refreshToken) {
    Cookies.set("refreshToken", refreshToken, {expires: 1 / 48});
  }
}
