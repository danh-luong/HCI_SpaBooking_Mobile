package rosie.com.rosiebeauty.UserData;

import rosie.com.rosiebeauty.LoginActivity;

public class CurrentUserData {

    private static String userName;
    private static String userRole;
    private static String name;

    public static void setCurrentUserAsCustomer() {
        userName = LoginActivity.ROLE_CUSTOMER;
        userRole = LoginActivity.ROLE_CUSTOMER;
        name = "Nguyễn Văn A";

    }

    public static void setCurrentUserAsManager() {
        userName = LoginActivity.ROLE_MANAGER;
        userRole = LoginActivity.ROLE_MANAGER;
        name = "Trần Thị B";
    }

    public static void setCurrentUserAsAdmin() {
        userName = LoginActivity.ROLE_ADMIN;
        userRole = LoginActivity.ROLE_ADMIN;
        name = "Lê Đức K";
    }

}
