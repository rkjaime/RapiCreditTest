package com.rapiCredit.test.controllers;

import java.util.List;
import java.util.UUID;

import com.rapiCredit.test.dto.ClientDTO;
import com.rapiCredit.test.exceptions.ControlledException;
import com.rapiCredit.test.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")
public class ClientController {

    @Autowired
    ClientService clientService;

    @GetMapping("/{clientId}")
    public ResponseEntity<ClientDTO> getClient(@PathVariable UUID clientId) throws ControlledException {
        ResponseEntity<ClientDTO> responseObject;
        ClientDTO clientDTO = clientService.getClientDTOById(clientId);
        responseObject = new ResponseEntity<>(clientDTO, HttpStatus.OK);
        return responseObject;
    }

    @PostMapping("")
    public ResponseEntity<Void> createClient(@RequestBody ClientDTO clientDTO) throws ControlledException {
        ResponseEntity<Void> responseObject;
        clientService.createClient(clientDTO);
        responseObject = new ResponseEntity<>(HttpStatus.CREATED);
        return responseObject;
    }

    @GetMapping("")
    public ResponseEntity<List<ClientDTO>> getClient() throws ControlledException {
        ResponseEntity<List<ClientDTO>> responseObject;
        List<ClientDTO> clientList = clientService.findAll();
        responseObject = new ResponseEntity<>(clientList, HttpStatus.OK);
        return responseObject;
    }

    @PutMapping("/{clientId}")
    public ResponseEntity<Void> updateClient(@RequestBody ClientDTO clientDTO, @PathVariable UUID clientId)
            throws ControlledException {
        ResponseEntity<Void> responseObject;
        clientService.updateClient(clientDTO, clientId);
        responseObject = new ResponseEntity<>(HttpStatus.ACCEPTED);
        return responseObject;
    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<Void> deleteClient(@PathVariable UUID clientId) throws ControlledException {
        ResponseEntity<Void> responseObject;
        clientService.deleteClient(clientId);
        responseObject = new ResponseEntity<>(HttpStatus.OK);
        return responseObject;
    }

    @GetMapping("/age")
    public ResponseEntity<List<ClientDTO>> getClientByAge() throws ControlledException {
        ResponseEntity<List<ClientDTO>> responseObject;
        List<ClientDTO> clientList = clientService.findClientByAge();
        responseObject = new ResponseEntity<>(clientList, HttpStatus.OK);
        return responseObject;
    }
}
