import model.Funcionario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.FuncionarioService;

import static org.junit.jupiter.api.Assertions.*;

public class FuncionarioServiceTest {

    private FuncionarioService funcionarioService;

    @BeforeEach
    public void setUp() {
        funcionarioService = new FuncionarioService();
    }

    private Funcionario criarFuncionarioBase() {
        Funcionario funcionario = new Funcionario();
        funcionario.setIdFuncionario(1);
        funcionario.setNome("Carlos Teste");
        funcionario.setCargo("Lavador");
        funcionario.setTelefone("61988887777");
        return funcionario;
    }

    @Test
    @DisplayName("Cadastro de funcionário com nome nulo deve lançar exceção")
    public void testCadastrarFuncionario_NomeNulo() {
        Funcionario f = criarFuncionarioBase();
        f.setNome(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.cadastrarFuncionario(f)
        );
        assertEquals("O nome do funcionário é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de funcionário com nome vazio deve lançar exceção")
    public void testCadastrarFuncionario_NomeVazio() {
        Funcionario f = criarFuncionarioBase();
        f.setNome("");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.cadastrarFuncionario(f)
        );
        assertEquals("O nome do funcionário é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de funcionário com nome contendo apenas espaços deve lançar exceção")
    public void testCadastrarFuncionario_NomeSoComEspacos() {
        Funcionario f = criarFuncionarioBase();
        f.setNome("   ");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.cadastrarFuncionario(f)
        );
        assertEquals("O nome do funcionário é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de funcionário com telefone nulo deve lançar exceção")
    public void testCadastrarFuncionario_TelefoneNulo() {
        Funcionario f = criarFuncionarioBase();
        f.setTelefone(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.cadastrarFuncionario(f)
        );
        assertEquals("O telefone do funcionário é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de funcionário com telefone vazio deve lançar exceção")
    public void testCadastrarFuncionario_TelefoneVazio() {
        Funcionario f = criarFuncionarioBase();
        f.setTelefone("");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.cadastrarFuncionario(f)
        );
        assertEquals("O telefone do funcionário é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de funcionário com cargo nulo deve lançar exceção")
    public void testCadastrarFuncionario_CargoNulo() {
        Funcionario f = criarFuncionarioBase();
        f.setCargo(null);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.cadastrarFuncionario(f)
        );
        assertEquals("O cargo do funcionário é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Cadastro de funcionário com cargo vazio deve lançar exceção")
    public void testCadastrarFuncionario_CargoVazio() {
        Funcionario f = criarFuncionarioBase();
        f.setCargo("   ");

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.cadastrarFuncionario(f)
        );
        assertEquals("O cargo do funcionário é obrigatório.", ex.getMessage());
    }

    @Test
    @DisplayName("Atualização de funcionário com ID inválido deve lançar exceção")
    public void testAtualizarFuncionario_IdInvalido() {
        Funcionario f = criarFuncionarioBase();
        f.setIdFuncionario(0);

        IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.atualizarFuncionario(f)
        );
        assertEquals("ID do funcionário inválido para atualização.", ex.getMessage());

        f.setIdFuncionario(-1);
        ex = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.atualizarFuncionario(f)
        );
        assertEquals("ID do funcionário inválido para atualização.", ex.getMessage());
    }

    @Test
    @DisplayName("Exclusão de funcionário com ID inválido deve lançar exceção")
    public void testExcluirFuncionario_IdInvalido() {
        IllegalArgumentException exZero = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.excluirFuncionario(0)
        );
        assertEquals("ID do funcionário inválido para exclusão.", exZero.getMessage());

        IllegalArgumentException exNegativo = assertThrows(
                IllegalArgumentException.class,
                () -> funcionarioService.excluirFuncionario(-5)
        );
        assertEquals("ID do funcionário inválido para exclusão.", exNegativo.getMessage());
    }
}
