package com.br.mercadopago.mercadopago.entities;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PaymentLink {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String link;
	private LocalDateTime localdate;
	
	public PaymentLink() {
		
	}

	public PaymentLink(Integer id, String link, LocalDateTime localdate) {
		this.id = id;
		this.link = link;
		this.localdate = localdate;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public LocalDateTime getLocaldate() {
		return localdate;
	}

	public void setLocaldate(LocalDateTime localdate) {
		this.localdate = localdate;
	}
}