package util;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtil {
    private static final String FILE_PATH = "data/students.txt";

    public static List<Student> loadStudentsFromFile() {
        List<Student> students = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 7) {
                    String fullName = parts[0];
                    String studentId = parts[1];
                    String major = parts[2];
                    int year = Integer.parseInt(parts[3]);
                    double gpa = Double.parseDouble(parts[4]);
                    String address = parts[5];

                    String[] advisorParts = parts[6].split(" - ");
                    String advisorName = advisorParts[0];
                    String advisorDept = advisorParts.length > 1 ? advisorParts[1] : "";

                    students.add(new Student(fullName, studentId, major, year, gpa, address,
                            new model.Advisor(advisorName, advisorDept)));
                }
            }
        } catch (IOException e) {
            System.out.println("No existing file or read error.");
        }

        return students;
    }

    public static void saveStudentsToFile(List<Student> students) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Student s : students) {
                bw.write(s.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
