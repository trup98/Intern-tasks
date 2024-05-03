import {myAxios} from "./helper";

export const loadUserService = async (searchkey,pageNumber,pageSize) => {
  console.log(searchkey)
  return await myAxios.get(`/find?pageNo=${pageNumber}&pageSize=${pageSize}&searchKey=${searchkey}`).then(response => response);
}

export const handleToggle = async (id, status) => {
  await myAxios.patch(`/changeStatus/${id}/${status}`);
}

export const addUser = async (user) => {
  return await myAxios.post("/save", user).then(response => response);
}
