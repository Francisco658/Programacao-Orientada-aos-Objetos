package src.Artigo.Tshirt;

import src.Artigo.Artigo;
import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.io.Serializable;

public class Tshirt extends Artigo implements Serializable {

    // Variaveis de Instancia
    public enum Tamanho {
        S,
        M,
        L,
        XL
    }

    public enum Padrao {
        LISO,
        RISCAS,
        PALMEIRAS
    }

    private Tamanho tamanho;
    private Padrao padrao;
    private int desconto = 50;

    // Construtor por Omissão
    public Tshirt() {
        super();
        this.tamanho = null;
        this.padrao = null;
    }

    // Construtor Parametrizado
    public Tshirt(String titulo, String descricao, long codigo, String marca, double precoBase, int stock,
                  CompradorVendedor vendedor, Transportador transportador, Tamanho tamanho, Padrao padrao,
                  int desconto) {
        super(titulo,descricao,codigo,marca,precoBase,stock,vendedor,transportador);
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.desconto = desconto;
    }

    public Tshirt(String titulo, String descricao, long codigo, String marca, double precoBase, int stock,
                  CompradorVendedor vendedor, Transportador transportador, Tamanho tamanho, Padrao padrao,
                  int desconto, String condicao, int numeroDonos, double estadoUtilizacao) {
        super(titulo,descricao,marca,precoBase,stock,vendedor,transportador,condicao,numeroDonos,estadoUtilizacao);
        this.tamanho = tamanho;
        this.padrao = padrao;
        this.desconto = desconto;
    }

    // Construtor por Cópia
    public Tshirt(Tshirt tshirt) {
        super(tshirt);
        this.tamanho = tshirt.getTamanho();
        this.padrao = tshirt.getPadrao();
        this.desconto = tshirt.getDesconto();
    }

    // Getters e Setters
    public Tamanho getTamanho() {
        return tamanho;
    }

    public void setTamanho(Tamanho tamanho) {
        this.tamanho = tamanho;
    }

    public Padrao getPadrao() {
        return padrao;
    }

    public void setPadrao(Padrao padrao) {
        this.padrao = padrao;
    }

    public int getDesconto() {
        return desconto;
    }

    public void setDesconto(int desconto) {
        this.desconto = desconto;
    }

    // Clone
    @Override
    public Tshirt clone() {
        return new Tshirt(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Tamanho : ").append(tamanho).append(", ");
        sb.append("Padrão : ").append(padrao).append(", ");
        sb.append("Desconto : ").append(desconto).append(".");
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
        Tshirt t = (Tshirt) o;
        return  super.equals(t) &&
                this.tamanho.equals(t.tamanho) &&
                this.padrao.equals(t.padrao) &&
                this.desconto == t.desconto;
    }

    // Função de Cálculo do Preço
    public double calcularPreco() {
        double precoFinal = getPrecoBase();
        if (this.padrao != Padrao.LISO && super.getNumeroDonos() != 0) {
            precoFinal = getPrecoBase() - (getPrecoBase() * (1 - (this.desconto * 0.01)));
        }
        return precoFinal;
    }

}