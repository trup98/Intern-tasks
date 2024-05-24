import Cookies from "js-cookie";

// set cookies
export const setCookie = (name, value, attributes = {}) => {
  Cookies.set(name, value, attributes);
}

// set Role to the cookies
export const setRoleToCookies = (name, value, attributes = {}) => {
  Cookies.set(name, value, attributes);
}

// get role from cookies
export const getRoleFromCookie = (name) => {
  return Cookies.get(name);
}

export const getCookie = (key) => {
  if (key !== null && key !== undefined) {
    return Cookies.get(key);
  } else {
    return Cookies.get();
  }
};

export const fourteenMinutesTime = () => {
  // change expiry time and then check user logs out automatically or not
  return new Date(new Date().getTime() + 14 * 60 * 1000);
};


// isLoggedIn
export const isAuthenticated = () => {
  let token = Cookies.get("token");
  return !(token == null && token === undefined);
}

export const isRoleAuthenticated = () => {
  const role = getRoleFromCookie("role")
  return role === 'ROLE_ADMIN';
}

// logout
export const doLogout = () => {
  Cookies.remove("token")
  Cookies.remove("refreshToken")

}

// refreshToken will be valid for 30 min
export const refreshToken = () => {
  const refreshToken = Cookies.get("token");
  if (refreshToken) {
    Cookies.set("refreshToken", refreshToken, {expires: 1 / 48});
  }
}
