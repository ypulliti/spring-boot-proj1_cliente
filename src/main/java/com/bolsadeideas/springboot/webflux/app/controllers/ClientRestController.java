package com.bolsadeideas.springboot.webflux.app.controllers;

import com.bolsadeideas.springboot.webflux.app.models.dao.BankClientDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.BankClient;
import com.bolsadeideas.springboot.webflux.app.models.documents.PersonalBankAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/apis")
public class ClientRestController
{
	@Autowired
	private BankClientDao daoC;
	private ClientController cControl = new ClientController();
	private static final Logger log = LoggerFactory.getLogger(ClientRestController.class);
	
	@GetMapping("showClients")
	public Flux<BankClient> showClients(){

		Flux<BankClient> productos = daoC.findAll()
				.map(producto -> {
					producto.setName(producto.getName().toUpperCase());
					return producto;
				})
				.doOnNext(prod -> log.info(prod.getName()));

		return productos;
	}

	@GetMapping("showClient/{id}")
	public Mono<BankClient> showClient(@PathVariable String id){

		/* Mono<Producto> producto = dao.findById(id); */
		Flux<BankClient> productos = daoC.findAll();

		Mono<BankClient> producto = productos
				.filter(p -> p.getId().equals(id))
				.next()
				.doOnNext(prod -> log.info(prod.getName()));

		return producto;
	}

	@PutMapping("insertClient/{id}/{nombre}/{tipoCliente}/{nombreCuentaBancaria}/{tipoCuentaBancaria}")
	public String insertClient(@PathVariable String nombre, @PathVariable String tipoCliente, @PathVariable String nombreCuentaBancaria, @PathVariable String tipoCuentaBancaria)
	{
		PersonalBankAccount pBcount = new PersonalBankAccount(nombreCuentaBancaria, tipoCuentaBancaria);
		BankClient bClient = new BankClient(nombre, tipoCliente, pBcount);

		cControl.updatePerson(bClient);

		/*Flux.just(bClient)
				.flatMap(c -> {
					c.setCreateAt(new Date());
					return daoC.save(c);
				})
				.subscribe(c -> log.info("Insert: " + c.getId() + " " + c.getName()));
		*/
		return "Sucess";
	}

	@PutMapping("updateClient/{id}/{nombre}/{tipoCliente}/{nombreCuentaBancaria}/{tipoCuentaBancaria}")
	public String updateClient(@PathVariable String id, @PathVariable String nombre, @PathVariable String tipoCliente, @PathVariable String nombreCuentaBancaria, @PathVariable String tipoCuentaBancaria)
	{
		PersonalBankAccount pBcount = new PersonalBankAccount(nombreCuentaBancaria, tipoCuentaBancaria);
		BankClient bClient = new BankClient(nombre, tipoCliente, id, pBcount);
		cControl.updatePerson(bClient);
		/*Flux.just(bClient)
				.flatMap(c -> {
					c.setCreateAt(new Date());
					return daoC.save(c);
				})
				.subscribe(c -> log.info("Insert: " + c.getId() + " " + c.getName()));*/

		return "Sucess";
	}

	@PutMapping("deleteClient/{id}")
	public String deleteClient(@PathVariable String id)
	{
		cControl.deletePerson(id);
		return "Sucess";
	}
}
