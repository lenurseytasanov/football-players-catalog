package edu.spring.footballplayerscatalog.dto;

import edu.spring.footballplayerscatalog.domain.Country;
import edu.spring.footballplayerscatalog.domain.Sex;

import java.time.LocalDate;

public record PlayerResponseDto(
        Long id,
        String firstname,
        String lastname,
        Sex sex,
        LocalDate birthdate,
        String team,
        Country country) {
}