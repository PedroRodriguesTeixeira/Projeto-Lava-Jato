import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AutenticacaoService;

import static org.junit.jupiter.api.Assertions.*;

public class AutenticacaoServiceTest {

    private AutenticacaoService autenticacaoService;

    @BeforeEach
    public void setUp() {
        autenticacaoService = new AutenticacaoService();
    }

    @Test
    @DisplayName("Login com login nulo deve lançar IllegalArgumentException")
    public void testLogin_LoginNulo() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> autenticacaoService.login(null, "1234")
        );
        assertEquals("O login é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Login com login vazio deve lançar IllegalArgumentException")
    public void testLogin_LoginVazio() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> autenticacaoService.login("", "1234")
        );
        assertEquals("O login é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Login com login contendo apenas espaços deve lançar IllegalArgumentException")
    public void testLogin_LoginSoComEspacos() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> autenticacaoService.login("   ", "1234")
        );
        assertEquals("O login é obrigatório.", exception.getMessage());
    }

    @Test
    @DisplayName("Login com senha nula deve lançar IllegalArgumentException")
    public void testLogin_SenhaNula() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> autenticacaoService.login("admin", null)
        );
        assertEquals("A senha é obrigatória.", exception.getMessage());
    }

    @Test
    @DisplayName("Login com senha vazia deve lançar IllegalArgumentException")
    public void testLogin_SenhaVazia() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> autenticacaoService.login("admin", "")
        );
        assertEquals("A senha é obrigatória.", exception.getMessage());
    }

    @Test
    @DisplayName("Login com senha contendo apenas espaços deve lançar IllegalArgumentException")
    public void testLogin_SenhaSoComEspacos() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> autenticacaoService.login("admin", "   ")
        );
        assertEquals("A senha é obrigatória.", exception.getMessage());
    }
}
