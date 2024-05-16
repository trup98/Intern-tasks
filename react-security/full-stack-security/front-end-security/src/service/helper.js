import axios from "axios";
import {getCurrentUser} from "../auth/AuthLogin";
import Cookies from "js-cookie";


export const BASE_URL = 'http://localhost:9096';

export const axiosInstance = axios.create({
  baseURL: BASE_URL,

})

export const axiosInstanceWithCredentials = axios.create({
  baseURL: BASE_URL,
  headers : {Authorization: `Bearer ${Cookies.get('token')}`},
})
