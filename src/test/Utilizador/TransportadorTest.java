//package test.Utilizador;
//
//import org.junit.Test;
//import src.Utilizador.Transportador;
//
//import static org.junit.Assert.assertEquals;
//import static org.junit.Assert.assertTrue;
//
//public class TransportadorTest {
//
//    @Test
//    public void testConstrutorParametrizado() {
//        Transportador transportador = new Transportador("password", "email@example.com", "Nome", "Morada", 123456789,
//                1.0f, 2.0f, 3.0f, 0.23f, 0.5f, true, "(VB * MLT * (1 + I)) * 0.9", null, 0.0f);
//
//        assertEquals("password", transportador.getPassword());
//        assertEquals("email@example.com", transportador.getEmail());
//        assertEquals("Nome", transportador.getNome());
//        assertEquals("Morada", transportador.getMorada());
//        assertEquals(123456789, transportador.getNumeroFiscal());
//        assertEquals(1.0f, transportador.getValorBaseExpedicaoPequena(), 0.0f);
//        assertEquals(2.0f, transportador.getValorBaseExpedicaoMedia(), 0.0f);
//        assertEquals(3.0f, transportador.getValorBaseExpedicaoGrande(), 0.0f);
//        assertEquals(0.23f, transportador.getFatorMultiplicativoImpostos(), 0.0f);
//        assertEquals(0.5f, transportador.getMargemLucro(), 0.0f);
//        assertTrue(transportador.isTransportePremium());
//        assertEquals("(VB * MLT * (1 + I)) * 0.9", transportador.getFormula());
//        assertEquals(null, transportador.getArtigosAEntregar());
//        assertEquals(0.0f, transportador.getPrecoTotalEntregas(), 0.0f);
//    }
//
//    @Test
//    public void testClone() {
//        Transportador transportador = new Transportador("password", "email@example.com", "Nome", "Morada", 123456789,
//                1.0f, 2.0f, 3.0f, 0.23f, 0.5f, true, "(VB * MLT * (1 + I)) * 0.9", null, 0.0f);
//        Transportador clone = transportador.clone();
//        assertEquals(transportador, clone);
//    }
//
//    @Test
//    public void testEquals() {
//        Transportador transportador1 = new Transportador("password", "email@example.com", "Nome", "Morada", 123456789,
//                1.0f, 2.0f, 3.0f, 0.23f, 0.5f, true, "(VB * MLT * (1 + I)) * 0.9", null, 0.0f);
//        Transportador transportador2 = new Transportador("password", "email@example.com", "Nome", "Morada", 123456789,
//                1.0f, 2.0f, 3.0f, 0.23f, 0.5f, true, "(VB * MLT * (1 + I)) * 0.9", null, 0.0f);
//        assertEquals(transportador1, transportador2);
//    }
//}
