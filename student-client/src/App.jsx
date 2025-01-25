import { useState } from "react";
import LoginForm from "@components/LoginForm";
import AttendanceTable from "@components/AttendanceTable";

const studentsFromDatabase = {
  "123456": {
    firstName: "Jan",
    lastName: "Kowalski",
    attendance: [
      { subject: "Zaawansowane Programowanie Obiektowe", date: "10-01-2025", startingTime: "8:15", endingTime: "9:45", attendanceStatus: "Nieobecny" },
      { subject: "Podstawy Programowania 2", date: "10-01-2025", startingTime: "10:15", endingTime: "11:45", attendanceStatus: "Nieobecny" },
      { subject: "Analiza Matematyczna", date: "10-01-2025", startingTime: "12:15", endingTime: "13:45", attendanceStatus: "Spóźniony" },
      { subject: "Podstawy Sztucznej Inteligencji", date: "10-01-2025", startingTime: "14:15", endingTime: "15:45", attendanceStatus: "Obecny" },
      { subject: "Analiza Danych", date: "10-01-2025", startingTime: "16:15", endingTime: "17:45", attendanceStatus: "Obecny" },
      { subject: "WF", date: "10-01-2025", startingTime: "18:15", endingTime: "19:45", attendanceStatus: "Obecny" },
      { subject: "Podstawy Programowania 2", date: "11-01-2025", startingTime: "8:15", endingTime: "9:45", attendanceStatus: "Obecny" },
      { subject: "Podstawy Programowania 2", date: "11-01-2025", startingTime: "10:15", endingTime: "11:45", attendanceStatus: "Obecny" },
      { subject: "Przetwarzanie Sygnałów i Obrazów", date: "11-01-2025", startingTime: "12:15", endingTime: "13:45", attendanceStatus: "Obecny" },
    ]
  },
  "111111": {
    firstName: "Anna",
    lastName: "Nowak",
    attendance: [
      { subject: "WF", date: "10-01-2025", startingTime: "8:15", endingTime: "9:45", attendanceStatus: "Nieobecny" },
      { subject: "Analiza Danych", date: "10-01-2025", startingTime: "10:15", endingTime: "11:45", attendanceStatus: "Nieobecny" },
      { subject: "Teoretyczne Podstawy Informatyki", date: "11-01-2025", startingTime: "8:15", endingTime: "9:45", attendanceStatus: "Obecny" },
      { subject: "Podstawy Oprogramowania", date: "11-01-2025", startingTime: "10:15", endingTime: "11:45", attendanceStatus: "Obecny" },
      { subject: "Przetwarzanie Obiektowe", date: "11-01-2025", startingTime: "12:15", endingTime: "13:45", attendanceStatus: "Obecny" },
    ]
  },
};

const App = () => {
  const [loggedIn, setLoggedIn] = useState(false);
  const [loggedInStudent, setLoggedInStudent] = useState(null);

  const handleLogin = (indexNumber) => {
    const student = studentsFromDatabase[indexNumber];
    
    if (student) {
      setLoggedIn(true);
      setLoggedInStudent(student);
    } else {
      alert("Błędny numer indeksu!");
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
            <h3>Witaj, {loggedInStudent.firstName} {loggedInStudent.lastName}!</h3>
            <button className="btn btn-danger mt-4" onClick={handleLogout}>
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
