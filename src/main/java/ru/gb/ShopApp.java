package ru.gb;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.gb.config.JpaConfig;
import ru.gb.dao.BuyerDao;
import ru.gb.dao.OrderDao;
import ru.gb.entity.Buyer;
import ru.gb.entity.Order;

public class ShopApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(JpaConfig.class);
        BuyerDao buyerDao = context.getBean(BuyerDao.class);
        OrderDao orderDao = context.getBean(OrderDao.class);

        Buyer buyer = buyerDao.findById(9L);

        Order order = new Order();

        order.addOrder("milk, bread", 13d, buyer);
        orderDao.save(order);

        System.out.println(buyerDao.findById(9L));


    }

}
