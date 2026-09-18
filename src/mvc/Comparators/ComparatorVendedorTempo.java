package mvc.Comparators;

import src.Utilizador.CompradorVendedor;

import java.util.Comparator;

public class ComparatorVendedorTempo implements Comparator<CompradorVendedor> {

    public int compare(CompradorVendedor cv1, CompradorVendedor cv2) {
        int returnValue = 0;
        if (cv1.getValorTotalVendasTempo() > cv2.getValorTotalVendasTempo()) {
            returnValue = -1;
        } else if (cv2.getValorTotalVendasTempo() > cv1.getValorTotalVendasTempo()) {
            returnValue = 1;
        }
        return returnValue;
    }
}
