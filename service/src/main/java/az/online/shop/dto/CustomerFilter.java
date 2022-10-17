package az.online.shop.dto;

import lombok.Value;

@Value(staticConstructor = "of")
public class CustomerFilter {
    String firstName;
    String surname;
}