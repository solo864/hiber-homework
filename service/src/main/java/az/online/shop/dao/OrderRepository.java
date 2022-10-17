package az.online.shop.dao;

import az.online.shop.entity.Order;
import az.online.shop.model.Status;
import java.util.List;
import javax.persistence.EntityManager;

public class OrderRepository extends RepositoryBase<Integer, Order> {

    public OrderRepository(EntityManager entityManager) {
        super(Order.class, entityManager);
    }

    public List<Order> findAllByStatus(Status status) {
        return getEntityManager().createQuery("select distinct o from Order o where o.status= :status", Order.class)
                .setParameter("status", status)
                .getResultList();
    }
}