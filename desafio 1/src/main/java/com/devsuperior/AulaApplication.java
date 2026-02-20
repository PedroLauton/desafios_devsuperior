package com.devsuperior;

import com.devsuperior.entities.Order;
import com.devsuperior.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Scanner;

@SpringBootApplication
public class AulaApplication implements CommandLineRunner {

    private final OrderService orderService;

    public AulaApplication(OrderService orderService) {
        this.orderService = orderService;
    }

	public static void main(String[] args) {
		SpringApplication.run(AulaApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
            Scanner sc = new Scanner(System.in);

        	System.out.println("\nDesafio 1 \n");

            System.out.print("Code: ");
            Integer code = sc.nextInt();

            System.out.print("Basic: ");
            Double basic = sc.nextDouble();

            System.out.print("Discount: ");
            Double discount = sc.nextDouble();

            Order order = new Order(code, basic, discount);
            System.out.println("\nPedido codigo: " + order.getCode());
            System.out.println("Valor total: R$ " + orderService.total(order));

            sc.close();
    }
}
