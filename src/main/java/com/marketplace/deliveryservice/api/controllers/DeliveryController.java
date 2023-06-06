package com.marketplace.deliveryservice.api.controllers;

import com.marketplace.deliveryservice.api.dto.GetDeliveryRequest;
import com.marketplace.deliveryservice.api.resource.DeliveryResource;
import com.marketplace.deliveryservice.service.DeliveryService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер, использующийся для выбора заказа курьером.
 */
@RestController
@Slf4j
@AllArgsConstructor
public class DeliveryController implements DeliveryResource {

    private final DeliveryService deliveryService;

    @Override
    public ResponseEntity<String> appointCourierToOrder(GetDeliveryRequest getDeliveryRequest) {
        try {
            deliveryService.appointCourierToOrder(getDeliveryRequest);
            log.info("На заказ с id {} назначен курьер", getDeliveryRequest.getOrderId());
            return ResponseEntity.ok("На заказ назначен курьер");
        } catch (Exception e) {
            log.warn("Не удалось взять заказ в работу. Message: {}.  StackTrace: {}."
                    , e.getMessage(), e.getStackTrace());
            return new ResponseEntity<>("Не удалось взять заказ в работу", HttpStatus.BAD_REQUEST);
        }
    }
}
