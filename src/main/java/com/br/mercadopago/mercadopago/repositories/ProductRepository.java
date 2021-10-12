package com.br.mercadopago.mercadopago.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.mercadopago.mercadopago.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
