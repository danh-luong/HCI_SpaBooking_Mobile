package rosie.com.rosiebeauty.Data;

import java.util.HashMap;

public class UserRepository {

    public static HashMap<String, User> userList = new HashMap<>();
    public static User currentUser;
    private static int firstTime = 0;

    public static HashMap<String, User> initDataUser() {
        if (firstTime == 0) {
            userList.put("customer", new User("customer", "Nguyễn Văn A", "customer", "anv@gmail.com", "0921456789", "imgUrl", User.ROLE_CUSTOMER));
            userList.put("admin", new User("admin", "Trần Văn B", "admin", "btv@gmail.com", "0321588664", "imgUrl", User.ROLE_ADMIN));
            userList.put("manager", new User("manager", "Lê Hoa", "manager", "hoale@gmail.com", "0554894155", "imgUrl", User.ROLE_MANAGER));
            firstTime++;
        }
        return userList;
    }

}
