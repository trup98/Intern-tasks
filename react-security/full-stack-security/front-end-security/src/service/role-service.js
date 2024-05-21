import {axiosInstance} from './helper';
import Cookies from "js-cookie";

export const getRoleAll = async () => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.get('/api/v1/role/getAllRole', token).then(response => response);
}

export const addNewRole = async (role) => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.post(`/api/v1/role/add`, role, token).then(response => response);
}

export const editRole = async (id, role) => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.put(`/api/v1/role/updateRole/${id}`, role, token).then(response => response);
}

export const deleteRole = async (id) => {
  const token = {
    headers: {Authorization: `Bearer ${Cookies.get('accessToken')}`}
  }
  return await axiosInstance.delete(`/api/v1/role/deleteRole/${id}`, token).then(response => response);

}
