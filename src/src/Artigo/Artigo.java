package src.Artigo;

import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.time.LocalDate;
import java.util.Random;
import java.io.Serializable;

public abstract class Artigo implements Serializable {

    // Variaveis de Instancia
    private String titulo;
    private String descricao;
    private long codigo;
    private String marca;
    private double precoBase;
    private int stock;
    private LocalDate dataEntrega;
    private CompradorVendedor vendedor;
    private CompradorVendedor comprador;
    private Transportador transportador;
    private String condicao;
    private int numeroDonos;
    private double estadoUtilizacao;

    // Construtor por Omissão
    public Artigo() {
        this.titulo = "";
        this.descricao = "";
        this.codigo = 0;
        this.marca = "";
        this.precoBase = 0;
        this.stock = 0;
        this.dataEntrega = null;
        this.vendedor = null;
        this.comprador = null;
        this.transportador = null;
        this.condicao = "";
        this.numeroDonos = 0;
        this.estadoUtilizacao = 10.0f;
    }

    // Construtor Parametrizado
    public Artigo(String titulo, String descricao, String marca, double precoBase, int stock,
                  CompradorVendedor vendedor ,Transportador transportador, String condicao,
                  int numeroDonos, double estadoUtilizacao) {
        this.titulo = titulo;
        this.descricao = descricao;
        Random r = new Random();
        this.codigo = (long) (r.nextInt(9) + 1) * 1000000000000L + r.nextLong() % 100000000000L;
        this.marca = marca;
        this.precoBase = precoBase;
        this.stock = stock;
        this.vendedor = vendedor;
        this.transportador = transportador;
        this.condicao = condicao;
        this.numeroDonos = numeroDonos;
        this.estadoUtilizacao = estadoUtilizacao;
    }

    public Artigo(String titulo, String descricao, long codigo, String marca, double precoBase,
                  int stock, CompradorVendedor vendedor,Transportador transportador) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.codigo = codigo;
        this.marca = marca;
        this.precoBase = precoBase;
        this.stock = stock;
        this.vendedor = vendedor;
        this.transportador = transportador;
    }

    // Construtor por Cópia
    public Artigo(Artigo artigo) {
        this.titulo = artigo.getTitulo();
        this.descricao = artigo.getDescricao();
        this.codigo = artigo.getCodigo();
        this.marca = artigo.getMarca();
        this.precoBase = artigo.getPrecoBase();
        this.stock = artigo.getStock();
        this.dataEntrega = artigo.getDataEntrega();
        this.vendedor = artigo.getVendedor();
        this.comprador = artigo.getComprador();
        this.transportador = artigo.getTransportador();
        this.condicao = artigo.getCondicao();
        this.numeroDonos = artigo.getNumeroDonos();
        this.estadoUtilizacao = artigo.getEstadoUtilizacao();
    }

    // Getters e Setters
    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo (String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(long codigo) {
        this.codigo = codigo;
    }

    public String getMarca() {
        return this.marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecoBase() {
        return this.precoBase;
    }

    public void setPrecoBase(double precoBase) {
        this.precoBase = precoBase;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock (int stock) {
        this.stock = stock;
    }

    public LocalDate getDataEntrega() {
        return this.dataEntrega;
    }

    public void setDataEntrega (LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    public CompradorVendedor getVendedor() {
        return this.vendedor;
    }

    public void setVendedor (CompradorVendedor vendedor) {
        this.vendedor = vendedor;
    }

    public CompradorVendedor getComprador() {
        return this.comprador;
    }

    public void setComprador (CompradorVendedor comprador) {
        this.comprador = comprador;
    }

    public Transportador getTransportador() {
        return this.transportador;
    }

    public void setTransportador(Transportador transportador) {
        this.transportador = transportador;
    }

    public String getCondicao() {
        return this.condicao;
    }

    public void setCondicao (String condicao) {
        this.condicao = condicao;
    }

    public int getNumeroDonos() {
        return this.numeroDonos;
    }

    public void setNumeroDonos (int numeroDonos) {
        this.numeroDonos = numeroDonos;
    }

    public double getEstadoUtilizacao() {
        return this.estadoUtilizacao;
    }

    public void setEstadoUtilizacao (double estadoUtilizacao) {
        this.estadoUtilizacao = estadoUtilizacao;
    }

    // Clone
    @Override
    public abstract Artigo clone();

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artigo : ").append(this.titulo).append(", ");
        sb.append("Descrição : ").append(this.descricao).append(", ");
        sb.append("Código : ").append(this.codigo).append(", ");
        sb.append("Marca : ").append(this.marca).append(", ");
        sb.append("Preço Base : ").append(String.format("%.2f", this.precoBase)).append(", ");
        if (this.dataEntrega != null) {
            sb.append("Data de Entrega: ").append(this.dataEntrega);
        }
        sb.append("Vendedor : ").append(this.vendedor.getNome()).append(", ");
        if (this.comprador != null) {
            sb.append("Comprador : ").append(this.comprador.getNome());
        }
        sb.append("Transportador : ").append(this.transportador.getNome()).append(", ");
        sb.append("Stock : ").append(this.stock).append(", ");
        sb.append("Condição : ").append(this.condicao).append(", ");
        sb.append("Número de Donos : ").append(this.numeroDonos).append(", ");
        sb.append("Estado de Utilização : ").append(this.estadoUtilizacao).append(", ");
        return sb.toString();
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Artigo artigo = (Artigo) o;
        return  this.titulo.equals(artigo.getTitulo()) &&
                this.codigo == artigo.getCodigo() &&
                this.precoBase == artigo.getPrecoBase() &&
                this.descricao.equals(artigo.getDescricao()) &&
                this.marca.equals(artigo.getMarca()) &&
                this.stock == artigo.getStock() &&
                this.vendedor.equals(artigo.getVendedor()) &&
                this.transportador.equals(artigo.getTransportador()) &&
                this.condicao.equals(artigo.getCondicao()) &&
                this.numeroDonos == artigo.getNumeroDonos() &&
                this.estadoUtilizacao == artigo.getEstadoUtilizacao();
    }

    public abstract double calcularPreco();
}