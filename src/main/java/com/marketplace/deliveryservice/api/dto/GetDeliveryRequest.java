package com.marketplace.deliveryservice.api.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

/**
 * Объект запроса, использующийся для выбора заказа курьером.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Объект запроса, использующийся для выбора заказа курьером.")

public class GetDeliveryRequest {
    @Schema(description = "Уникальный идентификатор курьера", example = "550e8400-e29b-41d4-a716-446655440000")
    private UUID courierId;

    @Schema(description = "Уникальный идентификатор заказа", example = "123e4567-e89b-12d3-a456-426614174000")
    private UUID orderId;

}
