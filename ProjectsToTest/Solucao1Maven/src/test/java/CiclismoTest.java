import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.example.Atividade;
import org.example.Ciclismo;
import org.example.UtilizadorAmador;
import org.example.UtilizadorPraticanteOcasional;
import org.example.UtilizadorProfissional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class CiclismoTest {

    private Ciclismo ciclismoDefault;
    private Ciclismo ciclismoParam;
    private UtilizadorAmador utilizadorAmador;
    private UtilizadorProfissional utilizadorProfissional;
    private UtilizadorPraticanteOcasional utilizadorOcasional;

    @Before
    public void setUp() {
        ciclismoDefault = new Ciclismo();
        ciclismoParam = new Ciclismo(
                LocalDateTime.of(2024, 6, 1, 10, 0),
                LocalTime.of(1, 0, 0),
                120,
                10500.0
        );
        utilizadorAmador = new UtilizadorAmador("A", "B", "C", 120, 70, 175, LocalDate.of(2000, 1, 1), 'M');
        utilizadorProfissional = new UtilizadorProfissional("D", "E", "F", 120, 80, 180, LocalDate.of(1990, 2, 2), 'F');
        utilizadorOcasional = new UtilizadorPraticanteOcasional("G", "H", "I", 120, 65, 170, LocalDate.of(1985, 3, 3), 'M');
    }

    @Test
    public void testConstrutorVazio() {
        assertNotNull(ciclismoDefault);
        assertEquals(0.0, ciclismoDefault.getDistancia(), 0.001);
    }

    @Test
    public void testConstrutorParametrizado() {
        assertEquals(LocalDateTime.of(2024, 6, 1, 10, 0), ciclismoParam.getDataRealizacao());
        assertEquals(LocalTime.of(1, 0, 0), ciclismoParam.getTempo());
        assertEquals(120, ciclismoParam.getFreqCardiaca());
        assertEquals(10500.0, ciclismoParam.getDistancia(), 0.001);
    }

    @Test
    public void testConstrutorCopia() {
        Ciclismo copia = new Ciclismo(ciclismoParam);
        assertEquals(ciclismoParam, copia);
        assertNotSame(ciclismoParam, copia);
    }

    @Test
    public void testConsumoCaloriasAmador() {
        ciclismoParam.setFreqCardiaca(120);
        double calorias = ciclismoParam.consumoCalorias(utilizadorAmador);
        assertTrue(calorias < 0);
    }

    @Test
    public void testConsumoCaloriasProfissional() {
        ciclismoParam.setFreqCardiaca(120);
        double calorias = ciclismoParam.consumoCalorias(utilizadorProfissional);
        assertTrue(calorias > 0);
    }

    @Test
    public void testConsumoCaloriasOcasional() {
        ciclismoParam.setFreqCardiaca(120);
        double calorias = ciclismoParam.consumoCalorias(utilizadorOcasional);
        assertTrue(calorias > 0);
    }

    @Test
    public void testGeraAtividade() {
        double calorias = 500.0;
        Atividade atividade = ciclismoParam.geraAtividade(utilizadorAmador, calorias);
        assertNotNull(atividade);
        assertTrue(atividade instanceof Ciclismo);
        assertEquals(0, atividade.getFreqCardiaca());
        assertEquals(((Ciclismo)atividade).getDistancia(), atividade.getTempo().toSecondOfDay() * 10.5, 0.001);
    }

    @Test
    public void testToString() {
        String str = ciclismoParam.toString();
        assertTrue(str.contains("Tipo de atividade: Ciclismo"));
        assertTrue(str.contains("Distancia: 10500.0 metros"));
    }

    @Test
    public void testEquals() {
        Ciclismo c1 = new Ciclismo(ciclismoParam);
        Ciclismo c2 = new Ciclismo(ciclismoParam);
        assertTrue(c1.equals(c2));
        assertFalse(c1.equals(null));
        assertFalse(c1.equals("not a ciclismo"));
        assertTrue(c1.equals(c1));
    }

    @Test
    public void testClone() {
        Object clone = ciclismoParam.clone();
        assertTrue(clone instanceof Ciclismo);
        assertEquals(ciclismoParam, clone);
        assertNotSame(ciclismoParam, clone);
    }
}
