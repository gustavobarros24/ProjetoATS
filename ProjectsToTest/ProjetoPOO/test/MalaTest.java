package test;

import org.junit.jupiter.api.Test;
import src.qql.Mala;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class MalaTest {

    @Test
    public void testConstrutoresEGetters() {
        Mala mala = new Mala("Nome", "Descrição", "Marca", 123, 100.0f, 10.0f, true, 0,
                "Boa", "Transportadora", 5, "Vendedor",
                10.0f, "Couro", 2020, true, 15.0f);

        assertEquals("Nome", mala.getNome());
        assertEquals("Descrição", mala.getDescricao());
        assertEquals("Marca", mala.getMarca());
        assertEquals(123, mala.getCodigo());
        assertEquals(100.0f, mala.getPrecoBase());
        assertEquals(10.0f, mala.getCorrecaoPreco());
        assertTrue(mala.getNovo());
        assertEquals(0, mala.getNumDonos());
        assertEquals("Boa", mala.getCondicao());
        assertEquals("Transportadora", mala.getTransportadora());
        assertEquals(5, mala.getStock());
        assertEquals("Vendedor", mala.getVendedor());
        assertEquals(10.0f, mala.getDimensao());
        assertEquals("Couro", mala.getMaterial());
        assertEquals(2020, mala.getAnoColecao());
        assertTrue(mala.isPremium());
        assertEquals(15.0f, mala.getValorizacao());
    }

    @Test
    public void testSetters() {
        Mala mala = new Mala();
        mala.setDimensao(12.5f);
        mala.setMaterial("Tecido");
        mala.setAnoColecao(2019);
        mala.setPremium(false);
        mala.setValorizacao(5.5f);

        assertEquals(12.5f, mala.getDimensao());
        assertEquals("Tecido", mala.getMaterial());
        assertEquals(2019, mala.getAnoColecao());
        assertFalse(mala.isPremium());
        assertEquals(5.5f, mala.getValorizacao());
    }

    @Test
    public void testClone() {
        Mala original = new Mala("Nome", "Desc", "Marca", 1, 100.0f, 0f, false, 2, "Usada", "Transp", 3, "Vend",
                5f, "Couro", 2021, false, 10f);
        Mala clone = original.clone();

        assertEquals(original, clone);
        assertNotSame(original, clone);
    }

    @Test
    public void testCalcularPrecoFinalPremium() {
        Mala mala = new Mala("Nome", "Desc", "Marca", 1, 100.0f, 0f, true, 1, "Nova", "Transp", 1, "Vend",
                10.0f, "Couro", 2020, true, 0f);
        float expected = 100.0f + (101100.0f / (10.0f * 2020));
        assertEquals(expected, mala.calcularPrecoFinal(), 0.01f);
    }

    @Test
    public void testCalcularPrecoFinalNaoPremium() {
        int currentYear = LocalDate.now().getYear();
        int anoColecao = 2015;
        int diff = currentYear - anoColecao;

        Mala mala = new Mala("Nome", "Desc", "Marca", 1, 100.0f, 0f, true, 1, "Nova", "Transp", 1, "Vend",
                10.0f, "Couro", anoColecao, false, 0f);

        float expected = 100.0f + (0.2f * diff) + (101100.0f / (10.0f * anoColecao));
        assertEquals(expected, mala.calcularPrecoFinal(), 0.01f);
    }

    @Test
    public void testEqualsAndHashCode() {
        Mala mala1 = new Mala("Nome", "Desc", "Marca", 1, 100.0f, 0f, true, 1, "Nova", "Transp", 1, "Vend",
                10.0f, "Couro", 2020, false, 0f);
        Mala mala2 = mala1.clone();
        Mala diferente = new Mala();

        assertEquals(mala1, mala2);
        assertNotEquals(mala1, diferente);
        assertNotEquals(mala1, null);
        assertNotEquals(mala1, "string");
    }

    @Test
    public void testToString() {
        Mala mala = new Mala("Nome", "Descrição", "Marca", 123, 100.0f, 10.0f, true, 1,
                "Boa", "Transp", 5, "Vendedor",
                10.0f, "Couro", 2020, true, 15.0f);
        String str = mala.toString();

        assertTrue(str.contains("código: 123"));
        assertTrue(str.contains("dimensão: 10.0"));
        assertTrue(str.contains("premium: true"));
        assertTrue(str.contains("valorização: 15.0%"));
    }
}
