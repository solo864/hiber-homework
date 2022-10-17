package az.online.shop.dto;

import az.online.shop.model.Role;
import java.time.LocalDate;
import java.util.List;

public record CustomerReadDto(
        Integer id,
        String firstName,
        String surname,
        LocalDate birthDate,
        Role role,
        List<OrderReadDto> orderReadDtos
) {
}