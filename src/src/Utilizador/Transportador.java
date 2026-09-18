package src.Utilizador;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;
import src.Artigo.Artigo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Transportador extends Utilizador implements Serializable {

    private double valorBaseExpedicaoPequena;
    private double valorBaseExpedicaoMedia;
    private double valorBaseExpedicaoGrande;
    private double fatorMultiplicativoImpostos;
    private double margemLucro;
    private boolean transportePremium;
    private String formula;
    private List<Artigo> artigosAEntregar;
    private double precoTotalEntregas;

    // Construtor por Omissão
    public Transportador() {
        super();
        this.fatorMultiplicativoImpostos = 0.0f;
        this.margemLucro = 1.0f;
        this.transportePremium = false;
        this.valorBaseExpedicaoPequena = 1.0f;
        this.valorBaseExpedicaoMedia = 3.0f;
        this.valorBaseExpedicaoGrande = 6.0f;
        this.formula = "(VB * MLT * (1 + I)) * 0.9";
        this.artigosAEntregar = new ArrayList<>();
        this.precoTotalEntregas = 0.0f;
    }

    // Construtor Parametrizado
    public Transportador(String password, String email, String nome, String morada, int numeroFiscal,
                         double valorBaseExpedicaoPequena, double valorBaseExpedicaoMedia,
                         double valorBaseExpedicaoGrande, double fatorMultiplicativoImpostos,
                         double margemLucro, boolean transportePremium, String formula,
                         List<Artigo> artigosAEntregar, double precoTotalEntregas) {
        super(password, email, nome, morada, numeroFiscal);
        this.fatorMultiplicativoImpostos = fatorMultiplicativoImpostos;
        this.margemLucro = margemLucro;
        this.transportePremium = transportePremium;
        this.valorBaseExpedicaoPequena = valorBaseExpedicaoPequena;
        this.valorBaseExpedicaoMedia = valorBaseExpedicaoMedia;
        this.valorBaseExpedicaoGrande = valorBaseExpedicaoGrande;
        this.formula = formula;
        this.artigosAEntregar = artigosAEntregar;
        this.precoTotalEntregas = precoTotalEntregas;
    }

    // Construtor por Cópia
    public Transportador(Transportador transportador) {
        super(transportador);
        this.fatorMultiplicativoImpostos = transportador.getFatorMultiplicativoImpostos();
        this.margemLucro = transportador.getMargemLucro();
        this.transportePremium = transportador.isTransportePremium();
        this.valorBaseExpedicaoPequena = transportador.getValorBaseExpedicaoPequena();
        this.valorBaseExpedicaoMedia = transportador.getValorBaseExpedicaoMedia();
        this.valorBaseExpedicaoGrande = transportador.getValorBaseExpedicaoGrande();
        this.formula = transportador.getFormula();
        this.artigosAEntregar = transportador.getArtigosAEntregar();
        this.precoTotalEntregas = transportador.getPrecoTotalEntregas();
    }

    // Getters e Setters
    public String getFormula() {
        return formula;
    }

    public void setFormula(String formula) {
        this.formula = formula;
    }

    public double getFatorMultiplicativoImpostos() {
        return fatorMultiplicativoImpostos;
    }

    public void setFatorMultiplicativoImpostos(double fatorMultiplicativoImpostos) {
        this.fatorMultiplicativoImpostos = fatorMultiplicativoImpostos;
    }

    public double getMargemLucro() {
        return margemLucro;
    }

    public void setMargemLucro(double margemLucro) {
        this.margemLucro = margemLucro;
    }

    public boolean isTransportePremium() {
        return transportePremium;
    }

    public void setTransportePremium(boolean transportePremium) {
        this.transportePremium = transportePremium;
    }

    public double getValorBaseExpedicaoPequena() {
        return valorBaseExpedicaoPequena;
    }

    public double getValorBaseExpedicaoMedia() {
        return valorBaseExpedicaoMedia;
    }

    public double getValorBaseExpedicaoGrande() {
        return valorBaseExpedicaoGrande;
    }

    public void setValorBaseExpedicaoPequena(double valorBaseExpedicaoPequena) {
        this.valorBaseExpedicaoPequena = valorBaseExpedicaoPequena;
    }

    public void setValorBaseExpedicaoMedia(double valorBaseExpedicaoMedia) {
        this.valorBaseExpedicaoMedia = valorBaseExpedicaoMedia;
    }

    public void setValorBaseExpedicaoGrande(double valorBaseExpedicaoGrande) {
        this.valorBaseExpedicaoGrande = valorBaseExpedicaoGrande;
    }

    public List<Artigo> getArtigosAEntregar() {
        List<Artigo> artigosAEntregar = new ArrayList<>();
        for (Artigo a : this.artigosAEntregar) {
            artigosAEntregar.add(a.clone());
        }
        return artigosAEntregar;
    }

    public void setArtigosAEntregar(List<Artigo> artigosAEntregar) {
        this.artigosAEntregar = new ArrayList<>();
        for (Artigo a : artigosAEntregar) {
            this.artigosAEntregar.add(a.clone());
        }
    }

    public double getPrecoTotalEntregas() {
        return this.precoTotalEntregas;
    }

    public void setPrecoTotalEntregas (double precoTotalEntregas) {
        this.precoTotalEntregas = precoTotalEntregas;
    }

    // Clone
    @Override
    public Transportador clone() {
        return new Transportador(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Transportador : ");
        sb.append(super.toString());
        sb.append("Valor Base de Expedição para Embalagens Pequenas : ")
                .append(this.valorBaseExpedicaoPequena).append(", ");
        sb.append("Valor Base de Expedição para Embalagens Médias : ")
                .append(this.valorBaseExpedicaoMedia).append(", ");
        sb.append("Valor Base de Expedição para Embalagens Grandes : ")
                .append(this.valorBaseExpedicaoGrande).append(", ");
        sb.append("Fator Multiplicativo de Impostos : ")
                .append(this.fatorMultiplicativoImpostos).append(", ");
        sb.append("Margem de Lucro : ").append(this.margemLucro).append(", ");
        if (this.transportePremium) {
            sb.append("TransportePremium : Sim").append(", ");
        } else {
            sb.append("TransportePremium : Não").append(", ");
        }
        sb.append("Formula : ").append(this.formula).append(", ");
        sb.append("Artigos a entregar: ").append(this.artigosAEntregar).append(", ");
        sb.append("Preço total entregas: ").append(this.precoTotalEntregas).append(".\n");
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
        Transportador t = (Transportador) o;
        return  super.equals(t) &&
                this.fatorMultiplicativoImpostos == t.getFatorMultiplicativoImpostos() &&
                this.margemLucro == t.getMargemLucro() &&
                this.transportePremium == t.isTransportePremium() &&
                this.valorBaseExpedicaoPequena == t.getValorBaseExpedicaoPequena() &&
                this.valorBaseExpedicaoMedia == t.getValorBaseExpedicaoMedia() &&
                this.valorBaseExpedicaoGrande == t.getValorBaseExpedicaoGrande() &&
                this.formula.equals(t.getFormula()) &&
                this.artigosAEntregar.equals(t.getArtigosAEntregar()) &&
                this.precoTotalEntregas == t.getPrecoTotalEntregas();
    }

    public double calculateCostGrande() {
        Expression exp = new ExpressionBuilder(this.formula)
                .variables("VB", "MLT", "I")
                .build()
                .setVariable("VB", this.valorBaseExpedicaoGrande)
                .setVariable("MLT", this.margemLucro)
                .setVariable("I", this.fatorMultiplicativoImpostos);

        return exp.evaluate();
    }

    public double calculateCostMedio() {
        Expression exp = new ExpressionBuilder(this.formula)
                .variables("VB", "MLT", "I")
                .build()
                .setVariable("VB", this.valorBaseExpedicaoMedia)
                .setVariable("MLT", this.margemLucro)
                .setVariable("I", this.fatorMultiplicativoImpostos);

        return exp.evaluate();
    }

    public double calculateCostPequeno() {
        Expression exp = new ExpressionBuilder(this.formula)
                .variables("VB", "MLT", "I")
                .build()
                .setVariable("VB", this.valorBaseExpedicaoPequena)
                .setVariable("MLT", this.margemLucro)
                .setVariable("I", this.fatorMultiplicativoImpostos);

        return exp.evaluate();
    }
}