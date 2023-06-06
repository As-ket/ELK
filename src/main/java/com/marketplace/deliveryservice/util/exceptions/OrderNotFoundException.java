package com.marketplace.deliveryservice.util.exceptions;

/**
 * Исключение при отсутствии заказа с ID, передаваемым
 * при выборе заказа курьером
 */
public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String msg) {
        super(msg);
    }
}

