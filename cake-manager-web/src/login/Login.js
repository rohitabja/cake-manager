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
      <div>
        <img
          width={50}
          height={50}
          src={userInfo.avatar_url}
          alt={userInfo.avatar_url}
        />
      </div>
    );

  return <></>;
};

export default Login;
