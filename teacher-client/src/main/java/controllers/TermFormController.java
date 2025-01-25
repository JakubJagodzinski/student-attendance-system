package controllers;

import entities.StudentsGroup;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.AlertHandler;
import utils.ViewsNames;

import static utils.AlertHandler.showAlert;
import static utils.SceneSwitcher.switchScene;

public class TermFormController {

    @FXML
    private TextField termNameField;

    @FXML
    private DatePicker termStartDateField;

    @FXML
    private Spinner<Integer> termStartHourField;

    @FXML
    private Spinner<Integer> termStartMinuteField;

    @FXML
    private Spinner<Integer> termEndHourField;

    @FXML
    private Spinner<Integer> termEndMinuteField;

    @FXML
    private TableView<StudentsGroup> groupsTableView;

    @FXML
    private TableColumn<StudentsGroup, String> groupName;

    @FXML
    protected void initialize() {

        SpinnerValueFactory<Integer> startHourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        termStartHourField.setValueFactory(startHourValueFactory);
        SpinnerValueFactory<Integer> endHourValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        termEndHourField.setValueFactory(endHourValueFactory);

        SpinnerValueFactory<Integer> startMinuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, 0, 15);
        termStartMinuteField.setValueFactory(startMinuteValueFactory);
        SpinnerValueFactory<Integer> endMinuteValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 45, 0, 15);
        termEndMinuteField.setValueFactory(endMinuteValueFactory);

        groupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));

        // groups temporary data
        groupsTableView.getItems().add(new StudentsGroup("Grupa A", "Opis grupy A", 5));
        groupsTableView.getItems().add(new StudentsGroup("Grupa B", "Opis grupy B", 10));
        groupsTableView.getItems().add(new StudentsGroup("Grupa C", "Opis grupy C", 8));

        // TODO dodac logike pobierania dostępnych grup z bazy danych

    }

    @FXML
    protected void handleCancelButtonAction(javafx.event.ActionEvent event) {
        switchScene(event, ViewsNames.FXML_MAIN_VIEW, ViewsNames.WINDOW_NAME_MAIN_VIEW);
    }

    @FXML
    protected void handleSaveButtonAction(javafx.event.ActionEvent event) {
        String name = termNameField.getText();
        String startDate = termStartDateField.getValue() == null ? null : termStartDateField.getValue().toString();
        String startHour = termStartHourField.getValue().toString();
        String startMinute = termStartMinuteField.getValue().toString();
        String endHour = termEndHourField.getValue().toString();
        String endMinute = termEndMinuteField.getValue().toString();
        StudentsGroup selectedGroup = groupsTableView.getSelectionModel().getSelectedItem();

        if (name != null && !name.trim().isEmpty()
                && startDate != null && !startDate.trim().isEmpty()
                && !startHour.trim().isEmpty()
                && !startMinute.trim().isEmpty()
                && !endHour.trim().isEmpty()
                && !endMinute.trim().isEmpty()
                && (Integer.parseInt(startHour) < Integer.parseInt(endHour)
                || (Integer.parseInt(startHour) == Integer.parseInt(endHour) && Integer.parseInt(startMinute) < Integer.parseInt(endMinute)))
                && selectedGroup != null) {

            // TODO dodawanie / edytowanie terminu w bazie danych :)

            showAlert(Alert.AlertType.INFORMATION, AlertHandler.SUCCESS, "Dodano termin");
            termNameField.clear();
            switchScene(event, ViewsNames.FXML_MAIN_VIEW, ViewsNames.WINDOW_NAME_MAIN_VIEW);
        } else {
            showAlert(Alert.AlertType.WARNING, AlertHandler.FAILURE, AlertHandler.FILL_ALL_FIELDS_ALERT);
        }
    }

}
