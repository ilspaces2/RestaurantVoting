package ru.javaops.bootjava.web;

import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.javaops.bootjava.AuthUser;
import ru.javaops.bootjava.model.User;

@RestController
@RequestMapping("api/account")
public class AccountController {

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public User User(@AuthenticationPrincipal AuthUser user) {
        return user.getUser();
    }
}
