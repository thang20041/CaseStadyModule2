package view;

import model.Student;
import model.StudentManager;
import user.LogInSignUp;
import user.User;
import user.UserManager;
import validate.Validate;

import java.util.List;
import java.util.Scanner;

public class MenuApp {
    private UserManager userManager;
    private StudentManager studentManager;
    private Scanner scanner;
    private LogInSignUp logInSignUp;
    private boolean isAuthenticated = false;

    public MenuApp() {
        userManager = new UserManager();
        studentManager = new StudentManager();
        scanner = new Scanner(System.in);
        logInSignUp = new LogInSignUp(userManager, scanner);
    }

    public void run() {
        userManager.addUser(new User("a", "1"));

        while (true) {
            System.out.println("==== MENU ====");
            System.out.println("1. Đăng nhập");
            System.out.println("2. Đăng ký tài khoản");
            System.out.println("3. Thoát");
            System.out.print("Chọn một tùy chọn: ");
            int choice;
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    isAuthenticated = logInSignUp.login();
                    if (isAuthenticated) {
                        System.out.println(" Đăng nhập thành công :))) ");
                        studentMenu();
                    }
                    break;
                case 2:
                    logInSignUp.register();
                    break;
                case 3:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn lại.");
                    break;
            }
        }
    }


    public void studentMenu() {
        int choice;

        do {
            System.out.println("\n== MENU SINH VIÊN ==");
            System.out.println("1. Thêm mới sinh viên");
            System.out.println("2. Xóa sinh viên");
            System.out.println("3. Sửa sinh viên");
            System.out.println("4. Tìm kiếm sinh viên theo ID");
            System.out.println("5. Tìm kiếm sinh viên theo tên gần đúng");
            System.out.println("6. Hiển thị học viên có điểm trung bình cao nhất");
            System.out.println("7. Hiển thị tất cả sinh viên");
            System.out.println("8. Thay đổi trạng thái chuyên cần");
            System.out.println("10. Đăng xuất");
            System.out.print("Chọn tùy chọn: ");
            choice = Validate.checkChoice();
            switch (choice) {
                case 1:
                    showAdd();
                    break;
                case 2:
                    showDelete();
                    break;
                case 3:
                    showEdit();
                    break;
                case 4:
                    showSearchById();
                    break;
                case 5:
                    showSearchByName();
                    break;
                case 6:
                    showGetMaxAvg();
                    break;
                case 7:
                    showAll();
                    break;
                case 8:
                    System.out.print("Nhập ID sinh viên cần thay đổi trạng thái: ");
                    String studentId = scanner.nextLine();
                    Student studentToChange = studentManager.findStudentById(studentId);

                    if (studentToChange != null) {
                        System.out.println("Chọn trạng thái mới:");
                        System.out.println("1. Đang đi học");
                        System.out.println("2. Đang nghỉ phép");
                        System.out.println("3. Đang đi học nghỉ không phép");
                        choice = Validate.checkChoice();

                        switch (choice) {
                            case 1:
                                studentToChange.setAttending(true);
                                System.out.println("Trạng thái chuyên cần của sinh viên đã được đặt thành đang đi học.");
                                break;
                            case 2:
                                studentToChange.setAttending(false);
                                System.out.println("Trạng thái chuyên cần của sinh viên đã được đặt thành đang nghỉ phép.");
                                break;
                            case 3:
                                studentToChange.setAttending(false);
                                System.out.println("Trạng thái chuyên cần của sinh viên đã được đặt thành đang đi học nghỉ không phép.");
                                break;
                            default:
                                System.out.println("Lựa chọn không hợp lệ.");
                        }
                    } else {
                        System.out.println("Không tìm thấy sinh viên có ID " + studentId);
                    }
                    break;
                case 9:
                    break;
                case 10:
                    System.out.println("Đăng xuất thành công.");
                    isAuthenticated = false;
                    run();
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ. Vui lòng chọn 0 để quay lại Menu.");
            }
        } while (choice != 0);
    }

    public void showAdd() {
        while (true){
            System.out.println("------- Thêm mới sinh viên--------");
            System.out.println("Nhập id sinh viên: ");
            String id = Validate.checkID(studentManager);
            System.out.println("Nhập tên sinh viên: ");
            String name = Validate.checkName();
            System.out.println("Nhập ngày sinh(yyyy//mm/dd): ");
            String birthday = Validate.checkBirthday();
            System.out.println("Nhập giới tính: ");
            String gender = Validate.checkGender();
            System.out.println("Nhập địa chỉ: ");
            String address = scanner.nextLine();
            System.out.println("Nhập điểm Toán ");
            double math = scanner.nextDouble();
            System.out.println("Nhập điểm Văn ");
            double literature = scanner.nextDouble();
            System.out.println("Nhập điểm Anh ");
            double english = scanner.nextDouble();
            Student student = new Student(id, name, birthday, gender, address, math, literature, english);
            studentManager.addStudent(student);
            System.out.println("Thêm thành công sinh viên mới 👈(⌒▽⌒)👉");
            System.out.println("Bạn có muốn tiếp tục thêm sinh viên khác? (Y/N)");
            scanner.nextLine();
            String choice = scanner.nextLine();

            if (!choice.equalsIgnoreCase("Y")) {
                break;
            }
        }

        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();

    }

    public void showAll() {
        List<Student> list = studentManager.showListStudent();
        if (list.isEmpty()) {
            System.out.println("Danh sách học sinh trống.");
        }else {
            System.out.println("Danh sách học sinh hiện tại là:");
            for (Student student : list) {
                System.out.println(student);
            }
        }
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();

    }

    public void showDelete() {
        System.out.println("---Xóa Học Sinh Khỏi Danh Sách---");
        System.out.print("Nhập ID sinh viên cần xóa: ");
        String deleteId = scanner.nextLine();
        Student studentToDelete = studentManager.findStudentById(deleteId);

        if (studentToDelete != null) {
            boolean isDeleted = studentManager.removeStudent(deleteId);

            if (isDeleted) {
                System.out.println("Xóa sinh viên thành công.");
            } else {
                System.out.println("Không thể xóa sinh viên có ID " + deleteId);
            }
        } else {
            System.out.println("Không tìm thấy sinh viên có ID " + deleteId);
        }
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();

    }

    public void showEdit() {
        System.out.println("---Sửa Học Sinh Trong Danh Sách---");
        System.out.print("Nhập ID sinh viên cần sửa: ");
        String editId = scanner.nextLine();

        Student studentToEdit = studentManager.findStudentById(editId);

        if (studentToEdit != null) {
            System.out.println("Nhập thông tin cập nhật:");

            System.out.print("Nhập tên mới: ");
            studentToEdit.setName(scanner.nextLine());
            System.out.println("Nhập ngày sinh(yyyy//mm/dd) mới: ");
            studentToEdit.setBirthday(scanner.nextLine());
            System.out.println("Nhập giới tính: ");
            studentToEdit.setGender(scanner.nextLine());
            System.out.println("Nhập địa chỉ: ");
            studentToEdit.setAddress(scanner.nextLine());
            System.out.println("Nhập điểm Toán: ");
            double newMath = scanner.nextDouble();
            System.out.println(("Nhập điểm Văn: "));
            double newLiterature = scanner.nextDouble();
            System.out.println("Nhập điểm Anh: ");
            double newEnglish = scanner.nextDouble();
            studentToEdit.setScore(newMath, newLiterature, newEnglish);
            System.out.println("Sửa thông tin sinh viên thành công.(▀̿Ĺ̯▀̿ ̿)");
        } else {
            System.out.println("Không tìm thấy sinh viên có ID " + editId);
        }
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();

    }

    public void showSearchById() {
        System.out.println("---Tim Kiếm Học Sinh Theo ID---");
        System.out.print("Nhập ID sinh viên cần tìm: ");
        String id = scanner.nextLine();
        if (studentManager.findStudentById(id) != null) {
            System.out.println(" =>>>Đã tìm thấy sinh viên: " + studentManager.showListStudent());
        } else {
            System.out.println("=>>> Không tìm thấy sinh viên với ID đã cho :-(");
        }
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();

    }

    public void showSearchByName() {
        System.out.println("---Tim Kiếm Học Sinh Theo Tên---");
        System.out.print("Nhập tên sinh viên cần tìm: ");
        String name = scanner.nextLine();
        System.out.println("Sinh viên có tên " + name + " là: ");
        if (!studentManager.findStudentByName(name).isEmpty()) {
            for (Student student : studentManager.findStudentByName(name)) {
                System.out.println(student.getData());
            }
        } else {
            System.out.println("không tìm thấy sinh viên có tên " + name + " :-(");
        }
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();


    }
    public void showGetMaxAvg(){
        System.out.println("---Học Sinh Có Điểm Trung Bình Môn Cao Nhất---");
        System.out.println(studentManager.studentHaveAverageGradesMax());
        System.out.println("Ấn Enter để quay lại Menu...");
        scanner.nextLine();

    }
}
