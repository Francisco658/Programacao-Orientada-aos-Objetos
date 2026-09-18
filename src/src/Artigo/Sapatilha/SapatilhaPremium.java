package src.Artigo.Sapatilha;

import src.Artigo.ArtigoPremium;
import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.awt.*;
import java.io.Serializable;
import java.time.LocalDate;

public class SapatilhaPremium extends Sapatilha implements ArtigoPremium, Serializable {

    // Variaveis de Instancia
    private double valorizacao;

    // Construtor por Omissão
    public SapatilhaPremium() {
        super();
        this.valorizacao = 0.0f;
    }

    // Construtor Parametrizado
    public SapatilhaPremium(String titulo, String descricao, long codigo, String marca, double precoBase,
                            int stock, CompradorVendedor vendedor, Transportador transportador, double tamanho,
                            boolean atacadores, Color cor, LocalDate dataLancamento, double valorizacao) {
        super(titulo,descricao,codigo,marca,precoBase,stock,vendedor,transportador,
                tamanho,atacadores,cor,dataLancamento);
        this.valorizacao = valorizacao;
    }

    public SapatilhaPremium(String titulo, String descricao, long codigo, String marca, double precoBase,
                            int stock, CompradorVendedor vendedor, Transportador transportador, String condicao,
                            int numeroDonos, double estadoUtilizacao, double tamanho, boolean atacadores, Color cor,
                            LocalDate dataLancamento, double valorizacao) {
        super(titulo,descricao,codigo,marca,precoBase,stock,vendedor,transportador,
                condicao,numeroDonos,estadoUtilizacao,tamanho,atacadores,cor,dataLancamento);
        this.valorizacao = valorizacao;
    }

    // Construtor por Cópia
    public SapatilhaPremium(SapatilhaPremium sapatilhaPremium) {
        super(sapatilhaPremium);
        this.valorizacao = sapatilhaPremium.getValorizacao();
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
    public SapatilhaPremium clone() {
        return new SapatilhaPremium(this);
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
        SapatilhaPremium s = (SapatilhaPremium) o;
        return  super.equals(s) &&
                this.valorizacao == s.getValorizacao();
    }

    // Função de Cálculo de Preço
    public double calcularPreco() {
        double precoFinal = super.getPrecoBase();
        double extra = this.valorizacao * 0.5;
        if (super.getDataLancamento().getYear() != LocalDate.now().getYear() && super.getTamanho() > 45
                && super.getNumeroDonos() != 0 && super.getEstadoUtilizacao() != 0) {
            precoFinal = precoFinal -
                    (precoFinal / (super.getNumeroDonos() * super.getEstadoUtilizacao()));
        }
        precoFinal = precoFinal + extra;
        return precoFinal;
    }

}