package ru.rostanin.springbootdemo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode(of = "id")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id cannot be null")
    @Min(value = 0, message = "id cannot be less then 0")
    private Long id;

    @NonNull
    @NotBlank(message = "This field cannot be blank")
    private String username;

    @NonNull
    @NotBlank(message = "This field cannot be blank")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

    @Pattern(regexp = "^(.+)@(.+)$", message = "Email should matches *@.* pattern")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;

    @NonNull
    private String roles;

    @NonNull
    private String authorities;

    public List<String> getRoles() {
        if (roles.length() > 0) {
            return Arrays.asList(roles.split(","));
        }
        return new ArrayList<>();
    }

    public List<String> getAuthorities() {
        if (authorities.length() > 0) {
            return Arrays.asList(authorities.split(","));
        }
        return new ArrayList<>();
    }

}
