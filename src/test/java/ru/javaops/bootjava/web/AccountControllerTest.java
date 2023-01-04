package ru.javaops.bootjava.web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.javaops.bootjava.AbstractControllerTest;
import ru.javaops.bootjava.UserTestUtil;
import ru.javaops.bootjava.model.User;
import ru.javaops.bootjava.repository.UserRepository;
import ru.javaops.bootjava.util.JsonUtil;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javaops.bootjava.UserTestUtil.*;
import static ru.javaops.bootjava.web.AccountController.URL;

public class AccountControllerTest extends AbstractControllerTest {

    private static final String REGISTER = "/register";

    @Autowired
    private UserRepository userRepository;

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void get() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaTypes.HAL_JSON_VALUE))
                .andExpect(jsonMatcher(user, UserTestUtil::assertNoIdEquals));
    }

    @Test
    void getUnAuth() throws Exception {
        perform(MockMvcRequestBuilders.get(URL))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void delete() throws Exception {
        perform(MockMvcRequestBuilders.delete(URL))
                .andExpect(status().isNoContent());
        Assertions.assertFalse(userRepository.findById(USER_ID).isPresent());
        Assertions.assertTrue(userRepository.findById(ADMIN_ID).isPresent());
    }

    @Test
    void register() throws Exception {
        User newUser = newUser();
        User registered = asUser(perform(MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(newUser)))
                .andExpect(status().isCreated())
                .andDo(print()).andReturn());
        UserTestUtil.assertNoIdEquals(registered, newUser);

    }

    @Test
    @WithUserDetails(value = USER_EMAIL)
    void update() throws Exception {
        User updateUser = updateUser();
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updateUser)))
                .andDo(print())
                .andExpect(status().isNoContent());
        UserTestUtil.assertEquals(updateUser, userRepository.findById(USER_ID).orElseThrow());
    }

    @Test
    @WithUserDetails(value = USER_MAIL)
    void updateHtmlUnsafe() throws Exception {
        User updated = UserTestUtil.getUpdated();
        updated.setFirstName("<script>alert(123)</script>");
        perform(MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(updated)))
                .andDo(print())
                .andExpect(status().isUnprocessableEntity());
    }
}
