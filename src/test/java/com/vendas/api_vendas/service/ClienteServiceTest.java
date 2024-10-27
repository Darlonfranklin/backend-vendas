import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import com.vendas.api_vendas.model.Cliente;
import com.vendas.api_vendas.repository.ClienteRepository;
import com.vendas.api_vendas.service.ClienteService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class ClienteServiceTest {

    @InjectMocks
    private ClienteService clienteService;

    @Mock
    private ClienteRepository clienteRepository;

    private Cliente cliente;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        cliente = new Cliente("Fulano", "333.444.666-99", new Date(), "Rua tal de tal", "M", "(44)9-9999-8888", "fulano@gmail.com");
    }

    @Test
    @DisplayName("Deve salvar um cliente")
    public void testSalvarCliente() {
           when(clienteRepository.save(cliente)).thenReturn(cliente);
           Cliente savedCliente = clienteService.salvarCliente(cliente);
           assertEquals(cliente.getId(), savedCliente.getId(), "O cliente salvo deve ter o mesmo ID");
           assertEquals(cliente.getNome(), savedCliente.getNome(), "O nome do cliente salvo deve ser o mesmo");
    }

    @Test
    @DisplayName("Deve retornar uma lista de clientes")
    public void testBuscarTodos() {
        when(clienteRepository.findAll()).thenReturn(Arrays.asList(cliente));
        List<Cliente> clientes = clienteService.buscarTodos();
        assertEquals(1, clientes.size(), "Deve retornar uma lista com um cliente");
        assertEquals(cliente.getNome(), clientes.get(0).getNome(), "O nome do cliente deve ser o mesmo");
    }
}
