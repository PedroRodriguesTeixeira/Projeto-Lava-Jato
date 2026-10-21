package unitarios;

import model.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ClienteService;

import static org.junit.jupiter.api.Assertions.*;

public class ClienteServiceTest {

    private ClienteService clienteService;

    @BeforeEach
    public void setUp() {
        clienteService = new ClienteService();
    }

    private Cliente criarClienteBase() {
        Cliente cliente = new Cliente();
        cliente.setNome("Ana Teste");
        cliente.setTelefone("61999998888");
        cliente.setPlaca("XYZ1234");
        cliente.setModeloVeiculo("Onix");
        cliente.setMarcaVeiculo("Chevrolet");
        return cliente;
    }

    // CT-UT-SCL-001: Campo obrigatório ausente ou vazio é rejeitado antes de qualquer acesso ao banco
    @Test
    @DisplayName("CT-UT-SCL-001 - D1: nome nulo")
    public void testCadastrarCliente_NomeNulo() {
        Cliente cliente = criarClienteBase();
        cliente.setNome(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("O nome do cliente é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-001 - D2: nome só com espaços")
    public void testCadastrarCliente_NomeSoComEspacos() {
        Cliente cliente = criarClienteBase();
        cliente.setNome("   ");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("O nome do cliente é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-001 - D3: telefone vazio")
    public void testCadastrarCliente_TelefoneVazio() {
        Cliente cliente = criarClienteBase();
        cliente.setTelefone("");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("O telefone do cliente é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-001 - D4: placa nula")
    public void testCadastrarCliente_PlacaNula() {
        Cliente cliente = criarClienteBase();
        cliente.setPlaca(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("A placa do veículo é obrigatória.", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-001 - D5: modelo vazio")
    public void testCadastrarCliente_ModeloVazio() {
        Cliente cliente = criarClienteBase();
        cliente.setModeloVeiculo("");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("O modelo do veículo é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-001 - D6: marca nula")
    public void testCadastrarCliente_MarcaNula() {
        Cliente cliente = criarClienteBase();
        cliente.setMarcaVeiculo(null);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("A marca do veículo é obrigatória.", exception.getMessage());
    }

    // CT-UT-SCL-002: Placa fora dos formatos ABC1234 e ABC1D23 é rejeitada
    @Test
    @DisplayName("CT-UT-SCL-002 - D1: AB12 (curta demais)")
    public void testCadastrarCliente_PlacaCurtaDemais() {
        Cliente cliente = criarClienteBase();
        cliente.setPlaca("AB12");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("Placa inválida. Use o formato ABC1234 ou ABC1D23 (Mercosul).", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-002 - D2: 1234ABC (números antes das letras)")
    public void testCadastrarCliente_PlacaNumerosAntesDasLetras() {
        Cliente cliente = criarClienteBase();
        cliente.setPlaca("1234ABC");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("Placa inválida. Use o formato ABC1234 ou ABC1D23 (Mercosul).", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-002 - D3: ABC12345 (comprida demais)")
    public void testCadastrarCliente_PlacaCompridaDemais() {
        Cliente cliente = criarClienteBase();
        cliente.setPlaca("ABC12345");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("Placa inválida. Use o formato ABC1234 ou ABC1D23 (Mercosul).", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-002 - D4: ABC1D2 (Mercosul incompleta)")
    public void testCadastrarCliente_PlacaMercosulIncompleta() {
        Cliente cliente = criarClienteBase();
        cliente.setPlaca("ABC1D2");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("Placa inválida. Use o formato ABC1234 ou ABC1D23 (Mercosul).", exception.getMessage());
    }

    @Test
    @DisplayName("CT-UT-SCL-002 - D5: --- (só símbolos, que somem na normalização)")
    public void testCadastrarCliente_PlacaSoSimbolos() {
        Cliente cliente = criarClienteBase();
        cliente.setPlaca("---");

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> clienteService.cadastrarCliente(cliente)
        );
        assertEquals("Placa inválida. Use o formato ABC1234 ou ABC1D23 (Mercosul).", exception.getMessage());
    }
}
