package az.online.shop.dto;

import az.online.shop.model.Status;
import java.time.LocalDate;

public record OrderReadDto(Integer id,
                           LocalDate registrationDate,
                           LocalDate closingDate,
                           Status status) {
}