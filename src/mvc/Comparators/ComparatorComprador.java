package mvc.Comparators;

import src.Utilizador.CompradorVendedor;

import java.util.Comparator;

public class ComparatorComprador implements Comparator<CompradorVendedor> {

    public int compare (CompradorVendedor cv1, CompradorVendedor cv2) {
        int returnValue = 0;
        if (cv1.getValorTotalCompras() > cv2.getValorTotalCompras()) {
            returnValue = -1;
        } else if (cv2.getValorTotalCompras() > cv1.getValorTotalCompras()) {
            returnValue = 1;
        }
        return returnValue;
    }
}
