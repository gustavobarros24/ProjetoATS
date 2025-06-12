import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.example.Atividade;
import org.example.BicepCurls;
import org.example.Utilizador;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BicepCurlsTest {

    private BicepCurls bicepCurls;
    private Utilizador utilizador;

    // Mock Utilizador for testing
    private static class UtilizadorMock extends Utilizador {
        private double fatorMultiplicativo = 1.0;
        private double bmr = 1800.0;
        private double peso = 80.0;
        private int freqCardiaca = 60;
        private char genero = 'M';

        public UtilizadorMock() {
            super();
        }

        @Override
        public double getFatorMultiplicativo() {
            return fatorMultiplicativo;
        }

        @Override
        public double getBMR() {
            return bmr;
        }

        @Override
        public double getPeso() {
            return peso;
        }

        @Override
        public int getFreqCardiaca() {
            return freqCardiaca;
        }

        @Override
        public char getGenero() {
            return genero;
        }

        // Stubs for abstract methods
        @Override
        public java.util.List<org.example.Atividade> allAtividades(LocalDate dataInicial, LocalDate dataFinal) {
            return new java.util.ArrayList<>();
        }

        @Override
        public Object utilizadorNumPeriodo(LocalDate dataInicio, LocalDate dataFim) {
            return this;
        }

        @Override
        public Object clone() {
            return new UtilizadorMock();
        }
    }

    @Before
    public void setUp() {
        bicepCurls = new BicepCurls(
                LocalDateTime.of(2024, 5, 10, 10, 0),
                LocalTime.of(0, 10, 0),
                120,
                40,
                20.0
        );
        utilizador = new UtilizadorMock();
    }

    @Test
    public void testConstrutores() {
        BicepCurls vazio = new BicepCurls();
        assertNotNull(vazio);

        BicepCurls copia = new BicepCurls(bicepCurls);
        assertEquals(bicepCurls, copia);
    }

    @Test
    public void testConsumoCalorias() {
        double calorias = bicepCurls.consumoCalorias(utilizador);
        assertTrue(calorias > 0);
    }

    @Test
    public void testToString() {
        String str = bicepCurls.toString();
        assertTrue(str.contains("Bicep curls"));
        assertTrue(str.contains("Peso"));
        assertTrue(str.contains("Repetiçoes"));
    }

    @Test
    public void testEqualsAndClone() {
        BicepCurls outro = new BicepCurls(bicepCurls);
        assertTrue(bicepCurls.equals(outro));
        assertTrue(outro.equals(bicepCurls));
        assertFalse(bicepCurls.equals(null));
        assertFalse(bicepCurls.equals(new Object()));

        BicepCurls clone = (BicepCurls) bicepCurls.clone();
        assertEquals(bicepCurls, clone);
    }

    @Test
    public void testGeraAtividade() {
        double consumo = 50.0;
        Atividade atividade = bicepCurls.geraAtividade(utilizador, consumo);
        assertNotNull(atividade);
        assertTrue(atividade instanceof BicepCurls);
        assertEquals(0, atividade.getFreqCardiaca());
        assertTrue(((BicepCurls)atividade).getPeso() > 0);
    }
}
