package rw.jazzybruno.istock.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import rw.jazzybruno.istock.v1.dto.Order.CreateOrderDTO;
import rw.jazzybruno.istock.v1.dto.Order.OrderDTOMapper;
import rw.jazzybruno.istock.v1.dto.Order.UpdateOrderDTO;
import rw.jazzybruno.istock.v1.models.Order;
import rw.jazzybruno.istock.v1.models.Product;
import rw.jazzybruno.istock.v1.models.User;
import rw.jazzybruno.istock.v1.payload.ApiResponse;
import rw.jazzybruno.istock.v1.repositories.OrderRepository;
import rw.jazzybruno.istock.v1.repositories.ProductRepository;
import rw.jazzybruno.istock.v1.repositories.UserRepository;
import rw.jazzybruno.istock.v1.services.OrderService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderDTOMapper orderDTOMapper;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;

    @Override
    public ResponseEntity<ApiResponse> getAllOrders() throws Exception {
        try {
            List<Order> orders = orderRepository.findAll();
            return  ResponseEntity.status(500).body(new ApiResponse(
                    true,
                    "Successfully fetched the orders",
                    orders.stream().map(orderDTOMapper).collect(Collectors.toList())
            ));

        }catch (Exception e){
            return ResponseEntity.status(500).body(new ApiResponse(
                    false,
                    "Failed to fetch the orders"
            ));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> getOrderById(Long order_id) throws Exception {
        if(orderRepository.existsById(order_id)){
            try {
                Optional<Order> order = orderRepository.findById(order_id);
                return ResponseEntity.ok().body(new ApiResponse(
                        true,
                        "Successfully fetched the order",
                        order.map(orderDTOMapper)
                ));
            }catch (Exception e){
                return ResponseEntity.status(500).body(new ApiResponse(
                        false,
                        "Failed to fetch the order"
                ));
            }
        }else{
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The order with the id:" + order_id + " does not exist"
            ));
        }

    }

    @Override
    public ResponseEntity<ApiResponse> createOrder(CreateOrderDTO createOrderDTO) throws Exception {
        if(userRepository.existsById(createOrderDTO.getUser_id())){
            if(productRepository.existsById(createOrderDTO
                    .getProduct_id())){

                User user = userRepository.findById(createOrderDTO.getUser_id()).get();
                Product product = productRepository.findById(createOrderDTO.getProduct_id()).get();
                Order order = new Order(
                        createOrderDTO.getOrder_description(),
                        createOrderDTO.getQuantity(),
                        createOrderDTO.getOrderDate(),
                        product,
                        user
                );

                try {
                    orderRepository.save(order);
                    return ResponseEntity.ok().body(new ApiResponse(
                            true,
                            "Successfully placed a new order",
                            order
                    ));
                }catch (Exception e){
                    return ResponseEntity.status(500).body(new ApiResponse(
                            false,
                            "Failed to place a new order"
                    ));
                }
            }else{
                return ResponseEntity.status(404).body(new ApiResponse(
                        false,
                        "The product with the id:" + createOrderDTO.getProduct_id() + " does not exist"
                ));
            }
        }else{
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The user with the id:" + createOrderDTO.getUser_id() + " does not exist"
            ));
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse> updateOrder(Long order_id, UpdateOrderDTO updateOrderDTO) throws Exception {
        if (orderRepository.existsById(order_id)) {
            Optional<Order> order = orderRepository.findById(order_id);
            //updating
            order.get().setQuantity(updateOrderDTO.getQuantity());
            order.get().setOrder_description(updateOrderDTO.getProd_description());

            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully updated the user",
                    order.map(orderDTOMapper)
            ));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The order with the id:" + order_id + " does not exist"
            ));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> deleteOrder(Long order_id) throws Exception {
        if (orderRepository.existsById(order_id)) {
            Optional<Order> order = orderRepository.findById(order_id);
            orderRepository.deleteById(order_id);
            return ResponseEntity.ok().body(new ApiResponse(
                    true,
                    "Successfully deleted the order",
                    order.map(orderDTOMapper)
            ));
        }else {
            return ResponseEntity.status(404).body(new ApiResponse(
                    false,
                    "The order with the id:" + order_id + " does not exist"
            ));
        }
    }
}
