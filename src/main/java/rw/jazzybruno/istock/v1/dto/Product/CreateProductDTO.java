package rw.jazzybruno.istock.v1.dto.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateProductDTO {
    private String prodName;
    private String prodDescription;
    private int quantity;
    private int unitPrice;
}
