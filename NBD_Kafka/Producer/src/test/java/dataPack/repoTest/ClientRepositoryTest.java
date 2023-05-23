package dataPack.repoTest;


import dataPack.data;
import model.Client;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.Test;
import repository.ClientRepository;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class ClientRepositoryTest {

    private ClientRepository clientRepository = new ClientRepository();


    @Test
    void addClientTest() {
        clientRepository.drop();
        clientRepository.addClient(data.client);
        clientRepository.addClient(data.client2);
        ArrayList<Client> ls = clientRepository.findAll();
        assertEquals(2, ls.size());
    }

    @Test
    void addExistingClientTest() {
        clientRepository.drop();
        clientRepository.addClient(data.client);
        clientRepository.addClient(data.client2);
        assertFalse(clientRepository.addClient(data.client));
        ArrayList<Client> ls = clientRepository.findAll();
        assertEquals(2, ls.size());
    }

    @Test
    void dropTest() {
        clientRepository.drop();
        ArrayList<Client> ls = clientRepository.findAll();
        assertEquals(0, ls.size());
    }
    @Test
    void removeClientTest() {
        clientRepository.drop();
        UUID id = data.client.getClientId();

        clientRepository.addClient(data.client);
        clientRepository.addClient(data.client2);

        Client removed = clientRepository.removeClient(id.toString());
        System.out.println(data.client.getClientId());
        assertEquals(removed, data.client);

        ArrayList<Client> ls = clientRepository.findAll();
        assertEquals(1, ls.size());
    }

    @Test
    void findAllTest() {
        clientRepository.drop();

        clientRepository.addClient(data.client);
        clientRepository.addClient(data.client2);

        ArrayList<Client> ls = clientRepository.findAll();
        assertEquals(data.client, ls.get(0));
        assertEquals(data.client2, ls.get(1));
    }


}