package src.Artigo;

import src.Utilizador.Transportador;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

public class Encomenda implements Serializable {
    public enum Embalagem {
        GRANDE,
        MEDIO,
        PEQUENO
    }

    public enum Estado {
        PENDENTE,
        FINALIZADA,
        EXPEDIDA
    }

    final double TAXA_ARTIGO_NOVO = 0.5;
    final double TAXA_ARTIGO_USADO = 0.25;

    private static int ultimoCodigo = 0;
    private List<Artigo> artigos;
    private Embalagem embalagem;
    private double precoFinal;
    private double custosExpedicao;
    private Estado estado;
    private LocalDate dataCriacao;
    private LocalDate dataExpedicao;
    private LocalDate dataEntrega;

    // Construtor por Omissão
    public Encomenda() {
        this.artigos = new ArrayList<Artigo>();
        this.embalagem = Embalagem.PEQUENO;
        this.precoFinal = 0.0f;
        this.custosExpedicao = 0.0f;
        this.estado = Estado.PENDENTE;
        this.dataCriacao = LocalDate.now();
        this.dataExpedicao = LocalDate.now();
        this.dataEntrega = null;
    }

    // Construtor Parametrizado
    public Encomenda(List<Artigo> artigos, Embalagem embalagem, double precoFinal, double custosExpedicao,
                     Estado estado, LocalDate dataCriacao, LocalDate dataExpedicao ,LocalDate dataEntregaPrevista) {
        this.artigos = artigos;
        this.embalagem = embalagem;
        this.precoFinal = precoFinal;
        this.custosExpedicao = custosExpedicao;
        this.estado = estado;
        this.dataCriacao = dataCriacao;
        this.dataExpedicao = dataExpedicao;
        this.dataEntrega = dataEntregaPrevista;
    }

    // Construtor por Cópia
    public Encomenda(Encomenda outra) {
        this.artigos = new ArrayList<Artigo>(outra.getArtigos());
        this.embalagem = outra.getEmbalagem();
        this.precoFinal = outra.getPrecoFinal();
        this.custosExpedicao = outra.getCustosExpedicao();
        this.estado = outra.getEstado();
        this.dataCriacao = outra.getDataCriacao();
        this.dataExpedicao = outra.getDataExpedicao();
        this.dataEntrega = outra.getDataEntrega();
    }

    // Getters e Setters
    public List<Artigo> getArtigos() {
        List<Artigo> listaArtigos = new ArrayList<Artigo>();
        for (Artigo a : this.artigos) {
            listaArtigos.add(a.clone());
        }
        return listaArtigos;
    }

    public void setArtigos(List<Artigo> listaArtigos) {
        this.artigos = new ArrayList<Artigo>();
        for (Artigo a : listaArtigos) {
            this.artigos.add(a.clone());
        }
    }

    public Embalagem getEmbalagem() {
        return embalagem;
    }

    public void setEmbalagem(Embalagem embalagem) {
        this.embalagem = embalagem;
    }

    public double getPrecoFinal() {
        return this.precoFinal;
    }

    public void setPrecoFinal(double precoFinal) {
        this.precoFinal = precoFinal;
    }

    public double getCustosExpedicao() {
        return this.custosExpedicao;
    }

    public void setCustosExpedicao(double custosExpedicao) {
        this.custosExpedicao = custosExpedicao;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDate getDataExpedicao() {
        return this.dataExpedicao;
    }

    public void setDataExpedicao (LocalDate dataExpedicao) {
        this.dataExpedicao = dataExpedicao;
    }

    public LocalDate getDataEntrega() {
        return dataEntrega;
    }

    public void setDataEntrega(LocalDate dataEntrega) {
        this.dataEntrega = dataEntrega;
    }

    // Clone
    @Override
    public Encomenda clone() {
        return new Encomenda(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Artigos: ").append("\n");
        for (Artigo a : this.artigos) {
            sb.append(a).append("\n");
        }
        if (this.embalagem.equals(Embalagem.GRANDE)) {
            sb.append("Embalagem : Grande, ");
        } else if (this.embalagem.equals(Embalagem.MEDIO)) {
            sb.append("Embalagem : Médio, ");
        } else {
            sb.append("Embalagem : Pequeno, ");
        }
        sb.append("Preço Final : ").append(String.format("%.2f",this.precoFinal)).append(", ");
        sb.append("Custos Expedição : ").append(String.format("%.2f",this.custosExpedicao)).append(", ");
        sb.append("Estado : ").append(estado).append(", ");
        sb.append("Data da Criação : ").append(dataCriacao).append(", ");
        sb.append("Data de Expedição : ").append(this.dataExpedicao).append(", ");
        sb.append("Data prevista de Entrega : ").append(dataEntrega).append(".\n");
        return sb.toString();
    }

    // Equals
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (o == null || o.getClass() != this.getClass()) {
            return false;
        }
        Encomenda e = (Encomenda) o;
        return this.artigos.equals(e.artigos) &&
                this.embalagem == e.embalagem &&
                this.precoFinal == e.precoFinal &&
                this.custosExpedicao == e.custosExpedicao &&
                this.estado == e.estado &&
                this.dataCriacao.equals(e.dataCriacao) &&
                this.dataExpedicao.equals(dataExpedicao) &&
                this.dataEntrega.equals(e.dataEntrega);
    }

   // Retorna a encomenda, caso esteja dentro do prazo para devolução
    public Encomenda devolverEncomenda() {
        if (this.estado == Estado.EXPEDIDA && LocalDate.now().isBefore(this.dataEntrega)) {
            // Cria uma nova encomenda igual à atual
            Encomenda novaEncomenda = new Encomenda(this.artigos, this.embalagem, this.precoFinal,
                    this.custosExpedicao, Estado.FINALIZADA, this.dataCriacao, this.dataExpedicao,
                    this.dataEntrega);
            return novaEncomenda;
        } else {
            return null;
        }
    }

    public void adicionarArtigo(Artigo artigo) {
        this.artigos.add(artigo.clone());
    }

    public double calcularPrecoFinal() {
        double precoFinal = 0.0f;
        for (Artigo a : this.artigos) {
            precoFinal = precoFinal + a.calcularPreco();
            if (a.getNumeroDonos() == 0) {
                precoFinal = precoFinal + TAXA_ARTIGO_NOVO;
            } else {
                precoFinal = precoFinal + TAXA_ARTIGO_USADO;
            }
        }
        return (0.01 * precoFinal);
    }

    public double calcularCustosExpedicao() {
        Map<Transportador, Integer> transportadorNum = new HashMap<>();
        double custosExpedicao = 0;
        for (Artigo a : this.artigos) {
            Transportador t = a.getTransportador();
            boolean found = false;
            for (Map.Entry<Transportador, Integer> entry : transportadorNum.entrySet()) {
                if (entry.getKey().equals(t)) {
                    int num = entry.getValue();
                    num++;
                    transportadorNum.put(entry.getKey(), num);
                    found = true;
                    break;
                }
            }
            if (!found) {
                transportadorNum.put(t, 1);
            }
        }
        for (Map.Entry<Transportador, Integer> entry : transportadorNum.entrySet()) {
            int num = entry.getValue();
            Transportador t = entry.getKey();
            double custo = t.getPrecoTotalEntregas();
            if (num == 1) {
                custosExpedicao = 0.3 * (custosExpedicao + t.calculateCostPequeno());
            } else if (num >= 2 && num < 5) {
                custosExpedicao = 0.2 * (custosExpedicao + t.calculateCostMedio());
            } else if (num >= 5) {
                custosExpedicao = 0.1 * (custosExpedicao + t.calculateCostGrande());
            }
            custo = custo + custosExpedicao;
            t.setPrecoTotalEntregas(custo);
        }
        this.setCustosExpedicao(custosExpedicao);
        return custosExpedicao;
    }

    public Embalagem atualizaEmbalagem () {
        Embalagem embalagem1 = this.getEmbalagem();
        if (this.artigos.size() == 1) {
            embalagem1 = Embalagem.PEQUENO;
        } else if (this.artigos.size() > 2 && this.artigos.size() < 5) {
            embalagem1 = Embalagem.MEDIO;
        } else {
            embalagem1 = Embalagem.GRANDE;
        }
        setEmbalagem(embalagem1);
        return embalagem1;
    }
}