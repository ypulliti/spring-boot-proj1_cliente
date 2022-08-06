package com.bolsadeideas.springboot.webflux.app.models.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection="personalbankaccount")
public class PersonalBankAccount
{
	@Id
	private String id;
	private String typeAccount;

	private String name;
	private Date createAt;

	public PersonalBankAccount() {}

	public PersonalBankAccount(String name, String tipoCuenta, String id) {
		this.name = name;
		this.typeAccount = tipoCuenta;
		this.id = id;
	}

	public PersonalBankAccount(String name, String tipoCuenta) {
		this.name = name;
		this.typeAccount = tipoCuenta;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTypeAccount() {
		return typeAccount;
	}
	public void setTypeAccount(String typeAccount) {
		this.typeAccount = typeAccount;
	}
	public Date getCreateAt() {
		return createAt;
	}
	public void setCreateAt(Date createAt) {
		this.createAt = createAt;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}
}
