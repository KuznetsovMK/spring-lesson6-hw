package ru.gb.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.gb.entity.Order;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
@Transactional
public class OrderDaoImpl implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Order findOrdersByPersonId(Long id) {
        TypedQuery<Order> query = entityManager.createNamedQuery("Orders.findOrdersByPersonId", Order.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public Order save(Order order) {
        if (order.getId() == null) {
            entityManager.persist(order);
        } else {
            entityManager.merge(order);
        }
        return order;
    }
}
