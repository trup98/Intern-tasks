import {axiosInstance} from './helper'

export const login = async (loginDetails) => {
  return await axiosInstance.post('/api/v1/auth/login', loginDetails).then(response => response.data);

}

export const callAllUser = async (searchkey, pageNumber, pageSize) => {
  return await axiosInstance.get(`/api/v1/user/getAll?pageNo=${pageNumber}&pageSize=${pageSize}&searchKey=${searchkey}`).then(response => response.data);
}

export const changeStatus = async (id, status) => {
  await axiosInstance.put(`/api/v1/user/changeStatus/${id}/${status}`).then(response => response.data);
}

export const addUser = async (user) => {
  return await axiosInstance.post("/api/v1/user/addUser", user).then(response => response);
}

export const updateUser = async (id, user) => {
  return await axiosInstance.put(`/api/v1/user/updateUser/${id}`, user).then(response => response);
}

export const deleteUser = async (id) => {
  return await axiosInstance.delete(`api/v1/user/deleteUser/${id}`).then(response => response);
}



