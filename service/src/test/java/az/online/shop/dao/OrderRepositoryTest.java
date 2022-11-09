package az.online.shop.dao;


import static org.assertj.core.api.Assertions.assertThat;

import az.online.shop.entity.Order;
import az.online.shop.model.Status;
import az.online.shop.util.HibernateUtil;
import az.online.shop.util.TestDataImporter;
import java.time.LocalDate;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OrderRepositoryTest {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final OrderRepository orderRepository = new OrderRepository(sessionFactory.openSession());

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAllIfStatusIsActive() {
        Status status = Status.ACTIVE;

        List<Order> actualResult = orderRepository.findAllByStatus(status);
        assertThat(actualResult).hasSize(7);

        List<LocalDate> registrationDates = actualResult.stream().map(Order::getRegistrationDate).toList();
        assertThat(registrationDates).containsExactlyInAnyOrder(LocalDate.of(2021, 5, 6), LocalDate.of(2022, 6, 7), LocalDate.of(2022, 6, 7),
                LocalDate.of(2022, 6, 7), LocalDate.of(2022, 6, 7), LocalDate.of(2021, 5, 6), LocalDate.of(2022, 6, 7));

    }
}

