package service;

import model.Student;
import util.*;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class StudentService {

    private final List<Student> students;

    public StudentService() {
        this.students = new ArrayList<>(FileUtil.loadStudentsFromFile());
    }

    public List<Student> getAllStudents() {
        return new ArrayList<>(students);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void deleteStudent(Student student) {
        students.remove(student);
    }

    public void updateStudent(Student oldStudent, Student newStudent) {
        int index = students.indexOf(oldStudent);
        if (index != -1) {
            students.set(index, newStudent);
        }
    }

    public List<Student> sortByGpa() {
        return students.stream()
                .sorted(Comparator.comparingDouble(Student::getGpa).reversed())
                .collect(Collectors.toList());
    }

    public List<Student> searchByName(String keyword) {
        return students.stream()
                .filter(s -> s.getFullName().toLowerCase().contains(keyword))
                .collect(Collectors.toList());
    }

    public List<Student> filterStudents(String city, String yearText, String gpaText) {
        return students.stream().filter(student -> {
            boolean match = true;

            if (!city.isEmpty()) {
                match &= student.getAddress().toLowerCase().contains(city.toLowerCase());
            }

            if (!yearText.isEmpty()) {
                try {
                    int year = Integer.parseInt(yearText);
                    match &= student.getYear() == year;
                } catch (NumberFormatException ignored) {}
            }

            if (!gpaText.isEmpty()) {
                try {
                    double minGpa = Double.parseDouble(gpaText);
                    match &= student.getGpa() >= minGpa;
                } catch (NumberFormatException ignored) {}
            }

            return match;
        }).collect(Collectors.toList());
    }
}
