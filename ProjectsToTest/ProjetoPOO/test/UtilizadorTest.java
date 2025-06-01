package test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.qql.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class UtilizadorTest {

    private Utilizador utilizador;

    @BeforeEach
    public void setUp() {
        utilizador = new Utilizador("João", "joao@mail.com", "Rua A", "123456789");
    }

    @Test
    public void testConstrutorVazio() {
        Utilizador u = new Utilizador();
        assertEquals("", u.getEmail());
        assertEquals("", u.getNome());
        assertEquals("", u.getMorada());
        assertEquals("", u.getNIF());
        assertEquals(0, u.getProdutosAVenda().size());
        assertEquals(0, u.getProdutosVendidos().size());
        assertEquals(0, u.getEncomendasFeitas().size());
        assertEquals(0, u.getFaturas().size());
        assertNull(u.getEncomenda());
        assertEquals(0.0f, u.getTotalVendas());
    }

    @Test
    public void testGetSet() {
        utilizador.setEmail("novo@mail.com");
        utilizador.setNome("Novo Nome");
        utilizador.setMorada("Nova Rua");
        utilizador.setNIF("987654321");
        utilizador.setTotalVendas(150.0f);

        assertEquals("novo@mail.com", utilizador.getEmail());
        assertEquals("Novo Nome", utilizador.getNome());
        assertEquals("Nova Rua", utilizador.getMorada());
        assertEquals("987654321", utilizador.getNIF());
        assertEquals(150.0f, utilizador.getTotalVendas());
    }

    @Test
    public void testAddRemoveArtigoProdutosAVenda() {
        Artigo artigo = new TShirt(); // supondo que TShirt herda Artigo
        utilizador.addArtigoProdutosAVenda(1, artigo);
        assertEquals(1, utilizador.getProdutosAVenda().size());

        utilizador.removeArtigoProdutosAVenda(1);
        assertEquals(0, utilizador.getProdutosAVenda().size());
    }

    @Test
    public void testAddRemoveArtigoProdutosVendidos() {
        Artigo artigo = new TShirt();
        artigo.setPrecoFinal(50.0f);

        utilizador.addArtigoProdutosVendidos(1, artigo, 2);
        assertEquals(1, utilizador.getProdutosVendidos().size());
        assertEquals(100.0f, utilizador.getTotalVendas());

        utilizador.removeArtigoProdutosVendidos(1);
        assertEquals(0, utilizador.getProdutosVendidos().size());
    }

    @Test
    public void testEncomendaLifecycle() {
        utilizador.setEncomenda();
        assertNotNull(utilizador.getEncomenda());

        utilizador.setEncomendaNull();
        assertNull(utilizador.getEncomenda());
    }

    @Test
    public void testAddRemoveEncomendaFeita() {
        Encomenda e = new Encomenda();
        utilizador.addEncomendaEncomendasFeitas(1, e);
        assertEquals(1, utilizador.getEncomendasFeitas().size());

        utilizador.removeEncomendaEncomendasFeitas(1);
        assertEquals(0, utilizador.getEncomendasFeitas().size());
    }

    @Test
    public void testAddFaturaAndPeriodoCalculations() {
        Encomenda e = new Encomenda();
        LocalDateTime now = LocalDateTime.now();

        Fatura f1 = new Fatura(100f, Fatura.Tipo.VENDA, e, now.minusDays(1), List.of(1));
        Fatura f2 = new Fatura(200f, Fatura.Tipo.COMPRA, e, now.minusDays(3), List.of(2));
        Fatura f3 = new Fatura(150f, Fatura.Tipo.VENDA, e, now.minusDays(2), List.of(3));

        utilizador.addFatura(f1);
        utilizador.addFatura(f2);
        utilizador.addFatura(f3);

        assertEquals(3, utilizador.getFaturas().size());

        LocalDateTime start = now.minusDays(3);
        LocalDateTime end = now;

        int vendas = utilizador.numeroVendasEfetuadasDuranteUmPeriodoTempo(start, end);
        float total = utilizador.getTotalVendasPeriodo(start, end);

        assertEquals(2, vendas);
        assertEquals(250.0f, total);
    }

    @Test
    public void testClone() {
        Utilizador clone = utilizador.clone();
        assertNotSame(utilizador, clone);
        assertEquals(utilizador.getEmail(), clone.getEmail());
    }

    @Test
    public void testSetProdutosAVendaAndVendidos() {
        TShirt tshirt = new TShirt();
        Sapatilha sap = new Sapatilha();
        Mala mala = new Mala();

        Map<Integer, Artigo> mapa = new HashMap<>();
        mapa.put(1, tshirt);
        mapa.put(2, sap);
        mapa.put(3, mala);

        utilizador.setProdutosAVenda(mapa);
        utilizador.setProdutosVendidos(mapa);

        assertEquals(3, utilizador.getProdutosAVenda().size());
        assertEquals(3, utilizador.getProdutosVendidos().size());
    }

    @Test
    public void testAdicionarRemoverArtigoEncomenda() {
        utilizador.setEncomenda();

        utilizador.addArtigoEncomenda(8, 2);
        assertTrue(utilizador.getEncomenda().getArtigos().containsKey(8));

        utilizador.removeArtigoEncomenda(8);
        assertFalse(utilizador.getEncomenda().getArtigos().containsKey(8));
    }
}
