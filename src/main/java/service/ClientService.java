package service;

import java.util.Collection;

import model.Client;

public interface ClientService {
	
    Collection<Client> findAll();

    Client findOne(Long id);

    Client create(Client client);

    Client update(Client client);

    void delete(Long id);

}
