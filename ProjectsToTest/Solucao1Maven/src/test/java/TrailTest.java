import org.example.*;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.LocalDate;


public class TrailTest {
    
    private Trail trail;
    private UtilizadorAmador utilizadorAmador;
    private UtilizadorProfissional utilizadorProfissional;
    private UtilizadorPraticanteOcasional utilizadorPraticante;
    
    @Before
    public void setUp() {
        trail = new Trail();
        utilizadorAmador = new UtilizadorAmador("João", "Rua A", "joao@email.com", 70, 75, 180, 
                                               LocalDate.of(1990, 1, 1), 'M');
        utilizadorProfissional = new UtilizadorProfissional("Maria", "Rua B", "maria@email.com", 65, 60, 165,
                                                           LocalDate.of(1995, 5, 15), 'F');
        utilizadorPraticante = new UtilizadorPraticanteOcasional("Pedro", "Rua C", "pedro@email.com", 80, 85, 175,
                                                                LocalDate.of(1985, 3, 10), 'M');
    }
    
    @Test
    public void testConstrutorVazio() {
        Trail t = new Trail();
        assertNotNull(t);
    }
    
    @Test
    public void testConstrutorParametrizado() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 500.0);
        
        assertEquals(data, t.getDataRealizacao());
        assertEquals(tempo, t.getTempo());
        assertEquals(150, t.getFreqCardiaca());
        assertEquals(10.5, t.getDistancia(), 0.001);
        assertEquals(500.0, t.getAltimetria(), 0.001);
    }
    
    @Test
    public void testConstrutorCopia() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail original = new Trail(data, tempo, 150, 10.5, 500.0);
        Trail copia = new Trail(original);
        
        assertEquals(original.getDataRealizacao(), copia.getDataRealizacao());
        assertEquals(original.getTempo(), copia.getTempo());
        assertEquals(original.getFreqCardiaca(), copia.getFreqCardiaca());
        assertEquals(original.getDistancia(), copia.getDistancia(), 0.001);
        assertEquals(original.getAltimetria(), copia.getAltimetria(), 0.001);
    }
    
    @Test
    public void testGetFatorHardAltimetriaBaixa() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 500.0);
        
        assertEquals(1.15, t.getFatorHard(), 0.001);
    }
    
    @Test
    public void testGetFatorHardAltimetriaMedia() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 1500.0);
        
        assertEquals(1.25, t.getFatorHard(), 0.001);
    }
    
    @Test
    public void testGetFatorHardAltimetriaAlta() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 2500.0);
        
        assertEquals(1.35, t.getFatorHard(), 0.001);
    }
    
    @Test
    public void testGetFatorHardAltimetriaLimite1000() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 1000.0);
        
        assertEquals(1.15, t.getFatorHard(), 0.001);
    }
    
    @Test
    public void testGetFatorHardAltimetriaLimite2000() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 2000.0);
        
        assertEquals(1.25, t.getFatorHard(), 0.001);
    }
    
    @Test
    public void testConsumoCalorias() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 0, 0);
        Trail t = new Trail(data, tempo, 150, 10.0, 1000.0);
        
        double consumo = t.consumoCalorias(utilizadorAmador);
        assertTrue(consumo > 0);
    }
    
    @Test
    public void testGeraAtividade() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 0, 0);
        Trail t = new Trail(data, tempo, 150, 10.0, 1000.0);
        
        Atividade atividade = t.geraAtividade(utilizadorAmador, 500.0);
        assertNotNull(atividade);
        assertTrue(atividade instanceof Trail);
        assertEquals(0, atividade.getFreqCardiaca());
    }
    
    @Test
    public void testToString() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 500.0);
        
        String resultado = t.toString();
        assertTrue(resultado.contains("Trail"));
        assertTrue(resultado.contains("Tipo de atividade: Trail"));
    }
    
    @Test
    public void testEqualsIgual() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t1 = new Trail(data, tempo, 150, 10.5, 500.0);
        Trail t2 = new Trail(data, tempo, 150, 10.5, 500.0);
        
        assertTrue(t1.equals(t2));
    }
    
    @Test
    public void testEqualsMesmoObjeto() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 500.0);
        
        assertTrue(t.equals(t));
    }
    
    @Test
    public void testEqualsObjetoNulo() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 500.0);
        
        assertFalse(t.equals(null));
    }
    
    @Test
    public void testEqualsClasseDiferente() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail t = new Trail(data, tempo, 150, 10.5, 500.0);
        String outro = "diferente";
        
        assertFalse(t.equals(outro));
    }
    
    @Test
    public void testClone() {
        LocalDateTime data = LocalDateTime.of(2024, 5, 10, 10, 30);
        LocalTime tempo = LocalTime.of(1, 30, 0);
        Trail original = new Trail(data, tempo, 150, 10.5, 500.0);
        
        Trail clone = (Trail) original.clone();
        
        assertNotSame(original, clone);
        assertEquals(original, clone);
        assertEquals(original.getDataRealizacao(), clone.getDataRealizacao());
        assertEquals(original.getTempo(), clone.getTempo());
        assertEquals(original.getFreqCardiaca(), clone.getFreqCardiaca());
        assertEquals(original.getDistancia(), clone.getDistancia(), 0.001);
        assertEquals(original.getAltimetria(), clone.getAltimetria(), 0.001);
    }
}