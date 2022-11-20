package ru.javaops.bootjava;

import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;
import ru.javaops.bootjava.model.User;

@Getter
@ToString(of = "user")
public class AuthUser extends org.springframework.security.core.userdetails.User {

    @NonNull
    private User user;

    public AuthUser(User user) {
        super(user.getEmail(), user.getPassword(), user.getRoles());
        this.user = user;
    }

    public int id() {
        return user.id();
    }
}
