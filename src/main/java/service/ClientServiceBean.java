package service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import model.Client;
import repository.ClientRepository;

@Service
public class ClientServiceBean implements ClientService {
	
	@Autowired
	private ClientRepository clientRepository;
	
	
	@Override
	public Collection<Client> findAll() {
		Collection<Client> clients = clientRepository.findAll();
		return clients;
	}


	@Override
	public Client findOne(Long id) {
		Client client =clientRepository.findOne(id);
		return client;
	}

	@Override
	public Client create(Client client) {
		if (client.getId() !=null){
			return null;
		}
		Client savedClient = clientRepository.save(client);
		return savedClient;
	}	

	@Override
	public Client update(Client client) {
		Client clientPersisted = findOne(client.getId());
		if(clientPersisted == null){
			return null;			
		}
		
		Client updatedClient = clientRepository.save(client);
		return updatedClient;
	}
	
	@Override
	public void delete(Long id) {
		
		clientRepository.delete(id);
	}


}
