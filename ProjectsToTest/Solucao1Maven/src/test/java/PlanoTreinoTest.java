import org.example.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.ArrayList;
import java.util.function.Predicate;

public class PlanoTreinoTest {
    
    private PlanoTreino plano;
    private PlanoTreino planoComData;
    private Corrida corrida;
    private Flexoes flexoes;
    private Trail trail;
    private UtilizadorAmador utilizador;
    private LocalDate dataTest;
    
    @Before
    public void setUp() {
        plano = new PlanoTreino();
        dataTest = LocalDate.of(2024, 5, 15);
        planoComData = new PlanoTreino(dataTest);
        
        // Create test activities
        corrida = new Corrida(LocalDateTime.of(2024, 5, 15, 10, 0), LocalTime.of(1, 0), 132, 10.0);
        flexoes = new Flexoes(LocalDateTime.of(2024, 5, 16, 9, 0), LocalTime.of(0, 30), 100, 50);
        trail = new Trail();

        utilizador = new UtilizadorAmador("João", "Porto", "joao@email.com", 140, 75, 180, LocalDate.of(1990, 5, 15), 'M');
    }

    @Test
    public void testConstructorVazio() {
        PlanoTreino p = new PlanoTreino();
        assertEquals(0, p.getCodPlano());
        assertEquals(LocalDate.now(), p.getDataRealizacao());
        assertTrue(p.getAtividades().isEmpty());
    }
    
    @Test
    public void testConstructorComData() {
        LocalDate data = LocalDate.of(2024, 6, 1);
        PlanoTreino p = new PlanoTreino(data);
        assertTrue(p.getCodPlano() > 0);
        assertEquals(data, p.getDataRealizacao());
        assertTrue(p.getAtividades().isEmpty());
    }
    
    @Test
    public void testConstructorCopia() {
        planoComData.addAtividade(corrida, 3);
        PlanoTreino copia = new PlanoTreino(planoComData);
        
        assertEquals(planoComData.getCodPlano(), copia.getCodPlano());
        assertEquals(planoComData.getDataRealizacao(), copia.getDataRealizacao());
        assertEquals(planoComData.getAtividades().size(), copia.getAtividades().size());
    }
    
    @Test
    public void testConstructorComPeriodo() {
        planoComData.addAtividade(corrida, 2);
        LocalDate inicio = LocalDate.of(2024, 5, 14);
        LocalDate fim = LocalDate.of(2024, 5, 16);
        
        PlanoTreino planoFiltrado = new PlanoTreino(planoComData, inicio, fim);
        
        assertEquals(planoComData.getCodPlano(), planoFiltrado.getCodPlano());
        assertEquals(planoComData.getDataRealizacao(), planoFiltrado.getDataRealizacao());
    }
    
    @Test
    public void testSetProximoCodigo() {
        plano.setProximoCodigo(100);
        PlanoTreino novoPlano = new PlanoTreino(LocalDate.now());
        assertEquals(100, novoPlano.getCodPlano());
    }
    
    @Test
    public void testGettersSetters() {
        LocalDate novaData = LocalDate.of(2024, 7, 1);
        plano.setDataRealizacao(novaData);
        assertEquals(novaData, plano.getDataRealizacao());
    }
    
    @Test
    public void testAddAtividade() {
        assertTrue(plano.getAtividades().isEmpty());
        
        plano.addAtividade(corrida, 5);
        assertEquals(1, plano.getAtividades().size());
        assertEquals(5, plano.getAtividades().get(0).getIteracoes());
    }
    
    @Test
    public void testGetAtividades() {
        plano.addAtividade(corrida, 3);
        plano.addAtividade(flexoes, 2);
        
        List<PlanoTreino.AtividadeIteracoes> atividades = plano.getAtividades();
        assertEquals(2, atividades.size());
        
        // Test defensive copy
        atividades.clear();
        assertEquals(2, plano.getAtividades().size());
    }
    
    @Test
    public void testGetAtividadesNumPeriodo() {
        LocalDate inicio = LocalDate.of(2024, 5, 10);
        LocalDate fim = LocalDate.of(2024, 5, 28);
        
        plano.addAtividade(corrida, 2); // dentro do período
        plano.addAtividade(flexoes, 1); // dentro do período
        
        List<PlanoTreino.AtividadeIteracoes> resultado = plano.getAtividadesNumPeriodo(inicio, fim);
        assertEquals(2, resultado.size());
    }
    
    @Test
    public void testGetAtividadesNumPeriodoForaPeriodo() {
        LocalDate inicio = LocalDate.of(2024, 6, 1);
        LocalDate fim = LocalDate.of(2024, 6, 30);
        
        plano.addAtividade(corrida, 2); // fora do período
        
        List<PlanoTreino.AtividadeIteracoes> resultado = plano.getAtividadesNumPeriodo(inicio, fim);
        assertEquals(0, resultado.size());
    }
    
    @Test
    public void testAtividadesQueRespeitamP() {
        LocalDate inicio = LocalDate.of(2024, 5, 14);
        LocalDate fim = LocalDate.of(2024, 5, 16);
        
        plano.addAtividade(corrida, 2);
        plano.addAtividade(flexoes, 3);
        
        Predicate<Atividade> predicado = atividade -> atividade instanceof Corrida;
        List<Atividade> resultado = plano.atividadesQueRespeitamP(inicio, fim, predicado);
        
        assertEquals(2, resultado.size()); // 2 iterações de corrida
    }
    
    @Test
    public void testCaloriasDispendidas() {
        plano.addAtividade(corrida, 2);
        plano.addAtividade(flexoes, 1);
        
        double calorias = plano.caloriasDispendidas(utilizador);
        assertTrue(calorias > 0);
    }
    
    @Test
    public void testCompareTo() {
        LocalDate data1 = LocalDate.of(2024, 5, 15);
        LocalDate data2 = LocalDate.of(2024, 5, 16);
        
        PlanoTreino p1 = new PlanoTreino(data1);
        PlanoTreino p2 = new PlanoTreino(data2);
        
        assertTrue(p1.compareTo(p2) < 0);
        assertTrue(p2.compareTo(p1) > 0);
        
        // Test same date, different codes
        PlanoTreino p3 = new PlanoTreino(data1);
        assertNotEquals(0, p1.compareTo(p3));
    }
    
    @Test
    public void testClone() {
        plano.addAtividade(corrida, 3);
        PlanoTreino clone = (PlanoTreino) plano.clone();
        
        assertEquals(plano.getCodPlano(), clone.getCodPlano());
        assertEquals(plano.getDataRealizacao(), clone.getDataRealizacao());
        assertEquals(plano.getAtividades().size(), clone.getAtividades().size());
    }
    
    @Test
    public void testPlanoTreinoNumPeriodo() {
        LocalDate inicio = LocalDate.of(2024, 5, 14);
        LocalDate fim = LocalDate.of(2024, 5, 16);
        
        plano.addAtividade(corrida, 2);
        PlanoTreino resultado = (PlanoTreino) plano.planoTreinoNumPeriodo(inicio, fim);
        
        assertNotNull(resultado);
        assertEquals(plano.getCodPlano(), resultado.getCodPlano());
    }
    
    @Test
    public void testToString() {
        plano.addAtividade(corrida, 2);
        String resultado = plano.toString();
        
        assertTrue(resultado.contains("Plano de Treino"));
        assertTrue(resultado.contains("Codigo de Plano de Treino"));
        assertTrue(resultado.contains("Data de realizaçao"));
        assertTrue(resultado.contains("Atividades e suas iteraçoes"));
    }
    
    @Test
    public void testGeraPlanoTreino() {
        List<Atividade> atividades = new ArrayList<>();
        atividades.add(corrida);
        atividades.add(flexoes);
        atividades.add(trail);
        
        List<PlanoTreino> planos = plano.geraPlanoTreino(
            utilizador, 
            atividades, 
            2, // maxAtivDia
            5, // ativPorSemana
            1000.0, // consumoCaloricoMinimo
            LocalDate.of(2024, 5, 20)
        );
        
        assertFalse(planos.isEmpty());
        assertTrue(planos.size() <= 7); // máximo uma semana
    }
    
    @Test
    public void testGeraPlanoTreinoComMuitasAtividadesHard() {
        List<Atividade> atividades = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            atividades.add(new Trail());
        }
        atividades.add(corrida);
        
        List<PlanoTreino> planos = plano.geraPlanoTreino(
            utilizador, 
            atividades, 
            2,
            6,
            1000.0,
            LocalDate.of(2024, 5, 20)
        );
        
        assertFalse(planos.isEmpty());
    }
    
    @Test
    public void testGeraPlanoTreinoUmaAtividadeHard() {
        List<Atividade> atividades = new ArrayList<>();
        atividades.add(trail);
        atividades.add(corrida);
        atividades.add(flexoes);
        
        List<PlanoTreino> planos = plano.geraPlanoTreino(
            utilizador, 
            atividades, 
            1,
            3,
            500.0,
            LocalDate.of(2024, 5, 20)
        );
        
        assertFalse(planos.isEmpty());
    }
    
    // Inner class AtividadeIteracoes tests
    @Test
    public void testAtividadeIteracoesConstructor() {
        PlanoTreino.AtividadeIteracoes ai = plano.new AtividadeIteracoes(5, corrida);
        assertEquals(5, ai.getIteracoes());
        assertNotNull(ai.getAtividade());
    }
    
    @Test
    public void testAtividadeIteracoesCopyConstructor() {
        PlanoTreino.AtividadeIteracoes original = plano.new AtividadeIteracoes(3, corrida);
        PlanoTreino.AtividadeIteracoes copia = plano.new AtividadeIteracoes(original);
        
        assertEquals(original.getIteracoes(), copia.getIteracoes());
        assertEquals(original.getAtividade().getClass(), copia.getAtividade().getClass());
    }
    
    @Test
    public void testAtividadeIteracoesSetters() {
        PlanoTreino.AtividadeIteracoes ai = plano.new AtividadeIteracoes(2, corrida);
        
        ai.setIteracoes(10);
        assertEquals(10, ai.getIteracoes());
        
        ai.setAtividade(flexoes);
        assertTrue(ai.getAtividade() instanceof Flexoes);
    }
    
    @Test
    public void testAtividadeIteracoesClone() {
        PlanoTreino.AtividadeIteracoes original = plano.new AtividadeIteracoes(4, corrida);
        PlanoTreino.AtividadeIteracoes clone = (PlanoTreino.AtividadeIteracoes) original.clone();
        
        assertEquals(original.getIteracoes(), clone.getIteracoes());
        assertEquals(original.getAtividade().getClass(), clone.getAtividade().getClass());
    }
    
    @Test
    public void testAtividadeIteracoesToString() {
        PlanoTreino.AtividadeIteracoes ai = plano.new AtividadeIteracoes(3, corrida);
        String resultado = ai.toString();
        
        assertTrue(resultado.contains("Iteracoes: 3"));
    }
    
    @Test
    public void testAtividadeIteracoesEquals() {
        PlanoTreino.AtividadeIteracoes ai1 = plano.new AtividadeIteracoes(3, corrida);
        PlanoTreino.AtividadeIteracoes ai2 = plano.new AtividadeIteracoes(3, corrida);
        PlanoTreino.AtividadeIteracoes ai3 = plano.new AtividadeIteracoes(2, corrida);
        
        assertTrue(ai1.equals(ai1)); // same object
        assertTrue(ai1.equals(ai2)); // same values
        assertFalse(ai1.equals(ai3)); // different iterations
        assertFalse(ai1.equals(null)); // null
        assertFalse(ai1.equals("string")); // different class
    }
}
