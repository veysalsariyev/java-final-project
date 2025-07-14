package model;

import javafx.beans.property.*;

public class Student {
    private final StringProperty fullName;
    private final StringProperty studentId;
    private final StringProperty major;
    private final IntegerProperty year;
    private final DoubleProperty gpa;
    private final StringProperty address;
    private final ObjectProperty<Advisor> advisor;

    public Student(String fullName, String studentId, String major, int year, double gpa, String address,
                   Advisor advisor) {
        this.fullName = new SimpleStringProperty(fullName);
        this.studentId = new SimpleStringProperty(studentId);
        this.major = new SimpleStringProperty(major);
        this.year = new SimpleIntegerProperty(year);
        this.gpa = new SimpleDoubleProperty(gpa);
        this.address = new SimpleStringProperty(address);
        this.advisor = new SimpleObjectProperty<>(advisor);
    }

    // Getters
    public String getFullName() {
        return fullName.get();
    }

    public String getStudentId() {
        return studentId.get();
    }

    public String getMajor() {
        return major.get();
    }

    public int getYear() {
        return year.get();
    }

    public double getGpa() {
        return gpa.get();
    }

    public String getAddress() {
        return address.get();
    }

    public Advisor getAdvisor() {
        return advisor.get();
    }

    // Property Getters
    public StringProperty fullNameProperty() {
        return fullName;
    }

    public StringProperty studentIdProperty() {
        return studentId;
    }

    public StringProperty majorProperty() {
        return major;
    }

    public IntegerProperty yearProperty() {
        return year;
    }

    public DoubleProperty gpaProperty() {
        return gpa;
    }

    public StringProperty addressProperty() {
        return address;
    }

    public ObjectProperty<Advisor> advisorProperty() {
        return advisor;
    }

    @Override
    public String toString() {
        return fullName.get() + "," + studentId.get() + "," + major.get() + "," + year.get() + "," + gpa.get() + ","
                + address.get() + "," + advisor.get().toString();
    }
}
