package rw.jazzybruno.istock.v1.dto;

import com.sun.istack.NotNull;
import com.sun.tools.javac.jvm.Gen;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long prod_id;
    @NotNull
    @Column(name = "prodName")
    private String prodName;
    @NotNull
    private String prodDescription;
    @NotNull
    private int quantity;
    @NotNull
    private int unitPrice;

    public Product(String prodName, String prodDescription, int quantity, int unitPrice) {
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
