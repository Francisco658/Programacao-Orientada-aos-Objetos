package src.Artigo.Mala;

import src.Artigo.ArtigoPremium;
import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.io.Serializable;

public class MalaPremium extends Mala implements ArtigoPremium, Serializable {

    // Variaveis de Instancia
    private double valorizacao;

    // Construtor por Omissão
    public MalaPremium() {
        super();
        this.valorizacao = 0.0f;
    }

    // Construtor Parametrizado
    public MalaPremium(String titulo, String descricao, long codigo, String marca, double precoBase,
                       int stock, CompradorVendedor vendedor, Transportador transportador, double dimensao,
                       String material, int anoColecao, double valorizacao) {
        super(titulo,descricao,codigo,marca,precoBase,stock, vendedor, transportador,dimensao,material,anoColecao);
        this.valorizacao = valorizacao;
    }

    public MalaPremium(String titulo, String descricao, long codigo, String marca, double precoBase,
                       int stock, CompradorVendedor vendedor, Transportador transportador, String condicao,
                       int numeroDonos, double estadoUtilizacao, double dimensao, String material,
                       int anoColecao, double valorizacao) {
        super(titulo,descricao,codigo,marca,precoBase,stock,vendedor,transportador,condicao,
                numeroDonos,estadoUtilizacao,dimensao,material,anoColecao);
        this.valorizacao = valorizacao;
    }

    // Construtor por Cópia
    public MalaPremium(MalaPremium malaPremium) {
        super(malaPremium);
        this.valorizacao = malaPremium.getValorizacao();
    }

    // Getters e Setters
    public double getValorizacao() {
        return this.valorizacao;
    }

    public void setValorizacao (double valorizacao) {
        this.valorizacao = valorizacao;
    }

    // Clone
    @Override
    public MalaPremium clone() {
        return new MalaPremium(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
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
        MalaPremium m = (MalaPremium) o;
        return  super.equals(m) &&
                this.valorizacao == m.getValorizacao();
    }

    // Função de Cálculo de Preço
    public double calcularPreco () {
        double precoFinal = super.getPrecoBase();
        double extra = super.getAnoColecao() * this.valorizacao;
        precoFinal = precoFinal + extra;
        if (super.getNumeroDonos() != 0) {
            precoFinal = precoFinal - (precoFinal /
                    (super.getNumeroDonos() * super.getEstadoUtilizacao() * super.getDimensao()));
        }
        return precoFinal;
    }
}