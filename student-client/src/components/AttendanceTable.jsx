const AttendanceTable = ({ student }) => {
    return (
      <div className="table-responsive mt-3">
        <table className="table table-striped table-bordered">
          <thead className="table-primary">
            <tr>
              <th>Przedmiot</th>
              <th>Data</th>
              <th>Godzina Rozpoczęcia</th>
              <th>Godzina Zakończenia</th>
              <th>Status Obecności</th>
            </tr>
          </thead>
          <tbody>
            {student.attendance.map((item, index) => (
              <tr key={index}>
                <td>{item.subject}</td>
                <td>{item.date}</td>
                <td>{item.startingTime}</td>
                <td>{item.endingTime}</td>
                <td>
                  <span
                    className={`badge ${
                      item.attendanceStatus === "Obecny"
                        ? "bg-success"
                        : item.attendanceStatus === "Usprawiedliwiony"
                        ? "bg-primary"
                        : "bg-danger"
                    }`}
                  >
                    {item.attendanceStatus}
                  </span>
                </td>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    );
  };
  
  export default AttendanceTable;
  