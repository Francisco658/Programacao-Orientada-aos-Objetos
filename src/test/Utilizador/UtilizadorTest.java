package test.Utilizador;

import static org.junit.Assert.*;
import org.junit.Test;
import src.Utilizador.Utilizador;

public class UtilizadorTest {

    @Test
    public void testConstrutorOmissao() {
        Utilizador utilizador = new Utilizador();
        assertEquals("", utilizador.getEmail());
        assertEquals("", utilizador.getPassword());
        assertEquals("", utilizador.getNome());
        assertEquals("", utilizador.getMorada());
        assertEquals(0, utilizador.getNumeroFiscal());
    }

    @Test
    public void testConstrutorParametrizado() {
        Utilizador utilizador = new Utilizador("joao@example.com", "password", "João", "Rua das Flores", 123456789);
        assertEquals("joao@example.com", utilizador.getEmail());
        assertEquals("password", utilizador.getPassword());
        assertEquals("João", utilizador.getNome());
        assertEquals("Rua das Flores", utilizador.getMorada());
        assertEquals(123456789L, utilizador.getNumeroFiscal());
    }

    @Test
    public void testConstrutorCopia() {
        Utilizador utilizador1 = new Utilizador("joao@example.com", "password", "João", "Rua das Flores", 123456789);
        Utilizador utilizador2 = new Utilizador(utilizador1);
        assertEquals(utilizador1.getEmail(), utilizador2.getEmail());
        assertEquals(utilizador1.getPassword(), utilizador2.getPassword());
        assertEquals(utilizador1.getNome(), utilizador2.getNome());
        assertEquals(utilizador1.getMorada(), utilizador2.getMorada());
        assertEquals(utilizador1.getNumeroFiscal(), utilizador2.getNumeroFiscal());
        assertNotSame(utilizador1, utilizador2);
    }

    @Test
    public void testSetEmail() {
        Utilizador utilizador = new Utilizador();
        utilizador.setEmail("joao@example.com");
        assertEquals("joao@example.com", utilizador.getEmail());
    }

    @Test
    public void testSetPassword() {
        Utilizador utilizador = new Utilizador();
        utilizador.setPassword("password");
        assertEquals("password", utilizador.getPassword());
    }

    @Test
    public void testSetNome() {
        Utilizador utilizador = new Utilizador();
        utilizador.setNome("João");
        assertEquals("João", utilizador.getNome());
    }

    @Test
    public void testSetMorada() {
        Utilizador utilizador = new Utilizador();
        utilizador.setMorada("Rua das Flores");
        assertEquals("Rua das Flores", utilizador.getMorada());
    }

    @Test
    public void testSetNumeroFiscal() {
        Utilizador utilizador = new Utilizador();
        utilizador.setNumeroFiscal(123456789);
        assertEquals(123456789, utilizador.getNumeroFiscal());
    }

    @Test
    public void testClone() {
        Utilizador utilizador1 = new Utilizador("joao@example.com", "password", "João", "Rua das Flores", 123456789);
        Utilizador utilizador2 = utilizador1.clone();
        assertEquals(utilizador1.getEmail(), utilizador2.getEmail());
        assertEquals(utilizador1.getPassword(), utilizador2.getPassword());
        assertEquals(utilizador1.getNome(), utilizador2.getNome());
        assertEquals(utilizador1.getMorada(), utilizador2.getMorada());
        assertEquals(utilizador1.getNumeroFiscal(), utilizador2.getNumeroFiscal());
        assertNotSame(utilizador1, utilizador2);
    }

//    @Test
//    public void testToString() {
//        Utilizador utilizador = new Utilizador("joao@example.com", "password", "João", "Rua das Flores", 123456789);
//        String expected = "Nome: João, Código: " + utilizador.getCodigo() + ", Email: joao@example.com, Morada: Rua das Flores, Número Fiscal:123456789, ";
//        assertEquals(expected, utilizador.toString());
//    }

    @Test
    public void testEquals() {
        Utilizador utilizador1 = new Utilizador("joao@example.com", "password", "João", "Rua das Flores", 123456789);
        Utilizador utilizador2 = new Utilizador("joao@example.com", "password", "João", "Rua das Flores", 123456789);
        Utilizador utilizador3 = new Utilizador("maria@example.com", "password", "Maria", "Rua do Sol", 987654321);
        assertTrue(utilizador1.equals(utilizador2));
        assertFalse(utilizador1.equals(utilizador3));
    }
}
