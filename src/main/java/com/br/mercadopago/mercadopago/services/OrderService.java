package com.br.mercadopago.mercadopago.services;

import java.util.Optional;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.mercadopago.mercadopago.entities.Order;
import com.br.mercadopago.mercadopago.repositories.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository repo;

	public Order find(Integer id) {
		Optional<Order> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Order not found! Id: " + id + ", Type: " + Order.class.getName(), null));
	}
}
