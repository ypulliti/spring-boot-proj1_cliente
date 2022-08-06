package com.bolsadeideas.springboot.webflux.app.models.dao;

import com.bolsadeideas.springboot.webflux.app.models.documents.BankClient;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface BankClientDao extends ReactiveMongoRepository<BankClient, String>{

}
