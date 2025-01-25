package controllers;

import entities.Attendance;
import entities.Student;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import utils.ViewsNames;

import java.util.ArrayList;

import static utils.SceneSwitcher.switchScene;

public class SetTermAttendanceController {

    @FXML
    private TableView<Student> studentsTableView;

    @FXML
    private TableColumn<Student, String> studentIndex;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableColumn<Student, String> studentSurname;

    @FXML
    private TableColumn<Student, Void> studentAttendance;

    private ArrayList<Attendance> attendances;

    static private Integer termId;

    @FXML
    private Label termNameLabel;

    @FXML
    private Label termGroupLabel;

    @FXML
    private Label termDateLabel;

    @FXML
    private Label termStartTimeLabel;

    @FXML
    private Label termEndTimeLabel;

    public static void setTermId(Integer newTermId) {
        termId = newTermId;
    }

    @FXML
    protected void initialize() {
        // TODO pobranie informacji o terminie z bazy danych
        termNameLabel.setText("Termin A");
        termGroupLabel.setText("Grupa A");
        termDateLabel.setText("01.01.2020");
        termStartTimeLabel.setText("08:00");
        termEndTimeLabel.setText("10:00");

        attendances = new ArrayList<>();

        // TODO pobranie obecnosci dla tego terminu z bazy danych,
        // pobieramy id_grupy która jest przypisana na ten termin
        // a nastepnie pobieramy liste wszystkich studentow ktorzy sa w tej grupie

        // temporary data
        attendances.add(new Attendance(1, "123456", 1, termId, Attendance.ATTENDANCE_STATUS_PRESENT));
        attendances.add(new Attendance(2, "654321", 1, termId, Attendance.ATTENDANCE_STATUS_EXCUSED));
        attendances.add(new Attendance(3, "987654", 1, termId, Attendance.ATTENDANCE_STATUS_EXCUSED));

        studentIndex.setCellValueFactory(new PropertyValueFactory<>("studentIndex"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentSurname.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));

        // TODO dodac logike pobierania studentow z bazy dla danego terminu

        studentsTableView.getItems().add(new Student("123456", "Jan", "Kowalski", ""));
        studentsTableView.getItems().add(new Student("654321", "Krzysztof", "Nowak", ""));
        studentsTableView.getItems().add(new Student("987654", "Anna", "Kowalska", ""));

        configurePresenceColumn();

    }

    private void configurePresenceColumn() {
        studentAttendance.setCellFactory(col -> new TableCell<>() {
            private final RadioButton presentButton = new RadioButton(Attendance.ATTENDANCE_STATUS_PRESENT);
            private final RadioButton absentButton = new RadioButton(Attendance.ATTENDANCE_STATUS_ABSENT);
            private final RadioButton excusedButton = new RadioButton(Attendance.ATTENDANCE_STATUS_EXCUSED);
            private final ToggleGroup presenceGroup = new ToggleGroup();

            {
                presentButton.setToggleGroup(presenceGroup);
                absentButton.setToggleGroup(presenceGroup);
                excusedButton.setToggleGroup(presenceGroup);
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Attendance attendance = attendances.get(getIndex());
                    switch (attendance.getAttendanceStatus()) {
                        case Attendance.ATTENDANCE_STATUS_PRESENT:
                            presentButton.setSelected(true);
                            break;
                        case Attendance.ATTENDANCE_STATUS_ABSENT:
                            absentButton.setSelected(true);
                            break;
                        case Attendance.ATTENDANCE_STATUS_EXCUSED:
                            excusedButton.setSelected(true);
                    }

                    // Listener zmiany zaznaczenia
                    presenceGroup.selectedToggleProperty().addListener((observable, oldValue, newValue) -> {
                        if (newValue == presentButton) {
                            attendance.setAttendanceStatus(Attendance.ATTENDANCE_STATUS_PRESENT);
                        } else if (newValue == absentButton) {
                            attendance.setAttendanceStatus(Attendance.ATTENDANCE_STATUS_ABSENT);
                        } else if (newValue == excusedButton) {
                            attendance.setAttendanceStatus(Attendance.ATTENDANCE_STATUS_EXCUSED);
                        }
                    });

                    setGraphic(new HBox(10, presentButton, absentButton, excusedButton));
                }
            }
        });
    }

    @FXML
    protected void handleSaveButtonAction(javafx.event.ActionEvent event) {
        for (Attendance attendence : attendances) {
            System.out.println("Zapisano obecność: " + attendence.getStudentIndex() + " - " + attendence.getAttendanceStatus());
            // TODO zapisz obecnosci w bazie dla zmienionych studentów
        }
        switchScene(event, ViewsNames.FXML_MAIN_VIEW, ViewsNames.WINDOW_NAME_MAIN_VIEW);
    }

    @FXML
    protected void handleCancelButtonAction(javafx.event.ActionEvent event) {
        switchScene(event, ViewsNames.FXML_MAIN_VIEW, ViewsNames.WINDOW_NAME_MAIN_VIEW);
    }

}
