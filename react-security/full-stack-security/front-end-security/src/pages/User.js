import {useEffect, useState} from "react";
import {callAllUser} from "../service/user-service";
import {getCurrentUser} from "../auth/AuthLogin";
import {axiosInstanceWithCredentials} from "../service/helper";

export const User = () => {

  const [users, setUser] = useState({
    data: [],
    totalPages: 0,
    pageNumber: 0,
    pageSize: 10
  });

  // const token = getCurrentUser();
  // console.log("token from user :::::::::::::", token)


  useEffect(() => {
    getAll()
    // callAllUser();
  }, []);


  const getAll = () =>{
    callAllUser().then(response=>{
      console.log(response.data.content)
    })
  }





  return (
    <>
      <h2>Welcome</h2>
    </>
  )
}
