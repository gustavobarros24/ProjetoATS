package Vintage.qql;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TShirtTest {

    private TShirt tshirt1;
    private TShirt tshirt2;

    @BeforeEach
    public void setUp() {
        tshirt1 = new TShirt("Casual Tee", "Algodão orgânico", "Zara", 303, 30.0f, 1.0f, false, 1, "Usada", "UPS", 15, "joao", "M", "PALMEIRAS", 10);
        tshirt2 = new TShirt(tshirt1);
    }

    @Test
    public void testConstrutores() {
        assertNotNull(tshirt1);
        assertEquals(tshirt1, tshirt2);

        TShirt tshirt3 = new TShirt();
        assertNull(tshirt3.getTamanho());
        assertNull(tshirt3.getPadrao());
        assertEquals(0, tshirt3.getDesconto());
    }

    @Test
    public void testGettersSetters() {
        tshirt1.setTamanho("L");
        assertEquals(TShirt.Tamanho.L, tshirt1.getTamanho());

        tshirt1.setPadrao("RISCAS");
        assertEquals(TShirt.Padrao.RISCAS, tshirt1.getPadrao());

        tshirt1.setDesconto(20);
        assertEquals(20, tshirt1.getDesconto());
    }

    @Test
    public void testSetTamanhoPadraoInvalidos() {
        assertThrows(IllegalArgumentException.class, () -> tshirt1.setTamanho("XXL"));
        assertThrows(IllegalArgumentException.class, () -> tshirt1.setPadrao("FLORAL"));
    }

    @Test
    public void testEquals() {
        assertEquals(tshirt1, tshirt2);
        assertNotEquals(null, tshirt1);
        assertNotEquals("string", tshirt1);

        TShirt tshirt3 = tshirt1.clone();
        tshirt3.setDesconto(99);
        assertNotEquals(tshirt1, tshirt3);
    }

    @Test
    public void testClone() {
        TShirt clone = tshirt1.clone();
        assertNotSame(clone, tshirt1);
        assertEquals(clone, tshirt1);
    }

    @Test
    public void testToString() {
        String str = tshirt1.toString();
        assertTrue(str.contains("tipo: t-shirt"));
        assertTrue(str.contains("padrão: PALMEIRAS"));
        assertTrue(str.contains("desconto: 10%"));
    }

    @Test
    public void testCalcularPrecoFinal_LisoOuNovo() {
        TShirt tshirt = new TShirt("Liso Novo", "Branca", "Uniqlo", 304, 25.0f, 1.0f, true, 0, "Novo", "FedEx", 20, "ana", "S", "LISO", 0);
        float result = tshirt.calcularPrecoFinal();
        assertEquals(25.0f, result, 0.01f);
    }

    @Test
    public void testCalcularPrecoFinal_NaoLisoENaoNovo() {
        tshirt1.setPadrao("RISCAS");
        tshirt1.setPrecoBase(40.0f);
        tshirt1.setNovo(false);
        float result = tshirt1.calcularPrecoFinal();
        assertEquals(20.0f, result, 0.01f);
    }
}
