import model.AtendimentoServico;
import model.Servico;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import services.AtendimentoService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AtendimentoServiceTest {

    private AtendimentoService atendimentoService;

    @BeforeEach
    public void setUp() {
        atendimentoService = new AtendimentoService();
    }

    // CT-UT-SAT-001: O total do atendimento é a soma de valor vezes quantidade dos serviços
    @Test
    @DisplayName("CT-UT-SAT-001 - D1: um serviço de valor 49.90 e quantidade 1")
    public void testCalcularTotal_UmServico() {
        Servico servico = new Servico();
        servico.setValor(49.90);

        AtendimentoServico item = new AtendimentoServico();
        item.setServico(servico);
        item.setQuantidade(1);

        List<AtendimentoServico> lista = Collections.singletonList(item);

        double total = atendimentoService.calcularTotal(lista);
        assertEquals(49.90, total, 0.001);
    }

    @Test
    @DisplayName("CT-UT-SAT-001 - D2: dois serviços, um de valor 40.00 e quantidade 2 e outro de valor 25.50 e quantidade 1")
    public void testCalcularTotal_DoisServicos() {
        Servico s1 = new Servico();
        s1.setValor(40.00);
        AtendimentoServico item1 = new AtendimentoServico();
        item1.setServico(s1);
        item1.setQuantidade(2);

        Servico s2 = new Servico();
        s2.setValor(25.50);
        AtendimentoServico item2 = new AtendimentoServico();
        item2.setServico(s2);
        item2.setQuantidade(1);

        List<AtendimentoServico> lista = new ArrayList<>();
        lista.add(item1);
        lista.add(item2);

        double total = atendimentoService.calcularTotal(lista);
        assertEquals(105.50, total, 0.001);
    }

    @Test
    @DisplayName("CT-UT-SAT-001 - D3: lista vazia")
    public void testCalcularTotal_ListaVazia() {
        List<AtendimentoServico> lista = Collections.emptyList();

        double total = atendimentoService.calcularTotal(lista);
        assertEquals(0.0, total, 0.001);
    }

    @Test
    @DisplayName("CT-UT-SAT-001 - D4: null não deve lançar exceção e retornar 0.0")
    public void testCalcularTotal_Null() {
        double total = atendimentoService.calcularTotal(null);
        assertEquals(0.0, total, 0.001);
    }

    // CT-UT-SAT-002: Item da lista sem serviço associado no cálculo do total (reprovação prevista no plano)
    @Test
    @DisplayName("CT-UT-SAT-002 - Item da lista sem serviço associado no cálculo do total")
    public void testCalcularTotal_ItemSemServicoAssociado() {
        AtendimentoServico item = new AtendimentoServico();
        item.setServico(null);
        item.setQuantidade(1);

        List<AtendimentoServico> lista = Collections.singletonList(item);

        assertThrows(
                IllegalArgumentException.class,
                () -> atendimentoService.calcularTotal(lista)
        );
    }
}
