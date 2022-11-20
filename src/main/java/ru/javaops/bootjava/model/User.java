package ru.javaops.bootjava.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.*;
import org.springframework.util.StringUtils;
import ru.javaops.bootjava.util.JsonDeserializers;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor()
@AllArgsConstructor
@ToString(callSuper = true, exclude = "password")
@Entity
@Table(name = "users")
public class User extends BaseEntity implements Serializable {

    @Column(name = "email", nullable = false, unique = true)
    @NotEmpty
    @Email
    @Size(max = 128)
    private String email;

    @Column(name = "first_name")
    @NotEmpty
    @Size(max = 128)
    private String firstName;

    @Column(name = "last_name")
    @NotEmpty
    @Size(max = 128)
    private String lastName;

    @Column(name = "password")
    @NotEmpty
    @Size(max = 128)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @JsonDeserialize(using = JsonDeserializers.PasswordDeserializer.class)
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique")})
    private Set<Role> roles;

    public void setEmail(String email) {
        this.email = StringUtils.hasText(email) ? email.toLowerCase() : null;
    }
}