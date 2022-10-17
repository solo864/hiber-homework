package az.online.shop.dao;

import static az.online.shop.entity.Order_.*;

import az.online.shop.entity.Customer;
import az.online.shop.entity.Order_;
import az.online.shop.model.Status;
import java.util.List;
import javax.persistence.EntityManager;

public class CustomerRepository extends RepositoryBase<Integer, Customer> {

    public CustomerRepository(EntityManager entityManager) {
        super(Customer.class, entityManager);
    }

    public List<Customer> findAllCustomersByStatus(Status status) {
        return getEntityManager().createQuery("select distinct c from Customer c join c.orders o on o.status= :status", Customer.class)
                .setParameter(STATUS, status)
                .getResultList();
    }
}