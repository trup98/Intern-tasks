import { useEffect, useRef, useState } from "react";
import { doLogout } from "../service/auth/CookieStore";
import { useNavigate } from "react-router-dom";

const useUserActivity = (logoutCallBack) => {
  const [isActive, setIsActive] = useState(true);
  const activityTimeOutRef = useRef(null);
  const logOutTimeOutRef = useRef(null);

  const navigate = useNavigate();

  useEffect(() => {
    const handleActivity = () => {
      setIsActive(true);

      // Clears any existing timeouts to reset the timers.
      if (activityTimeOutRef.current) {
        clearTimeout(activityTimeOutRef.current);
      }
      if (logOutTimeOutRef.current) {
        clearTimeout(logOutTimeOutRef.current);
      }

      activityTimeOutRef.current = setTimeout(() => {
        setIsActive(false);
      }, 60000); // 15 minutes

      logOutTimeOutRef.current = setTimeout(() => {
        logoutCallBack();
        doLogout();
        navigate("/login");
      }, 60000); // 15 minutes
    };

    // Initializing the timeouts when the component mounts
    handleActivity();

    window.addEventListener("mousemove", handleActivity);
    window.addEventListener("keydown", handleActivity);

    return () => {
      window.removeEventListener("mousemove", handleActivity);
      window.removeEventListener("keydown", handleActivity);
      if (activityTimeOutRef.current) {
        clearTimeout(activityTimeOutRef.current);
      }
      if (logOutTimeOutRef.current) {
        clearTimeout(logOutTimeOutRef.current);
      }
    };
  }, [logoutCallBack, navigate]);

  return isActive;
};

export default useUserActivity;
