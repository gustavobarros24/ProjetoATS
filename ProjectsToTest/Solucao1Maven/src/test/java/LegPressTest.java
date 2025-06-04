import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.example.Atividade;
import org.example.LegPress;
import org.example.UtilizadorAmador;
import org.example.UtilizadorProfissional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class LegPressTest {
    
    private LegPress legPress;
    private UtilizadorAmador utilizadorAmador;
    private UtilizadorProfissional utilizadorProfissional;
    private LocalDateTime dataRealizacao;
    private LocalTime tempo;
    
    @Before
    public void setUp() {
        dataRealizacao = LocalDateTime.of(2024, 5, 15, 10, 30);
        tempo = LocalTime.of(0, 30, 0);
        legPress = new LegPress(dataRealizacao, tempo, 120, 15, 80.0);
        utilizadorAmador = new UtilizadorAmador("João", "Porto", "joao@email.com", 70, 75, 180, LocalDate.of(1990, 5, 15), 'M');
        utilizadorProfissional = new UtilizadorProfissional("Maria", "Lisboa", "maria@email.com", 65, 60, 165, LocalDate.of(1985, 3, 20), 'F');
    }
    
    @Test
    public void testConstrutorVazio() {
        LegPress lp = new LegPress();
        assertNotNull(lp);
    }
    
    @Test
    public void testConstrutorParametrizado() {
        LegPress lp = new LegPress(dataRealizacao, tempo, 120, 15, 80.0);
        assertNotNull(lp);
        assertEquals(dataRealizacao, lp.getDataRealizacao());
        assertEquals(tempo, lp.getTempo());
        assertEquals(120, lp.getFreqCardiaca());
        assertEquals(15, lp.getRepeticoes());
        assertEquals(80.0, lp.getPeso(), 0.01);
    }
    
    @Test
    public void testConstrutorCopia() {
        LegPress original = new LegPress(dataRealizacao, tempo, 120, 15, 80.0);
        LegPress copia = new LegPress(original);
        assertNotNull(copia);
        assertEquals(original.getDataRealizacao(), copia.getDataRealizacao());
        assertEquals(original.getTempo(), copia.getTempo());
        assertEquals(original.getFreqCardiaca(), copia.getFreqCardiaca());
        assertEquals(original.getRepeticoes(), copia.getRepeticoes());
        assertEquals(original.getPeso(), copia.getPeso(), 0.01);
        assertNotSame(original, copia);
    }
    
    @Test
    public void testConsumoCaloriasUtilizadorAmador() {
        double calorias = legPress.consumoCalorias(utilizadorAmador);
        assertTrue(calorias > 0);
    }
    
    @Test
    public void testConsumoCaloriasUtilizadorProfissional() {
        double calorias = legPress.consumoCalorias(utilizadorProfissional);
        assertTrue(calorias > 0);
    }
    
    @Test
    public void testGeraAtividadeComUtilizadorAmador() {
        double consumoCalorias = 500.0;
        Atividade atividade = legPress.geraAtividade(utilizadorAmador, consumoCalorias);
        assertNotNull(atividade);
        assertTrue(atividade instanceof LegPress);
        assertTrue(atividade.getTempo().toSecondOfDay() > 0);
        assertTrue(((LegPress)atividade).getPeso() > 0);
        assertTrue(((LegPress)atividade).getRepeticoes() >= 0);
        assertEquals(0, atividade.getFreqCardiaca());
    }
    
    @Test
    public void testGeraAtividadeComUtilizadorProfissional() {
        double consumoCalorias = 800.0;
        Atividade atividade = legPress.geraAtividade(utilizadorProfissional, consumoCalorias);
        assertNotNull(atividade);
        assertTrue(atividade instanceof LegPress);
        assertTrue(atividade.getTempo().toSecondOfDay() > 0);
        assertTrue(((LegPress)atividade).getPeso() > 0);
        assertTrue(((LegPress)atividade).getRepeticoes() >= 0);
        assertEquals(0, atividade.getFreqCardiaca());
    }
    
    @Test
    public void testToString() {
        String resultado = legPress.toString();
        assertNotNull(resultado);
        assertTrue(resultado.contains("Leg press"));
    }
    
    @Test
    public void testEqualsComObjetoIgual() {
        LegPress outro = new LegPress(dataRealizacao, tempo, 120, 15, 80.0);
        assertTrue(legPress.equals(outro));
    }
    
    @Test
    public void testEqualsComMesmoObjeto() {
        assertTrue(legPress.equals(legPress));
    }
    
    @Test
    public void testEqualsComObjetoNull() {
        assertFalse(legPress.equals(null));
    }
    
    @Test
    public void testEqualsComClasseDiferente() {
        String outroObjeto = "teste";
        assertFalse(legPress.equals(outroObjeto));
    }
    
    @Test
    public void testEqualsComObjetoDiferente() {
        LegPress outro = new LegPress(dataRealizacao, tempo, 130, 15, 80.0);
        assertFalse(legPress.equals(outro));
    }
    
    @Test
    public void testClone() {
        Object clone = legPress.clone();
        assertNotNull(clone);
        assertTrue(clone instanceof LegPress);
        assertNotSame(legPress, clone);
        assertEquals(legPress, clone);
    }
}
