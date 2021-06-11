package ru.rostanin.springbootdemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "meditations")
public class Meditation {

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

    @Min(value = 0, message = "Rating cannot be less then 0")
    private double rating;
}
