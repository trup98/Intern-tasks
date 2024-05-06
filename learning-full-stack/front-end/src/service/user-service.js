import {myAxios} from "./helper";

export const loadUserService = async (searchkey, pageNumber, pageSize) => {
  console.log(searchkey)
  return await myAxios.get(`/find?pageNo=${pageNumber}&pageSize=${pageSize}&searchKey=${searchkey}`).then(response => response);
}

export const handleToggle = async (id, status) => {
  await myAxios.patch(`/changeStatus/${id}/${status}`);
}

export const addUser = async (user) => {
  return await myAxios.post("/save", user).then(response => response);
}

export const findById = async (id) => {
  return await myAxios.get(`/findById/${id}`).then(response => response);
}

export const updateUser = async (id, user) => {
  return await myAxios.put(`/update/${id}`, user).then(response => response);
}

export const deleteUser = async (id) => {
  return await myAxios.delete(`/delete/${id}`).then(response => response);
}
