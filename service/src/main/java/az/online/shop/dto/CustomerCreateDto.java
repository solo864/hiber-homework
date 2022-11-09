package az.online.shop.dto;

import az.online.shop.entity.PersonalInfo;
import az.online.shop.model.Role;
import java.time.LocalDate;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public record CustomerCreateDto(
        @Valid
        PersonalInfo personalInfo,
        @NotNull
        String firstName,
        @NotNull
        String surname,
        @Email
        String email,
        @NotNull
        String password,
        @NotNull
        Role role,
        @NotNull
        LocalDate birthDate
) {
}