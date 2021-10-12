package com.br.mercadopago.mercadopago.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.mercadopago.mercadopago.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
