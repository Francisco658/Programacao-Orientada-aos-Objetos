package src.Artigo.Sapatilha;

import exceptions.DadosInvalidos;
import src.Artigo.Artigo;
import src.Utilizador.CompradorVendedor;
import src.Utilizador.Transportador;

import java.io.Serializable;
import java.time.LocalDate;
import java.awt.Color;

public class Sapatilha extends Artigo implements Serializable {

    // Variaveis de Instancia
    private double tamanho;
    private boolean atacadores;
    private Color cor; // String Cor;
    private LocalDate dataLancamento;

    // Construtor por Omissão
    public Sapatilha() {
        super();
        this.tamanho = 0;
        this.atacadores = false;
        // this.cor = null;
        this.cor = Color.RED;
        // this.dataLancamento = null;
        this.dataLancamento = LocalDate.now();
    }

    // Construtor Parametrizado
    public Sapatilha(String titulo, String descricao, long codigo, String marca, double precoBase, int stock,
                     CompradorVendedor vendedor, Transportador transportador, double tamanho,
                     boolean atacadores, Color cor, LocalDate dataLancamento) {
        super(titulo,descricao,codigo,marca,precoBase,stock,vendedor,transportador);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.dataLancamento = dataLancamento;
    }

    public Sapatilha(String titulo, String descricao, long codigo, String marca, double precoBase,
                     int stock, CompradorVendedor vendedor, Transportador transportador, String condicao,
                     int numeroDonos, double estadoUtilizacao, double tamanho, boolean atacadores,
                     Color cor, LocalDate dataLancamento) {
        super(titulo,descricao,marca,precoBase,stock,vendedor,transportador,condicao,numeroDonos,estadoUtilizacao);
        this.tamanho = tamanho;
        this.atacadores = atacadores;
        this.cor = cor;
        this.dataLancamento = dataLancamento;
    }

    // Construtor por Cópia
    public Sapatilha(Sapatilha sapatilha) {
        super(sapatilha);
        this.tamanho = sapatilha.getTamanho();
        this.atacadores = sapatilha.isAtacadores();
        this.cor = sapatilha.getCor();
        this.dataLancamento = sapatilha.getDataLancamento();
    }

    // Getters e Setters
    public double getTamanho() {
        return tamanho;
    }

    public void setTamanho(double tamanho) {
        this.tamanho = tamanho;
    }

    public boolean isAtacadores() {
        return atacadores;
    }

    public void setAtacadores(boolean atacadores) {
        this.atacadores = atacadores;
    }

    public Color getCor() {
        return cor;
    }

    public void setCor(Color cor) {
        this.cor = cor;
    }

    public LocalDate getDataLancamento() {
        return dataLancamento;
    }

    public void setDataLancamento(LocalDate dataLancamento) {
        this.dataLancamento = dataLancamento;
    }

    // Clone
    @Override
    public Sapatilha clone() {
        return new Sapatilha(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Tamanho : ").append(tamanho).append(", ");
        if (this.atacadores) {
            sb.append("Atacadores : Sim").append(", ");
        } else {
            sb.append("Atacadores : Não").append(", ");
        }
        sb.append("Cor : ").append(cor).append(", ");
        sb.append("Data de Lançamento : ").append(dataLancamento).append(".");
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
        Sapatilha s = (Sapatilha) o;
        return  this.tamanho == s.tamanho &&
                this.atacadores == s.atacadores &&
                this.cor.equals(s.cor) &&
                this.dataLancamento.equals(s.dataLancamento);
    }

    // Função de Cálculo de Preço
    public double calcularPreco() {
        double precoFinal = super.getPrecoBase();
        if (this.dataLancamento.getYear() != LocalDate.now().getYear() && this.tamanho > 45
                && super.getNumeroDonos() != 0 && super.getEstadoUtilizacao() != 0) {
            precoFinal = precoFinal -
                    (precoFinal / (super.getNumeroDonos() * super.getEstadoUtilizacao()));
        }
        return precoFinal;
    }

    // Função de Validação de Dados
    public boolean validarDados() throws DadosInvalidos {
        if (tamanho < 34 || tamanho > 47) {
            throw new DadosInvalidos("O tamanho está fora do intervalo permitido.");
        }
        if (cor == null) {
            throw new DadosInvalidos("A cor não foi especificada.");
        }
        try {
            Color.getColor(cor.toString());
        } catch (IllegalArgumentException e) {
            throw new DadosInvalidos("A cor especificada é inválida.");
        }
        return true;
    }
}