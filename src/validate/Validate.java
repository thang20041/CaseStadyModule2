package validate;

import model.StudentManager;

import java.util.Scanner;

public class Validate {
    static Scanner input = new Scanner(System.in);
    public static String IDRegex = "[A-Za-z0-9]{3}";
    public static String NameRegex = "^[a-zA-ZÀ-ỹ ]{4,40}$";
    public static  String BirthDayRegex = "\\d{4}-\\d{2}-\\d{2}";
    public static  String GenderRegex = "(?i)(Nam|Nữ|Khác)";


    public static String choiceRegex = "[0-9]";
    public static int checkChoice() {
        String choice = "";
        while (true) {
            choice = input.nextLine();
            if (!choice.matches(choiceRegex)) {
                System.out.println("Nhập đúng lựa chọn trong menu");
            } else {
                break;
            }
        }
        return Integer.parseInt(choice);
    }
    public static String checkID(StudentManager productManager) {
        String ID = "";
        while (true) {
            ID = input.nextLine();
            if (!ID.matches(IDRegex)) {
                System.out.println("Hãy nhập đúng định dạng ID");
            } else if (productManager.isIDExists(ID)) {
                System.out.println("ID đã tồn tại. Hãy nhập ID khác.");
            } else {
                break;
            }
        }
        return ID;
    }
    public static String checkName() {
        String Name = "";
        while (true) {
            Name = input.nextLine();
            if (!Name.matches(NameRegex)) {
                System.out.println("Hãy nhập đúng định dạng Tên");
            } else {
                break;
            }
        }
        return Name;
    }
    public static String checkBirthday() {
        String birthday = "";
        while (true) {
            birthday = input.nextLine();
            if (!birthday.matches(BirthDayRegex)) {
                System.out.println("Hãy nhập đúng định dạng ngày sinh (yyyy-MM-dd)");
            } else {
                break;
            }
        }
        return birthday;
    }
    public static String checkGender() {
        String gender = "";
        while (true) {
            gender = input.nextLine();
            if (!gender.matches(GenderRegex)) {
                System.out.println("Hãy nhập giới tính hợp lệ (Nam/Nữ/Khác)");
            } else {
                break;
            }
        }
        return gender;
    }

    public static double[] checkScores() {
        double[] scores;
        while (true) {
            String scoresInput = input.nextLine();
            String[] scoreStrArray = scoresInput.split(",");
            scores = new double[scoreStrArray.length];
            boolean valid = true;
            for (int i = 0; i < scoreStrArray.length; i++) {
                try {
                    scores[i] = Double.parseDouble(scoreStrArray[i].trim());
                    if (scores[i] < 0.0 || scores[i] > 100.0) {
                        System.out.println("Nhập điểm trong khoảng từ 0 đến 100");
                        valid = false;
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Nhập điểm không hợp lệ, hãy nhập lại");
                    valid = false;
                    break;
                }
            }
            if (valid) {
                break;
            }
        }
        return scores;
    }
}
