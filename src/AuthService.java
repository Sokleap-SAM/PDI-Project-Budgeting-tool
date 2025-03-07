import java.util.HashMap;
import java.util.Map;

public class AuthService {
    private Map<String, User> users = new HashMap<>();
    private User user;
    private DataManagement dataManagement = new DataManagement();

    AuthService(){
        users = dataManagement.loadUser();
    }

    public void register(String username, String password) {
        if (users.containsKey(username)) {
            System.out.println("Username already exists!");
        } else {
            users.put(username, new User(username, password));
            System.out.println("User registered successfully!");
        }
    }

    public boolean login(String username, String password) {
        user = users.get(username); //object = object that contain "username"
        if (user != null && user.getPassword().equals(password)) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid username or password!");
            return false;
        }
    }

    public void changePassword(String newPassword){
       user.setPassword(newPassword);
    }

}