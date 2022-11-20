package ru.javaops.bootjava.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor()
@AllArgsConstructor
@ToString(callSuper = true, exclude = "password")
@Entity
@Table(name = "users")
public class User extends BaseEntity {

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
    private String password;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role"}, name = "user_roles_unique")})
    private Set<Role> roles;
}