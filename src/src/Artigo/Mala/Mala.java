package src.Artigo.Mala;

import src.Artigo.Artigo;
import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.io.Serializable;

public class Mala extends Artigo implements Serializable {

    // Variaveis de Instancia
    private double dimensao;
    private String material;
    private int anoColecao;

    // Construtor por Omissão
    public Mala() {
        super();
        this.dimensao = 0;
        this.material = "";
        this.anoColecao = 0;
    }

    // Construtor Parametrizado
    public Mala(String titulo, String descricao, long codigo, String marca, double precoBase, int stock,
                CompradorVendedor vendedor, Transportador transportador, double dimensao, String material,
                int anoColecao) {
        super(titulo, descricao, codigo, marca, precoBase, stock, vendedor, transportador);
        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    public Mala(String titulo, String descricao, long codigo, String marca, double precoBase,
                int stock, CompradorVendedor vendedor, Transportador transportador, String condicao,
                int numeroDonos, double estadoUtilizacao, double dimensao, String material, int anoColecao) {
        super(titulo, descricao, marca, precoBase, stock, vendedor, transportador, condicao, numeroDonos,
                estadoUtilizacao);
        this.dimensao = dimensao;
        this.material = material;
        this.anoColecao = anoColecao;
    }

    // Construtor por Cópia
    public Mala(Mala mala) {
        super(mala);
        this.dimensao = mala.getDimensao();
        this.material = mala.getMaterial();
        this.anoColecao = mala.getAnoColecao();
    }

    // Getters e Setters
    public double getDimensao() {
        return dimensao;
    }

    public void setDimensao(double dimensao) {
        this.dimensao = dimensao;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public int getAnoColecao() {
        return anoColecao;
    }

    public void setAnoColecao(int anoColecao) {
        this.anoColecao = anoColecao;
    }

    // Clone
    @Override
    public Mala clone() {
        return new Mala(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Dimensão : ").append(dimensao).append(", ");
        sb.append("Material : ").append(material).append(", ");
        sb.append("Ano Coleção : ").append(anoColecao).append(".");
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
        Mala m = (Mala) o;
        return super.equals(m) &&
                this.dimensao == m.getDimensao() &&
                this.material.equals(m.getMaterial()) &&
                this.anoColecao == m.getAnoColecao();
    }

    public double calcularPreco() {
        double preco = super.getPrecoBase();
        if (super.getNumeroDonos() != 0) {
            preco = preco - (preco / (super.getNumeroDonos() * super.getEstadoUtilizacao() * this.dimensao));
        }
        return preco;
    }
}