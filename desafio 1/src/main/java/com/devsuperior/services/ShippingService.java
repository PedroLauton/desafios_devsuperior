package com.devsuperior.services;

import com.devsuperior.entities.Order;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    public Double shipment(Order order) {
        if(order == null) {
            throw new IllegalArgumentException("Order cannot be null");
        }

        if (order.getBasic() < 100.0) {
            return 20.0;
        } else if (order.getBasic() >= 100.00 && order.getBasic() < 200.00) {
            return 12.0;
        } else {
            return 0.0;
        }
    }
}
