package com.rapiCredit.test;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.rapiCredit.test.dto.ClientDTO;
import com.rapiCredit.test.entity.Client;
import com.rapiCredit.test.exceptions.ControlledException;
import com.rapiCredit.test.exceptions.InvalidClientException;
import com.rapiCredit.test.exceptions.ClientNotFoundException;
import com.rapiCredit.test.repositories.ClientRepository;
import com.rapiCredit.test.services.ClientService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    ClientService clientService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createClientTest() throws ControlledException {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setName("Client");
        clientDTO.setAge(18);
        clientDTO.setEmail("jmejia@rapiCredit.com.co");
        Client client = new Client(clientDTO);

        Client clientSaved = new Client();
        clientSaved.setId(clientDTO.getId());
        clientSaved.setName("Client 2");
        clientSaved.setAge(19);
        clientSaved.setEmail("jmejia@rapiCredit.com.co");

        Mockito.when(clientRepository.save(client)).thenReturn(clientSaved);
        ClientDTO clientDTOResult = clientService.createClient(clientDTO);
        Assert.assertNotNull(clientDTOResult);
    }

    @Test
    public void deleteClientTest() throws ControlledException {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setName("jaime");
        clientDTO.setAge(100);
        clientDTO.setEmail("jmejia@rapiCredit.com.co");
        Client client = new Client(clientDTO);

        Mockito.doNothing().when(clientRepository).deleteById(client.getId());
        Mockito.when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));

        clientService.deleteClient(client.getId());

        Mockito.verify(clientRepository, Mockito.times(1)).deleteById(client.getId());
    }

    @Test
    public void updateClientTest() throws ControlledException {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setName("jaime");
        clientDTO.setAge(100);
        clientDTO.setEmail("jmejia@rapiCredit.com.co");
        Client client = new Client(clientDTO);

        ClientDTO clientDTOExpected = new ClientDTO();
        clientDTOExpected.setId(clientDTO.getId());
        clientDTOExpected.setName("Jaime Quintero");
        clientDTOExpected.setAge(200);
        clientDTOExpected.setEmail("jdmejiaq@unal.edu.co");
        Client clientSaved = new Client(clientDTOExpected);

        Mockito.when(clientRepository.save(client)).thenReturn(clientSaved);
        Mockito.when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        ClientDTO clientDTOResult = clientService.createClient(clientDTO);
        Assert.assertEquals(clientDTOExpected, clientDTOResult);
    }

    @Test
    public void retrieveClientTest() throws ControlledException {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setName("jaime");
        clientDTO.setAge(100);
        clientDTO.setEmail("jmejia@rapiCredit.com.co");
        Client client = new Client(clientDTO);

        Mockito.when(clientRepository.findById(client.getId())).thenReturn(Optional.of(client));
        ClientDTO clientDTOExpected = clientService.getClientDTOById(client.getId());
        Assert.assertEquals(clientDTOExpected, clientDTO);
    }

    @Test
    public void retrieveClientsTest() {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setName("jaime");
        clientDTO.setAge(100);
        clientDTO.setEmail("jmejia@rapiCredit.com.co");
        Client client = new Client(clientDTO);

        ClientDTO clientDTOExpected = new ClientDTO();
        clientDTOExpected.setId(clientDTO.getId());
        clientDTOExpected.setName("Jaime Quintero");
        clientDTOExpected.setAge(200);
        clientDTOExpected.setEmail("jdmejiaq@unal.edu.co");
        Client clientSaved = new Client(clientDTOExpected);
        
        Client[] clientList = {client, clientSaved};

        List<ClientDTO> clientDTOList = new ArrayList<>();
        clientDTOList.add(clientDTO);
        clientDTOList.add(clientDTOExpected);

        Mockito.when(clientRepository.findAll()).thenReturn(Arrays.asList(clientList));

        List<ClientDTO> clientDTOListResult = clientService.findAll();
        Assert.assertEquals(clientDTOList, clientDTOListResult);
    }

    @Test(expected = ClientNotFoundException.class)
    public void retrieveClientNotRegisteredTest() throws ControlledException {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setName("Client");
        clientDTO.setAge(18);
        clientDTO.setEmail("jmejia@rapiCredit.com.co");
        Client client = new Client(clientDTO);

        clientService.getClientDTOById(client.getId());
    }

    @Test(expected = InvalidClientException.class)
    public void createClientAgeInvalidTest() throws ControlledException {
        ClientDTO clientDTO = new ClientDTO();
        clientDTO.setId(UUID.randomUUID());
        clientDTO.setName("Client");
        clientDTO.setAge(-100);
        clientDTO.setEmail("jmejia@rapiCredit.com.co");
        clientService.createClient(clientDTO);
    }
}
