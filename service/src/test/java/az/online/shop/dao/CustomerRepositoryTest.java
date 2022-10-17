package az.online.shop.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.TestInstance.Lifecycle.PER_CLASS;

import az.online.shop.entity.Customer;
import az.online.shop.model.Status;
import az.online.shop.util.HibernateUtil;
import az.online.shop.util.TestDataImporter;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

@TestInstance(PER_CLASS)
class CustomerRepositoryTest {

    private final SessionFactory sessionFactory = HibernateUtil.buildSessionFactory();
    private final CustomerRepository customerRepository = new CustomerRepository(sessionFactory.openSession());

    @BeforeAll
    public void initDb() {
        TestDataImporter.importData(sessionFactory);
    }

    @AfterAll
    public void finish() {
        sessionFactory.close();
    }

    @Test
    void findAllCustomersByStatusIfStatusActive() {
        var status = Status.ACTIVE;

        List<Customer> actualResult = customerRepository.findAllCustomersByStatus(status);
        assertThat(actualResult).hasSize(3);

        List<String> names = actualResult.stream().map(Customer::getFirstName).toList();
        assertThat(names).containsExactlyInAnyOrder("Cleveland", "Isobelle", "Findlay");
    }

    @Test
    void findAllCustomerByStatusIfStatusInactive() {
        var status = Status.INACTIVE;

        List<Customer> actualResult = customerRepository.findAllCustomersByStatus(Status.INACTIVE);
        assertThat(actualResult).hasSize(2);

        List<String> names = actualResult.stream().map(Customer::getFirstName).toList();
        assertThat(names).containsExactlyInAnyOrder("Findlay", "Cleveland");
    }
}

