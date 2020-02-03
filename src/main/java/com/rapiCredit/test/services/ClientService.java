package com.rapiCredit.test.services;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import com.rapiCredit.test.dto.ClientDTO;
import com.rapiCredit.test.entity.Client;
import com.rapiCredit.test.exceptions.ControlledException;
import com.rapiCredit.test.exceptions.InvalidClientException;
import com.rapiCredit.test.exceptions.ClientNotFoundException;
import com.rapiCredit.test.repositories.ClientRepository;
import com.rapiCredit.test.utils.Utilities;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.log4j.Logger;

@Service
@Transactional
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    static final Logger logger = Logger.getLogger(ClientService.class);

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDTO> findAll() {
        logger.info("inicia ejecución para mostrar los clientes");
        Iterable<Client> iterable = clientRepository.findAll();
        List<Client> clientList = IterableUtils.toList(iterable);
        return Utilities.convertEntityListToDTO(clientList);
    }

    public ClientDTO getClientDTOById(UUID clientId) throws ControlledException {
        logger.info("inicia ejecución para mostrar el cliente por id");
        return getClientById(clientId).getDTO();
    }

    public ClientDTO createClient(ClientDTO clientDTO) throws ControlledException {
        logger.info("inicia ejecución para crear cliente");
        return saveClient(clientDTO);
    }

    public ClientDTO updateClient(ClientDTO clientDTO, UUID clientId) throws ControlledException {
        logger.info("inicia ejecución para actualizar el cliente");
        Client client = getClientById(clientId);
        clientDTO.setId(client.getId());
        return saveClient(clientDTO);
    }

    private ClientDTO saveClient(ClientDTO clientDTO) throws ControlledException {
        logger.info("inicia ejecución para guardar el cliente");
        Client client = new Client(clientDTO);
        validateClient(client);
        Client clientSaved = clientRepository.save(client);
        return clientSaved.getDTO();
    }

    public void deleteClient(UUID clientId) throws ControlledException {
        logger.info("inicia ejecución para borrar el cliente");
        clientRepository.deleteById(clientId);
    }

    private void validateClient(Client client) throws ControlledException {
        validateAge(client);
    }

    private void validateAge(Client client) throws ControlledException {
        if (client.getAge() < 0) {
            logger.error("El cliente es menor de edad");
            throw new InvalidClientException("El cliente es menor de edad");
        }
    }

    private Client getClientById(UUID clientId) throws ControlledException {
        logger.info("Obtiene el cliente por el id");
        Optional<Client> optionalClient = clientRepository.findById(clientId);
        if (optionalClient.isPresent()) {
            return optionalClient.get();
        } else {
            throw new ClientNotFoundException("Cliente no encontrado");
        }
    }

    public List<ClientDTO> findClientByAge() {
        logger.error("Obtiene los clientes mayores de edad");
        Iterable<Client> iterable = clientRepository.findClientByAge();
        List<Client> clientList = IterableUtils.toList(iterable);
        return Utilities.convertEntityListToDTO(clientList);
    }
}

