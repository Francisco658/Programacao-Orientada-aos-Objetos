package mvc.Comparators;

import src.Utilizador.CompradorVendedor;

import java.util.Comparator;

public class ComparatorVendedor implements Comparator<CompradorVendedor> {

    public int compare(CompradorVendedor cv1, CompradorVendedor cv2) {
        int returnValue = 0;
        if (cv1.getValorTotalVendas() > cv2.getValorTotalVendas()) {
            returnValue = -1;
        } else if (cv2.getValorTotalVendas() > cv1.getValorTotalVendas()) {
            returnValue = 1;
        }
        return returnValue;
    }
}
