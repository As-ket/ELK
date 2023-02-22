package com.marketplace.deliveryservice.api.services;


import com.marketplace.deliveryservice.api.entity.Order;
import com.marketplace.deliveryservice.api.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void addAllOrderItems(List<Order> orderItems) {
        orderRepository.saveAll(orderItems);
    }
}
