package rw.jazzybruno.istock.v1.serviceImpls;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import rw.jazzybruno.istock.v1.repositories.OrderRepository;
import rw.jazzybruno.istock.v1.services.OrderService;

@Component
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
}
