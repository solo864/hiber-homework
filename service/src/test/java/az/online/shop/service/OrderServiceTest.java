package az.online.shop.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import az.online.shop.dao.OrderRepository;
import az.online.shop.dto.OrderReadDto;
import az.online.shop.mapper.OrderReadMapper;
import az.online.shop.model.Status;
import az.online.shop.util.HibernateUtil;
import az.online.shop.util.TestDataImporter;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(PER_CLASS)
public class OrderServiceTest {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private OrderRepository orderRepository;
    private OrderReadMapper orderReadMapper;
    private OrderService orderService;


    @BeforeAll
    void init() {
        orderReadMapper = new OrderReadMapper();
        orderRepository = new OrderRepository(sessionFactory.openSession());
        orderService = new OrderService(orderRepository, orderReadMapper);
        TestDataImporter.importData(sessionFactory);
    }


    @Test
    void getByIdIfOrderExist() {
        var actualResult = orderService.findById(1);
        assertThat(actualResult).isPresent();
    }

    @Test
    void getByIdIfOrderNotExist() {
        var actualResult = orderService.findById(Integer.MAX_VALUE);
        assertThat(actualResult).isEmpty();
    }

    @Test
    void getAllByActiveStatus() {
        var status = Status.ACTIVE;

        List<OrderReadDto> actualResult = orderService.getAllByStatus(status);
        assertThat(actualResult).hasSize(7);

        List<LocalDate> localDates = actualResult.stream().map(OrderReadDto::registrationDate).toList();
        assertThat(localDates).containsExactlyInAnyOrder(
                LocalDate.of(2021, 5, 6),
                LocalDate.of(2022, 6, 7),
                LocalDate.of(2022, 6, 7),
                LocalDate.of(2022, 6, 7),
                LocalDate.of(2022, 6, 7),
                LocalDate.of(2022, 6, 7),
                LocalDate.of(2021, 5, 6)
        );
    }
}