import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.example.Abdominais;
import org.example.Atividade;
import org.example.Utilizador;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class AbdominaisTest {

    private Abdominais abdominais;
    private Utilizador utilizador;

    @Before
    public void setUp() {
        abdominais = new Abdominais(
                LocalDateTime.of(2024, 6, 1, 10, 0),
                LocalTime.of(0, 10, 0),
                80,
                100
        );
        utilizador = new Utilizador() {
            @Override
            public double getFatorMultiplicativo() {
                return 1.0;
            }

            @Override
            public double getBMR() {
                return 1800.0;
            }

            @Override
            public Object clone() {
                return null;
            }

            @Override
            public int getFreqCardiaca() {
                return 100;
            }

            @Override
            public Object utilizadorNumPeriodo(LocalDate dataInicio, LocalDate dataFim) {
                return null;
            }
        };
    }

    @Test
    public void testConstrutorVazio() {
        Abdominais abd = new Abdominais();
        assertNotNull(abd);
        assertEquals(0, abd.getRepeticoes());
    }

    @Test
    public void testConstrutorParametrizado() {
        assertEquals(LocalTime.of(0, 10, 0), abdominais.getTempo());
        assertEquals(80, abdominais.getFreqCardiaca());
        assertEquals(100, abdominais.getRepeticoes());
    }

    @Test
    public void testConstrutorCopia() {
        Abdominais copia = new Abdominais(abdominais);
        assertEquals(abdominais, copia);
        assertNotSame(abdominais, copia);
    }

    @Test
    public void testConsumoCalorias() {
        double calorias = abdominais.consumoCalorias(utilizador);
        assertTrue(calorias > 0);
    }

    @Test
    public void testGeraAtividade() {
        double calorias = abdominais.consumoCalorias(utilizador);
        Atividade nova = abdominais.geraAtividade(utilizador, calorias);
        assertNotNull(nova);
        assertTrue(nova instanceof Abdominais);
        assertEquals(0, nova.getFreqCardiaca());
    }

    @Test
    public void testToString() {
        String str = abdominais.toString();
        assertTrue(str.contains("Tipo de atividade: Abdominais"));
        assertTrue(str.contains("Repetiçoes: 100"));
    }
}