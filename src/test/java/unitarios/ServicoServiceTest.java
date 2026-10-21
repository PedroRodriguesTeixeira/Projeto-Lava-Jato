package unitarios;

import model.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.ServicoService;

import static org.junit.jupiter.api.Assertions.*;

public class ServicoServiceTest {

    private ServicoService servicoService;

    @BeforeEach
    public void setUp() {
        servicoService = new ServicoService();
    }

    private Servico criarServicoBase() {
        Servico s = new Servico();
        s.setIdServico(1);
        s.setNomeServico("Lavagem Simples");
        s.setDescricao("Lavagem externa");
        s.setValor(49.90);
        return s;
    }

    @Test
    @DisplayName("Cadastro de serviço com nome nulo deve lançar exceção")
    public void testCadastrarServico_NomeNulo() {
        Servico s = criarServicoBase();
        s.setNomeServico(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.cadastrarServico(s)
        );
        assertEquals("O nome do serviço é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de serviço com nome vazio deve lançar exceção")
    public void testCadastrarServico_NomeVazio() {
        Servico s = criarServicoBase();
        s.setNomeServico("");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.cadastrarServico(s)
        );
        assertEquals("O nome do serviço é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de serviço com nome contendo apenas espaços deve lançar exceção")
    public void testCadastrarServico_NomeSoComEspacos() {
        Servico s = criarServicoBase();
        s.setNomeServico("   ");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.cadastrarServico(s)
        );
        assertEquals("O nome do serviço é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de serviço com valor zero deve lançar exceção")
    public void testCadastrarServico_ValorZero() {
        Servico s = criarServicoBase();
        s.setValor(0.0);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.cadastrarServico(s)
        );
        assertEquals("O valor do serviço deve ser maior que zero.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de serviço com valor negativo deve lançar exceção")
    public void testCadastrarServico_ValorNegativo() {
        Servico s = criarServicoBase();
        s.setValor(-10.0);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.cadastrarServico(s)
        );
        assertEquals("O valor do serviço deve ser maior que zero.", ex.getMessage());
    }

    @Test
    @DisplayName("Atualização de serviço com ID inválido deve lançar exceção")
    public void testAtualizarServico_IdInvalido() {
        Servico s = criarServicoBase();
        s.setIdServico(0);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.atualizarServico(s)
        );
        assertEquals("ID do serviço inválido para atualização.", ex.getMessage());

        s.setIdServico(-1);
        ex = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.atualizarServico(s)
        );
        assertEquals("ID do serviço inválido para atualização.", ex.getMessage());
    }

    @Test
    @DisplayName("Exclusão de serviço com ID inválido deve lançar exceção")
    public void testExcluirServico_IdInvalido() {
        IllegalArgumentException exZero = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.excluirServico(0)
        );
        assertEquals("ID do serviço inválido para exclusão.", exZero.getMessage());

        IllegalArgumentException exNegativo = assertThrows(
                IllegalArgumentException.class,
                () -> servicoService.excluirServico(-10)
        );
        assertEquals("ID do serviço inválido para exclusão.", exNegativo.getMessage());
    }
}
