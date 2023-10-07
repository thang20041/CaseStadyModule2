package user;

import java.util.ArrayList;
import java.util.List;

public class UserManager {
    private List<User> users;
    public UserManager(){
        users =  new ArrayList<>();
    }
    public void addUser(User user) {
        users.add(user);
    }
    public boolean authenticate(String userName, String password) {
        for (User user : users) {
            if (user.getUserName().equals(userName) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
