package user;

import java.util.Scanner;

public class LogInSignUp {
    private UserManager userManager;
    private Scanner scanner;

    public LogInSignUp(UserManager userManager, Scanner scanner) {
        this.userManager = userManager;
        this.scanner = scanner;
    }

    public boolean login() {
        while (true) {
            System.out.print("Nhập tên người dùng: ");
            String userName = scanner.nextLine();

            System.out.print("Nhập mật khẩu: ");
            String password = scanner.nextLine();

            if (userManager.authenticate(userName, password)) {
                return true;

            } else {
                System.out.println("Sai tài khoản hoặc mật khẩu. Vui lòng thử lại.");
            }
        }
    }

    public void register() {
        System.out.print("Nhập tên người dùng mới: ");
        String newUserName = scanner.nextLine();

        System.out.print("Nhập mật khẩu mới: ");
        String newPassword = scanner.nextLine();

        userManager.addUser(new User(newUserName, newPassword));
        System.out.println("Đăng ký tài khoản thành công.");
    }
}
