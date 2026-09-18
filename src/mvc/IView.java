package mvc;

import exceptions.FormulaInvalida;
import exceptions.InputIncorreto;
import src.Admin;
import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.util.Scanner;

public interface IView {

    void runMenuInicial() throws InputIncorreto;
    void runMenuLogin(Scanner scanner);
    void runMenuRegisto();
    void runRegisterCompradorVendedor(Scanner scanner);
    void runRegisterTransportador(Scanner scanner);
    void runMenuCompradorVendedor(CompradorVendedor compradorVendedor);
    void runMenuTransportador(Transportador transportador);
    void runMenuAdmin(Admin admin);
    void runMenuRemoveArtigoCompradorVendedor(CompradorVendedor compradorVendedor);
    void runMenuConsultarCarrinhoCompras(CompradorVendedor compradorVendedor);
    void runMenuTipoArtigo(CompradorVendedor compradorVendedor);
    void runMenuAdicionarArtigo(CompradorVendedor compradorVendedor);
    void runMenuMala(CompradorVendedor compradorVendedor);
    void runMenuSapatilha(CompradorVendedor compradorVendedor);
    void runMenuTshirt(CompradorVendedor compradorVendedor);
    void runMenuAlterarFormula(Transportador transportador);
    void runMenuNovaFormula(Transportador transportador) throws FormulaInvalida;
    void runMenuAdicionarStock(CompradorVendedor compradorVendedor);
    void showMenuInicial();
    void showMenuRegistarUtilizador();
    void showMenuCompradorVendedor(CompradorVendedor compradorVendedor);
    void showMenuTransportador(Transportador transportador);
    void showMenuAdmin(Admin admin);
    void showMenuRemoveArtigo(CompradorVendedor compradorVendedor);
    void showConsultarCarrinhoCompras(CompradorVendedor compradorVendedor);
    void showAdicionarArtigo(CompradorVendedor compradorVendedor);
    void showMenuSelecionarArtigo(CompradorVendedor compradorVendedor);
    void showMenuTipoArtigo(CompradorVendedor compradorVendedor);
}