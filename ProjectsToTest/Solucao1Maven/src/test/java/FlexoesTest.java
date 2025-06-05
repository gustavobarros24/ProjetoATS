import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import org.example.AtivRepeticoes;
import org.example.Atividade;
import org.example.Flexoes;
import org.example.UtilizadorAmador;
import org.example.UtilizadorProfissional;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;

public class FlexoesTest {
    
    private Flexoes flexoes;
    private UtilizadorAmador utilizadorAmador;
    private UtilizadorProfissional utilizadorProfissional;
    private LocalDateTime dataRealizacao;
    private LocalTime tempo;
    
    @Before
    public void setUp() {
        dataRealizacao = LocalDateTime.of(2024, 5, 9, 10, 30);
        tempo = LocalTime.of(0, 15, 0); // 15 minutes
        flexoes = new Flexoes();
        
        utilizadorAmador = new UtilizadorAmador("João", "Rua A", "joao@email.com", 
                                               70, 75, 180, LocalDate.of(1990, 1, 1), 'M');
        utilizadorProfissional = new UtilizadorProfissional("Maria", "Rua B", "maria@email.com", 
                                                           80, 65, 170, LocalDate.of(1985, 5, 15), 'F');
    }
    
    @Test
    public void testConstructorVazio() {
        Flexoes f = new Flexoes();
        assertNotNull(f);
    }
    
    @Test
    public void testConstructorParametrizado() {
        Flexoes f = new Flexoes(dataRealizacao, tempo, 80, 50);
        assertNotNull(f);
        assertEquals(tempo, f.getTempo());
        assertEquals(80, f.getFreqCardiaca());
        assertEquals(50, f.getRepeticoes());
        assertEquals(dataRealizacao, f.getDataRealizacao());
    }
    
    @Test
    public void testConstructorCopia() {
        Flexoes original = new Flexoes(dataRealizacao, tempo, 80, 50);
        Flexoes copia = new Flexoes(original);
        
        assertNotNull(copia);
        assertEquals(original.getTempo(), copia.getTempo());
        assertEquals(original.getFreqCardiaca(), copia.getFreqCardiaca());
        assertEquals(original.getRepeticoes(), copia.getRepeticoes());
        assertEquals(original.getDataRealizacao(), copia.getDataRealizacao());
        assertNotSame(original, copia);
    }
    
    @Test
    public void testConsumoCalorias() {
        Flexoes f = new Flexoes(dataRealizacao, tempo, 80, 50);
        double calorias = f.consumoCalorias(utilizadorAmador);
        
        assertTrue(calorias > 0);
        assertNotEquals(Double.NaN, calorias);
        assertNotEquals(Double.POSITIVE_INFINITY, calorias);
    }
    
    @Test
    public void testConsumoCaloriasUtilizadorProfissional() {
        Flexoes f = new Flexoes(dataRealizacao, tempo, 80, 50);
        double caloriasAmador = f.consumoCalorias(utilizadorAmador);
        double caloriasProfissional = f.consumoCalorias(utilizadorProfissional);
        
        // Profissional deve queimar mais calorias devido ao fator multiplicativo maior
        assertTrue(caloriasProfissional > caloriasAmador);
    }
    
    @Test
    public void testGeraAtividade() {
        double consumoCalorias = 100.0;
        Atividade atividade = flexoes.geraAtividade(utilizadorAmador, consumoCalorias);
        
        assertNotNull(atividade);
        assertTrue(atividade instanceof Flexoes);
        assertNotNull(atividade.getTempo());
        assertEquals(0, atividade.getFreqCardiaca());
        assertTrue(((AtivRepeticoes)atividade).getRepeticoes() >= 0);
    }
    
    @Test
    public void testGeraAtividadeComValorZero() {
        double consumoCalorias = 0.0;
        Atividade atividade = flexoes.geraAtividade(utilizadorAmador, consumoCalorias);
        
        assertNotNull(atividade);
        assertTrue(atividade instanceof Flexoes);
        assertEquals(0, ((AtivRepeticoes)atividade).getRepeticoes());
    }
    
    @Test
    public void testToString() {
        Flexoes f = new Flexoes(dataRealizacao, tempo, 80, 50);
        String resultado = f.toString();
        
        assertNotNull(resultado);
        assertTrue(resultado.contains("Flexões"));
        assertTrue(resultado.contains("Tipo de atividade: Flexões"));
    }
    
    @Test
    public void testEquals() {
        Flexoes f1 = new Flexoes(dataRealizacao, tempo, 80, 50);
        Flexoes f2 = new Flexoes(dataRealizacao, tempo, 80, 50);
        Flexoes f3 = new Flexoes(dataRealizacao, LocalTime.of(0, 20, 0), 80, 50);
        
        // Reflexividade
        assertTrue(f1.equals(f1));
        
        // Simetria
        assertTrue(f1.equals(f2));
        assertTrue(f2.equals(f1));
        
        // Diferentes objetos
        assertFalse(f1.equals(f3));
        
        // Null
        assertFalse(f1.equals(null));
        
        // Classe diferente
        assertFalse(f1.equals("string"));
    }
    
    @Test
    public void testClone() {
        Flexoes original = new Flexoes(dataRealizacao, tempo, 80, 50);
        Flexoes clonado = (Flexoes) original.clone();
        
        assertNotNull(clonado);
        assertTrue(original.equals(clonado));
        assertNotSame(original, clonado);
        assertEquals(original.getTempo(), clonado.getTempo());
        assertEquals(original.getFreqCardiaca(), clonado.getFreqCardiaca());
        assertEquals(original.getRepeticoes(), clonado.getRepeticoes());
    }
    
    @Test
    public void testMETConstant() {
        // Verifica se a constante MET é acessível através do comportamento da classe
        Flexoes f = new Flexoes(dataRealizacao, tempo, 80, 50);
        double calorias1 = f.consumoCalorias(utilizadorAmador);
        double calorias2 = f.consumoCalorias(utilizadorAmador);
        
        // Se MET é constante, o resultado deve ser o mesmo para os mesmos parâmetros
        assertEquals(calorias1, calorias2, 0.001);
    }
}
