package az.online.shop.mapper;

import az.online.shop.dto.CustomerReadDto;
import az.online.shop.entity.Customer;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CustomerReadMapper implements Mapper<Customer, CustomerReadDto> {

    private final OrderReadMapper orderReadMapper;

    @Override
    public CustomerReadDto mapFrom(Customer customer) {
        return new CustomerReadDto(
                customer.getId(),
                customer.getFirstName(),
                customer.getSurname(),
                customer.getBirthDate(),
                customer.getRole(),
                orderReadMapper.mapFrom(customer.getOrders())
        );
    }

    public List<CustomerReadDto> mapFrom(List<Customer> customers) {
        return customers.stream().map(this::mapFrom)
                .collect(Collectors.toList());
    }
}