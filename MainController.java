package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.Student;
import model.Advisor;
import service.*;
import util.*;

public class MainController {

    @FXML private TableView<Student> studentTable;
    @FXML private TableColumn<Student, String> nameColumn;
    @FXML private TableColumn<Student, String> idColumn;
    @FXML private TableColumn<Student, String> majorColumn;
    @FXML private TableColumn<Student, Integer> yearColumn;
    @FXML private TableColumn<Student, Double> gpaColumn;
    @FXML private TableColumn<Student, String> addressColumn;
    @FXML private TableColumn<Student, String> advisorColumn;

    @FXML private TextField fullNameField, idField, majorField, yearField, gpaField;
    @FXML private TextField addressField, advisorNameField, departmentField;
    @FXML private TextField searchField, filterCityField, filterYearField, filterGpaField;

    private final StudentService studentService = new StudentService();
    private final ObservableList<Student> studentObservableList = FXCollections.observableArrayList();


    /*
      Initializes the controller.
      Loads students from file and sets up the TableView bindings.
    */

    @FXML
    public void initialize() {
        studentObservableList.addAll(studentService.getAllStudents());

        nameColumn.setCellValueFactory(cell -> cell.getValue().fullNameProperty());
        idColumn.setCellValueFactory(cell -> cell.getValue().studentIdProperty());
        majorColumn.setCellValueFactory(cell -> cell.getValue().majorProperty());
        yearColumn.setCellValueFactory(cell -> cell.getValue().yearProperty().asObject());
        gpaColumn.setCellValueFactory(cell -> cell.getValue().gpaProperty().asObject());
        addressColumn.setCellValueFactory(cell -> cell.getValue().addressProperty());
        advisorColumn.setCellValueFactory(cell -> cell.getValue().advisorProperty().
                asString());

        studentTable.setItems(studentObservableList);
        studentTable.getSelectionModel().selectedItemProperty().addListener((
                obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                fullNameField.setText(newSelection.getFullName());
                idField.setText(newSelection.getStudentId());
                majorField.setText(newSelection.getMajor());
                yearField.setText(String.valueOf(newSelection.getYear()));
                gpaField.setText(String.valueOf(newSelection.getGpa()));
                addressField.setText(newSelection.getAddress());
                advisorNameField.setText(newSelection.getAdvisor().getName());
                departmentField.setText(newSelection.getAdvisor().getDepartment());
            }
        });

    }

    /*
      Adds a new student.
      Validates all inputs before adding.
    */

    @FXML
    private void addStudent() {
        String fullName = fullNameField.getText();
        String studentId = idField.getText();
        String major = majorField.getText();
        String yearText = yearField.getText();
        String gpaText = gpaField.getText();
        String address = addressField.getText();
        String advisorName = advisorNameField.getText();
        String department = departmentField.getText();

        if (!ValidationUtil.isValidStudent(fullName, studentId, major, yearText, gpaText,
                address, advisorName, department)) {
            showAlert("Invalid input", "Please fill all fields correctly.");
            return;
        }

        int year = Integer.parseInt(yearText);
        double gpa = Double.parseDouble(gpaText);

        Advisor advisor = new Advisor(advisorName, department);
        Student student = new Student(fullName, studentId, major, year, gpa, address, advisor);

        studentService.addStudent(student);
        studentObservableList.add(student);

        FileUtil.saveStudentsToFile(studentService.getAllStudents());
        clearFields();
    }


    /*
      Deletes the selected student from the table and from file.
      Shows alert if no student is selected.
    */

    @FXML
    private void deleteSelected() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No selection", "Please select a student to delete.");
            return;
        }

        studentService.deleteStudent(selected);
        studentObservableList.remove(selected);
        FileUtil.saveStudentsToFile(studentService.getAllStudents());
    }

    @FXML
    private void updateStudent() {
        Student selected = studentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("No selection", "Please select a student to update.");
            return;
        }

        String fullName = fullNameField.getText();
        String studentId = idField.getText();
        String major = majorField.getText();
        String yearText = yearField.getText();
        String gpaText = gpaField.getText();
        String address = addressField.getText();
        String advisorName = advisorNameField.getText();
        String department = departmentField.getText();

        if (!ValidationUtil.isValidStudent(fullName, studentId, major, yearText, gpaText,
                address, advisorName, department)) {
            showAlert("Invalid input", "Please fill all fields correctly.");
            return;
        }

        int year = Integer.parseInt(yearText);
        double gpa = Double.parseDouble(gpaText);
        Advisor advisor = new Advisor(advisorName, department);
        Student updated = new Student(fullName, studentId, major, year, gpa, address, advisor);

        studentService.updateStudent(selected, updated);
        studentObservableList.set(studentObservableList.indexOf(selected), updated);
        FileUtil.saveStudentsToFile(studentService.getAllStudents());
        clearFields();
    }

    @FXML
    private void sortByGpa() {
        studentObservableList.setAll(studentService.sortByGpa());
    }

    @FXML
    private void searchStudent() {
        String keyword = searchField.getText().trim().toLowerCase();
        if (keyword.isEmpty()) {
            showAlert("Empty search", "Please enter a name to search.");
            return;
        }
        studentObservableList.setAll(studentService.searchByName(keyword));
    }

    @FXML
    private void resetTable() {
        studentObservableList.setAll(studentService.getAllStudents());
    }

    /*
      Filters students based on entered city, year, and minimum GPA.
      Supports any combination of filters.
    */

    @FXML
    private void applyFilter() {
        String city = filterCityField.getText().trim();
        String yearText = filterYearField.getText().trim();
        String gpaText = filterGpaField.getText().trim();

        studentObservableList.setAll(studentService.filterStudents(city, yearText, gpaText));
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        fullNameField.clear();
        idField.clear();
        majorField.clear();
        yearField.clear();
        gpaField.clear();
        addressField.clear();
        advisorNameField.clear();
        departmentField.clear();
    }
}
