package ru.rostanin.springbootdemo.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tales")
@ToString
@EqualsAndHashCode(of = "id")
public class Tale {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id cannot be null")
    @Min(value = 0, message = "id cannot be less then 0")
    Long id;

    @NotBlank(message = "This field cannot be blank")
    private String title;

    @NotBlank(message = "This field cannot be blank")
    private String description;

    @NotBlank(message = "This field cannot be blank")
    private String sourcePath;

    @NotBlank(message = "This field cannot be blank")
    private String narrator;
}

