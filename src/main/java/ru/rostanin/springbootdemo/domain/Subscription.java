package ru.rostanin.springbootdemo.domain;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "subscriptions")
@ToString
@EqualsAndHashCode(of = "id")
public class Subscription {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    @NotNull(message = "Id cannot be null")
    @Min(value = 0, message = "id cannot be less then 0")
    Long id;

    @NotNull(message = "This field cannot be null")
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Future(message = "Subscription's end date should be in the future")
    private LocalDate end;

    @OneToOne(mappedBy = "subscription")
    private User user;
}
