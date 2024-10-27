package com.vendas.api_vendas.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vendas.api_vendas.model.Cliente;
import com.vendas.api_vendas.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


public class ClienteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ClienteService clienteService;

    @InjectMocks
    private ClienteController clienteController;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente("Fulano", "333.444.666-99", new Date(), "Rua tal de tal", "M", "(44)9-9999-8888", "fulano@gmail.com");
        mockMvc = MockMvcBuilders.standaloneSetup(clienteController).build();
    }

    @Test
    @DisplayName("Deve salvar um cliente e retornar status 200 OK")
    public void testSalvarCliente() throws Exception {
        Cliente cliente = new Cliente("Fulano", "333.444.666-99", new Date(), "Rua tal de tal", "M", "(44)9-9999-8888", "fulano@gmail.com");
        when(clienteService.salvarCliente(any(Cliente.class))).thenReturn(cliente);
        mockMvc.perform(post("/clientes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(cliente)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Fulano"))
                .andExpect(jsonPath("$.rg").value("333.444.666-99"));
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes e status 200")
    public void testBuscarTodosClientes() throws Exception {
        List<Cliente> clientes = Arrays.asList(cliente);
        when(clienteService.buscarTodos()).thenReturn(clientes);

        mockMvc.perform(get("/clientes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Fulano"))
                .andExpect(jsonPath("$[0].rg").value("333.444.666-99"));
    }
}
