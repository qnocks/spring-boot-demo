package ru.rostanin.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id cannot be null")
    @Min(value = 0, message = "id cannot be less then 0")
    private Long id;

    @NotBlank(message = "This field cannot be blank")
    private String login;

    @NotBlank(message = "This field cannot be blank")
    @Size(min = 6, message = "Password should be at least 6 characters")
    private String password;

    @NotBlank(message = "This field cannot be blank")
    @Pattern(regexp = "^(.+)@(.+)$", message = "Email should matches *@.* pattern")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    private Subscription subscription;
}
