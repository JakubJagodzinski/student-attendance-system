import React, { useState } from "react";
import PropTypes from "prop-types";
import { loginStudent, getAllAttendances, getGroupInfo, getTerms } from "@api";

const mergeAttendancesAndTerms = (attendanceList, termsList) => {
  return attendanceList.map(attendance => {
    const matchingTerm = termsList.find(term => term.termId === attendance.termId);
    return matchingTerm ? {
      subject: matchingTerm.termName,
      date: matchingTerm.termDate,
      startingTime: matchingTerm.termStartTime,
      endingTime: matchingTerm.termEndTime,
      attendanceStatus: attendance.attendanceStatus
    } : null;
  }).filter(item => item !== null);
}


const createStudentData = (studentInfo, allAttendances, groupInfo, allTerms) => {
  allAttendances = allAttendances.filter((attendance) => attendance.studentIndex === studentInfo.studentIndex)

  const studentData = {
    index: studentInfo.studentIndex,
    firstName: studentInfo.studentName,
    lastName: studentInfo.studentSurname,
    groupID: studentInfo.studentGroupId,
    groupName: groupInfo.groupName,
    attendance: mergeAttendancesAndTerms(allAttendances, allTerms)
  }

  return studentData;
}


const LoginForm = ({ onLogin }) => {
  const [indexNumber, setIndexNumber] = useState("");

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      const studentInfo = await loginStudent(indexNumber);
      const allAttendances = await getAllAttendances();
      const groupInfo = await getGroupInfo(studentInfo.studentGroupId);
      const allTerms = await getTerms();
      const studentData = createStudentData(studentInfo, allAttendances, groupInfo, allTerms);
      console.log(studentInfo);
      console.log(allAttendances);
      console.log(groupInfo);
      console.log(allTerms);

      console.log(studentData);

      onLogin(studentData);
    } catch (err) {
      console.log('Błąd podczas logowania');
      alert("Błędny numer indeksu!");
    }
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