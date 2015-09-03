package com.atlwork.angularseed.app.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.atlwork.angularseed.app.domain.Client;
import com.atlwork.angularseed.app.service.ClientService;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @RequestMapping(method = RequestMethod.GET)
    public List<Client> list() {
	return clientService.list();
    }

    @RequestMapping(method = RequestMethod.POST)
    public Client create(@RequestBody @Valid Client client) {
	return clientService.save(client);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{id}")
    public Client get(@PathVariable Long id) {
	return clientService.read(id);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Client update(@Valid @RequestBody Client client) {
	return clientService.save(client);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{id}")
    public void delete(@PathVariable Long id) {
	clientService.delete(id);
    }

}