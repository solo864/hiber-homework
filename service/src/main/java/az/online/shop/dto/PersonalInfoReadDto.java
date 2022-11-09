package az.online.shop.dto;

import az.online.shop.model.Gender;
import lombok.Builder;
import lombok.Value;


@Builder
public record PersonalInfoReadDto(
        String phoneNumber,
        String address,
        Gender gender
) {
}