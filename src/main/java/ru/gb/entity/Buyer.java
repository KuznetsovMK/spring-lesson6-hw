package ru.gb.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "Buyer")
@NamedQueries({
        @NamedQuery(name = "Buyer.findNameById",
                query = "select m.name from Buyer m where m.id = :id"),
        @NamedQuery(name = "Buyer.findById",
                query = "select m from Buyer m where m.id = :id")
})
public class Buyer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<Order> orders;

    public boolean addOrder(Order order) {
        if (orders == null) {
            orders = new HashSet<>();
        }
        return orders.add(order);
    }

    @Override
    public String toString() {
        return "Buyer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", orders=" + orders +
                '}';
    }
}
