package com.bolsadeideas.springboot.webflux.app.controllers;

import com.bolsadeideas.springboot.webflux.app.models.dao.BankClientDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.BankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@RestController
@RequestMapping("/api/clients")
public class ClientRestController
{
	@Autowired
	private BankClientDao daoC;

	@Autowired
	private ClientController cControl;
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
	public Mono<BankClient> showClient(@PathVariable String id)
	{

		return cControl.getPerson(id);
	}

	@PutMapping("insertClient/{id}/{nombre}/{tipoCliente}/{tipoCuentaBancaria}")
	public String insertClient(@PathVariable String id, @PathVariable String nombre, @PathVariable String tipoCliente, @PathVariable String tipoCuentaBancaria)
	{
		BankClient bClient = new BankClient(nombre, tipoCliente, id, tipoCuentaBancaria);
		cControl.savePerson(bClient);
		return "Sucess";
	}

	@PutMapping("updateClient/{id}/{nombre}/{tipoCliente}/{tipoCuentaBancaria}")
	public String updateClient(@PathVariable String id, @PathVariable String nombre, @PathVariable String tipoCliente, @PathVariable String tipoCuentaBancaria)
	{
		BankClient bClient = new BankClient(nombre, tipoCliente, id, tipoCuentaBancaria);
		cControl.savePerson(bClient);
		return "Sucess";
	}

	@DeleteMapping("deleteClient/{id}")
	public String deleteClient(@PathVariable String id)
	{
		cControl.deletePerson(id);
		return "Sucess";
	}

	public void setcControl(ClientController cControl)
	{
		this.cControl = cControl;
	}
}
