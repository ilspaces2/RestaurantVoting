package ru.javaops.bootjava;

import ru.javaops.bootjava.model.Role;
import ru.javaops.bootjava.model.User;

import java.util.EnumSet;

public class UserTestUtil {

    public static final String ADMIN_EMAIL = "admin@mail.com";

    public static final int ADMIN_ID = 1;

    public static final String USER_EMAIL = "user@mail.com";

    public static final int USER_ID = 2;

    public static User newUser() {
        return new User("new@mail.com", "newFirstName", "newLastName", "new_user", EnumSet.of(Role.USER));
    }

    public static User updateUser() {
        User user = new User("update@mail.com", "updateName", "updateName", "update_user", EnumSet.of(Role.USER));
        user.setId(USER_ID);
        return user;
    }
}
