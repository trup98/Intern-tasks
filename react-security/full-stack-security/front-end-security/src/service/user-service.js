import {axiosInstance} from './helper'

export const login = async (loginDetails) => {
  console.log(loginDetails)
  return await axiosInstance.post('/api/v1/auth/login', loginDetails).then(response => response.data);
}

