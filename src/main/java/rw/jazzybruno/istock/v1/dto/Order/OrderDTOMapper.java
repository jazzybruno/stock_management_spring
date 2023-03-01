package rw.jazzybruno.istock.v1.dto.Order;

import rw.jazzybruno.istock.v1.models.Order;

import java.util.function.Function;

public class OrderDTOMapper implements Function<Order, OrderDTO> {
    @Override
    public OrderDTO apply(Order order) {
        return new OrderDTO(
                order.getOrder_id(),
                order.getOrder_description(),
                order.getQuantity(),
                order.getOrderDate(),
                order.getProduct(),
                order.getUser()
        );
    }
}
