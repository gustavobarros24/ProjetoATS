import java.time.LocalDate;

import org.example.UtilizadorProfissional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UtilizadorProfissionalTest {

    @Test
    public void testConstrutorVazio() {
        UtilizadorProfissional up = new UtilizadorProfissional();
        assertNotNull(up);
        assertTrue(up instanceof UtilizadorProfissional);
    }

    @Test
    public void testConstrutorParametrizado() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up = new UtilizadorProfissional("Nome", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        assertEquals("Nome", up.getNome());
        assertEquals("Morada", up.getMorada());
        assertEquals("email@test.com", up.getEmail());
        assertEquals(70, up.getFreqCardiaca());
        assertEquals(80.0, up.getPeso(), 0.01);
        assertEquals(180, up.getAltura());
        assertEquals(data, up.getDataNascimento());
        assertEquals('M', up.getGenero());
    }

    @Test
    public void testConstrutorCopia() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up1 = new UtilizadorProfissional("Nome", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        UtilizadorProfissional up2 = new UtilizadorProfissional(up1);
        assertEquals(up1, up2);
        assertNotSame(up1, up2);
    }

    @Test
    public void testConstrutorPeriodo() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up1 = new UtilizadorProfissional("Nome", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        LocalDate inicio = LocalDate.of(2020, 1, 1);
        LocalDate fim = LocalDate.of(2020, 12, 31);
        UtilizadorProfissional up2 = new UtilizadorProfissional(up1, inicio, fim);
        assertEquals(up1.getNome(), up2.getNome());
        assertEquals(up1.getEmail(), up2.getEmail());
    }

    @Test
    public void testGetFatorMultiplicativo() {
        UtilizadorProfissional up = new UtilizadorProfissional();
        assertEquals(1.5, up.getFatorMultiplicativo(), 0.0001);
    }

    @Test
    public void testToString() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up = new UtilizadorProfissional("Nome", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        String str = up.toString();
        assertTrue(str.contains("Profissional"));
        assertTrue(str.contains("Nome"));
    }

    @Test
    public void testEqualsTrue() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up1 = new UtilizadorProfissional("Nome", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        UtilizadorProfissional up2 = new UtilizadorProfissional(up1);
        assertTrue(up1.equals(up2));
        assertTrue(up1.equals(up1));
    }

    @Test
    public void testEqualsFalse() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up1 = new UtilizadorProfissional("Nome1", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        UtilizadorProfissional up2 = new UtilizadorProfissional("Nome2", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        assertFalse(up1.equals(up2));
        assertFalse(up1.equals(null));
        assertFalse(up1.equals("string"));
    }

    @Test
    public void testClone() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up1 = new UtilizadorProfissional("Nome", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        UtilizadorProfissional up2 = (UtilizadorProfissional) up1.clone();
        assertEquals(up1, up2);
        assertNotSame(up1, up2);
    }

    @Test
    public void testUtilizadorNumPeriodo() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorProfissional up1 = new UtilizadorProfissional("Nome", "Morada", "email@test.com", 70, 80, 180, data, 'M');
        LocalDate inicio = LocalDate.of(2020, 1, 1);
        LocalDate fim = LocalDate.of(2020, 12, 31);
        UtilizadorProfissional up2 = (UtilizadorProfissional) up1.utilizadorNumPeriodo(inicio, fim);
        assertNotNull(up2);
        assertEquals(up1.getNome(), up2.getNome());
        assertEquals(up1.getEmail(), up2.getEmail());
    }
}
