package rosie.com.rosiebeauty.Data;

import java.util.HashMap;

import rosie.com.rosiebeauty.R;

public class UserRepository {

    public static HashMap<String, User> userList = new HashMap<>();
    public static User currentUser;
    private static int firstTime = 0;

    public static HashMap<String, User> initDataUser() {
        if (firstTime == 0) {
            userList.put("customer", new User("customer", "Nguyễn Văn A", "customer", "anv@gmail.com", "0921456789", R.drawable.ic_user, User.ROLE_CUSTOMER, "active"));
            userList.put("admin", new User("admin", "Trần Văn B", "admin", "btv@gmail.com", "0321588664", R.drawable.ic_user, User.ROLE_ADMIN, "active"));
            userList.put("manager", new User("manager", "Lê Hoa", "manager", "hoale@gmail.com", "0554894155", R.drawable.ic_user, User.ROLE_MANAGER, "active"));
            firstTime++;
        }
        return userList;
    }

}
