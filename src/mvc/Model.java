package mvc;

import mvc.Comparators.*;
import src.Artigo.*;
import src.Admin;
import src.Utilizador.*;
import src.Utilizador.Transportador;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Model implements IModel, Serializable {

    private List<Artigo> listaDeArtigos;
    private Map<String, CompradorVendedor> listaDeCompradoresVendedores;
    private List<Transportador> listaDeTransportadores;
    private List<Transportador> listaDeTransportadoresPremium;
    private List<Admin> listaDeAdmins;
    private double lucroVintage;
    private LocalDate tempoSistema;

    //  Construtor por Omissão
    public Model() {
        this.listaDeArtigos = new ArrayList<>();
        this.listaDeCompradoresVendedores = new HashMap<>();
        this.listaDeTransportadores = new ArrayList<>();
        this.listaDeTransportadoresPremium = new ArrayList<>();
        this.listaDeAdmins = new ArrayList<>();
        this.lucroVintage = 0.0f;
    }

    // Construtor Parametrizado
    public Model (List<Artigo> listaArtigos, Map<String, CompradorVendedor> listaCompradoresVendedores,
                  List<Transportador> listaTransportadores, List<Transportador> listaTransportadoresPremium,
                  List<Admin> listaAdmins, double lucroVintage) {
        setListaDeArtigos(listaArtigos);
        setListaDeCompradoresVendedores(listaCompradoresVendedores);
        setListaDeTransportadores(listaTransportadores);
        setListaDeTransportadoresPremium(listaTransportadoresPremium);
        setListaDeAdmins(listaAdmins);
        this.lucroVintage = lucroVintage;
    }

    // Construtor por Cópia
    public Model (Model m) {
        setListaDeArtigos(m.getListaDeArtigos());
        setListaDeCompradoresVendedores(m.getListaDeCompradoresVendedores());
        setListaDeTransportadores(m.getListaDeTransportadores());
        setListaDeTransportadoresPremium(m.getListaDeTransportadoresPremium());
        setListaDeAdmins(m.getListaDeAdmins());
        this.lucroVintage = m.getLucroVintage();
    }

    // Getters e Setters
    public List<Artigo> getListaDeArtigos() {
        List<Artigo> listaArtigos = new ArrayList<Artigo>();
        for (Artigo artigo : this.listaDeArtigos) {
            listaArtigos.add(artigo.clone());
        }
        return listaArtigos;
    }

    public void setListaDeArtigos(List<Artigo> listaArtigos) {
        this.listaDeArtigos = new ArrayList<Artigo>();
        for (Artigo artigo : listaArtigos){
            this.listaDeArtigos.add(artigo.clone());
        }
    }

    public Map<String, CompradorVendedor> getListaDeCompradoresVendedores() {
        Map<String, CompradorVendedor> listaCompradoresVendedores = new HashMap<String, CompradorVendedor>();
        for (Map.Entry<String, CompradorVendedor> entry : listaDeCompradoresVendedores.entrySet()) {
            listaCompradoresVendedores.put(entry.getKey(), entry.getValue());
        }
        return listaCompradoresVendedores;
    }

    public void setListaDeCompradoresVendedores(Map<String, CompradorVendedor> listaCompradoresVendedores) {
        this.listaDeCompradoresVendedores = new HashMap<String, CompradorVendedor>();
        for (Map.Entry<String, CompradorVendedor> entry : listaCompradoresVendedores.entrySet()){
            this.listaDeCompradoresVendedores.put(entry.getKey(),entry.getValue().clone());
        }
    }

    public List<Transportador> getListaDeTransportadores() {
        List<Transportador> listaTransportadores = new ArrayList<>();
        for (Transportador transportador : listaDeTransportadores) {
            listaTransportadores.add(transportador.clone());
        }
        return listaTransportadores;
    }

    public void setListaDeTransportadores(List<Transportador> listaTransportadores) {
        this.listaDeTransportadores = new ArrayList<>();
        for (Transportador t : listaTransportadores){
            this.listaDeTransportadores.add(t.clone());
        }
    }

    public List<Transportador> getListaDeTransportadoresPremium() {
        List<Transportador> listaTransportadoraPremium = new ArrayList<Transportador>();
        for (Transportador t : this.listaDeTransportadoresPremium) {
            listaTransportadoraPremium.add(t.clone());
        }
        return listaTransportadoraPremium;
    }

    public void setListaDeTransportadoresPremium(List<Transportador> listaTransportadoraPremium) {
        this.listaDeTransportadoresPremium = new ArrayList<Transportador>();
        for (Transportador t : listaTransportadoraPremium){
            this.listaDeTransportadoresPremium.add(t.clone());
        }
    }

    public List<Admin> getListaDeAdmins() {
        List<Admin> listaAdmins = new ArrayList<Admin>();
        for (Admin a : this.listaDeAdmins) {
            listaAdmins.add(a.clone());
        }
        return listaAdmins;
    }

    public void setListaDeAdmins(List<Admin> listaAdmins) {
        this.listaDeAdmins = new ArrayList<Admin>();
        for (Admin a : listaAdmins) {
            this.listaDeAdmins.add(a.clone());
        }
    }

    public void atualizaArtigo(Artigo artigo) {
        for (int i = 0; i < listaDeArtigos.size(); i++) {
            if (listaDeArtigos.get(i).equals(artigo)) {
                listaDeArtigos.set(i, artigo);
                break;
            }
        }
    }

    public double getLucroVintage() {
        return this.lucroVintage;
    }

    public void setLucroVintage (double lucroVintage) {
        this.lucroVintage = lucroVintage;
    }

    public LocalDate getTempoSistema() {
        return this.tempoSistema;
    }

    public void setTempoSistema (LocalDate tempoSistema) {
        this.tempoSistema = tempoSistema;
    }

    // Equals
    @Override
    public boolean equals(Object o){
        if (o == this) {
            return true;
        }
        if (o.getClass() != this.getClass()) {
            return false;
        }
        Model m = (Model) o;
        return  this.listaDeArtigos.equals(m.getListaDeArtigos()) &&
                this.listaDeCompradoresVendedores.equals(m.getListaDeCompradoresVendedores()) &&
                this.listaDeTransportadores.equals(m.getListaDeTransportadores()) &&
                this.listaDeTransportadoresPremium.equals(m.getListaDeTransportadoresPremium()) &&
                this.listaDeAdmins.equals(m.getListaDeAdmins());
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Model : ").append("\n");
        sb.append("Artigos : \n");
        for (Artigo a : this.listaDeArtigos) {
            sb.append(a.toString());
        }
        sb.append("CompradoresVendedores : \n");
        for (Map.Entry<String,CompradorVendedor> cv : this.listaDeCompradoresVendedores.entrySet()) {
            sb.append(cv.getValue().toString());
        }
        sb.append("Transportadores : \n");
        for (Transportador t : this.listaDeTransportadores) {
            sb.append(t.toString());
        }
        sb.append("Transportadores Premium : \n");
        for (Transportador t : this.listaDeTransportadoresPremium) {
            sb.append(t.toString());
        }
        return sb.toString();
    }

    // Clone
    @Override
    public Model clone() {
        return new Model(this);
    }

    // Gets Especificos
    public CompradorVendedor getCompradorVendedor(String email, String password) {
        for (Map.Entry<String, CompradorVendedor> entry : this.listaDeCompradoresVendedores.entrySet()) {
            CompradorVendedor compradorVendedor = entry.getValue();
            if (compradorVendedor.getEmail().equals(email) && compradorVendedor.getPassword().equals(password)) {
                return compradorVendedor;
            }
        }
        return null;
    }

    public Transportador getTransportador(String email, String password) {
        for (Transportador t : listaDeTransportadores) {
            if (t.getEmail().equals(email) && t.getPassword().equals(password)) {
                return t;
            }
        }
        for (Transportador t : listaDeTransportadoresPremium) {
            if (t.getEmail().equals(email) && t.getPassword().equals(password)) {
                return t;
            }
        }
        return null;
    }

    public Admin getAdmin(String email, String password) {
        List<Admin> listaAdmin = this.listaDeAdmins;
        for (Admin admin : this.listaDeAdmins) {
            if (admin.getEmail().equals(email) && admin.getPassword().equals(password)) {
                return admin;
            }
        }
        return null;
    }

    public List<Artigo> getArtigosEncomenda(CompradorVendedor compradorVendedor) {
        Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
        return encomenda.getArtigos();
    }

    public List<Encomenda> getEncomendasExpedidas (CompradorVendedor compradorVendedor) {
        return compradorVendedor.getListaEncomendasExpedida();
    }

    public List<Encomenda> getEncomendasFinalizadas (CompradorVendedor compradorVendedor) {
        return compradorVendedor.getListaEncomendasFinalizada();
    }

    public void removeArtigoCompradorVendedor (CompradorVendedor compradorVendedor, Artigo artigo) {
        List<Artigo> listaArtigo = compradorVendedor.getProdutosVenda();
        listaArtigo.remove(artigo);
        compradorVendedor.setProdutosVenda(listaArtigo);
    }

    public void removeArtigoCarrinhoCompras (CompradorVendedor compradorVendedor, Artigo artigo) {
        Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
        List<Artigo> carrinhoCompras = encomenda.getArtigos();
        carrinhoCompras.remove(artigo);
        encomenda.setArtigos(carrinhoCompras);
        compradorVendedor.setCarrinhoDeCompras(encomenda);
    }

    public Artigo getArtigoCompradorVendedor (CompradorVendedor compradorVendedor, int option) {
        List<Artigo> listaArtigos = compradorVendedor.getProdutosVenda();
        return listaArtigos.get(option-1);
    }

    public Artigo getArtigoCompradorVendedorCarrinhodeCompras (CompradorVendedor compradorVendedor, int option) {
        Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
        List<Artigo> listaArtigos = encomenda.getArtigos();
        return listaArtigos.get(option-1);
    }

    public boolean verificaEmail(String email) {
        for (CompradorVendedor cv : listaDeCompradoresVendedores.values()) {
            if (cv.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificaEmailTransportador(String email) {
        for (Transportador t : listaDeTransportadores) {
            if (t.getEmail().equals(email)) {
                return true;
            }
        }
        return false;
    }

    public void removeArtigo(Artigo artigo) {
        List<Artigo> listaArtigoAux = getListaDeArtigos();
        listaArtigoAux.remove(artigo);
        setListaDeArtigos(listaArtigoAux);
    }

    public void adicionaArtigo(Artigo artigo) {
        List<Artigo> listaArtigoAux = getListaDeArtigos();
        listaArtigoAux.add(artigo);
        setListaDeArtigos(listaArtigoAux);
    }

    public void adicionaArtigoProdutosVenda(CompradorVendedor compradorVendedor, Artigo artigo) {
        List<Artigo> listaArtigoPV = compradorVendedor.getProdutosVenda();
        listaArtigoPV.add(artigo);
        compradorVendedor.setProdutosVenda(listaArtigoPV);
    }

    public boolean verificaIgualdade(CompradorVendedor compradorVendedor, Artigo artigo) {
        List<Artigo> listaArtigoPV = compradorVendedor.getProdutosVenda();
        for(Artigo a : listaArtigoPV) {
            if(igualdade(a, artigo)) {
                return true;
            }
        }
        return false;
    }

    public void alteraStockArtigo(Artigo artigo, int stockNovo) {
        List<Artigo> listaArtigoAux = getListaDeArtigos();
        for (Artigo a : listaArtigoAux) {
            if (a.equals(artigo)) {
                a.setStock(stockNovo);
                break;
            }
        }
        setListaDeArtigos(listaArtigoAux);
    }

    public void updateCV(CompradorVendedor compradorVendedor, int quantidade, int opcao) {
        Map<String, CompradorVendedor> cv = getListaDeCompradoresVendedores();

        List<Artigo> listaArtigo = compradorVendedor.getProdutosVenda();
        Artigo artigo = listaArtigo.get(opcao - 1);
        int stock = artigo.getStock();
        artigo.setStock(stock + quantidade);

        compradorVendedor.setProdutosVenda(listaArtigo);
        listaDeCompradoresVendedores.put(compradorVendedor.getEmail(), compradorVendedor);
    }

    public void removeStockArtigoCarrinhoCompras(CompradorVendedor compradorVendedor, int option, int stockManter) {
        Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
        List<Artigo> carrinhoCompras = encomenda.getArtigos();
        Artigo artigoNovo = carrinhoCompras.get(option - 1);
        artigoNovo.setStock(stockManter);
        carrinhoCompras.set(option - 1, artigoNovo);
        encomenda.setArtigos(carrinhoCompras);
        compradorVendedor.setCarrinhoDeCompras(encomenda);
    }

    public void adicionarStockArtigo(Artigo artigo, int quantidade) {
        List<Artigo> listaAux = getListaDeArtigos();
        boolean artigoExists = false;
        for (Artigo a1 : listaAux) {
            if (igualdade(a1,artigo)) {
                int stock = a1.getStock();
                a1.setStock(stock + quantidade);
                artigoExists = true;
                break;
            }
        }
        if (!artigoExists) {
            artigo.setStock(quantidade);
            listaAux.add(artigo);
        }
        setListaDeArtigos(listaAux);
    }

    public void adicionaTransportadoraPremium(Transportador t) {
        // percorre a lista de transportadoras
        for (int i = 0; i < listaDeTransportadoresPremium.size(); i++) {
            Transportador transportadora = listaDeTransportadoresPremium.get(i);
            // verifica se a transportadora já existe
            if (transportadora.getNome().equals(t.getNome())) {
                // substitui a transportadora existente pela nova transportadora premium
                listaDeTransportadoresPremium.set(i, t);
                return;
            }
        }
        // se não encontrou nenhuma transportadora existente com o mesmo nome ou id, adiciona a nova transportadora premium à lista
        listaDeTransportadoresPremium.add(t);
    }

    public void adicionaTransportadora(Transportador t) {
        // percorre a lista de transportadoras
        for (int i = 0; i < listaDeTransportadores.size(); i++) {
            Transportador transportadora = listaDeTransportadores.get(i);
            // verifica se a transportadora já existe
            if (transportadora.getNome().equals(t.getNome())) {
                // substitui a transportadora existente pela nova transportadora premium
                listaDeTransportadores.set(i, t);
                return;
            }
        }
        // se não encontrou nenhuma transportadora existente com o mesmo nome ou id, adiciona a nova transportadora premium à lista
        listaDeTransportadores.add(t);
    }

    public void atualizaTransportador(Transportador transportador) {
        List<Transportador> catalogoTrans = getListaDeTransportadores();

        for (int i = 0; i < catalogoTrans.size(); i++) {
            if (catalogoTrans.get(i).getNome().equals(transportador.getNome())) {
                catalogoTrans.set(i, transportador);
                break;
            }
        }
        setListaDeTransportadores(catalogoTrans);
    }

    public void atualizaTransportadorPremium(Transportador transportador) {
        List<Transportador> catalogoTrans = getListaDeTransportadoresPremium();

        for (int i = 0; i < catalogoTrans.size(); i++) {
            if (catalogoTrans.get(i).getNome().equals(transportador.getNome())) {
                catalogoTrans.set(i, transportador);
                break;
            }
        }
        setListaDeTransportadoresPremium(catalogoTrans);
    }

    public void atualizaCompradorVendedor(CompradorVendedor compradorVendedor) {
        Map<String, CompradorVendedor> listaCV = getListaDeCompradoresVendedores();
        listaCV.put(compradorVendedor.getEmail(),compradorVendedor);
        setListaDeCompradoresVendedores(listaCV);
    }

    public boolean igualdade(Artigo a, Artigo b) {
        boolean returnValue = false;
        if(a.getTitulo().equals(b.getTitulo()) &&
                a.getMarca().equals(b.getMarca())) {
            returnValue = true;
        }
        return returnValue;
    }

    public List<Artigo> getProdutosVenda(CompradorVendedor compradorVendedor) {
        return compradorVendedor.getProdutosVenda();
    }

    public Encomenda finalizaEncomenda (CompradorVendedor compradorVendedor) {
        Encomenda encomenda = compradorVendedor.getCarrinhoDeCompras();
        List<Artigo> listaArtigos = encomenda.getArtigos();
        if (listaArtigos.size() > 2 && listaArtigos.size() < 5) {
            encomenda.setEmbalagem(Encomenda.Embalagem.MEDIO);
        } else if (listaArtigos.size() >= 5) {
            encomenda.setEmbalagem(Encomenda.Embalagem.GRANDE);
        }
        double lucroVintage = 0;
        for (Artigo a : listaArtigos) {
            if (a.getNumeroDonos() == 0){
                lucroVintage = lucroVintage + 0.5;
            } else {
                lucroVintage = lucroVintage + 0.25;
            }
        }
        for (Artigo a : encomenda.getArtigos()) {
            CompradorVendedor vendedor = a.getVendedor();
            double custo = a.calcularPreco();
            double custofinal = vendedor.getValorTotalVendas();
            custofinal = custo + custofinal;
            Map<LocalDate,Artigo> artigosVendedor = vendedor.getArtigosVendidosData();
            artigosVendedor.put(encomenda.getDataExpedicao(),a.clone());
            vendedor.setArtigosVendidosData(artigosVendedor);
            vendedor.setValorTotalVendas(custofinal);
            for (Artigo ae : encomenda.getArtigos()) {
                if (ae.getVendedor().equals(vendedor)){
                    ae.setVendedor(vendedor);
                }
            }
            a.setComprador(compradorVendedor);
            Transportador t = a.getTransportador();
            List<Artigo> artigosEntregar = t.getArtigosAEntregar();
            artigosEntregar.add(a);
            t.setArtigosAEntregar(artigosEntregar);
            if (t.isTransportePremium()) {
                this.atualizaTransportadorPremium(t);
            } else {
                this.atualizaTransportador(t);
            }
        }
        double lucroVintageFinal = this.lucroVintage;
        lucroVintageFinal = lucroVintageFinal + lucroVintage;
        this.setLucroVintage(lucroVintageFinal);
        encomenda.setDataExpedicao(LocalDate.now());
        encomenda.setEstado(Encomenda.Estado.EXPEDIDA);
        double valorCompra = encomenda.calcularPrecoFinal();
        double valorTotalCompras = compradorVendedor.getValorTotalCompras();
        valorTotalCompras = valorTotalCompras + valorCompra;
        compradorVendedor.setValorTotalCompras(valorTotalCompras);
        encomenda.calcularCustosExpedicao();
        List<Encomenda> listaEncomendasExpedidas = compradorVendedor.getListaEncomendasExpedida();
        listaEncomendasExpedidas.add(encomenda);
        compradorVendedor.setListaEncomendasExpedida(listaEncomendasExpedidas);
        Encomenda encomenda1 = new Encomenda();
        compradorVendedor.setCarrinhoDeCompras(encomenda1);
        this.atualizaCompradorVendedor(compradorVendedor);
        return encomenda;
    }

    public void produzFaturaComprador (CompradorVendedor compradorVendedor, Encomenda encomenda) {
        StringBuilder sb = new StringBuilder();
        sb.append("Data de Finalização : ").append(encomenda.getDataExpedicao()).append(", ");
        sb.append("Data de Entrega : ").append(encomenda.getDataEntrega()).append(".\n");
        sb.append("Lista de Produtos :\n");
        for (Artigo a : encomenda.getArtigos()) {
            sb.append(a.toString()).append("\n");
        }
        sb.append("Custo total da Encomenda : ")
                .append(String.format("%.2f",encomenda.calcularPrecoFinal()))
                .append(String.format("%.2f", encomenda.calcularCustosExpedicao()))
                .append(".\n");
        String fatura = sb.toString();
        List<String> faturasComprador = compradorVendedor.getListaDeFaturasComprador();
        List<String> faturasCompradorLoop = compradorVendedor.getListaDeFaturasComprador();
        int size = faturasComprador.size();
        if (faturasComprador.isEmpty()) {
            faturasComprador.add(fatura);
        } else {
            for (String s : faturasCompradorLoop) {
                if (!s.equals(fatura)) {
                    faturasComprador.add(fatura);
                }
            }
        }
        compradorVendedor.setListaDeFaturasComprador(faturasComprador);
        this.atualizaCompradorVendedor(compradorVendedor);
    }

    public void produzFaturaVendedor (Encomenda encomenda) {
        List<Artigo> listaDeArtigos = encomenda.getArtigos();
        CompradorVendedor vendedor = null;
        for (Artigo a : listaDeArtigos) {
            Map<String,CompradorVendedor> compradorVendedorMap = this.getListaDeCompradoresVendedores();
            vendedor = a.getVendedor();
            StringBuilder sb = new StringBuilder();
            sb.append("Fatura do Artigo ").append(a.getTitulo()).append(" vendido na data ");
            sb.append(encomenda.getDataExpedicao()).append(" ao Comprador ").append(vendedor.getNome());
            sb.append(" com custo ").append(String.format("%.2f", a.calcularPreco())).append(".\n");

            List<String> listaFaturasVendedor = vendedor.getListaDeFaturasVendedor();
            listaFaturasVendedor.add(sb.toString());
            vendedor.setListaDeFaturasVendedor(listaFaturasVendedor);
            for (Artigo ae : listaDeArtigos) {
                if (ae.getVendedor().equals(vendedor)) {
                    ae.setVendedor(vendedor);
                }
            }
            compradorVendedorMap.put(vendedor.getEmail(),vendedor);
            this.setListaDeCompradoresVendedores(compradorVendedorMap);
        }
        encomenda.setArtigos(listaDeArtigos);
    }

    public void avancarNoTempo (LocalDate tempoAvancar, CompradorVendedor compradorVendedor) {
        for (Encomenda e : compradorVendedor.getListaEncomendasExpedida()) {
            if (!e.getDataEntrega().isAfter(tempoAvancar)) {
                this.produzFaturaComprador(compradorVendedor,e);
                this.produzFaturaVendedor(e);
                List<Encomenda> listaEncomendasExpedidas = compradorVendedor.getListaEncomendasExpedida();
                listaEncomendasExpedidas.remove(e);
                compradorVendedor.setListaEncomendasExpedida(listaEncomendasExpedidas);
                List<Encomenda> listaEncomendasFinalizadas = compradorVendedor.getListaEncomendasFinalizada();
                e.setEstado(Encomenda.Estado.FINALIZADA);
                listaEncomendasFinalizadas.add(e);
                compradorVendedor.setListaEncomendasFinalizada(listaEncomendasFinalizadas);
                this.atualizaCompradorVendedor(compradorVendedor);
                for (Artigo a : e.getArtigos()) {
                    Transportador t = a.getTransportador();
                    List<Artigo> listaArtigos = t.getArtigosAEntregar();
                    listaArtigos.remove(a);
                    t.setArtigosAEntregar(listaArtigos);
                    for (Artigo a1 : e.getArtigos()) {
                        if (a1.getTransportador().equals(t)) {
                            if (t.isTransportePremium()) {
                                this.atualizaTransportadorPremium(t);
                            } else {
                                this.atualizaTransportador(t);
                            }
                        }
                    }
                    CompradorVendedor cv = a.getVendedor();
                    List<Artigo> artigosVendidos = cv.getProdutosVendidos();
                    artigosVendidos.add(a);
                    cv.setProdutosVendidos(artigosVendidos);
                    Map<LocalDate,Artigo> mapArtigo = cv.getArtigosVendidosData();
                    mapArtigo.put(e.getDataEntrega(),a.clone());
                    cv.setArtigosVendidosData(mapArtigo);
                    double vendas = cv.getValorTotalVendas();
                    vendas = vendas + a.calcularPreco();
                    cv.setValorTotalVendas(vendas);
                    for (Artigo a2 : e.getArtigos()) {
                        a2.setVendedor(cv);
                    }
                    this.atualizaCompradorVendedor(cv);
                }
            }
        }
        this.setTempoSistema(tempoAvancar);
    }

    public void devolverEncomenda (CompradorVendedor compradorVendedor, Encomenda encomenda) {
        List<Encomenda> encomendasFinalizadas = compradorVendedor.getListaEncomendasFinalizada();
        encomendasFinalizadas.remove(encomenda);
        compradorVendedor.setListaEncomendasFinalizada(encomendasFinalizadas);
        for (Artigo a : encomenda.getArtigos()) {
            CompradorVendedor vendedor = a.getVendedor();
            List<Artigo> listaProdutosVendidos = vendedor.getProdutosVendidos();
            List<Artigo> listaProdutosVenda = vendedor.getProdutosVenda();
            listaProdutosVendidos.remove(a);
            vendedor.setProdutosVendidos(listaProdutosVendidos);
            if (a.getStock() == 0) {
                listaProdutosVenda.add(a);
                this.listaDeArtigos.add(a);
            } else {
                for (Artigo av : listaProdutosVenda) {
                    if (igualdade(a, av)) {
                        int stock = av.getStock();
                        stock++;
                        av.setStock(stock);
                    }
                }
                List<Artigo> listaArtigos = this.listaDeArtigos;
                for (Artigo ac : listaArtigos) {
                    if (igualdade(a, ac)) {
                        int stock = ac.getStock();
                        stock++;
                        ac.setStock(stock);
                    }
                }
                setListaDeArtigos(listaArtigos);
            }
            vendedor.setProdutosVenda(listaProdutosVenda);
            for (Artigo ae : encomenda.getArtigos()) {
                if (ae.getVendedor().equals(vendedor)) {
                    ae.setVendedor(vendedor);
                }
                this.atualizaCompradorVendedor(vendedor);
            }
        }
    }

    public void atualizaEncomenda (CompradorVendedor compradorVendedor, Encomenda encomenda) {
        compradorVendedor.setCarrinhoDeCompras(encomenda);
        this.atualizaCompradorVendedor(compradorVendedor);
    }

    // Stats
    // 1 -> Qual é o Vendedor que mais faturou desde sempre.
    public CompradorVendedor vendedorMaiorFaturacaoSempre() {
        CompradorVendedor vendedorMaiorFaturacao = null;
        double maiorFaturacao = 0;
        for (Map.Entry<String,CompradorVendedor> entry : this.listaDeCompradoresVendedores.entrySet()) {
            if (entry.getValue().getValorTotalVendas() > maiorFaturacao) {
                maiorFaturacao = entry.getValue().getValorTotalVendas();
                vendedorMaiorFaturacao = entry.getValue().clone();
            }
        }
        return vendedorMaiorFaturacao;
    }

    // 2 -> Qual é o Vendedor que mais faturou num determinado periodo.
    public CompradorVendedor vendedorMaiorFaturacaoPeriodoTempo(LocalDate localDate1, LocalDate localDate2) {
        int compareValue = localDate1.compareTo(localDate2);
        LocalDate antes, depois;
        if (compareValue > 0) {
            antes = localDate2;
            depois = localDate1;
        } else {
            antes = localDate1;
            depois = localDate2;
        }
        CompradorVendedor compradorVendedor = new CompradorVendedor();
        double maiorValorFaturacao = 0.0f;
        for (Map.Entry<String,CompradorVendedor> cv : this.listaDeCompradoresVendedores.entrySet()) {
            double faturacao = 0.0f;
            CompradorVendedor cv1 = cv.getValue();
            for (Map.Entry<LocalDate,Artigo> entry : cv1.getArtigosVendidosData().entrySet()) {
                Artigo a = entry.getValue();
                LocalDate data = entry.getKey();
                if (!data.isBefore(antes) && data.isAfter(depois)) {
                    faturacao = faturacao + a.calcularPreco();
                }
            }
            if (faturacao > maiorValorFaturacao) {
                maiorValorFaturacao = cv.getValue().getValorTotalVendas();
                compradorVendedor = new CompradorVendedor(cv.getValue().clone());
            }
        }
        return compradorVendedor;
    }

    // 3 -> Qual é o Transportador com maior volume de faturação.
    public Transportador transportadorMaiorFaturacaoSempre() {
        // Inicializa a variável de transporte com null e a maior faturação com 0.
        Transportador transportadorMaiorFaturacao = null;
        double maiorFaturacao = 0;

        // Itera sobre todos os elementos na lista de transportadores.
        for (Transportador t : listaDeTransportadores) {
            // Calcula a faturação do transportador atual.
            double faturacaoTransportador = t.getPrecoTotalEntregas();

            // Verifica se a faturação atual é maior que a faturação anterior.
            if (faturacaoTransportador > maiorFaturacao) {
                // Atualiza a maior faturação e o transportador com a maior faturação.
                maiorFaturacao = faturacaoTransportador;
                transportadorMaiorFaturacao = t.clone();
            }
        }

        // Itera sobre todos os elementos na lista de transportadores premium.
        for (Transportador t : listaDeTransportadoresPremium) {
            // Calcula a faturação do transportador atual.
            double faturacaoTransportador = t.getPrecoTotalEntregas();

            // Verifica se a faturação atual é maior que a faturação anterior.
            if (faturacaoTransportador > maiorFaturacao) {
                // Atualiza a maior faturação e o transportador com a maior faturação.
                maiorFaturacao = faturacaoTransportador;
                transportadorMaiorFaturacao = t.clone();
            }
        }

        // Retorna o transportador com a maior faturação.
        return transportadorMaiorFaturacao;
    }

    // 4 -> Listar os Artigos vendidos por um Vendedor.
    public List<Artigo> listaArtigosVendidos (String nome) {
        CompradorVendedor compradorVendedor = null;
        List<Artigo> listaArtigosVendidos = new ArrayList<Artigo>();
        for (Map.Entry<String,CompradorVendedor> entry : this.listaDeCompradoresVendedores.entrySet()) {
            if (nome.equals(entry.getValue().getNome())) {
                compradorVendedor = entry.getValue().clone();
            }
        }
        if (compradorVendedor != null) {
            listaArtigosVendidos = compradorVendedor.getProdutosVendidos();
        }
        return listaArtigosVendidos;
    }

    // 5 -> Obter uma ordenação dos maiores Compradores desde sempre.
    public Set<CompradorVendedor> maioresCompradoresSempre() {
        TreeSet<CompradorVendedor> compradorOrdem = new TreeSet<CompradorVendedor>(new ComparatorComprador());
        for (Map.Entry<String,CompradorVendedor> entry : this.listaDeCompradoresVendedores.entrySet()) {
            compradorOrdem.add(entry.getValue().clone());
        }
        return compradorOrdem;
    }

    // 6 -> Obter uma ordenação dos maiores Compradores num determinado periodo.
    public Set<CompradorVendedor> maioresCompradoresPeriodoTempo (LocalDate localDate1, LocalDate localDate2) {
        int compareValue = localDate1.compareTo(localDate2);
        LocalDate antes, depois;
        if (compareValue > 0) {
            antes = localDate2;
            depois = localDate1;
        } else {
            antes = localDate1;
            depois = localDate2;
        }
        TreeSet<CompradorVendedor> compradorOrdem = new TreeSet<CompradorVendedor>(new ComparatorCompradorTempo());
        for (Map.Entry<String,CompradorVendedor> entry : this.listaDeCompradoresVendedores.entrySet()) {
            double faturacao = 0.0f;
            for (Encomenda e : entry.getValue().getListaEncomendasFinalizada()){
                if (e.getDataEntrega().isBefore(depois) && e.getDataEntrega().isAfter(antes)) {
                    faturacao = faturacao + e.getPrecoFinal() + e.getCustosExpedicao();
                }
            }
            entry.getValue().setValorTotalComprasTempo(faturacao);
            compradorOrdem.add(entry.getValue().clone());
        }
        return compradorOrdem;
    }

    // 7 -> Obter uma ordenação dos maiores Vendedores desde sempre.
    public Set<CompradorVendedor> maioresVendedoresSempre() {
        TreeSet<CompradorVendedor> vendedorOrdem = new TreeSet<CompradorVendedor>(new ComparatorVendedor());
        for (Map.Entry<String,CompradorVendedor> entry : this.listaDeCompradoresVendedores.entrySet()) {
            vendedorOrdem.add(entry.getValue().clone());
        }
        return vendedorOrdem;
    }

    // 8 -> Obter uma ordenação dos maiores Vendedores num determinado periodo.
    public Set<CompradorVendedor> maioresVendedoresPeriodoTempo (LocalDate localDate1, LocalDate localDate2) {
        int compareValue = localDate1.compareTo(localDate2);
        LocalDate antes, depois;
        if (compareValue > 0) {
            antes = localDate2;
            depois = localDate1;
        } else {
            antes = localDate1;
            depois = localDate2;
        }
        TreeSet<CompradorVendedor> vendedorOrdem = new TreeSet<CompradorVendedor>(new ComparatorVendedorTempo());
        for (Map.Entry<String,CompradorVendedor> entry : this.listaDeCompradoresVendedores.entrySet()) {
            double faturacao = 0.0f;
            for (Map.Entry<LocalDate,Artigo> a : entry.getValue().getArtigosVendidosData().entrySet()){
                if (a.getKey().isBefore(depois) && a.getKey().isAfter(antes)) {
                    faturacao = faturacao + a.getValue().getPrecoBase();
                }
            }
            entry.getValue().setValorTotalVendasTempo(faturacao);
            vendedorOrdem.add(entry.getValue().clone());
        }
        return vendedorOrdem;
    }

    // Parser
    public void adicionaCompradorVendedor (CompradorVendedor compradorVendedor) {
        this.listaDeCompradoresVendedores.put(compradorVendedor.getEmail(),compradorVendedor.clone());
    }

    public void adicionaTransportador (Transportador transportador) {
        if (!transportador.isTransportePremium()){
            this.listaDeTransportadores.add(transportador.clone());
        } else {
            this.listaDeTransportadoresPremium.add(transportador.clone());
        }
    }

    public void adicionaAdmin (Admin admin) {
        this.listaDeAdmins.add(admin.clone());
    }

    public void adicionaArtigo (Artigo artigo, CompradorVendedor compradorVendedor) {
        adicionaArtigo(artigo);
        List<Artigo> listaArtigos = compradorVendedor.getProdutosVenda();
        listaArtigos.add(artigo);
        compradorVendedor.setProdutosVenda(listaArtigos);
        atualizaCompradorVendedor(compradorVendedor);
    }

    public List<String> getListaFaturasCompradorEspecifico(CompradorVendedor compradorVendedor) {
        List<String> listaFaturas = compradorVendedor.getListaDeFaturasComprador();
        return listaFaturas;
    }

    public List<String> getListaFaturasVendedorEspecifico(CompradorVendedor compradorVendedor) {
        List<String> listaFaturas = compradorVendedor.getListaDeFaturasVendedor();
        return listaFaturas;
    }

    public List<Artigo> listaArtigosAEntregar (Transportador t) {
        List<Artigo> artigosAEntregar = t.getArtigosAEntregar();
        return artigosAEntregar;
    }
}