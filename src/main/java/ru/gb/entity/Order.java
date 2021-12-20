package ru.gb.entity;


import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Orders")
@NamedQueries({
        @NamedQuery(name = "Orders.findOrdersByPersonId",
                query = "select m from Order m where m.buyer = :id")
})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "product_list")
    private String product_list;

    @Column(name = "cost")
    private double cost;

    @ManyToOne
    @JoinColumn(name = "BUYER_ID")
    private Buyer buyer;

    public void addOrder(String product_list, double cost, Buyer buyer) {
        this.product_list = product_list;
        this.cost = cost;
        this.buyer = buyer;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", product_list='" + product_list + '\'' +
                ", cost=" + cost +
                ", buyer=" + buyer.getName() +
                '}';
    }
}

