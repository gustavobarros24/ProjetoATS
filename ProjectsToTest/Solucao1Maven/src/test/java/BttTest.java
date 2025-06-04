import java.time.LocalDateTime;
import java.time.LocalTime;

import org.example.Atividade;
import org.example.Btt;
import org.example.Utilizador;
import org.example.UtilizadorAmador;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class BttTest {

    private Btt bttDefault;
    private Btt bttParam;
    private Btt bttCopy;
    private Utilizador utilizador;

    @Before
    public void setUp() {
        bttDefault = new Btt();
        bttParam = new Btt(LocalDateTime.of(2024, 6, 1, 10, 0),
                LocalTime.of(1, 0, 0), 120, 30.0, 1500.0);
        bttCopy = new Btt(bttParam);

        utilizador = new UtilizadorAmador(
            "Teste", "Rua", "email@email.com", 60, 70, 170,
            java.time.LocalDate.of(2000, 1, 1), 'M'
        );
    }

    @Test
    public void testConstrutores() {
        assertNotNull(bttDefault);
        assertNotNull(bttParam);
        assertNotNull(bttCopy);
        assertEquals(bttParam, bttCopy);
    }

    @Test
    public void testGetFatorHard() {
        // altimetria = 0
        assertEquals(1.05, bttDefault.getFatorHard(), 0.0001);

        // altimetria = 1500
        Btt btt1 = new Btt(LocalDateTime.now(), LocalTime.of(1,0), 100, 10, 1500);
        assertEquals(1.15, btt1.getFatorHard(), 0.0001);

        // altimetria = 2500
        Btt btt2 = new Btt(LocalDateTime.now(), LocalTime.of(1,0), 100, 10, 2500);
        assertEquals(1.25, btt2.getFatorHard(), 0.0001);
    }

    @Test
    public void testConsumoCalorias() {
        Btt btt = new Btt(LocalDateTime.now(), LocalTime.of(1,0), 100, 10, 1500);
        double fatorMultiplicativo = utilizador.getFatorMultiplicativo();
        double fatorVelocidade = btt.getFatorVelocidade(10.5, 0.11);
        double fatorFreqCardiaca = btt.getFatorFreqCardiaca(utilizador);
        double fatorAltimetria = btt.getFatorAltimetria();
        double bmr = utilizador.getBMR();
        double fatorHard = btt.getFatorHard();
        int tempoSegundos = btt.getTempo().toSecondOfDay();

        double expected = 10 * (fatorMultiplicativo + fatorVelocidade + fatorFreqCardiaca + fatorAltimetria)
                * bmr / (24 * 60 * 60) * fatorHard * tempoSegundos;

        assertEquals(expected, btt.consumoCalorias(utilizador), 0.01);
    }

    @Test
    public void testGeraAtividade() {
        double calorias = 500.0;
        Atividade a = bttParam.geraAtividade(utilizador, calorias);

        assertNotNull(a);

        // Replicar o cálculo do método geraAtividade
        double maxAltimetria = utilizador.infoDasAtividadesNumPeriodoQueRespeitamP(
            java.time.LocalDate.MIN, java.time.LocalDate.MAX,
            at -> at instanceof org.example.BicepCurls,
            at -> ((Btt)at).getAltimetria()
        ).stream().reduce((p1, p2) -> p1 > p2 ? p1 : p2).orElse(0.0);

        double altimetria = maxAltimetria * 0.8;
        double fatorAltimetria = altimetria * 0.0005;
        double tempoDouble = calorias / (10 * (utilizador.getBMR() / (24 * 60 * 60)) * (utilizador.getFatorMultiplicativo() + fatorAltimetria));
        int tempo = (int) tempoDouble;

        assertEquals(LocalTime.MIN.plusSeconds(tempo), a.getTempo());
        assertEquals(0, a.getFreqCardiaca());
        assertTrue(a instanceof Btt);
    }

    @Test
    public void testToString() {
        String s = bttParam.toString();
        assertTrue(s.contains("Tipo de atividade: BTT"));
    }

    @Test
    public void testEquals() {
        assertTrue(bttParam.equals(bttCopy));
        assertFalse(bttParam.equals(null));
        assertFalse(bttParam.equals("string"));
        Btt other = new Btt(LocalDateTime.now(), LocalTime.of(1,0), 100, 10, 1000);
        assertFalse(bttParam.equals(other));
    }

    @Test
    public void testClone() {
        Btt clone = (Btt) bttParam.clone();
        assertEquals(bttParam, clone);
        assertNotSame(bttParam, clone);
    }
}
