package com.br.mercadopago.mercadopago.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import com.br.mercadopago.mercadopago.entities.Order;
import com.br.mercadopago.mercadopago.entities.PaymentLink;
import com.br.mercadopago.mercadopago.repositories.PaymentRepository;
import com.br.mercadopago.mercadopago.services.OrderService;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import com.mercadopago.resources.datastructures.preference.PaymentMethods;

@RestController
@RequestMapping(value = "/order")
public class MercadoPagoController {

	@Autowired
	private OrderService service;

	@Autowired
	private PaymentRepository repo;

	@GetMapping(value = "/createAndRedirect")
	public String createAndRedirect() throws MPException {
		Preference preference = new Preference();

		PaymentMethods paymentMethods = new PaymentMethods();
		paymentMethods.setInstallments(1);

		preference.setPaymentMethods(paymentMethods);
		preference.setStatementDescriptor("Payment products");

		preference.setBackUrls(new BackUrls().setFailure("http://localhost:8080/failure")
				.setPending("http://localhost:8080/pending").setSuccess("http://localhost:8080/success"));

		Order order = service.find(1);

		Item item = new Item();
		item.setTitle("Valor dos produtos").setQuantity(1).setUnitPrice(order.getValorTotal().floatValue())
				.setDescription("Mensalidade").setPictureUrl(
						"https://scontent.fpoo2-1.fna.fbcdn.net/v/t39.30808-6/241265470_4395275910572236_3485376806660129671_n.png?_nc_cat=102&ccb=1-5&_nc_sid=730e14&_nc_ohc=HbPYsiMsTawAX9vjA2J&_nc_ht=scontent.fpoo2-1.fna&oh=0b24fda2055195f01a55f65b266e871a&oe=6167D52E");
		preference.appendItem(item);

		var result = preference.save();

		PaymentLink pl = new PaymentLink(null, result.getSandboxInitPoint(), LocalDateTime.now());
		repo.save(pl);

		return "redirect:" + result.getSandboxInitPoint();
	}

	@RequestMapping(value = "/success", method = RequestMethod.GET)
	public RedirectView success(HttpServletRequest request, @RequestParam("collection_id") String collectionId,
			@RequestParam("collection_status") String collectionStatus,
			@RequestParam("external_reference") String externalReference,
			@RequestParam("payment_type") String paymentType, @RequestParam("merchant_order_id") String merchantOrderId,
			@RequestParam("preference_id") String preferenceId, @RequestParam("site_id") String siteId,
			@RequestParam("processing_mode") String processingMode,
			@RequestParam("merchant_account_id") String merchantAccountId, RedirectAttributes attributes)
			throws MPException {
		attributes.addFlashAttribute("genericResponse", true);
		attributes.addFlashAttribute("collection_id", collectionId);
		attributes.addFlashAttribute("collection_status", collectionStatus);
		attributes.addFlashAttribute("external_reference", externalReference);
		attributes.addFlashAttribute("payment_type", paymentType);
		attributes.addFlashAttribute("merchant_order_id", merchantOrderId);
		attributes.addFlashAttribute("preference_id", preferenceId);
		attributes.addFlashAttribute("site_id", siteId);
		attributes.addFlashAttribute("processing_mode", processingMode);
		attributes.addFlashAttribute("merchant_account_id", merchantAccountId);

		return new RedirectView("/");
	}

	@GetMapping("/pending")
	public RedirectView pending(HttpServletRequest request, @RequestParam("collection_id") String collectionId,
			@RequestParam("collection_status") String collectionStatus,
			@RequestParam("external_reference") String externalReference,
			@RequestParam("payment_type") String paymentType, @RequestParam("merchant_order_id") String merchantOrderId,
			@RequestParam("preference_id") String preferenceId, @RequestParam("site_id") String siteId,
			@RequestParam("processing_mode") String processingMode,
			@RequestParam("merchant_account_id") String merchantAccountId, RedirectAttributes attributes)
			throws MPException {
		attributes.addFlashAttribute("genericResponse", true);
		attributes.addFlashAttribute("collection_id", collectionId);
		attributes.addFlashAttribute("collection_status", collectionStatus);
		attributes.addFlashAttribute("external_reference", externalReference);
		attributes.addFlashAttribute("payment_type", paymentType);
		attributes.addFlashAttribute("merchant_order_id", merchantOrderId);
		attributes.addFlashAttribute("preference_id", preferenceId);
		attributes.addFlashAttribute("site_id", siteId);
		attributes.addFlashAttribute("processing_mode", processingMode);
		attributes.addFlashAttribute("merchant_account_id", merchantAccountId);

		return new RedirectView("/");
	}

	@GetMapping("/failure")
	public RedirectView failure(HttpServletRequest request, @RequestParam("collection_id") String collectionId,
			@RequestParam("collection_status") String collectionStatus,
			@RequestParam("external_reference") String externalReference,
			@RequestParam("payment_type") String paymentType, @RequestParam("merchant_order_id") String merchantOrderId,
			@RequestParam("preference_id") String preferenceId, @RequestParam("site_id") String siteId,
			@RequestParam("processing_mode") String processingMode,
			@RequestParam("merchant_account_id") String merchantAccountId, RedirectAttributes attributes)
			throws MPException {
		attributes.addFlashAttribute("genericResponse", true);
		attributes.addFlashAttribute("collection_id", collectionId);
		attributes.addFlashAttribute("collection_status", collectionStatus);
		attributes.addFlashAttribute("external_reference", externalReference);
		attributes.addFlashAttribute("payment_type", paymentType);
		attributes.addFlashAttribute("merchant_order_id", merchantOrderId);
		attributes.addFlashAttribute("preference_id", preferenceId);
		attributes.addFlashAttribute("site_id", siteId);
		attributes.addFlashAttribute("processing_mode", processingMode);
		attributes.addFlashAttribute("merchant_account_id", merchantAccountId);

		return new RedirectView("/");
	}
}