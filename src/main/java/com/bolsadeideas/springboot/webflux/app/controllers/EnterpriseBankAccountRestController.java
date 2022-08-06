package com.bolsadeideas.springboot.webflux.app.controllers;

import com.bolsadeideas.springboot.webflux.app.models.dao.BankClientDao;
import com.bolsadeideas.springboot.webflux.app.models.dao.EnterpriseBankAccountDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.BankClient;
import com.bolsadeideas.springboot.webflux.app.models.documents.EnterpriseBankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/apis")
public class EnterpriseBankAccountRestController
{
	@Autowired
	private EnterpriseBankAccountDao dao;

	private static final Logger log = LoggerFactory.getLogger(EnterpriseBankAccountRestController.class);

	@GetMapping("showEnterpriseBankAccounts")
	public Flux<EnterpriseBankAccount> showEnterpriseBankAccounts(){

		Flux<EnterpriseBankAccount> productos = dao.findAll()
				.map(producto -> {
					producto.setNombre(producto.getNombre().toUpperCase());
					return producto;
				})
				.doOnNext(prod -> log.info(prod.getNombre()));

		return productos;
	}

	@GetMapping("showEnterpriseBankAccount/{id}")
	public Mono<EnterpriseBankAccount> showEnterpriseBankAccount(@PathVariable String id){
		
		/* Mono<Producto> producto = dao.findById(id); */
		
		Flux<EnterpriseBankAccount> productos = dao.findAll();
		
		Mono<EnterpriseBankAccount> producto = productos
				.filter(p -> p.getId().equals(id))
				.next()
				.doOnNext(prod -> log.info(prod.getNombre()));
				
		return producto;
	}



}
