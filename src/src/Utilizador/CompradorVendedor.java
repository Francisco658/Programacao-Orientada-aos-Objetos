package src.Utilizador;

import src.Artigo.Artigo;
import src.Artigo.Encomenda;

import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CompradorVendedor extends Utilizador implements Serializable {

    private Encomenda carrinhoDeCompras;
    private List<Encomenda> listaEncomendasFinalizada;
    private List<Encomenda> listaEncomendasExpedida;
    private double valorTotalCompras;
    private double valorTotalComprasTempo;
    private List<String> listaDeFaturasComprador;

    private List<String> listaDeFaturasVendedor;
    private Map<LocalDate,Artigo> artigosVendidosData;
    private List<Artigo> produtosVendidos;
    private List<Artigo> produtosVenda;
    private double valorTotalVendas;
    private double valorTotalVendasTempo;

    // Construtor por Omissão
    public CompradorVendedor() {
        super();
        this.carrinhoDeCompras = new Encomenda();
        this.listaEncomendasFinalizada = new ArrayList<>();
        this.listaEncomendasExpedida = new ArrayList<>();
        this.valorTotalCompras = 0.0f;
        this.valorTotalComprasTempo = 0.0f;
        this.listaDeFaturasComprador = new ArrayList<>();
        this.listaDeFaturasVendedor = new ArrayList<>();
        this.artigosVendidosData = new HashMap<>();
        this.produtosVendidos = new ArrayList<>();
        this.produtosVenda = new ArrayList<>();
        this.valorTotalVendas = 0.0f;
        this.valorTotalVendasTempo = 0.0f;
    }

    // Construtor Parametrizado
    public CompradorVendedor(String password, String email, String nome, String morada, int numeroFiscal,
                             Encomenda carrinhoDeCompras, List<Encomenda> listaEncomendasFinalizada,
                             List<Encomenda> listaEncomendasExpedida, double valorTotalCompras,
                             double valorTotalComprasTempo, List<String> listaDeFaturasComprador,
                             List<String> listaDeFaturasVendedor, Map<LocalDate,Artigo> artigosVendidosData,
                             List<Artigo> produtosVendidos, List<Artigo> produtosVenda, double valorTotalVendas,
                             double getValorTotalVendasTempo) {
        super(password, email, nome, morada, numeroFiscal);
        this.carrinhoDeCompras = carrinhoDeCompras;
        setListaEncomendasFinalizada(listaEncomendasFinalizada);
        setListaEncomendasExpedida(listaEncomendasExpedida);
        this.valorTotalCompras = valorTotalCompras;
        this.valorTotalComprasTempo = valorTotalComprasTempo;
        setListaDeFaturasComprador(listaDeFaturasComprador);
        setListaDeFaturasVendedor(listaDeFaturasVendedor);
        setArtigosVendidosData(artigosVendidosData);
        setProdutosVendidos(produtosVendidos);
        setProdutosVenda(produtosVenda);
        this.valorTotalVendas = valorTotalVendas;
        this.valorTotalVendasTempo = getValorTotalVendasTempo;
    }

    // Construtor por Cópia
    public CompradorVendedor(CompradorVendedor compradorVendedor) {
        super(compradorVendedor);
        this.carrinhoDeCompras = compradorVendedor.getCarrinhoDeCompras();
        this.listaEncomendasFinalizada = compradorVendedor.getListaEncomendasFinalizada();
        this.listaEncomendasExpedida = compradorVendedor.getListaEncomendasExpedida();
        this.valorTotalCompras = compradorVendedor.getValorTotalCompras();
        this.valorTotalComprasTempo = compradorVendedor.getValorTotalComprasTempo();
        this.listaDeFaturasComprador = compradorVendedor.getListaDeFaturasComprador();
        this.listaDeFaturasVendedor = compradorVendedor.getListaDeFaturasVendedor();
        this.artigosVendidosData = compradorVendedor.getArtigosVendidosData();
        this.produtosVendidos = compradorVendedor.getProdutosVendidos();
        this.produtosVenda = compradorVendedor.getProdutosVenda();
        this.valorTotalVendas = compradorVendedor.getValorTotalVendas();
        this.valorTotalVendasTempo = compradorVendedor.getValorTotalVendasTempo();
    }

    // Getters e Setters
    public Encomenda getCarrinhoDeCompras() {
        return this.carrinhoDeCompras;
    }

    public void setCarrinhoDeCompras (Encomenda carrinhoCompras) {
        this.carrinhoDeCompras = carrinhoCompras;
    }

    public List<Encomenda> getListaEncomendasFinalizada() {
        List<Encomenda> listaDeEncomendas = new ArrayList<>();
        for (Encomenda enc : this.listaEncomendasFinalizada) {
            listaDeEncomendas.add(enc.clone());
        }
        return listaDeEncomendas;
    }

    public void setListaEncomendasFinalizada(List<Encomenda> listaDeEncomendas) {
        this.listaEncomendasFinalizada = new ArrayList<>();
        for (Encomenda enc : listaDeEncomendas) {
            this.listaEncomendasFinalizada.add(enc.clone());
        }
    }

    public List<Encomenda> getListaEncomendasExpedida() {
        List<Encomenda> listaDeEncomendas = new ArrayList<>();
        for (Encomenda enc : this.listaEncomendasExpedida) {
            listaDeEncomendas.add(enc.clone());
        }
        return listaDeEncomendas;
    }

    public void setListaEncomendasExpedida(List<Encomenda> listaDeEncomendas) {
        this.listaEncomendasExpedida = new ArrayList<>();
        for (Encomenda enc : listaDeEncomendas) {
            this.listaEncomendasExpedida.add(enc.clone());
        }
    }

    public double getValorTotalCompras() {
        return this.valorTotalCompras;
    }

    public void setValorTotalCompras (double valorTotalCompras) {
        this.valorTotalCompras = valorTotalCompras;
    }

    public double getValorTotalComprasTempo() {
        return this.valorTotalComprasTempo;
    }

    public void setValorTotalComprasTempo (double valorTotalComprasTempo) {
        this.valorTotalComprasTempo = valorTotalComprasTempo;
    }

    public List<String> getListaDeFaturasComprador() {
        List<String> listaDeFaturas = new ArrayList<String>();
        for (String s : this.listaDeFaturasComprador) {
            listaDeFaturas.add(s);
        }
        return listaDeFaturas;
    }

    public void setListaDeFaturasComprador (List<String> listaDeFaturas) {
        this.listaDeFaturasComprador = new ArrayList<String>();
        for (String s : listaDeFaturas) {
            this.listaDeFaturasComprador.add(s);
        }
    }

    public List<String> getListaDeFaturasVendedor() {
        List<String> listaDeFaturas = new ArrayList<String>();
        for (String s : this.listaDeFaturasVendedor) {
            listaDeFaturas.add(s);
        }
        return listaDeFaturas;
    }

    public void setListaDeFaturasVendedor (List<String> listaDeFaturas) {
        this.listaDeFaturasVendedor = new ArrayList<String>();
        for (String s : listaDeFaturas) {
            this.listaDeFaturasVendedor.add(s);
        }
    }

    public Map<LocalDate,Artigo> getArtigosVendidosData() {
        Map<LocalDate,Artigo> artigosVendidosDate = new HashMap<LocalDate, Artigo>();
        for (Map.Entry<LocalDate,Artigo> entry : this.artigosVendidosData.entrySet()) {
            artigosVendidosDate.put(entry.getKey(),entry.getValue().clone());
        }
        return artigosVendidosDate;
    }

    public void setArtigosVendidosData (Map<LocalDate,Artigo> artigosVendidosDate) {
        this.artigosVendidosData = new HashMap<LocalDate,Artigo>();
        for (Map.Entry<LocalDate,Artigo> entry : artigosVendidosDate.entrySet()) {
            this.artigosVendidosData.put(entry.getKey(),entry.getValue().clone());
        }
    }

    public List<Artigo> getProdutosVendidos() {
        List<Artigo> listaProdutosVendidos = new ArrayList<>();
        for (Artigo a : this.produtosVendidos) {
            listaProdutosVendidos.add(a.clone());
        }
        return listaProdutosVendidos;
    }

    public void setProdutosVendidos (List<Artigo> listaProdutosVendidos) {
        this.produtosVendidos = new ArrayList<>();
        for (Artigo a : listaProdutosVendidos) {
            this.produtosVendidos.add(a.clone());
        }
    }

    public List<Artigo> getProdutosVenda() {
        List<Artigo> produtosVendaClone = new ArrayList<>();
        for (Artigo art : this.produtosVenda) {
            produtosVendaClone.add(art.clone());
        }
        return produtosVendaClone;
    }

    public void setProdutosVenda(List<Artigo> produtosVenda) {
        this.produtosVenda = new ArrayList<>();
        for (Artigo art : produtosVenda) {
            this.produtosVenda.add(art.clone());
        }
    }

    public double getValorTotalVendas () {
        return this.valorTotalVendas;
    }

    public void setValorTotalVendas (double valorTotalVendas) {
        this.valorTotalVendas = valorTotalVendas;
    }

    public double getValorTotalVendasTempo() {
        return this.valorTotalVendasTempo;
    }

    public void setValorTotalVendasTempo (double valorTotalVendasTempo) {
        this.valorTotalVendasTempo = valorTotalVendasTempo;
    }

    // Clone
    @Override
    public CompradorVendedor clone() {
        return new CompradorVendedor(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Valor total das compras: ").append(this.valorTotalCompras).append(", ");
        sb.append("Valor total das vendas : ").append(this.valorTotalVendas).append(".");
        for(Artigo a : produtosVenda) {
            sb.append(a);
        }
        sb.append("\n");
        return sb.toString();
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        CompradorVendedor cv = (CompradorVendedor) o;
        return  super.equals(cv);
    }

}