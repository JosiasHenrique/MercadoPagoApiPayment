package com.br.mercadopago.mercadopago.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.mercadopago.mercadopago.entities.PaymentLink;

public interface PaymentRepository extends JpaRepository<PaymentLink, Integer> {

}
