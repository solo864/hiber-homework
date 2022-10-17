package az.online.shop.mapper;

import az.online.shop.dto.OrderReadDto;
import az.online.shop.entity.Order;
import java.util.List;
import java.util.stream.Collectors;

public class OrderReadMapper implements Mapper<Order, OrderReadDto> {
    @Override
    public OrderReadDto mapFrom(Order order) {
        return new OrderReadDto(
                order.getId(),
                order.getRegistrationDate(),
                order.getClosingDate(),
                order.getStatus()
        );
    }

    public List<OrderReadDto> mapFrom(List<Order> orders) {
        return orders.stream()
                .map(this::mapFrom)
                .collect(Collectors.toList());
    }
}