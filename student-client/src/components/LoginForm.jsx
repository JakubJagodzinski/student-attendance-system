import React, { useState } from "react";
import PropTypes from "prop-types";

const LoginForm = ({ onLogin }) => {
  const [indexNumber, setIndexNumber] = useState("");

  const handleSubmit = (e) => {
    e.preventDefault();
    onLogin(indexNumber);
  };

  return (
    <div className="d-flex flex-column justify-content-center align-items-center" style={{ height: "100%"}}>
      <h1 className="text-center mb-4">Students Attendance System</h1>
      <div className="card p-4" style={{ width: "100%", maxWidth: "400px", marginTop: "4rem" }}>
        
        <form onSubmit={handleSubmit}>
          <div className="mb-3">
            <label htmlFor="indexNumber" className="form-label">
              Numer Indeksu
            </label>
            <input
              type="text"
              id="indexNumber"
              className="form-control"
              value={indexNumber}
              onChange={(e) => setIndexNumber(e.target.value)}
              required
            />
          </div>
          <button type="submit" className="btn btn-primary w-100">
            Zaloguj się
          </button>
        </form>
      </div>
    </div>
  );
};

LoginForm.propTypes = {
  onLogin: PropTypes.func.isRequired,
};

export default LoginForm;