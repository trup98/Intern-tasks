import axios from "axios";
import {fourteenMinutesTime, getCookie, setCookie} from "./auth/CookieStore";
import {toast, Zoom} from "react-toastify";


export const BASE_URL = 'http://localhost:9096';

export const axiosInstance = axios.create({
  baseURL: BASE_URL,

})

axiosInstance.interceptors.request.use(
  (config) => {
    const token = getCookie("token")
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    return config;
  },
  (error) => {
    if (error.response.status === 500) {
      toast.error(error.response.data.message, {
        transition: Zoom
      })
    }
  }
)

axiosInstance.interceptors.response.use(
  (response) => {
    if (response.config.headers && response.headers.Authorization && response.config.url) {
      setCookie("token", response.headers.authorization, {
        expires: fourteenMinutesTime(),
      });
    }
    return response;
  },
  (error) => {
    return Promise.reject(error.response);
  }
);

