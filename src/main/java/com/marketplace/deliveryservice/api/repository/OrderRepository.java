package com.marketplace.deliveryservice.api.repository;

import com.marketplace.deliveryservice.api.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
