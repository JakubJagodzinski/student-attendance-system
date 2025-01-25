package controllers;

import entities.Student;
import entities.StudentsGroup;
import entities.Term;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Callback;
import utils.ViewsNames;

import static utils.SceneSwitcher.switchScene;

public class MainController {

    // ------------------------------------- GROUPS -------------------------------------
    @FXML
    private TableView<StudentsGroup> groupsTableView;

    @FXML
    private TableColumn<StudentsGroup, String> groupName;

    @FXML
    private TableColumn<StudentsGroup, String> groupDescription;

    @FXML
    private TableColumn<StudentsGroup, Integer> groupNumberOfStudents;

    @FXML
    private TableColumn<StudentsGroup, Void> groupOperations;

    // ------------------------------------- STUDENTS -------------------------------------
    @FXML
    private TableView<Student> studentsTableView;

    @FXML
    private TableColumn<Student, String> studentIndex;

    @FXML
    private TableColumn<Student, String> studentName;

    @FXML
    private TableColumn<Student, String> studentSurname;

    @FXML
    private TableColumn<Student, String> studentGroup;

    @FXML
    private TableColumn<Student, Void> studentOperations;

    // ------------------------------------- TERMS -------------------------------------
    @FXML
    private TableView<Term> termsTableView;

    @FXML
    private TableColumn<Term, String> termName;

    @FXML
    private TableColumn<Term, String> termGroup;

    @FXML
    private TableColumn<Term, String> termDate;

    @FXML
    private TableColumn<Term, String> termHourStart;

    @FXML
    private TableColumn<Term, String> termHourEnd;

    @FXML
    private TableColumn<Term, Void> termOperations;

    @FXML
    private TabPane mainTabPane;

    static private int lastSelectedTabIndex = 0;

    public void setLastSelectedTabIndex(int newLastSelectedTabIndex) {
        lastSelectedTabIndex = newLastSelectedTabIndex;
    }

    @FXML
    public void initialize() {
        mainTabPane.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            lastSelectedTabIndex = newValue.intValue();
        });

        mainTabPane.getSelectionModel().select(lastSelectedTabIndex);

        // Mapowanie kolumn na właściwości klasy Group
        groupName.setCellValueFactory(new PropertyValueFactory<>("groupName"));
        groupDescription.setCellValueFactory(new PropertyValueFactory<>("groupDescription"));
        groupNumberOfStudents.setCellValueFactory(new PropertyValueFactory<>("groupNumberOfStudents"));

        // Mapowanie kolumn na właściwości klasy Student
        studentIndex.setCellValueFactory(new PropertyValueFactory<>("studentIndex"));
        studentName.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        studentSurname.setCellValueFactory(new PropertyValueFactory<>("studentSurname"));
        studentGroup.setCellValueFactory(new PropertyValueFactory<>("studentGroup"));

        // Mapowanie kolumn na właściwości klasy Term
        termName.setCellValueFactory(new PropertyValueFactory<>("termName"));
        termGroup.setCellValueFactory(new PropertyValueFactory<>("termGroup"));
        termDate.setCellValueFactory(new PropertyValueFactory<>("termDate"));
        termHourStart.setCellValueFactory(new PropertyValueFactory<>("termHourStart"));
        termHourEnd.setCellValueFactory(new PropertyValueFactory<>("termHourEnd"));

        //Todo Logika pobrania rekordów z bazy danych

        // groups temporary data
        groupsTableView.getItems().add(new StudentsGroup("Grupa A", "Opis grupy A", 5));
        groupsTableView.getItems().add(new StudentsGroup("Grupa B", "Opis grupy B", 10));
        groupsTableView.getItems().add(new StudentsGroup("Grupa C", "Opis grupy C", 8));

        // students temporary data
        studentsTableView.getItems().add(new Student("123456", "Jan", "Kowalski", "Grupa A"));
        studentsTableView.getItems().add(new Student("654321", "Krzysztof", "Nowak", "Grupa B"));
        studentsTableView.getItems().add(new Student("987654", "Anna", "Kowalska", "Grupa C"));

        // terms temporary data
        termsTableView.getItems().add(new Term("Termin A", "Grupa A", "0", "1", "2"));
        termsTableView.getItems().add(new Term("Termin B", "Grupa B", "1", "2", "3"));
        termsTableView.getItems().add(new Term("Termin C", "Grupa C", "1", "3", "4"));

        addButtonsToGroupsTable();
        addButtonsToStudentsTable();
        addButtonsToTermsTable();
    }

    private void addButtonsToGroupsTable() {
        Callback<TableColumn<StudentsGroup, Void>, TableCell<StudentsGroup, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<StudentsGroup, Void> call(final TableColumn<StudentsGroup, Void> param) {
                return new TableCell<>() {
                    private final Button deleteGroupButton = new Button();
                    private final Button addStudentsButton = new Button();

                    {
                        Integer iconWidth = 16;
                        Integer iconHeight = 16;

                        ImageView addStudentsIcon = new ImageView(new Image(getClass().getResource("/icons/user.png").toExternalForm()));
                        addStudentsIcon.setFitWidth(iconWidth);
                        addStudentsIcon.setFitHeight(iconHeight);
                        addStudentsButton.setGraphic(addStudentsIcon);
                        Tooltip addStudentsButtonTooltip = new Tooltip("Dodaj studentów do grupy");
                        addStudentsButtonTooltip.setShowDelay(javafx.util.Duration.millis(500));
                        addStudentsButtonTooltip.setHideDelay(javafx.util.Duration.millis(200));
                        addStudentsButton.setTooltip(addStudentsButtonTooltip);

                        ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/icons/remove.png").toExternalForm()));
                        deleteIcon.setFitWidth(iconWidth);
                        deleteIcon.setFitHeight(iconHeight);
                        deleteGroupButton.setGraphic(deleteIcon);
                        Tooltip deleteGroupButtontooltip = new Tooltip("Usuń grupę");
                        deleteGroupButtontooltip.setShowDelay(javafx.util.Duration.millis(500));
                        deleteGroupButtontooltip.setHideDelay(javafx.util.Duration.millis(200));
                        deleteGroupButton.setTooltip(deleteGroupButtontooltip);

                        deleteGroupButton.setOnAction(event -> {
                            StudentsGroup data = getTableView().getItems().get(getIndex());
                            System.out.println("Usuń grupe " + data.getGroupName());
                            // TODO logika usuwania grupy z bazy
                            getTableView().getItems().remove(data);
                        });

                        addStudentsButton.setOnAction(event -> {
                            StudentsGroup data = getTableView().getItems().get(getIndex());
                            System.out.println("Dodaj studentów do grupy " + data.getGroupName());
                            AddStudentsToGroupController.setGroupName(data.getGroupName());
                            switchScene(event, ViewsNames.FXML_ADD_STUDENTS_TO_GROUP_VIEW, ViewsNames.WINDOW_NAME_ADD_STUDENTS_TO_GROUP_VIEW);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new javafx.scene.layout.HBox(5, addStudentsButton, deleteGroupButton));
                        }
                    }
                };
            }
        };

        groupOperations.setCellFactory(cellFactory);
    }

    private void addButtonsToStudentsTable() {
        Callback<TableColumn<Student, Void>, TableCell<Student, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Student, Void> call(final TableColumn<Student, Void> param) {
                return new TableCell<>() {
                    private final Button removeStudentFromGroupButton = new Button();
                    private final Button deleteStudentButton = new Button();

                    {
                        Integer iconWidth = 16;
                        Integer iconHeight = 16;

                        ImageView removeStudentFromGroupIcon = new ImageView(new Image(getClass().getResource("/icons/remove_from_group.png").toExternalForm()));
                        removeStudentFromGroupIcon.setFitWidth(iconWidth);
                        removeStudentFromGroupIcon.setFitHeight(iconHeight);
                        removeStudentFromGroupButton.setGraphic(removeStudentFromGroupIcon);
                        Tooltip removeStudentFromGroupButtontooltip = new Tooltip("Usuń studenta z grupy");
                        removeStudentFromGroupButtontooltip.setShowDelay(javafx.util.Duration.millis(500));
                        removeStudentFromGroupButtontooltip.setHideDelay(javafx.util.Duration.millis(200));
                        removeStudentFromGroupButton.setTooltip(removeStudentFromGroupButtontooltip);

                        ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/icons/remove.png").toExternalForm()));
                        deleteIcon.setFitWidth(iconWidth);
                        deleteIcon.setFitHeight(iconHeight);
                        deleteStudentButton.setGraphic(deleteIcon);
                        Tooltip deleteStudentButtontooltip = new Tooltip("Usuń studenta");
                        deleteStudentButtontooltip.setShowDelay(javafx.util.Duration.millis(500));
                        deleteStudentButtontooltip.setHideDelay(javafx.util.Duration.millis(200));
                        deleteStudentButton.setTooltip(deleteStudentButtontooltip);

                        removeStudentFromGroupButton.setOnAction(event -> {
                            Student data = getTableView().getItems().get(getIndex());
                            System.out.println("Usunięto studenta " + data.getStudentIndex() + " z grupy " + data.getStudentGroup());
                            // TODO dodac logike usuwania nr grupy studenta
                        });

                        deleteStudentButton.setOnAction(event -> {
                            Student data = getTableView().getItems().get(getIndex());
                            System.out.println("Usuń studenta " + data.getStudentIndex());
                            // TODO logika usuwania studenta z bazy
                            getTableView().getItems().remove(data);
                        });

                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new javafx.scene.layout.HBox(5, removeStudentFromGroupButton, deleteStudentButton));
                        }
                    }
                };
            }
        };

        studentOperations.setCellFactory(cellFactory);
    }

    private void addButtonsToTermsTable() {
        Callback<TableColumn<Term, Void>, TableCell<Term, Void>> cellFactory = new Callback<>() {
            @Override
            public TableCell<Term, Void> call(final TableColumn<Term, Void> param) {
                return new TableCell<>() {
                    private final Button setPresenceButton = new Button();
                    private final Button deleteTermButton = new Button();

                    {
                        Integer iconWidth = 16;
                        Integer iconHeight = 16;

                        ImageView setPresenceIcon = new ImageView(new Image(getClass().getResource("/icons/list.png").toExternalForm()));
                        setPresenceIcon.setFitWidth(iconWidth);
                        setPresenceIcon.setFitHeight(iconHeight);
                        setPresenceButton.setGraphic(setPresenceIcon);
                        Tooltip setPresenceButtontooltip = new Tooltip("Ustaw obecności dla terminu");
                        setPresenceButtontooltip.setShowDelay(javafx.util.Duration.millis(500));
                        setPresenceButtontooltip.setHideDelay(javafx.util.Duration.millis(200));
                        setPresenceButton.setTooltip(setPresenceButtontooltip);

                        ImageView deleteIcon = new ImageView(new Image(getClass().getResource("/icons/remove.png").toExternalForm()));
                        deleteIcon.setFitWidth(iconWidth);
                        deleteIcon.setFitHeight(iconHeight);
                        deleteTermButton.setGraphic(deleteIcon);
                        Tooltip deleteTermButtontooltip = new Tooltip("Usuń termin");
                        deleteTermButtontooltip.setShowDelay(javafx.util.Duration.millis(500));
                        deleteTermButtontooltip.setHideDelay(javafx.util.Duration.millis(200));
                        deleteTermButton.setTooltip(deleteTermButtontooltip);

                        deleteTermButton.setOnAction(event -> {
                            Term data = getTableView().getItems().get(getIndex());
                            System.out.println("Usuń termin " + data.getTermName());
                            // TODO logika usuwania terminu z bazy
                            getTableView().getItems().remove(data);
                        });

                        setPresenceButton.setOnAction(event -> {
                            Term data = getTableView().getItems().get(getIndex());
                            System.out.println("Ustaw obecności dla terminu " + data.getTermName());
                            SetTermAttendanceController.setTermId(data.getTermId());
                            switchScene(event, ViewsNames.FXML_SET_TERM_PRESENCE_VIEW, ViewsNames.WINDOW_NAME_SET_TERM_PRESENCE_VIEW);
                        });
                    }

                    @Override
                    protected void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(new javafx.scene.layout.HBox(5, setPresenceButton, deleteTermButton));
                        }
                    }
                };
            }
        };

        termOperations.setCellFactory(cellFactory);
    }

    @FXML
    protected void handleAddGroupButtonAction(javafx.event.ActionEvent event) {
        switchScene(event, ViewsNames.FXML_GROUP_FORM_VIEW, ViewsNames.WINDOW_NAME_GROUP_FORM_VIEW);
    }

    @FXML
    protected void handleAddStudentButtonAction(javafx.event.ActionEvent event) {
        switchScene(event, ViewsNames.FXML_STUDENT_FORM_VIEW, ViewsNames.WINDOW_NAME_STUDENT_FORM_VIEW);
    }

    @FXML
    protected void handleAddTermButtonAction(javafx.event.ActionEvent event) {
        switchScene(event, ViewsNames.FXML_TERM_FORM_VIEW, ViewsNames.WINDOW_NAME_TERM_FORM_VIEW);
    }

}