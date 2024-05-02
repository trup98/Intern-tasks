import {myAxios} from "./helper";
import axios from "axios";

export const loadUserService = async (e) => {
  console.log(e)
  return await myAxios.get(`/find?pageNo=0&pageSize=10&searchKey=${e}`).then(response => response);
}

export const handleToggle = async (id, status) => {
  await myAxios.patch(`http://localhost:9000/api/user/changeStatus/${id}/${status}`);
}

export const addUser = async (user) => {
  await myAxios.post("/save", user);
}
