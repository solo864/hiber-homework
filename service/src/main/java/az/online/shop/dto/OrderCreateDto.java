package az.online.shop.dto;

import java.time.LocalDate;

public record OrderCreateDto(
        LocalDate registrationDate,
        LocalDate closingDate
) {
}