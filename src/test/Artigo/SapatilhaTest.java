/*
package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.time.LocalDate;

import org.junit.Test;
import src.Artigo.Sapatilha.Sapatilha;

public class SapatilhaTest {

    @Test
    public void testClone() {
        Sapatilha sapatilha1 = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        Sapatilha sapatilha2 = sapatilha1.clone();
        assertTrue(sapatilha1.equals(sapatilha2));
        assertFalse(sapatilha1 == sapatilha2);
    }

    @Test
    public void testConstructorAndGetters() {
        Sapatilha sapatilha = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        assertEquals(42, sapatilha.getTamanho());
        assertTrue(sapatilha.isAtacadores());
        assertEquals(Color.RED, sapatilha.getCor());
        assertEquals(LocalDate.of(2022, 1, 1), sapatilha.getDataLancamento());
        assertTrue(sapatilha.isPremium());
        assertEquals(1.5f, sapatilha.getValorizacao(), 0.01);
    }

    @Test
    public void testEquals() {
        Sapatilha sapatilha1 = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        Sapatilha sapatilha2 = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        Sapatilha sapatilha3 = new Sapatilha(41, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        assertTrue(sapatilha1.equals(sapatilha2));
        assertFalse(sapatilha1.equals(sapatilha3));
    }

    @Test
    public void testCalcularPreco() {
        Sapatilha sapatilha1 = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        float precoBase = 100f;
        int numeroDonos = 2;
        int estadoUtilizacao = 1;
        float precoFinal = sapatilha1.calcularPreco(precoBase, numeroDonos, estadoUtilizacao);
        assertEquals(50f, precoFinal, 0.01);
    }

    @Test
    public void testCompararDataLancamento() {
        Sapatilha sapatilha1 = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        Sapatilha sapatilha2 = new Sapatilha(42, true, Color.RED, LocalDate.of(2023, 1, 1), true, 1.5f);
        int comparacao = sapatilha1.compararDataLancamento(sapatilha2);
        assertTrue(comparacao < 0);
    }

    @Test
    public void testCompararTamanho() {
        Sapatilha sapatilha1 = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        Sapatilha sapatilha2 = new Sapatilha(41, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        int comparacao = sapatilha1.compararTamanho(sapatilha2);
        assertTrue(comparacao > 0);
    }

    @Test
    public void testValidarDados() {
        Sapatilha sapatilha1 = new Sapatilha(42, true, Color.RED, LocalDate.of(2022, 1, 1), true, 1.5f);
        assertTrue(sapatilha1.validarDados());
        sapatilha1.setTamanho(31);
        assertFalse(sapatilha1.validarDados());
        sapatilha1.setTamanho(42);
        sapatilha1.setCor(null);
        assertFalse(sapatilha1.validarDados());
        sapatilha1.setCor(new Color(0, 0, 255));
        assertTrue(sapatilha1.validarDados());
    }
}*/
