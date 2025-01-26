import { useState } from "react";
import LoginForm from "@components/LoginForm";
import AttendanceTable from "@components/AttendanceTable";




const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [loggedInStudent, setLoggedInStudent] = useState(null);

  const handleLogin = (studentData) => {
    if (studentData) {
      setLoggedIn(true);
      setLoggedInStudent(studentData);
    }
  };

  const handleLogout = () => {
    setLoggedIn(false);
    setLoggedInStudent(null);
  };

  return (
    <div className="container mt-0" style={{ height: "90vh" }}>
      {loggedIn ? (
        <div style={{ marginTop: "1rem" }}>
          <div className="d-flex flex-row justify-content-between">
            <div>
              <h3>Witaj, {loggedInStudent.firstName} {loggedInStudent.lastName}!</h3>
              <h5>Indeks: {loggedInStudent.index}</h5>
              <h5>Grupa: {loggedInStudent.groupName}</h5>
            </div>
            <button className="btn btn-danger mt-4 mb-4" onClick={handleLogout}>
              Wyloguj się
            </button>
          </div>
          <AttendanceTable student={loggedInStudent} />
        </div>
      ) : (
        <LoginForm onLogin={handleLogin} />
      )}
    </div>
  );
};

export default App;
