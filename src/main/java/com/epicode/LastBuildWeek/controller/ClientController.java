package com.epicode.LastBuildWeek.controller;

import com.epicode.LastBuildWeek.payload.ClientDTO;
import com.epicode.LastBuildWeek.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/client")
public class ClientController {

    @Autowired
    ClientService clientService;



    @PostMapping
    public ResponseEntity<ClientDTO> createClient(@RequestBody @Validated ClientDTO clientDTO){
     return  ResponseEntity.status(HttpStatus.CREATED).body(clientService.createClient(clientDTO));
    }
    @GetMapping
    public ResponseEntity<Page<ClientDTO>> getAllClients(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        return ResponseEntity.ok(clientService.getAllClients(page,size));
    }
    @GetMapping("/{id}")
    public ResponseEntity<ClientDTO> getClientById(@PathVariable Long id){
        return ResponseEntity.ok(clientService.getClientById(id));
    }
    @PutMapping("/{id}")
    public ResponseEntity<ClientDTO> updateClient(@PathVariable Long id, @RequestBody ClientDTO clientDTO){
        return ResponseEntity.ok(clientService.updateClient(id,clientDTO));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>("Cliente eliminato con successo!👌", HttpStatus.OK);
    }
}
