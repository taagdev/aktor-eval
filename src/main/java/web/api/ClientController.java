package web.api;

import java.util.Collection;

import model.Client;
import service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(
            value = "/api/clients",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<Client>> getClients() {

        Collection<Client> clients = clientService.findAll();

        return new ResponseEntity<Collection<Client>>(clients,
                HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/clients/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> getClient(@PathVariable("id") Long id) {

        Client client = clientService.findOne(id);
        if (client == null) {
            return new ResponseEntity<Client>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Client>(client, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/clients",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> createClient(
            @RequestBody Client client) {

        Client savedClient = clientService.create(client);

        return new ResponseEntity<Client>(savedClient, HttpStatus.CREATED);
    }

    @RequestMapping(
            value = "/api/clients/{id}",
            method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> updateClient(
            @RequestBody Client client) {

        Client updatedClient = clientService.update(client);
        if (updatedClient == null) {
            return new ResponseEntity<Client>(
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<Client>(updatedClient, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/api/clients/{id}",
            method = RequestMethod.DELETE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Client> deleteClient(@PathVariable("id") Long id,
            @RequestBody Client client) {

        clientService.delete(id);

        return new ResponseEntity<Client>(HttpStatus.NO_CONTENT);
    }

}
