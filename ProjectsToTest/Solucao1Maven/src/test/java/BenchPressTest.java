import org.example.BenchPress;
import org.example.Utilizador;
import org.example.Atividade;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDateTime;
import java.time.LocalTime;
import static org.junit.Assert.*;

public class BenchPressTest {

    private BenchPress benchPressDefault;
    private BenchPress benchPressParam;
    private Utilizador utilizadorMock;

    @Before
    public void setUp() {
        benchPressDefault = new BenchPress();
        benchPressParam = new BenchPress(
                LocalDateTime.of(2024, 6, 1, 10, 0),
                LocalTime.of(0, 30, 0),
                120,
                60,
                80.0
        );
        utilizadorMock = new Utilizador() {
            @Override public double getFatorMultiplicativo() { return 1.0; }
            @Override public double getBMR() { return 1800.0; }
            @Override public double getPeso() { return 80.0; }
            @Override public int getFreqCardiaca() { return 120; }
            @Override public <T> java.util.List<T> infoDasAtividadesNumPeriodoQueRespeitamP(
                    java.time.LocalDate ini, java.time.LocalDate fim,
                    java.util.function.Predicate<Atividade> p,
                    java.util.function.Function<Atividade, T> f) {
                return (java.util.List<T>) java.util.Arrays.asList(100.0, 90.0);
            }
            @Override
            public Object clone() {
                return this; // ou return new ... se quiser um clone real
            }
            @Override
            public Object utilizadorNumPeriodo(java.time.LocalDate dataInicio, java.time.LocalDate dataFim) {
                return this;
            }
        };
    }

    @Test
    public void testConstrutorVazio() {
        assertNotNull(benchPressDefault);
        assertEquals(0, benchPressDefault.getPeso(), 0.001);
        assertEquals(0, benchPressDefault.getRepeticoes());
    }

    @Test
    public void testConstrutorParametrizado() {
        assertEquals(LocalDateTime.of(2024, 6, 1, 10, 0), benchPressParam.getDataRealizacao());
        assertEquals(LocalTime.of(0, 30, 0), benchPressParam.getTempo());
        assertEquals(120, benchPressParam.getFreqCardiaca());
        assertEquals(60, benchPressParam.getRepeticoes());
        assertEquals(80.0, benchPressParam.getPeso(), 0.001);
    }

    @Test
    public void testConstrutorCopia() {
        BenchPress copia = new BenchPress(benchPressParam);
        assertEquals(benchPressParam, copia);
        assertNotSame(benchPressParam, copia);
    }

    @Test
    public void testConsumoCalorias() {
        BenchPress bp = new BenchPress(
                LocalDateTime.now(),
                LocalTime.of(0, 20, 0),
                120,
                100,
                80.0
        );
        double calorias = bp.consumoCalorias(utilizadorMock);
        assertTrue(calorias > 0);
    }

    @Test
    public void testGeraAtividade() {
        double calorias = 200.0;
        Atividade atividade = benchPressDefault.geraAtividade(utilizadorMock, calorias);
        assertTrue(atividade instanceof BenchPress);
        assertEquals(0, atividade.getFreqCardiaca());
        assertTrue(((BenchPress)atividade).getPeso() > 0);
    }

    @Test
    public void testToString() {
        String str = benchPressParam.toString();
        assertTrue(str.contains("Bench press"));
        assertTrue(str.contains("Peso"));
        assertTrue(str.contains("Repetiçoes"));
    }

    @Test
    public void testEqualsAndHashCode() {
        BenchPress bp1 = new BenchPress(benchPressParam);
        BenchPress bp2 = new BenchPress(benchPressParam);
        assertTrue(bp1.equals(bp2));
        assertFalse(bp1.equals(null));
        assertFalse(bp1.equals("string"));
        assertTrue(bp1.equals(bp1));
    }

    @Test
    public void testClone() {
        BenchPress clone = (BenchPress) benchPressParam.clone();
        assertEquals(benchPressParam, clone);
        assertNotSame(benchPressParam, clone);
    }
}
