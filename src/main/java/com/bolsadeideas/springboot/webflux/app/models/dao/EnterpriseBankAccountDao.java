package com.bolsadeideas.springboot.webflux.app.models.dao;

import com.bolsadeideas.springboot.webflux.app.models.documents.EnterpriseBankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface EnterpriseBankAccountDao extends ReactiveMongoRepository<EnterpriseBankAccount, String>{

}
