package com.marketplace.deliveryservice.repository;

import com.marketplace.deliveryservice.entity.Courier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.UUID;

@Repository
public interface CourierRepository extends JpaRepository<Courier, UUID> {

    @Modifying
    @Query("UPDATE Courier c SET c.deliveryCount = c.deliveryCount + 1 WHERE c.id = ?1")
    void incrementDeliveryCount(UUID courierId);

}

