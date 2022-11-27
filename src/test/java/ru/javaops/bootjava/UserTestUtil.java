package ru.javaops.bootjava;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import ru.javaops.bootjava.model.Role;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.util.JsonUtil;

import java.io.UnsupportedEncodingException;
import java.util.EnumSet;
import java.util.function.BiConsumer;

import static org.assertj.core.api.Assertions.assertThat;

public class UserTestUtil {

    public static final String ADMIN_EMAIL = "admin@mail.com";

    public static final int ADMIN_ID = 1;

    public static final String USER_EMAIL = "user@mail.com";

    public static final int USER_ID = 2;

    public static final User user = new User(USER_ID, USER_EMAIL, "first", "last", "user", EnumSet.of(Role.USER));

    public static final User admin = new User(ADMIN_ID, ADMIN_EMAIL, "first", "last", "admin", EnumSet.of(Role.ADMIN));

    public static User newUser() {
        return new User("new@mail.com", "newFirstName", "newLastName", "new_user", EnumSet.of(Role.USER));
    }

    public static User updateUser() {
        return new User(USER_ID, "update@mail.com", "updateName", "updateName", "update_user", EnumSet.of(Role.USER));
    }

    public static void assertEquals(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("password").isEqualTo(expected);
    }

    // No id in HATEOAS answer
    public static void assertNoIdEquals(User actual, User expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id", "password").isEqualTo(expected);
    }

    public static User asUser(MvcResult mvcResult) throws UnsupportedEncodingException, JsonProcessingException {
        String jsonActual = mvcResult.getResponse().getContentAsString();
        return JsonUtil.readValue(jsonActual, User.class);
    }

    public static ResultMatcher jsonMatcher(User expected, BiConsumer<User, User> equalsAssertion) {
        return mvcResult -> equalsAssertion.accept(asUser(mvcResult), expected);
    }
}
