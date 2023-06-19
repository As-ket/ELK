package com.marketplace.deliveryservice.service;

import com.marketplace.deliveryservice.api.dto.GetDeliveryRequest;
import com.marketplace.deliveryservice.entity.Order;
import com.marketplace.deliveryservice.repository.CourierRepository;
import com.marketplace.deliveryservice.repository.OrderRepository;
import com.marketplace.deliveryservice.util.exceptions.OrderNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Сервис, реализующий действия курьера с заказом.
 */

@Slf4j
@Service
@AllArgsConstructor
public class DeliveryService {

    private final OrderRepository orderRepository;

    private final CourierRepository courierRepository;

    private RestTemplate restTemplate;

    @Transactional
    public void appointCourierToOrder(GetDeliveryRequest getDeliveryRequest) {
        UUID orderId = getDeliveryRequest.getOrderId();
        UUID courierId = getDeliveryRequest.getCourierId();
        if (orderRepository.findById(orderId).isEmpty()) {
            throw new OrderNotFoundException(String.format("Заказ с id %s не найден.", orderId));
        } else {
            Order order = orderRepository.findById(orderId).get();
            order.setCourier(courierRepository.findById(courierId).get());
            order.setState("IN_DELIVERY");
            orderRepository.save(order);
            courierRepository.incrementDeliveryCount(courierId);
            log.info("На заказ с id {} назначен курьер.", orderId);
//            String url = "http://localhost:9093/order/change-state/" + orderId;
//            MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
//            params.add("state", "IN_DELIVERY");
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
//            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
//            ResponseEntity<HttpStatus> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, HttpStatus.class);
//            if (responseEntity.getStatusCode() != HttpStatus.OK) {
//                throw new RuntimeException("Ошибка при изменении статуса заказа.");
//            }
        }
    }
}
