package mvc.Comparators;

import src.Utilizador.CompradorVendedor;

import java.util.Comparator;

public class ComparatorCompradorTempo implements Comparator<CompradorVendedor> {

    public int compare (CompradorVendedor cv1, CompradorVendedor cv2) {
        int returnValue = 0;
        if (cv1.getValorTotalComprasTempo() > cv2.getValorTotalComprasTempo()) {
            returnValue = -1;
        } else if (cv2.getValorTotalComprasTempo() > cv1.getValorTotalComprasTempo()) {
            returnValue = 1;
        }
        return returnValue;
    }
}
