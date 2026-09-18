package test;

import static org.junit.Assert.*;
import org.junit.Test;
import src.Admin;

public class AdminTest {

    @Test
    public void testConstrutorOmissao() {
        Admin admin = new Admin();
        assertEquals("", admin.getNome());
        assertEquals("", admin.getEmail());
        assertEquals("", admin.getPassword());
    }

    @Test
    public void testConstrutorParametrizado() {
        Admin admin = new Admin("Maria", "maria@example.com", "password");
        assertEquals("Maria", admin.getNome());
        assertEquals("maria@example.com", admin.getEmail());
        assertEquals("password", admin.getPassword());
    }

    @Test
    public void testConstrutorCopia() {
        Admin admin1 = new Admin("Maria", "maria@example.com", "password");
        Admin admin2 = new Admin(admin1);
        assertEquals(admin1.getNome(), admin2.getNome());
        assertEquals(admin1.getEmail(), admin2.getEmail());
        assertEquals(admin1.getPassword(), admin2.getPassword());
    }

    @Test
    public void testSetNome() {
        Admin admin = new Admin();
        admin.setNome("Maria");
        assertEquals("Maria", admin.getNome());
    }

    @Test
    public void testSetEmail() {
        Admin admin = new Admin();
        admin.setEmail("maria@example.com");
        assertEquals("maria@example.com", admin.getEmail());
    }

    @Test
    public void testSetPassword() {
        Admin admin = new Admin();
        admin.setPassword("password");
        assertEquals("password", admin.getPassword());
    }

    @Test
    public void testClone() {
        Admin admin1 = new Admin("Maria", "maria@example.com", "password");
        Admin admin2 = admin1.clone();
        assertEquals(admin1.getNome(), admin2.getNome());
        assertEquals(admin1.getEmail(), admin2.getEmail());
        assertEquals(admin1.getPassword(), admin2.getPassword());
        assertNotSame(admin1, admin2);
    }

    @Test
    public void testToString() {
        Admin admin = new Admin("Maria", "maria@example.com", "password");
        assertEquals("Admin: Maria, Email: maria@example.com\n", admin.toString());
    }

    @Test
    public void testEquals() {
        Admin admin1 = new Admin("Maria", "maria@example.com", "password");
        Admin admin2 = new Admin("Maria", "maria@example.com", "password");
        Admin admin3 = new Admin("João", "joao@example.com", "password");
        Admin admin4 = new Admin("Maria", "maria@example.com", "123456");
        assertTrue(admin1.equals(admin2));
        assertFalse(admin1.equals(admin3));
        assertFalse(admin1.equals(admin4));
    }
}
