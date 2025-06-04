import java.time.LocalDate;

import org.example.UtilizadorPraticanteOcasional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UtilizadorPraticanteOcasionalTest {

    @Test
    public void testConstrutorVazio() {
        UtilizadorPraticanteOcasional u = new UtilizadorPraticanteOcasional();
        assertNotNull(u);
        assertTrue(u instanceof UtilizadorPraticanteOcasional);
    }

    @Test
    public void testConstrutorParametrizado() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorPraticanteOcasional u = new UtilizadorPraticanteOcasional("Ana", "Rua A", "ana@email.com", 70, 60, 170, data, 'F');
        assertEquals("Ana", u.getNome());
        assertEquals("Rua A", u.getMorada());
        assertEquals("ana@email.com", u.getEmail());
        assertEquals(70, u.getFreqCardiaca());
        assertEquals(60.0, u.getPeso(), 0.001);
        assertEquals(170, u.getAltura());
        assertEquals(data, u.getDataNascimento());
        assertEquals('F', u.getGenero());
    }

    @Test
    public void testConstrutorCopia() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorPraticanteOcasional u1 = new UtilizadorPraticanteOcasional("Ana", "Rua A", "ana@email.com", 70, 60, 170, data, 'F');
        UtilizadorPraticanteOcasional u2 = new UtilizadorPraticanteOcasional(u1);
        assertEquals(u1, u2);
        assertNotSame(u1, u2);
    }

    @Test
    public void testConstrutorPeriodo() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorPraticanteOcasional u1 = new UtilizadorPraticanteOcasional("Ana", "Rua A", "ana@email.com", 70, 60, 170, data, 'F');
        LocalDate inicio = LocalDate.of(2020, 1, 1);
        LocalDate fim = LocalDate.of(2021, 1, 1);
        UtilizadorPraticanteOcasional u2 = new UtilizadorPraticanteOcasional(u1, inicio, fim);
        assertEquals(u1.getNome(), u2.getNome());
        assertEquals(u1.getEmail(), u2.getEmail());
    }

    @Test
    public void testGetFatorMultiplicativo() {
        UtilizadorPraticanteOcasional u = new UtilizadorPraticanteOcasional();
        assertEquals(1.25, u.getFatorMultiplicativo(), 0.0001);
    }

    @Test
    public void testToStringContainsTipo() {
        UtilizadorPraticanteOcasional u = new UtilizadorPraticanteOcasional();
        String s = u.toString();
        assertTrue(s.contains("Tipo de Utilizador: Praticante Ocasional"));
    }

    @Test
    public void testEqualsTrue() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorPraticanteOcasional u1 = new UtilizadorPraticanteOcasional("Ana", "Rua A", "ana@email.com", 70, 60, 170, data, 'F');
        UtilizadorPraticanteOcasional u2 = new UtilizadorPraticanteOcasional(u1);
        assertTrue(u1.equals(u2));
        assertTrue(u2.equals(u1));
        assertTrue(u1.equals(u1));
    }

    @Test
    public void testEqualsFalse() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorPraticanteOcasional u1 = new UtilizadorPraticanteOcasional("Ana", "Rua A", "ana@email.com", 70, 60, 170, data, 'F');
        UtilizadorPraticanteOcasional u2 = new UtilizadorPraticanteOcasional("Joao", "Rua B", "joao@email.com", 80, 80, 180, data, 'M');
        assertFalse(u1.equals(u2));
        assertFalse(u1.equals(null));
        assertFalse(u1.equals("string"));
    }

    @Test
    public void testClone() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorPraticanteOcasional u1 = new UtilizadorPraticanteOcasional("Ana", "Rua A", "ana@email.com", 70, 60, 170, data, 'F');
        UtilizadorPraticanteOcasional u2 = (UtilizadorPraticanteOcasional) u1.clone();
        assertEquals(u1, u2);
        assertNotSame(u1, u2);
    }

    @Test
    public void testUtilizadorNumPeriodo() {
        LocalDate data = LocalDate.of(1990, 1, 1);
        UtilizadorPraticanteOcasional u1 = new UtilizadorPraticanteOcasional("Ana", "Rua A", "ana@email.com", 70, 60, 170, data, 'F');
        LocalDate inicio = LocalDate.of(2020, 1, 1);
        LocalDate fim = LocalDate.of(2021, 1, 1);
        Object obj = u1.utilizadorNumPeriodo(inicio, fim);
        assertTrue(obj instanceof UtilizadorPraticanteOcasional);
        UtilizadorPraticanteOcasional u2 = (UtilizadorPraticanteOcasional) obj;
        assertEquals(u1.getNome(), u2.getNome());
    }
}
