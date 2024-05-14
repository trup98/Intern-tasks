import axios from "axios";


export const BASE_URL = 'http://localhost:9096';

export const axiosInstance = axios.create({
  baseURL: BASE_URL
})
