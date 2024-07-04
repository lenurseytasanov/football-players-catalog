package edu.spring.footballplayerscatalog.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Player {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    @Enumerated(EnumType.STRING)
    private Sex sex;

    private LocalDate birthdate;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private Team team;

    @Enumerated(EnumType.STRING)
    private Country country;
}
