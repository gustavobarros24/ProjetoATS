package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.qql.Encomenda;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class EncomendaTest {

    private Encomenda encomenda;
    private Encomenda encomendaCompleta;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataFinalizacao;
    private LocalDateTime dataEntrega;

    @BeforeEach
    public void setUp() {
        encomenda = new Encomenda();

        Map<Integer, Integer> artigos = new HashMap<>();
        artigos.put(101, 2);
        artigos.put(102, 1);
        artigos.put(103, 5);

        dataCriacao = LocalDateTime.now().minusDays(2);
        dataFinalizacao = LocalDateTime.now().minusDays(1);
        dataEntrega = LocalDateTime.now().plusDays(3);

        encomendaCompleta = new Encomenda(1000, artigos, Encomenda.TamanhoEmbalagem.MEDIO, 199.99f, Encomenda.EstadoEncomenda.EXPEDIDA, dataCriacao, dataFinalizacao, dataEntrega);
    }

    @Test
    public void testConstrutorPadrao() {
        assertNotNull(encomenda.getCodigo());
        assertTrue(encomenda.getArtigos().isEmpty());
        assertNull(encomenda.getDataFinalizacao());
        assertNull(encomenda.getDataPrevistaEntrega());
        assertEquals(0.0f, encomenda.getPrecoFinal());
        assertEquals(Encomenda.EstadoEncomenda.PENDENTE, encomenda.getEstado());
        assertNotNull(encomenda.getDataCriacao());
    }

    @Test
    public void testConstrutorComParametros() {
        Encomenda e = new Encomenda(Encomenda.TamanhoEmbalagem.GRANDE, 0.5f);
        assertEquals(Encomenda.TamanhoEmbalagem.GRANDE, e.getTamanhoEmbalagem());
        assertEquals(0.0f, e.getPrecoFinal());
        assertEquals(Encomenda.EstadoEncomenda.PENDENTE, e.getEstado());
    }

    @Test
    public void testAdicionarArtigoNovo() {
        encomenda.adicionarArtigo(1, 5);
        assertEquals(1, encomenda.getArtigos().size());
        assertEquals(5, encomenda.getArtigos().get(1));
    }

    @Test
    public void testAdicionarArtigoExistente() {
        encomenda.adicionarArtigo(1, 5);
        encomenda.adicionarArtigo(1, 3);
        assertEquals(8, encomenda.getArtigos().get(1));
    }

    @Test
    public void testRemoverArtigo() {
        encomenda.adicionarArtigo(2, 4);
        Integer removed = encomenda.removerArtigo(2);
        assertEquals(4, removed);
        assertFalse(encomenda.getArtigos().containsKey(2));
    }

    @Test
    public void testSetPrecoFinal() {
        encomenda.setPrecoFinal(42.50f);
        assertEquals(42.50f, encomenda.getPrecoFinal());
    }

    @Test
    public void testSetEstado() {
        encomenda.setEstado(Encomenda.EstadoEncomenda.FINALIZADA);
        assertEquals(Encomenda.EstadoEncomenda.FINALIZADA, encomenda.getEstado());
    }

    @Test
    public void testSetDataFinalizacao() {
        assertNull(encomenda.getDataFinalizacao());
        encomenda.setDataFinalizacao();
        assertNotNull(encomenda.getDataFinalizacao());
        assertTrue(ChronoUnit.SECONDS.between(encomenda.getDataFinalizacao(), LocalDateTime.now()) < 2);
    }

    @Test
    public void testSetDataPrevistaEntrega() {
        LocalDateTime data = LocalDateTime.now().plusDays(3);
        encomenda.setDataPrevistaEntrega(data);
        assertEquals(data, encomenda.getDataPrevistaEntrega());
    }

    @Test
    public void testSetTamanhoEmbalagem_Pequeno() {
        encomenda.adicionarArtigo(1, 1);
        encomenda.setTamanhoEmbalagem();
        assertEquals(Encomenda.TamanhoEmbalagem.PEQUENO, encomenda.getTamanhoEmbalagem());
    }

    @Test
    public void testSetTamanhoEmbalagem_Medio() {
        for (int i = 1; i <= 3; i++) {
            encomenda.adicionarArtigo(i, 1);
        }
        encomenda.setTamanhoEmbalagem();
        assertEquals(Encomenda.TamanhoEmbalagem.MEDIO, encomenda.getTamanhoEmbalagem());
    }

    @Test
    public void testSetTamanhoEmbalagem_Grande() {
        for (int i = 1; i <= 6; i++) {
            encomenda.adicionarArtigo(i, 1);
        }
        encomenda.setTamanhoEmbalagem();
        assertEquals(Encomenda.TamanhoEmbalagem.GRANDE, encomenda.getTamanhoEmbalagem());
    }

    @Test
    public void testDevolverEncomenda_ExpedidaDentroPrazo() {
        encomenda.setEstado(Encomenda.EstadoEncomenda.EXPEDIDA);
        encomenda.devolverEncomenda();
        assertEquals(Encomenda.EstadoEncomenda.DEVOLVIDA, encomenda.getEstado());
    }

    @Test
    public void testDevolverEncomenda_NaoExpedida() {
        encomenda.setEstado(Encomenda.EstadoEncomenda.PENDENTE);
        encomenda.devolverEncomenda();
        assertEquals(Encomenda.EstadoEncomenda.PENDENTE, encomenda.getEstado());
    }

    @Test
    public void testValoresIniciaisCompletos() {
        assertEquals(3, encomendaCompleta.getArtigos().size());
        assertEquals(Encomenda.TamanhoEmbalagem.MEDIO, encomendaCompleta.getTamanhoEmbalagem());
        assertEquals(199.99f, encomendaCompleta.getPrecoFinal());
        assertEquals(Encomenda.EstadoEncomenda.EXPEDIDA, encomendaCompleta.getEstado());
        assertNotNull(encomendaCompleta.getDataCriacao());
        assertNotNull(encomendaCompleta.getDataFinalizacao());
        assertEquals(dataEntrega, encomendaCompleta.getDataPrevistaEntrega());
    }

    @Test
    public void testDevolverEncomendaDentroPrazoCompleta() {
        encomendaCompleta.devolverEncomenda();
        assertEquals(Encomenda.EstadoEncomenda.DEVOLVIDA, encomendaCompleta.getEstado());
    }

    @Test
    public void testToStringCompleto() {
        encomendaCompleta.devolverEncomenda();
        String str = encomendaCompleta.toString();
        assertTrue(str.contains("código:"));
        assertTrue(str.contains("artigos: 3"));
        assertTrue(str.contains("embalagem: MEDIO"));
        assertTrue(str.contains("preço final: 199.99"));
        assertTrue(str.contains("estado: DEVOLVIDA"));
        assertTrue(str.contains("data de criação:"));
        assertTrue(str.contains("data de finalização:"));
        assertTrue(str.contains("data prevista de entrega:"));
    }

    @Test
    public void testAdicionarArtigoQuantidadeNegativa() {
        encomenda.adicionarArtigo(5, -3);
        assertTrue(encomenda.getArtigos().isEmpty(), "Não deve adicionar artigo com quantidade negativa");
    }

    @Test
    public void testRemoverArtigoNaoExistente() {
        Integer removed = encomenda.removerArtigo(999);
        assertNull(removed, "Remover artigo inexistente deve retornar null");
    }

    @Test
    public void testSetEstadoInvalido() {
        encomenda.setEstado(Encomenda.EstadoEncomenda.PENDENTE);
        encomenda.setEstado(null);
        assertEquals(Encomenda.EstadoEncomenda.PENDENTE, encomenda.getEstado(), "Estado não deve mudar para null");
    }

    @Test
    public void testSetDataPrevistaEntregaAnteriorDataCriacao() {
        LocalDateTime dataAnterior = encomenda.getDataCriacao().minusDays(1);
        encomenda.setDataPrevistaEntrega(dataAnterior);
        assertNotEquals(dataAnterior, encomenda.getDataPrevistaEntrega(), "Data prevista não deve ser anterior à data de criação");
    }


    @Test
    public void testToString() {
        encomenda.adicionarArtigo(1, 2);
        encomenda.setTamanhoEmbalagem();
        encomenda.setPrecoFinal(123.45f);
        encomenda.setEstado(Encomenda.EstadoEncomenda.EXPEDIDA);
        encomenda.setDataFinalizacao();
        encomenda.setDataPrevistaEntrega(LocalDateTime.now().plusDays(3));

        String output = encomenda.toString();
        assertTrue(output.contains("código:"));
        assertTrue(output.contains("artigos: 1"));
        assertTrue(output.contains("embalagem: PEQUENO"));
        assertTrue(output.contains("preço final: 123.45"));
        assertTrue(output.contains("estado: EXPEDIDA"));
        assertTrue(output.contains("data de criação:"));
        assertTrue(output.contains("data de finalização:"));
        assertTrue(output.contains("data prevista de entrega:"));
    }
}
