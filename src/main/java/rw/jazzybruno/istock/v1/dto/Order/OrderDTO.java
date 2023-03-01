package rw.jazzybruno.istock.v1.dto.Order;

import rw.jazzybruno.istock.v1.models.Product;
import rw.jazzybruno.istock.v1.models.User;

import java.time.LocalDate;

public record OrderDTO(
        Long order_id,
        String order_description,
        int quantity,
        LocalDate orderDate,
        Product product,
        User user
) {
}
