package com.br.mercadopago.mercadopago;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.mercadopago.mercadopago.entities.Order;
import com.br.mercadopago.mercadopago.entities.Product;
import com.br.mercadopago.mercadopago.repositories.OrderRepository;
import com.br.mercadopago.mercadopago.repositories.ProductRepository;
import com.mercadopago.MercadoPago;

@SpringBootApplication
public class MercadopagoApplication implements CommandLineRunner {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderRepository orderRepository;

	public static void main(String[] args) {
		SpringApplication.run(MercadopagoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		MercadoPago.SDK.setAccessToken("");

		Product p1 = new Product(null, "Pizza Napolitana", 15.0);
		Product p2 = new Product(null, "Pizza 4 queijos", 12.0);
		Product p3 = new Product(null, "Coca-cola 2L", 8.0);

		productRepository.saveAll(Arrays.asList(p1, p2, p3));

		Order od = new Order(null);

		od.getProducts().addAll(Arrays.asList(p1, p2, p3));

		od.setInstant(LocalDateTime.of(2021, Month.OCTOBER, 03, 16, 40));

		orderRepository.saveAll(Arrays.asList(od));
	}
}