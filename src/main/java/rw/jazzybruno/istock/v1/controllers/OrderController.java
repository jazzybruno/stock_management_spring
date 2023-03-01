package rw.jazzybruno.istock.v1.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.jazzybruno.istock.v1.dto.Order.CreateOrderDTO;
import rw.jazzybruno.istock.v1.dto.Product.CreateProductDTO;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import rw.jazzybruno.istock.v1.serviceImpls.OrderServiceImpl;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderServiceImpl orderService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllOrders() throws Exception{
        return orderService.getAllOrders();
    }

    @GetMapping("/id/{order_id}")
    public ResponseEntity<ApiResponse> getOrderById(@PathVariable Long order_id) throws Exception{
        return orderService.getOrderById(order_id);
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createOrder(@RequestBody CreateOrderDTO createOrderDTO) throws Exception{
        return orderService.createOrder(createOrderDTO);
    }

    @PutMapping("/update/{order_id}")
    public ResponseEntity<ApiResponse> updateOrder (@PathVariable Long order_id  ,  @RequestBody CreateOrderDTO createOrderDTO) throws Exception{
        return orderService.updateOrder(order_id , createOrderDTO);
    }

    @DeleteMapping("/delete/{order_id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long order_id) throws Exception{
        return orderService.deleteOrder(order_id);
    }
}
