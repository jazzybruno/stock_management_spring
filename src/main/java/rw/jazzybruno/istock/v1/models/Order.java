package rw.jazzybruno.istock.v1.models;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long order_id;
    @NotNull
    private String order_description;
    @NotNull
    private int quantity;
    @NotNull
    private LocalDate orderDate;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "prod_id")
    private Product product;
    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Order(String order_description, int quantity, LocalDate orderDate, Product product, User user) {
        this.order_description = order_description;
        this.quantity = quantity;
        this.orderDate = orderDate;
        this.product = product;
        this.user = user;
    }
}
