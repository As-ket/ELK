package com.marketplace.deliveryservice.api.resource;

import com.marketplace.deliveryservice.api.constant.UrlConstants;
import com.marketplace.deliveryservice.api.dto.GetDeliveryRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Ресурс для контролллера, реализующий выбор заказа курьером.
 */

@RequestMapping(UrlConstants.MAIN_URL)
@Tag(name = "Выбор-заказа", description = "Назначение курьера, после взятия заказа в работу")
public interface DeliveryResource {
    @PutMapping (value = "/get-delivery")
    @Operation(summary = "Выбор заказа", tags = "Выбор заказа курьером")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Заказ взят",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "401", description = "Для выбора заказа необходимо зарегистрироваться",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "403", description = "Доступ запрещен",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "404", description = "Страница не найдена",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))}),
            @ApiResponse(responseCode = "400", description = "Не удалось взять заказ",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))})
    })
    ResponseEntity<String> appointCourierToOrder(@RequestBody GetDeliveryRequest getDeliveryRequest);

}


