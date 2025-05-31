package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.qql.Encomenda;
import src.qql.Fatura;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FaturaTest {

    private Fatura fatura;
    private Encomenda encomenda;
    private List<Integer> artigos;
    private LocalDateTime dataFinalizacao;

    @BeforeEach
    public void setUp() {
        encomenda = new Encomenda();
        artigos = Arrays.asList(101, 102, 103);
        dataFinalizacao = LocalDateTime.of(2024, 5, 15, 14, 30);
        fatura = new Fatura(200.75f, Fatura.Tipo.VENDA, encomenda, dataFinalizacao, artigos);
    }

    @Test
    public void testGetCusto() {
        assertEquals(200.75f, fatura.getCusto());
    }

    @Test
    public void testSetCusto() {
        fatura.setCusto(300.99f);
        assertEquals(300.99f, fatura.getCusto());
    }

    @Test
    public void testGetTipo() {
        assertEquals(Fatura.Tipo.VENDA, fatura.getTipo());
    }

    @Test
    public void testSetTipo() {
        fatura.setTipo(Fatura.Tipo.COMPRA);
        assertEquals(Fatura.Tipo.COMPRA, fatura.getTipo());
    }

    @Test
    public void testGetEncomenda() {
        assertEquals(encomenda, fatura.getEncomenda());
    }

    @Test
    public void testSetEncomenda() {
        Encomenda novaEncomenda = new Encomenda();
        fatura.setEncomenda(novaEncomenda);
        assertEquals(novaEncomenda, fatura.getEncomenda());
    }

    @Test
    public void testGetDataFinalizacao() {
        assertEquals(dataFinalizacao, fatura.getDataFinalizacao());
    }

    @Test
    public void testSetDataFinalizacao() {
        LocalDateTime novaData = LocalDateTime.of(2025, 1, 10, 8, 0);
        fatura.setDataFinalizacao(novaData);
        assertEquals(novaData, fatura.getDataFinalizacao());
    }

    @Test
    public void testGetArtigos() {
        assertEquals(artigos, fatura.getArtigos());
    }

    @Test
    public void testSetArtigos() {
        List<Integer> novosArtigos = Arrays.asList(999, 888, 777);
        fatura.setArtigos(novosArtigos);
        assertEquals(novosArtigos, fatura.getArtigos());
    }

    @Test
    public void testToString() {
        String expected = "tipo: " + Fatura.Tipo.VENDA +
                ", custo: " + 200.75f +
                ", data de finalização: " + dataFinalizacao +
                ", artigos: " + artigos.toString();
        assertEquals(expected, fatura.toString());
    }
}
