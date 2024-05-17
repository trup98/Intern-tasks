import axios from "axios";
import Cookies from "js-cookie";


export const BASE_URL = 'http://localhost:9096';

export const axiosInstance = axios.create({
  baseURL: BASE_URL,


})

export const axiosInstanceWithToken = axios.create({
  baseURL: BASE_URL,
  headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
})
