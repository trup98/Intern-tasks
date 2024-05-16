import {axiosInstance, axiosInstanceWithCredentials} from './helper'
import Cookies from "js-cookie";

export const login = async (loginDetails) => {
  console.log(loginDetails)
  return await axiosInstance.post('/api/v1/auth/login', loginDetails).then(response => response.data);
}



export const callAllUser = async () => {
  const token= {
    headers : {Authorization: `Bearer ${Cookies.get('token')}`},
  }
  console.log(token)
  return await axiosInstance.get('/api/v1/user/getAll',token).then(response => response.data);
}



