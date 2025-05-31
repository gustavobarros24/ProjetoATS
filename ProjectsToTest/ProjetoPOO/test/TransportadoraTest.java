package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import src.qql.Encomenda.TamanhoEmbalagem;
import src.qql.Transportadora;

public class TransportadoraTest {

    private Transportadora t;

    @BeforeEach
    void setUp() {
        t = new Transportadora("FedEx", 0.2f, 10.0f, 20.0f, 30.0f, 1.6f);
    }

    @Test
    void testConstrutorPadrao() {
        Transportadora t1 = new Transportadora();
        assertEquals("", t1.getNome());
        assertEquals(0.0f, t1.getValorExpEncPeq());
        assertEquals(0.0f, t1.getValorExpEncMed());
        assertEquals(0.0f, t1.getValorExpEncGra());
        assertEquals(0.0f, t1.getImposto());
        assertEquals(0.0f, t1.getMargemLucro());
        assertEquals(0.0f, t1.getLucroEfetivo());
        assertEquals("(VB * MLT * (1 + I)) * 0.9", t1.getFormula());
    }

    @Test
    void testConstrutorCopia() {
        Transportadora copia = new Transportadora(t);
        assertEquals(t.getNome(), copia.getNome());
        assertEquals(t.getValorExpEncPeq(), copia.getValorExpEncPeq());
        assertEquals(t.getValorExpEncMed(), copia.getValorExpEncMed());
        assertEquals(t.getValorExpEncGra(), copia.getValorExpEncGra());
        assertEquals(t.getImposto(), copia.getImposto());
        assertEquals(t.getMargemLucro(), copia.getMargemLucro());
        assertEquals(t.getLucroEfetivo(), copia.getLucroEfetivo());
        assertEquals(t.getFormula(), copia.getFormula());
    }

    @Test
    void testGettersSetters() {
        Transportadora t1 = new Transportadora();
        t1.setNome("Nova");
        t1.setValorExpEncPeq(5.5f);
        t1.setValorExpEncMed(10.5f);
        t1.setValorExpEncGra(16.5f);
        t1.setImposto(0.1f);
        t1.setMargemLucro(1.2f);
        t1.setLucroEfetivo(100.0f);
        t1.setFormula("VB + MLT + I");

        assertEquals("Nova", t1.getNome());
        assertEquals(5.5f, t1.getValorExpEncPeq());
        assertEquals(10.5f, t1.getValorExpEncMed());
        assertEquals(16.5f, t1.getValorExpEncGra());
        assertEquals(0.1f, t1.getImposto());
        assertEquals(1.2f, t1.getMargemLucro());
        assertEquals(100.0f, t1.getLucroEfetivo());
        assertEquals("VB + MLT + I", t1.getFormula());
    }

    @Test
    void testClone() {
        Transportadora copia = t.clone();
        assertNotSame(t, copia);
        assertEquals(t.getNome(), copia.getNome());
    }

    @Test
    void testCalculaPrecoExpedicaoPequeno() {
        float expected = (10.0f * 1.6f * (1 + 0.2f)) * 0.9f;
        assertEquals(expected, t.calculaPrecoExpedicao(TamanhoEmbalagem.PEQUENO), 0.001f);
    }

    @Test
    void testCalculaPrecoExpedicaoMedio() {
        float expected = (20.0f * 1.6f * (1 + 0.2f)) * 0.9f;
        assertEquals(expected, t.calculaPrecoExpedicao(TamanhoEmbalagem.MEDIO), 0.001f);
    }

    @Test
    void testCalculaPrecoExpedicaoGrande() {
        float expected = (30.0f * 1.6f * (1 + 0.2f)) * 0.9f;
        assertEquals(expected, t.calculaPrecoExpedicao(TamanhoEmbalagem.GRANDE), 0.001f);
    }

    @Test
    void testFormulaPersonalizada() {
        t.setFormula("VB + (VB * I) + (VB * MLT)");
        float expected = 10.0f + (10.0f * 0.2f) + (10.0f * 1.6f);
        assertEquals(expected, t.calculaPrecoExpedicao(TamanhoEmbalagem.PEQUENO), 0.001f);
    }
}
