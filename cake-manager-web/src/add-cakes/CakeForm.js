import React, { useState } from "react";
import { Spinner } from "reactstrap";
import { useNavigate } from "react-router-dom";

const CakeForm = () => {
  const [cake, setCake] = useState(undefined);
  const [submitting, setSubmitting] = useState(false);

  const navigate = useNavigate();

  const handleSubmit = async (event) => {
    event.preventDefault();
    setSubmitting(true);

    await fetch("/api/v1/cake", {
      method: "POST",
      headers: {
        Accept: "application/json",
        "Content-Type": "application/json",
      },
      body: JSON.stringify(cake),
    })
      .then(() => navigate("/"))
      .finally(() => setSubmitting(false));
  };

  const handleChange = (event, fieldName) => {
    setCake((prevState) => {
      return {
        ...prevState,
        [fieldName]: event.target.value,
      };
    });
  };

  if (submitting) {
    return <Spinner />;
  }

  return (
    <>
      <form className="mt-2" onSubmit={handleSubmit}>
        <div className="form-group">
          <label className="control-label col-sm-2" htmlFor="name">
            Name
          </label>
          <input
            id="name"
            className="form-control"
            type="text"
            value={cake?.name}
            required={true}
            onChange={(e) => handleChange(e, "name")}
          />
        </div>
        <div className="form-group">
          <label className="control-label col-sm-2" htmlFor="desc">
            Description
          </label>
          <input
            id="desc"
            className="form-control"
            type="text"
            value={cake?.desc}
            required={true}
            onChange={(e) => handleChange(e, "desc")}
          />
        </div>
        <div className="form-group">
          <label className="control-label col-sm-2" htmlFor="image">
            Image Url
          </label>
          <input
            id="image"
            className="form-control"
            type="text"
            value={cake?.image}
            onChange={(e) => handleChange(e, "image")}
          />
        </div>
        <div className="form-group">
          <div className="mt-3">
            <button type="submit" className="btn btn-primary">
              Submit
            </button>
          </div>
        </div>
      </form>
    </>
  );
};

export default CakeForm;
