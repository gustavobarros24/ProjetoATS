import java.time.LocalDate;

import org.example.UtilizadorAmador;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class UtilizadorAmadorTest {

    @Test
    public void testConstrutorVazio() {
        UtilizadorAmador u = new UtilizadorAmador();
        assertNotNull(u);
        assertEquals(0, u.getCodUtilizador());
        assertEquals("", u.getNome());
        assertEquals("", u.getMorada());
        assertEquals("", u.getEmail());
        assertEquals(0, u.getFreqCardiaca());
        assertEquals(0.0, u.getPeso(), 0.001);
        assertEquals(0, u.getAltura());
        assertNotNull(u.getDataNascimento());
        assertEquals(0, u.getGenero());
        assertNotNull(u.getAtividadesPlanoTreino());
        assertNotNull(u.getAtividadesIsoladas());
    }

    @Test
    public void testConstrutorParametrizado() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        UtilizadorAmador u = new UtilizadorAmador("Nome", "Rua", "email@mail.com", 70, 80, 180, data, 'M');
        assertEquals("Nome", u.getNome());
        assertEquals("Rua", u.getMorada());
        assertEquals("email@mail.com", u.getEmail());
        assertEquals(70, u.getFreqCardiaca());
        assertEquals(80.0, u.getPeso(), 0.001);
        assertEquals(180, u.getAltura());
        assertEquals(data, u.getDataNascimento());
        assertEquals('M', u.getGenero());
    }

    @Test
    public void testConstrutorCopia() {
        LocalDate data = LocalDate.of(1990, 5, 10);
        UtilizadorAmador u1 = new UtilizadorAmador("A", "B", "C", 60, 70, 170, data, 'F');
        UtilizadorAmador u2 = new UtilizadorAmador(u1);
        assertEquals(u1, u2);
        assertNotSame(u1, u2);
    }

    @Test
    public void testConstrutorPeriodo() {
        LocalDate data = LocalDate.of(1995, 3, 15);
        UtilizadorAmador u1 = new UtilizadorAmador("X", "Y", "Z", 65, 75, 175, data, 'M');
        LocalDate inicio = LocalDate.of(2020, 1, 1);
        LocalDate fim = LocalDate.of(2020, 12, 31);
        UtilizadorAmador u2 = new UtilizadorAmador(u1, inicio, fim);
        assertEquals(u1.getNome(), u2.getNome());
        assertEquals(u1.getEmail(), u2.getEmail());
    }

    @Test
    public void testGetFatorMultiplicativo() {
        UtilizadorAmador u = new UtilizadorAmador();
        assertEquals(1.0, u.getFatorMultiplicativo(), 0.001);
    }

    @Test
    public void testToString() {
        UtilizadorAmador u = new UtilizadorAmador();
        String s = u.toString();
        assertTrue(s.contains("Tipo de Utilizador: Amador"));
    }

    @Test
    public void testToStringNotNullAndContainsNome() {
        LocalDate data = LocalDate.of(2001, 2, 2);
        UtilizadorAmador u = new UtilizadorAmador("TesteNome", "Rua", "mail@mail.com", 60, 70, 170, data, 'F');
        String s = u.toString();
        assertNotNull(s);
        assertTrue(s.contains("TesteNome"));
    }

    @Test
    public void testEqualsReflexive() {
        UtilizadorAmador u = new UtilizadorAmador();
        assertTrue(u.equals(u));
    }

    @Test
    public void testEqualsNull() {
        UtilizadorAmador u = new UtilizadorAmador();
        assertFalse(u.equals(null));
    }

    @Test
    public void testEqualsDifferentClass() {
        UtilizadorAmador u = new UtilizadorAmador();
        assertFalse(u.equals("not a UtilizadorAmador"));
    }

    @Test
    public void testEqualsTrue() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        UtilizadorAmador u1 = new UtilizadorAmador("Nome", "Rua", "email@mail.com", 70, 80, 180, data, 'M');
        UtilizadorAmador u2 = new UtilizadorAmador(u1);
        assertTrue(u1.equals(u2));
    }

    @Test
    public void testEqualsFalse() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        UtilizadorAmador u1 = new UtilizadorAmador("Nome", "Rua", "email@mail.com", 70, 80, 180, data, 'M');
        UtilizadorAmador u2 = new UtilizadorAmador("Outro", "Rua", "email@mail.com", 70, 80, 180, data, 'M');
        assertFalse(u1.equals(u2));
    }

    @Test
    public void testClone() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        UtilizadorAmador u1 = new UtilizadorAmador("Nome", "Rua", "email@mail.com", 70, 80, 180, data, 'M');
        UtilizadorAmador u2 = (UtilizadorAmador) u1.clone();
        assertEquals(u1, u2);
        assertNotSame(u1, u2);
    }

    @Test
    public void testUtilizadorNumPeriodo() {
        LocalDate data = LocalDate.of(2000, 1, 1);
        UtilizadorAmador u1 = new UtilizadorAmador("Nome", "Rua", "email@mail.com", 70, 80, 180, data, 'M');
        LocalDate inicio = LocalDate.of(2022, 1, 1);
        LocalDate fim = LocalDate.of(2022, 12, 31);
        UtilizadorAmador u2 = (UtilizadorAmador) u1.utilizadorNumPeriodo(inicio, fim);
        assertEquals(u1.getNome(), u2.getNome());
        assertEquals(u1.getEmail(), u2.getEmail());
    }
}
