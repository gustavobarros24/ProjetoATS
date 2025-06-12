package Vintage.qql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class SapatilhaTest {

    private Sapatilha sapatilha1;
    private Sapatilha sapatilha2;

    @BeforeEach
    public void setUp() {
        sapatilha1 = new Sapatilha("Air Max", "Confortável", "Nike", 101, 100.0f, 1.0f, false, 2, "Usada", "DHL", 10, "vendedor", 42, true, "Branco", 2020, true);
        sapatilha2 = new Sapatilha(sapatilha1);
    }

    @Test
    public void testConstrutores() {
        assertNotNull(sapatilha1);
        assertEquals(sapatilha1, sapatilha2);

        Sapatilha s3 = new Sapatilha(41, false, "Azul", 2021, false);
        assertEquals(41, s3.getTamanho());
        assertFalse(s3.getAtacadores());
        assertEquals("Azul", s3.getCor());
        assertEquals(2021, s3.getAnoLancamento());
        assertFalse(s3.isPremium());
    }

    @Test
    public void testGettersSetters() {
        sapatilha1.setTamanho(43);
        assertEquals(43, sapatilha1.getTamanho());

        sapatilha1.setAtacadores(false);
        assertFalse(sapatilha1.getAtacadores());

        sapatilha1.setCor("Vermelho");
        assertEquals("Vermelho", sapatilha1.getCor());

        sapatilha1.setAnoLancamento(2019);
        assertEquals(2019, sapatilha1.getAnoLancamento());

        sapatilha1.setPremium(false);
        assertFalse(sapatilha1.isPremium());
    }

    @Test
    public void testEquals() {
        assertEquals(sapatilha1, sapatilha2);
        assertNotEquals(null, sapatilha1);
        assertNotEquals("string", sapatilha1);

        Sapatilha s3 = sapatilha1.clone();
        assertEquals(sapatilha1, s3);

        s3.setTamanho(100);
        assertNotEquals(sapatilha1, s3);
    }

    @Test
    public void testClone() {
        Sapatilha clone = sapatilha1.clone();
        assertNotSame(clone, sapatilha1);
        assertEquals(clone, sapatilha1);
    }

    @Test
    public void testToString() {
        String str = sapatilha1.toString();
        assertTrue(str.contains("tipo: sapatilha"));
        assertTrue(str.contains("Air Max"));
        assertTrue(str.contains("vendedor: vendedor"));
    }

    @Test
    public void testCalcularPrecoFinal_Premium() {
        int anoAtual = LocalDate.now().getYear();
        int diff = anoAtual - sapatilha1.getAnoLancamento();

        float expected = 100.0f + diff * 2.0f;
        float result = sapatilha1.calcularPrecoFinal();
        assertEquals(expected, result, 0.01f);
    }

    @Test
    public void testCalcularPrecoFinal_NaoPremiumComDonos() {
        sapatilha1.setPremium(false);
        sapatilha1.setNumDonos(2);
        sapatilha1.setPrecoBase(120.0f);
        float expected = 120.0f - (120.0f / 2);
        float result = sapatilha1.calcularPrecoFinal();
        assertEquals(expected, result, 0.01f);
    }

    @Test
    public void testCalcularPrecoFinal_NaoPremiumSemDonos() {
        sapatilha1.setPremium(false);
        sapatilha1.setNumDonos(0);
        sapatilha1.setPrecoBase(90.0f);
        float expected = 90.0f;
        float result = sapatilha1.calcularPrecoFinal();
        assertEquals(expected, result, 0.01f);
    }
}
