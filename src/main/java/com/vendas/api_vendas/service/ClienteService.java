package com.vendas.api_vendas.service;

import com.vendas.api_vendas.model.Cliente;
import com.vendas.api_vendas.repository.ClienteRepository;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente salvarCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public List<Cliente> buscarTodos() {
        return repository.findAll();
    }

    public Cliente editarCliente(Cliente clienteAtualizado) {
        return repository.save(clienteAtualizado);
    }

    public void exluirCliente(Long id) {
        repository.deleteById(id);
    }
}
