package az.online.shop.service;

import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import az.online.shop.dao.CustomerRepository;
import az.online.shop.dto.CustomerCreateDto;
import az.online.shop.dto.CustomerReadDto;
import az.online.shop.entity.PersonalInfo;
import az.online.shop.mapper.CustomerCreateMapper;
import az.online.shop.mapper.CustomerReadMapper;
import az.online.shop.mapper.OrderReadMapper;
import az.online.shop.model.Gender;
import az.online.shop.model.Role;
import az.online.shop.model.Status;
import az.online.shop.util.HibernateUtil;
import az.online.shop.util.TestDataImporter;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.assertj.core.api.Assertions;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.w3c.dom.traversal.NodeIterator;

@TestInstance(PER_CLASS)
public class CustomerServiceTest {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private CustomerService customerService;
    private CustomerRepository customerRepository;
    private CustomerReadMapper customerReadMapper;
    private CustomerCreateMapper customerCreateMapper;
    private OrderReadMapper orderReadMapper;


    @BeforeAll
    void init() {
        orderReadMapper = new OrderReadMapper();
        customerRepository = new CustomerRepository(sessionFactory.openSession());
        customerCreateMapper = new CustomerCreateMapper();
        customerReadMapper = new CustomerReadMapper(orderReadMapper);
        customerService = new CustomerService(customerRepository, customerReadMapper, customerCreateMapper);
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    void close() {
        sessionFactory.close();
    }


    @Test
    void getAllCustomersWhenOrderStatusIsActive() {
        var status = Status.ACTIVE;

        List<CustomerReadDto> actualResult = customerService.getAllCustomersByOrderStatus(status);
        Assertions.assertThat(actualResult).hasSize(3);

        List<String> names = actualResult.stream().map(CustomerReadDto::firstName).toList();
        Assertions.assertThat(names).containsExactlyInAnyOrder("Isobelle","Findlay","Cleveland");
    }

    @Test
    void getAllCustomersWhenOrderStatusIsNotActive() {
        var status = Status.INACTIVE;

        List<CustomerReadDto> actualResult = customerService.getAllCustomersByOrderStatus(status);
        Assertions.assertThat(actualResult).hasSize(2);

        List<String> names = actualResult.stream().map(CustomerReadDto::firstName).toList();
        Assertions.assertThat(names).containsExactlyInAnyOrder("Findlay","Cleveland");
    }

    @Test
    void getByIdIfCustomerExist() {
        Optional<CustomerReadDto> actualResult = customerService.findById(1);
        Assertions.assertThat(actualResult).isNotEmpty();
    }

    @Test
    void getByIdIfCustomerNotExist() {
        Optional<CustomerReadDto> actualResult = customerService.findById(Integer.MAX_VALUE);
        Assertions.assertThat(actualResult).isEmpty();
    }
}