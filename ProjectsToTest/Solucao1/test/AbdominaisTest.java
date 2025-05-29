package test;

import Projeto.Abdominais;
import Projeto.Atividade;
import Projeto.Utilizador;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;
import java.time.*;


public class AbdominaisTest {

    private Abdominais abdominais;
    private Utilizador utilizador;

    @BeforeEach
    void setUp() {
        abdominais = new Abdominais(
            LocalDateTime.of(2024, 6, 1, 10, 0),
            LocalTime.of(0, 10, 0),
            80,
            100
        );
        utilizador = new Utilizador() {
            @Override public double getFatorMultiplicativo() { return 1.0; }
            @Override public double getBMR() { return 1800.0; }

            @Override
            public Object clone() {
                return null;
            }

            @Override public int getFreqCardiaca() { return 70; }

            @Override
            public Object utilizadorNumPeriodo(LocalDate dataInicio, LocalDate dataFim) {
                return null;
            }
        };
    }

    @Test
    void testConstrutorVazio() {
        Abdominais abd = new Abdominais();
        assertNotNull(abd);
        assertEquals(0, abd.getRepeticoes());
    }

    @Test
    void testConstrutorParametrizado() {
        assertEquals(LocalTime.of(0, 10, 0), abdominais.getTempo());
        assertEquals(80, abdominais.getFreqCardiaca());
        assertEquals(100, abdominais.getRepeticoes());
    }

    @Test
    void testConstrutorCopia() {
        Abdominais copia = new Abdominais(abdominais);
        assertEquals(abdominais, copia);
        assertNotSame(abdominais, copia);
    }

    @Test
    void testConsumoCalorias() {
        double calorias = abdominais.consumoCalorias(utilizador);
        assertTrue(calorias > 0);
    }

    @Test
    void testGeraAtividade() {
        double calorias = abdominais.consumoCalorias(utilizador);
        Atividade nova = abdominais.geraAtividade(utilizador, calorias);
        assertNotNull(nova);
        assertTrue(nova instanceof Abdominais);
        assertEquals(0, nova.getFreqCardiaca());
    }

    @Test
    void testToString() {
        String str = abdominais.toString();
        assertTrue(str.contains("Tipo de atividade: Abdominais"));
        assertTrue(str.contains("Repetiçoes: 100"));
    }

    @Test
    void testEquals() {
        Abdominais abd2 = new Abdominais(abdominais);
        assertEquals(abdominais, abd2);
        assertNotEquals(abdominais, null);
        assertNotEquals(abdominais, new Object());
    }

    @Test
    void testClone() {
        Object clone = abdominais.clone();
        assertTrue(clone instanceof Abdominais);
        assertEquals(abdominais, clone);
        assertNotSame(abdominais, clone);
    }
}