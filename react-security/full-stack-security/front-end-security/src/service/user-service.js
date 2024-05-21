import {axiosInstance, axiosInstanceWithToken} from './helper'
import Cookies from "js-cookie";

export const login = async (loginDetails) => {
  return await axiosInstance.post('/api/v1/auth/login', loginDetails).then(response => response.data);
}


export const callAllUser = async (searchkey, pageNumber, pageSize) => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.get(`/api/v1/user/getAll?pageNo=${pageNumber}&pageSize=${pageSize}&searchKey=${searchkey}`, token).then(response => response.data);
}

export const changeStatus = async (id, status) => {
  await axiosInstanceWithToken.put(`/api/v1/user/changeStatus/${id}/${status}`).then(response => response.data);
}

export const addUser = async (user) => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.post("/api/v1/user/addUser", user, token).then(response => response);
}

export const updateUser = async (id, user) => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.put(`/api/v1/user/updateUser/${id}`, user, token).then(response => response);
}

export const deleteUser = async (id) => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.delete(`api/v1/user/deleteUser/${id}`, token).then(response => response);
}



