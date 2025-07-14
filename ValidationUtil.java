package util;

public class ValidationUtil {

    public static boolean isValidStudent(String fullName, String studentId, String major, String yearText,
                                         String gpaText, String address, String advisorName, String department) {
        if (fullName.isEmpty() || studentId.isEmpty() || major.isEmpty() || yearText.isEmpty() ||
                gpaText.isEmpty() || address.isEmpty() || advisorName.isEmpty() || department.isEmpty()) {
            return false;
        }

        try {
            int year = Integer.parseInt(yearText);
            if (year < 1 || year > 4) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        try {
            double gpa = Double.parseDouble(gpaText);
            if (gpa < 0.0 || gpa > 4.0) return false;
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }
}
