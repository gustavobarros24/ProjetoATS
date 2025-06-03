import org.example.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class CorridaTest {

    private Corrida corrida;
    private Utilizador utilizador;

    @Before
    public void setUp() {
        corrida = new Corrida();
        utilizador = new UtilizadorAmador("Test User", "Test Address", "test@email.com", 100, 80, 170, java.time.LocalDate.of(1990, 1, 1), 'M');
    }

    @Test
    public void testConstrutorVazio() {
        Corrida c = new Corrida();
        assertNotNull(c);
    }

    @Test
    public void testConstrutorParametrizado() {
        LocalDateTime realizacao = LocalDateTime.now();
        LocalTime tempo = LocalTime.of(1, 30, 0);
        int freqCardiaca = 150;
        double distancia = 10.0;

        Corrida c = new Corrida(realizacao, tempo, freqCardiaca, distancia);

        assertEquals(realizacao, c.getDataRealizacao());
        assertEquals(tempo, c.getTempo());
        assertEquals(freqCardiaca, c.getFreqCardiaca());
        assertEquals(distancia, c.getDistancia(), 0.001);
    }

    @Test
    public void testConstrutorCopia() {
        LocalDateTime realizacao = LocalDateTime.now();
        LocalTime tempo = LocalTime.of(1, 0, 0);
        Corrida original = new Corrida(realizacao, tempo, 140, 8.5);

        Corrida copia = new Corrida(original);

        assertEquals(original.getDataRealizacao(), copia.getDataRealizacao());
        assertEquals(original.getTempo(), copia.getTempo());
        assertEquals(original.getFreqCardiaca(), copia.getFreqCardiaca());
        assertEquals(original.getDistancia(), copia.getDistancia(), 0.001);
        assertNotSame(original, copia);
    }

    @Test
    public void testConsumoCalorias() {
        LocalTime tempo = LocalTime.of(1, 0, 0);
        corrida.setTempo(tempo);
        corrida.setDistancia(10.0);
        corrida.setFreqCardiaca(150);

        double calorias = corrida.consumoCalorias(utilizador);

        assertTrue(calorias > 0);
    }

    @Test
    public void testConsumoCaloriasTempoZero() {
        LocalTime tempo = LocalTime.of(0, 0, 0);
        corrida.setTempo(tempo);
        corrida.setDistancia(0.0);
        corrida.setFreqCardiaca(120);

        double calorias = corrida.consumoCalorias(utilizador);

        assertEquals(0.0, calorias, 0.001);
    }

    @Test
    public void testGeraAtividade() {
        double consumoCalorias = 500.0;

        Atividade atividade = corrida.geraAtividade(utilizador, consumoCalorias);

        assertNotNull(atividade);
        assertTrue(atividade instanceof Corrida);
        assertEquals(0, atividade.getFreqCardiaca());
        assertTrue(atividade.getTempo().toSecondOfDay() > 0);
        assertTrue(((AtivDistancia) atividade).getDistancia() > 0);
    }

    @Test
    public void testGeraAtividadeConsumoZero() {
        double consumoCalorias = 0.0;

        Atividade atividade = corrida.geraAtividade(utilizador, consumoCalorias);

        assertNotNull(atividade);
        assertEquals(LocalTime.of(0, 0, 0), atividade.getTempo());
        assertEquals(0.0, ((AtivDistancia) atividade).getDistancia(), 0.001);
    }

    @Test
    public void testToString() {
        String resultado = corrida.toString();

        assertNotNull(resultado);
        assertTrue(resultado.contains("Tipo de atividade: Corrida"));
    }

    @Test
    public void testEqualsObjetosIguais() {
        LocalDateTime realizacao = LocalDateTime.now();
        LocalTime tempo = LocalTime.of(1, 0, 0);
        Corrida c1 = new Corrida(realizacao, tempo, 150, 10.0);
        Corrida c2 = new Corrida(realizacao, tempo, 150, 10.0);

        assertEquals(c1, c2);
    }

    @Test
    public void testEqualsMesmoObjeto() {
        assertEquals(corrida, corrida);
    }

    @Test
    public void testEqualsObjetoNull() {
        assertNotEquals(corrida, null);
    }

    @Test
    public void testEqualsClasseDiferente() {
        String outroObjeto = "string";
        assertNotEquals(corrida, outroObjeto);
    }

    @Test
    public void testClone() {
        LocalDateTime realizacao = LocalDateTime.now();
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Corrida original = new Corrida(realizacao, tempo, 140, 12.0);

        Corrida clone = (Corrida) original.clone();

        assertEquals(original, clone);
        assertNotSame(original, clone);
    }

    @Test
    public void testClonePreservaDados() {
        LocalDateTime realizacao = LocalDateTime.of(2023, 12, 25, 10, 30);
        LocalTime tempo = LocalTime.of(0, 45, 30);
        Corrida original = new Corrida(realizacao, tempo, 160, 7.5);

        Corrida clone = (Corrida) original.clone();

        assertEquals(original.getDataRealizacao(), clone.getDataRealizacao());
        assertEquals(original.getTempo(), clone.getTempo());
        assertEquals(original.getFreqCardiaca(), clone.getFreqCardiaca());
        assertEquals(original.getDistancia(), clone.getDistancia(), 0.001);
    }
}
