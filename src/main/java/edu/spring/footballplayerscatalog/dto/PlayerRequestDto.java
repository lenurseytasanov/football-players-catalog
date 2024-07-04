package edu.spring.footballplayerscatalog.dto;

import edu.spring.footballplayerscatalog.domain.Country;
import edu.spring.footballplayerscatalog.domain.Sex;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record PlayerRequestDto(
        @NotBlank @Size(max = 50) String firstname,
        @NotBlank @Size(max = 50) String lastname,
        @NotNull Sex sex,
        @NotNull LocalDate birthdate,
        @NotBlank @Size(max = 50) String team,
        @NotNull Country country) {
}
