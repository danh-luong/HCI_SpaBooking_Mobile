package rosie.com.rosiebeauty.Data;

import java.util.HashMap;

import rosie.com.rosiebeauty.R;

public class UserRepository {

    public static HashMap<String, User> userList = new HashMap<>();
    public static User currentUser;
    private static int firstTime = 0;

    public static HashMap<String, User> initDataUser() {
        if (firstTime == 0) {
            userList.put("customer", new User("customer", "Nguyễn Văn A", "customer", "anv@gmail.com", "0921456789", R.drawable.ic_user, User.ROLE_CUSTOMER, "active", true));
            userList.put("customer1", new User("customer1", "Trần Đăng Kiệt", "customer", "trandangkiet@gmail.com", "0921456789", R.drawable.ic_user, User.ROLE_CUSTOMER, "deactive", true));
            userList.put("customer2", new User("customer2", "Lê Toàn Phúc", "customer", "letoanphuc@gmail.com", "0921456789", R.drawable.ic_user, User.ROLE_CUSTOMER, "active", true));
            userList.put("customer3", new User("customer3", "Phạm Khắc Thành", "customer", "phamkhacthanh@gmail.com", "0921456789", R.drawable.ic_user, User.ROLE_CUSTOMER, "active", true));
            userList.put("customer4", new User("customer4", "Bùi Bá Thành", "customer", "buibathanh@gmail.com", "0921456789", R.drawable.ic_user, User.ROLE_CUSTOMER, "deactive", true));
            userList.put("admin", new User("admin", "Trần Văn B", "admin", "btv@gmail.com", "0321588664", R.drawable.ic_user, User.ROLE_ADMIN, "active", true));
            userList.put("manager", new User("manager", "Lê Hoa", "manager", "hoale@gmail.com", "0554894155", R.drawable.ic_user, User.ROLE_MANAGER, "active", true));
            userList.put("manager1", new User("manager1", "Nguyễn Đinh", "manager", "dinhnguyen@gmail.com", "0984513648", R.drawable.ic_user, User.ROLE_MANAGER, "active", true));
            userList.put("manager2", new User("manager2", "Nguyễn Văn Đức", "manager", "dinhnguyen@gmail.com", "0994101212", R.drawable.ic_user, User.ROLE_MANAGER, "active", true));
            userList.put("manager3", new User("manager3", "Trần Thiên Lộc", "manager", "dinhnguyen@gmail.com", "09878413", R.drawable.ic_user, User.ROLE_MANAGER, "active", true));
            userList.put("manager4", new User("manager4", "Lê Đình Toàn", "manager", "dinhnguyen@gmail.com", "0932155454", R.drawable.ic_user, User.ROLE_MANAGER, "deactive", true));
            userList.put("unaccepted_manager", new User("unaccepted_manager", "Trần Quang", "manager", "trangquang@gmail.com", "0314899846", R.drawable.ic_user, User.ROLE_MANAGER, "active", false, "Hoa Cúc", "462/1 Tân Sơn, Gò Vấp"));
            userList.put("unaccepted_manager1", new User("unaccepted_manager1", "Nguyễn Khoa", "manager", "khoanguyen@gmail.com", "031597789", R.drawable.ic_user, User.ROLE_MANAGER, "active", false, "Golden Spa", "462/1 Quang Trung, Gò Vấp"));
            firstTime++;
        }
        return userList;
    }

}
