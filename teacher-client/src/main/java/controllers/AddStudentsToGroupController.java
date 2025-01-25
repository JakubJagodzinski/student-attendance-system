package controllers;

import entities.Student;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import utils.ViewsNames;

import static utils.SceneSwitcher.switchScene;

public class AddStudentsToGroupController {

    @FXML
    private TableView<Student> studentsTableView;

    @FXML
    private TableColumn<Student, String> studentIndex;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableColumn<Student, String> studentSurname;

    static private String groupName = null;

    static public void setGroupName(String newGroupName) {
        groupName = newGroupName;
    }

    @FXML
    protected void initialize() {
        studentsTableView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        studentIndex.setCellValueFactory(new PropertyValueFactory<>("studentIndex"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentSurname.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));

        // TODO dodac logike pobierania studentow z bazy (studenci którzy nie mają przypisanej grrupy bądz ich grupa nie istnieje D:

        studentsTableView.getItems().add(new Student("123456", "Jan", "Kowalski", ""));
        studentsTableView.getItems().add(new Student("654321", "Krzysztof", "Nowak", ""));
        studentsTableView.getItems().add(new Student("987654", "Anna", "Kowalska", ""));

    }

    @FXML
    protected void handleSaveButtonAction(javafx.event.ActionEvent event) {

        var students = studentsTableView.getSelectionModel().getSelectedItems();
        System.out.println("This group name " + groupName);
        for (var student : students) {
            System.out.println(student.toString());
        }


        //TODO Dodawnie studentow do grupy do bazy danych :)

        switchScene(event, ViewsNames.FXML_MAIN_VIEW, ViewsNames.WINDOW_NAME_MAIN_VIEW);
    }

    @FXML
    protected void handleCancelButtonAction(javafx.event.ActionEvent event) {
        switchScene(event, ViewsNames.FXML_MAIN_VIEW, ViewsNames.WINDOW_NAME_MAIN_VIEW);
    }

}
