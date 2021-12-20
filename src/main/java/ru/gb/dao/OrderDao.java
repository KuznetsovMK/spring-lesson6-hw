package ru.gb.dao;

import ru.gb.entity.Order;

public interface OrderDao {
    Order findOrdersByPersonId(Long id);
    Order save(Order order);
}
