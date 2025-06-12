package Vintage.qql;

import net.jqwik.api.*;
import net.jqwik.api.constraints.*;
import java.time.LocalDate;
import java.util.Random;
import static org.junit.jupiter.api.Assertions.*;

class SapatilhaProperties {

    private String randomString(Random random, int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append((char) (random.nextInt(26) + 'a'));
        }
        return sb.toString();
    }

    private Sapatilha createDefaultSapatilha() {
        return new Sapatilha(
                "Test", "Description", "Brand", 123,
                100.0f, 0.0f, true, 0,
                "New", "UPS", 10, "Seller",
                42, true, "Black", 2020, false
        );
    }

    @Provide
    Arbitrary<Sapatilha> randomSapatilha() {
        return Arbitraries.randomValue(random -> {
            Sapatilha s = createDefaultSapatilha();
            s.setNome(randomString(random, 10));
            s.setDescricao(randomString(random, 20));
            s.setMarca(randomString(random, 10));
            s.setCodigo(random.nextInt(1000));
            s.setPrecoBase(random.nextFloat() * 1000);
            s.setCorrecaoPreco(random.nextFloat() * 100);
            s.setNovo(random.nextBoolean());
            s.setNumDonos(random.nextInt(10));
            s.setCondicao(random.nextBoolean() ? "New" : "Used");
            s.setTransportadora(randomString(random, 5));
            s.setStock(random.nextInt(100));
            s.setVendedor(randomString(random, 8));
            s.setTamanho(30 + random.nextInt(21)); // 30-50
            s.setAtacadores(random.nextBoolean());
            s.setCor(randomString(random, 5));
            s.setAnoLancamento(2000 + random.nextInt(24)); // 2000-2023
            s.setPremium(random.nextBoolean());
            return s;
        });
    }

    @Property
    void constructorShouldSetAllFields(@ForAll("randomSapatilha") Sapatilha sapatilha) {
        assertNotNull(sapatilha.getNome());
        assertNotNull(sapatilha.getDescricao());
        assertNotNull(sapatilha.getMarca());
        assertTrue(sapatilha.getCodigo() >= 0);
        assertTrue(sapatilha.getPrecoBase() >= 0);
        assertTrue(sapatilha.getNumDonos() >= 0);
        assertNotNull(sapatilha.getCondicao());
        assertNotNull(sapatilha.getTransportadora());
        assertTrue(sapatilha.getStock() >= 0);
        assertNotNull(sapatilha.getVendedor());
        assertTrue(sapatilha.getTamanho() >= 30 && sapatilha.getTamanho() <= 50);
        assertNotNull(sapatilha.getCor());
        assertTrue(sapatilha.getAnoLancamento() >= 2000 && sapatilha.getAnoLancamento() <= 2023);
    }

    @Property
    void copyConstructorShouldCreateEqualObject(@ForAll("randomSapatilha") Sapatilha original) {
        Sapatilha copy = new Sapatilha(original);
        assertEquals(original, copy);
        assertNotSame(original, copy);
    }

    @Provide
    Arbitrary<Sapatilha> usedSapatilha() {
        return randomSapatilha()
                .filter(s -> !s.isPremium() && s.getNumDonos() > 0);
    }

    @Property
    void priceCalculationForNonPremiumShouldDecreaseWithOwners(@ForAll("usedSapatilha") Sapatilha sapatilha) {
        float expected = sapatilha.getPrecoBase() - (sapatilha.getPrecoBase() / sapatilha.getNumDonos());
        assertEquals(expected, sapatilha.calcularPrecoFinal());
    }

    @Provide
    Arbitrary<Sapatilha> premiumSapatilha() {
        return randomSapatilha()
                .filter(Sapatilha::isPremium);
    }

    @Property
    void priceCalculationForPremiumShouldIncreaseWithAge(@ForAll("premiumSapatilha") Sapatilha sapatilha) {
        int anosDiferenca = LocalDate.now().getYear() - sapatilha.getAnoLancamento();
        float expected = sapatilha.getPrecoBase() + (float) anosDiferenca * 2.0f;
        assertEquals(expected, sapatilha.calcularPrecoFinal());
    }
}