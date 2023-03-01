package rw.jazzybruno.istock.v1.services;

import org.springframework.http.ResponseEntity;
import rw.jazzybruno.istock.v1.dto.Order.CreateOrderDTO;
import rw.jazzybruno.istock.v1.dto.Order.UpdateOrderDTO;
import rw.jazzybruno.istock.v1.dto.Product.CreateProductDTO;
import rw.jazzybruno.istock.v1.payload.ApiResponse;

public interface OrderService {
    public ResponseEntity<ApiResponse> getAllOrders() throws Exception;
    public ResponseEntity<ApiResponse> getOrderById(Long order_id) throws Exception;
    public ResponseEntity<ApiResponse> createOrder( CreateOrderDTO createOrderDTO) throws Exception;
    public ResponseEntity<ApiResponse> updateOrder(Long order_id ,  UpdateOrderDTO updateOrderDTO) throws Exception;
    ResponseEntity<ApiResponse> deleteOrder(Long order_id) throws Exception;
}
