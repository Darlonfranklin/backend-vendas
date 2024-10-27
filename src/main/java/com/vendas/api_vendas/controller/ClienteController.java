package com.vendas.api_vendas.controller;
import com.vendas.api_vendas.model.Cliente;
import com.vendas.api_vendas.service.ClienteService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public Cliente salvarCliente(@RequestBody Cliente cliente) {
        Cliente savedCliente = service.salvarCliente(cliente);
        return ResponseEntity.ok(savedCliente).getBody();
    }

    @GetMapping
    public ResponseEntity<List<Cliente>> buscarTodos() {
        List<Cliente> clientes = service.buscarTodos();
        return ResponseEntity.ok(clientes);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Long id, @RequestBody Cliente clienteAtualizado) {
        clienteAtualizado.setId(id);
        Cliente clienteEditado = service.editarCliente(clienteAtualizado);
        return ResponseEntity.ok(clienteEditado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluirCliente(@PathVariable Long id) {
        service.exluirCliente(id);
        return ResponseEntity.noContent().build();
    }
}
