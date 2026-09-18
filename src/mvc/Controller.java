package mvc;

import src.Admin;
import src.Artigo.Artigo;
import src.Artigo.Encomenda;
import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.io.Serializable;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Controller implements IController, Serializable {
    private IModel model;
    private IView view;

    // Construtor por Omissão
    public Controller() {
        this.model = new Model();
    }

    // Construtor Parametrizado
    public Controller (IModel model, IView view) {
        this.model = model;
        this.view = view;
    }

    public Controller (IModel model) {
        this.model = model;
        this.view = new View();
    }

    // Construtor por Cópia
    public Controller (Controller c) {
        this.model = c.getModel();
        this.view = c.getView();
    }

    // Getters e Setters
    public IModel getModel() {
        return this.model;
    }

    public IView getView() {
        return this.view;
    }

    public void setModel (IModel model) {
        this.model = model;
    }

    public void setView (IView view) {
        this.view = view;
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || (o.getClass() != this.getClass())) {
            return false;
        }
        Controller c = (Controller) o;
        return  this.model.equals(c.getModel()) &&
                this.view.equals(c.getView());
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(this.model);
        sb.append(this.view);
        return sb.toString();
    }

    // Clone
    @Override
    public Controller clone() {
        return new Controller(this);
    }

    // Grava Estado do Programa
    public void gravarEstado(String filepath) throws FileNotFoundException, IOException {
        FileOutputStream f = new FileOutputStream(filepath);
        ObjectOutputStream o = new ObjectOutputStream(f);
        o.writeObject(this.model);
        // o.writeObject(this);
        o.flush();
        o.close();
    }


    public void carregarEstado(String filepath) throws FileNotFoundException, IOException, ClassNotFoundException {
        FileInputStream f = new FileInputStream(filepath);
        ObjectInputStream o = new ObjectInputStream(f);
        Model m = (Model) o.readObject();
        this.model = m;
        o.close();
    }

    // Gets
    @Override
    public List<Transportador> getListaDeTransportadores() {
        return model.getListaDeTransportadores();
    }

    public List<Artigo> getArtigosEncomenda(CompradorVendedor compradorVendedor) {
        return model.getArtigosEncomenda(compradorVendedor);
    }

    public List<Artigo> getArtigosCatalogo() {
        return model.getListaDeArtigos();
    }

    public void adicionaArtigo(Artigo artigo){
        model.adicionaArtigo(artigo);
    }

    public void adicionaArtigoProdutosVenda(CompradorVendedor compradorVendedor, Artigo artigo) {
        model.adicionaArtigoProdutosVenda(compradorVendedor, artigo);
    }

    public boolean verificaIgualdade(CompradorVendedor compradorVendedor, Artigo artigo) {
        return model.verificaIgualdade(compradorVendedor, artigo);
    }

    public CompradorVendedor getCompradorVendedor(String email, String password) {
        return model.getCompradorVendedor(email, password);
    }

    public Transportador getTransportador(String email, String password) {
        return model.getTransportador(email, password);
    }

    public Admin getAdmin(String email, String password) {
        return model.getAdmin(email, password);
    }

    // Verificar Email Válido
    public boolean isValidEmail(String email) {
        Pattern emailPattern = Pattern.compile("^\\S+@\\S+\\.\\S+$");
        Matcher emailMatcher = emailPattern.matcher(email);
        return emailMatcher.matches();
    }

    // Verificar um Intervalo de Tempo Válido
    public boolean isValidData1(String data) {
        Pattern dataPattern = Pattern.compile("^\\d{4}-\\d{2}-\\d{2} \\d{4}-\\d{2}-\\d{2}$");
        Matcher dataMatcher = dataPattern.matcher(data);
        return dataMatcher.matches();
    }

    // Verificar uma Data Válida
    public boolean isValidData2(String data) {
        Pattern dataPattern = Pattern
                .compile("^((19|2[0-9])[0-9]{2})-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$");
        Matcher dataMatcher = dataPattern.matcher(data);
        return dataMatcher.matches();
    }

    public boolean verificaEmail(String email) {
        return model.verificaEmail(email);
    }

    public boolean verificaEmailTransportador(String email) {
        return model.verificaEmailTransportador(email);
    }

    public void atualizaTransportador(Transportador transportador) {
        model.atualizaTransportador(transportador);
    }

    public void atualizaTransportadorPremium(Transportador transportador) {
        model.atualizaTransportadorPremium(transportador);
    }

    public void removeArtigoCarrinhoCompras (CompradorVendedor compradorVendedor, Artigo artigo) {
        model.removeArtigoCarrinhoCompras(compradorVendedor, artigo);
    }

    public Artigo getArtigoCompradorVendedorCarrinhodeCompras (CompradorVendedor compradorVendedor, int option) {
        return model.getArtigoCompradorVendedorCarrinhodeCompras(compradorVendedor, option);
    }

    public void atualizaCompradorVendedor(CompradorVendedor compradorVendedor) {
        model.atualizaCompradorVendedor(compradorVendedor);
    }

    public boolean igualdade(Artigo a, Artigo b) {
        return model.igualdade(a, b);
    }

    public List<Artigo> getProdutosVenda(CompradorVendedor compradorVendedor) {
        return model.getProdutosVenda(compradorVendedor);
    }

    public Encomenda getFinalizaEncomenda(CompradorVendedor compradorVendedor) {
        return this.model.finalizaEncomenda(compradorVendedor);
    }

    public void getProduzFaturaComprador(CompradorVendedor compradorVendedor, Encomenda encomenda) {
        this.model.produzFaturaComprador(compradorVendedor,encomenda);
    }

    public void getProduzFaturaVendedor(Encomenda encomenda) {
        this.model.produzFaturaVendedor(encomenda);
    }

    public void getAvancarNoTempo (LocalDate tempoAvancar, CompradorVendedor compradorVendedor) {
        this.model.avancarNoTempo(tempoAvancar,compradorVendedor);
    }

    public void getDevolverEncomenda (CompradorVendedor compradorVendedor, Encomenda encomenda) {
        this.model.devolverEncomenda(compradorVendedor,encomenda);
    }

    public void updateCV(CompradorVendedor compradorVendedor, int quantidade, int opcao) {
        model.updateCV(compradorVendedor, quantidade, opcao);
    }

    public void adicionarStockArtigo(Artigo artigo, int quantidade) {
        model.adicionarStockArtigo(artigo, quantidade);
    }

    public Artigo getArtigoCompradorVendedor (CompradorVendedor compradorVendedor, int option) {
        return model.getArtigoCompradorVendedor(compradorVendedor, option);
    }

    public List<Artigo> getListaDeArtigos() {
        return model.getListaDeArtigos();
    }

    public void removeArtigo(Artigo artigo) {
        model.removeArtigo(artigo);
    }

    public void alteraStockArtigo(Artigo artigo, int stockNovo) {
        model.alteraStockArtigo(artigo, stockNovo);
    }

    public LocalDate getTempoDoSistema () {
        return this.model.getTempoSistema();
    }

    public void setTempoDoSistema (LocalDate localDate) {
        this.model.setTempoSistema(localDate);
    }

    public void getAtualizaEncomenda (CompradorVendedor compradorVendedor, Encomenda encomenda) {
        this.model.atualizaEncomenda(compradorVendedor,encomenda);
    }

    // Stats
    // 1 -> Qual é o Vendedor que mais faturou desde sempre.
    public CompradorVendedor getVendedorMaiorFaturacaoSempre () {
        return this.model.vendedorMaiorFaturacaoSempre();
    }

    // 2 -> Qual é o Vendedor que mais faturou num determinado periodo.
    public CompradorVendedor getVendedorMaiorFaturacaoPeriodoTempo(LocalDate localDate1, LocalDate localDate2) {
        return this.model.vendedorMaiorFaturacaoPeriodoTempo(localDate1,localDate2);
    }

    // 3 -> Qual é o Transportador com maior volume de faturação.
    public Transportador getTransportadorMaiorFaturacaoSempre () {
        return this.model.transportadorMaiorFaturacaoSempre();
    }

    // 4 -> Listar os Artigos vendidos por um Vendedor.
    public List<Artigo> getListaArtigosVendidos (String nome) {
        return this.model.listaArtigosVendidos(nome);
    }

    // 5 -> Obter uma ordenação dos maiores Compradores desde sempre.
    public Set<CompradorVendedor> getMaioresCompradoresSempre () {
        return this.model.maioresCompradoresSempre();
    }

    // 6 -> Obter uma ordenação dos maiores Compradores num determinado periodo.
    public Set<CompradorVendedor> getMaioresCompradoresPeriodoTempo (LocalDate localDate1, LocalDate localDate2) {
        return this.model.maioresCompradoresPeriodoTempo(localDate1,localDate2);
    }

    // 7 -> Obter uma ordenação dos maiores Vendedores desde sempre.
    public Set<CompradorVendedor> getMaioresVendedoresSempre () {
        return this.model.maioresVendedoresSempre();
    }

    // 8 -> Obter uma ordenação dos maiores Vendedores num determinado periodo.
    public Set<CompradorVendedor> getMaioresVendedoresPeriodoTempo (LocalDate localDate1, LocalDate localDate2) {
        return this.model.maioresVendedoresPeriodoTempo(localDate1,localDate2);
    }

    //9 -> Determinar quanto dinheiro ganhou a Vintage no seu funcionamento.
    public double getLucroVintage() {
        return this.model.getLucroVintage();
    }

    public List<CompradorVendedor> getListaCompradoresVendedores () {
        List<CompradorVendedor> listaCompradoresVendedores = new ArrayList<CompradorVendedor>();
        Map<String,CompradorVendedor> mapCompradoresVendedores = this.model.getListaDeCompradoresVendedores();
        for (Map.Entry<String,CompradorVendedor> entry : mapCompradoresVendedores.entrySet()) {
            listaCompradoresVendedores.add(entry.getValue().clone());
        }
        return listaCompradoresVendedores;
    }

    public Map<String, CompradorVendedor> getListaDeCompradoresVendedores() {
        return model.getListaDeCompradoresVendedores();
    }

    public void removeStockArtigoCarrinhoCompras(CompradorVendedor compradorVendedor, int option, int stockManter) {
        model.removeStockArtigoCarrinhoCompras(compradorVendedor, option, stockManter);
    }


    public void removeArtigoCompradorVendedor (CompradorVendedor compradorVendedor, Artigo artigo) {
        this.model.removeArtigoCompradorVendedor(compradorVendedor, artigo);
    }

    public void setListaDeTransportadoresPremium(List<Transportador> listaTransportadoraPremium) {
        model.setListaDeTransportadoresPremium(listaTransportadoraPremium);
    }

    public List<Transportador> getListaDeTransportadoresPremium() {
        return model.getListaDeTransportadoresPremium();
    }

    public void adicionaTransportadoraPremium(Transportador t) {
        model.adicionaTransportadoraPremium(t);
    }

    public void adicionaTransportadora(Transportador t){
        model.adicionaTransportadora(t);
    }

    public List<Encomenda> getEncomendasExpedidas (CompradorVendedor compradorVendedor) {
        return this.model.getEncomendasExpedidas(compradorVendedor);
    }

    public List<Encomenda> getEncomendasFinalizadas (CompradorVendedor compradorVendedor) {
        return this.model.getEncomendasFinalizadas(compradorVendedor);
    }

    public List<String> getListaFaturasComprador (CompradorVendedor compradorVendedor) {
        return this.model.getListaFaturasCompradorEspecifico(compradorVendedor);
    }

    public List<String> getListaFaturasVendedor (CompradorVendedor compradorVendedor) {
        return this.model.getListaFaturasVendedorEspecifico(compradorVendedor);
    }

    public List<Artigo> getListaArtigosAEntregar (Transportador t) {
        return this.model.listaArtigosAEntregar(t);
    }
}