package rw.jazzybruno.istock.v1.dto.Order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import rw.jazzybruno.istock.v1.models.Product;
import rw.jazzybruno.istock.v1.models.User;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateOrderDTO {
   private String order_description;
   private int quantity;
   private LocalDate orderDate;
   private Long product_id;
   private Long user_id;
}
