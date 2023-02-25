package rw.jazzybruno.istock.v1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    private Long prod_id;
    private String prodName;
    private String prodDescription;
    private int quantity;
    private int unitPrice;

    public Product(String prodName, String prodDescription, int quantity, int unitPrice) {
        this.prodName = prodName;
        this.prodDescription = prodDescription;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
}
