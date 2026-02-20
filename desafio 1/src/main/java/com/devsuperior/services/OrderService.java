package com.devsuperior.services;

import com.devsuperior.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private ShippingService shippingService;

    public OrderService(ShippingService shippingService) {
        this.shippingService = shippingService;
    }

    public Double total(Order order) {
        if (order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        return order.getBasic() - calculateDiscount(order) + shippingService.shipment(order);
    }

    private Double calculateDiscount(Order order) {
        return order.getBasic() * (order.getDiscount() / 100);
    }
}
