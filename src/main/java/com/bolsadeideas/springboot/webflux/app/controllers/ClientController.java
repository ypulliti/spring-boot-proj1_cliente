package com.bolsadeideas.springboot.webflux.app.controllers;

import com.bolsadeideas.springboot.webflux.app.models.dao.BankClientDao;
import com.bolsadeideas.springboot.webflux.app.models.documents.BankClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import reactor.core.publisher.Mono;

public class ClientController
{
    @Autowired
    private BankClientDao daoC;
    private static final Logger log = LoggerFactory.getLogger(ClientRestController.class);

    public Mono<BankClient> getPerson(final String id){
        return this.daoC.findById(id);
    }

    public Mono<BankClient> savePerson(BankClient person){
        return this.daoC.save(person);
    }

    public Mono<BankClient> updatePerson(BankClient person){
        return this.daoC.findById(person.getId())
                .map(p -> person)
                .flatMap(this.daoC::save);
    }

    public Mono<Void> deletePerson(final String id){
        return this.daoC.deleteById(id);
    }
}
