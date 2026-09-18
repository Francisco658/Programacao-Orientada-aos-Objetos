/*
package test;

import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;

import src.Artigo.Tshirt.Tshirt;

public class TshirtTest {

    @Test
    public void testClone() {
        Tshirt original = new Tshirt(Tshirt.Tamanho.M, Tshirt.Padrao.RISCAS, 10);
        Tshirt clone = original.clone();
        assertTrue(original != clone);
        Assert.assertEquals(original, clone);
    }

    @Test
    public void testConstructorAndGetters() {
        Tshirt tshirt1 = new Tshirt();
        assertNull(tshirt1.getTamanho());
        assertNull(tshirt1.getPadrao());
        Assert.assertEquals(0, tshirt1.getDesconto());

        Tshirt tshirt2 = new Tshirt(Tshirt.Tamanho.M, Tshirt.Padrao.RISCAS, 10);
        Assert.assertEquals(Tshirt.Tamanho.M, tshirt2.getTamanho());
        Assert.assertEquals(Tshirt.Padrao.RISCAS, tshirt2.getPadrao());
        Assert.assertEquals(10, tshirt2.getDesconto());
    }

    @Test
    public void testSetters() {
        Tshirt tshirt1 = new Tshirt();
        tshirt1.setTamanho(Tshirt.Tamanho.L);
        Assert.assertEquals(Tshirt.Tamanho.L, tshirt1.getTamanho());
        tshirt1.setPadrao(Tshirt.Padrao.PALMEIRAS);
        Assert.assertEquals(Tshirt.Padrao.PALMEIRAS, tshirt1.getPadrao());
        tshirt1.setDesconto(20);
        Assert.assertEquals(20, tshirt1.getDesconto());
    }

    @Test
    public void testEquals() {
        Tshirt tshirt1 = new Tshirt(Tshirt.Tamanho.M, Tshirt.Padrao.RISCAS, 10);
        Tshirt tshirt2 = new Tshirt(Tshirt.Tamanho.M, Tshirt.Padrao.RISCAS, 10);
        Tshirt tshirt3 = new Tshirt(Tshirt.Tamanho.M, Tshirt.Padrao.PALMEIRAS, 20);

        assertTrue(tshirt1.equals(tshirt2));
        assertFalse(tshirt1.equals(tshirt3));
        assertFalse(tshirt1.equals(null));
        assertFalse(tshirt1.equals(new Object()));
    }

    @Test
    public void testCalcularPreco() {
        Tshirt tshirt1 = new Tshirt(Tshirt.Tamanho.S, Tshirt.Padrao.LISO, 0);
        double expected1 = tshirt1.getPrecoBase();
        Assert.assertEquals(expected1, tshirt1.calcularPreco(), 0.001);

        Tshirt tshirt2 = new Tshirt(Tshirt.Tamanho.M, Tshirt.Padrao.RISCAS, 10);
        double expected2 = tshirt2.getPrecoBase() - (tshirt2.getPrecoBase() * (1 - (tshirt2.getDesconto()/100.0)));
        Assert.assertEquals(expected2, tshirt2.calcularPreco(), 0.001);

        Tshirt tshirt3 = new Tshirt(Tshirt.Tamanho.XL, Tshirt.Padrao.PALMEIRAS, 25);
        double expected3 = tshirt3.getPrecoBase() - (tshirt3.getPrecoBase() * (1 - (tshirt3.getDesconto()/100.0)));
        Assert.assertEquals(expected3, tshirt3.calcularPreco(), 0.001);
    }
}
*/
