import { Button } from "bootstrap";
import React from "react";

const DownloadCakes = () => {
  const download = () => {
    fetch("/api/v1/download-cakes").then((response) => {
      const filename = response.headers
        .get("Content-Disposition")
        .split("filename=")[1];
      response.blob().then((blob) => {
        let url = window.URL.createObjectURL(blob);
        let a = document.createElement("a");
        a.href = url;
        a.download = filename;
        a.click();
      });
    });
  };

  return (
    <div className="mt-3 mb-3">
      <button className="btn btn-primary" onClick={download}>
        Download Cakes
      </button>
    </div>
  );
};

export default DownloadCakes;
