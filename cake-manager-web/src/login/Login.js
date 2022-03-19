import React, { useEffect, useState } from "react";

const Login = () => {
  const [userInfo, setUserInfo] = useState(undefined);

  useEffect(async () => {
    const response = await fetch("/user");
    const userResponse = await response.json();
    setUserInfo(userResponse);
  }, []);

  if (userInfo)
    return (
      <div className="float-right">{`Logged in as: ${userInfo.login}`}</div>
    );

  return <></>;
};

export default Login;
