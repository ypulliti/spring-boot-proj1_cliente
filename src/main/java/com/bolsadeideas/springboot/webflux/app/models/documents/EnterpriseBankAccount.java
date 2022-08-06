package com.bolsadeideas.springboot.webflux.app.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="enterprisebankaccount")
public class EnterpriseBankAccount
{
	@Id
	private String id;

	private String nombre;
	private String typeProduct;
	private Date createAt;

	private BankClient bankclient;

	public EnterpriseBankAccount() {}

	public EnterpriseBankAccount(String nombre, String typeProduct, BankClient bankclient) {
		this.nombre = nombre;
		this.typeProduct = typeProduct;
		this.bankclient = bankclient;
	}

	public EnterpriseBankAccount(String nombre, String typeProduct, String id, BankClient bankclient) {
		this.nombre = nombre;
		this.typeProduct = typeProduct;
		this.id = id;
		this.bankclient = bankclient;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getPrecio() {
		return typeProduct;
	}
	public void setPrecio(String precio) {
		this.typeProduct = precio;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}


	public BankClient getBankclient()
	{
		return bankclient;
	}

	public void setBankclient(BankClient bankclient)
	{
		this.bankclient = bankclient;
	}
}
