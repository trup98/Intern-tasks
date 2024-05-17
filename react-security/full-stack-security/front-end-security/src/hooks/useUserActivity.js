import {useEffect, useState} from "react";
import {doLogout} from "../service/auth/AuthTokenProvider";
import {useNavigate} from "react-router-dom";

const useUserActivity = (logoutCallBack) => {

  const [isActive, setIsActive] = useState(true);
  let activityTimeOut;
  let logOutTimeOut;

  const navigate = useNavigate();

  useEffect(() => {
    const handleActivity = () => {
      setIsActive(true);
      clearTimeout(activityTimeOut);
      clearTimeout(logOutTimeOut);
      activityTimeOut = setTimeout(() => {
        setIsActive(false);
      }, 30000)
      logOutTimeOut = setTimeout(() => {
        logoutCallBack();
        doLogout()
        navigate("/login")
      }, 300000)
    }
    window.addEventListener("mousemove", handleActivity);
    window.addEventListener("keydown", handleActivity);

    return () => {
      window.removeEventListener("mousemove", handleActivity);
      window.removeEventListener("keydown", handleActivity);
    }
  }, [logoutCallBack]);

  return isActive;
}

export default useUserActivity;
