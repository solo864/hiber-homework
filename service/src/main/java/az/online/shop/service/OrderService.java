package az.online.shop.service;

import az.online.shop.dao.OrderRepository;
import az.online.shop.dto.OrderReadDto;
import az.online.shop.entity.Order;
import az.online.shop.mapper.OrderReadMapper;
import az.online.shop.model.Status;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderReadMapper orderReadMapper;

    public Optional<OrderReadDto> findById(Integer id) {
        var order = orderRepository.findById(id);
        return order.map(orderReadMapper::mapFrom);
    }

    public List<OrderReadDto> getAllByStatus(Status status) {
        List<Order> orders = orderRepository.findAllByStatus(status);
        return orderReadMapper.mapFrom(orders);
    }
}
