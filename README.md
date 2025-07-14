Student Management System – JavaFX Final Project
================================================

This is a student information management system implemented as a final project for the CSCI1202 course (Summer 2025).
The application uses Java, JavaFX GUI, and file I/O, with full support for sorting, filtering, searching, and modifying student records.

Core Features (Mandatory)
-------------------------
- Add new student
- List all students
- Show total student count
- Input validation and error handling
- Search by any field (e.g., name)
- Delete a student (via selection)
- Update a student (via selection)
- Sort students (by GPA)
- GUI instead of CLI (replaces CLI menu requirement)

Bonus Features (Optional)
-------------------------
- JavaFX GUI interface
- Filtering by any combination (e.g., city + year + min GPA)

Technologies Used
-----------------
- Java 17+
- JavaFX 21
- SceneBuilder (for FXML)
- MVC-style modular design
- File I/O with .txt file
- ObservableList and Property binding for TableView

Project Structure
-----------------
StudentManagementSystem/
├── data/
│   └── students.txt
├── resources/
│   └── MainView.fxml
├── src/
│   ├── controller/
│   │   └── MainController.java
│   ├── model/
│   │   ├── Advisor.java
│   │   └── Student.java
│   ├── service/
│   │   └── StudentService.java
│   ├── util/
│   │   ├── FileUtil.java
│   │   └── ValidationUtil.java
│   └── Main.java

How to Run
----------
1. Download and extract the project.
2. Make sure JavaFX SDK 21+ is installed.
3. Add JavaFX to your IDE (IntelliJ: Project Structure → Libraries).
4. Set VM options:
   --module-path /path/to/javafx-sdk-21/lib --add-modules javafx.controls,javafx.fxml
5. Run Main.java.

Notes
-----
- Sample data is stored in data/students.txt
- GUI allows full CRUD operations
- Filtering supports multi-criteria search
- No database or CLI is used – this is a file-based, GUI-driven Java project
