package az.online.shop.mapper;

import az.online.shop.dto.CustomerCreateDto;
import az.online.shop.entity.Customer;

public class CustomerCreateMapper implements Mapper<CustomerCreateDto, Customer> {

    @Override
    public Customer mapFrom(CustomerCreateDto object) {
        return Customer.builder()
                .personalInfo(object.personalInfo())
                .surname(object.surname())
                .firstName(object.firstName())
                .role(object.role())
                .birthDate(object.birthDate())
                .password(object.password())
                .email(object.email())
                .build();
    }
}