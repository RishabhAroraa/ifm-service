package com.imageflowmeta.service.user;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(
            name = "image_sequence",
            sequenceName = "image_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "image_sequence"
    )
    private @Getter @Setter Long id;

    private @Getter @Setter String name;
    private @Getter @Setter String email;
    private @Getter @Setter LocalDate dob;

    @Transient
    private Long age;
    public Integer getAge() {
        return Period.between(this.dob, LocalDate.now()).getYears();
    }
}
