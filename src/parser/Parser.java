package parser;

import mvc.IModel;
import src.Artigo.Encomenda;
import src.Artigo.Mala.*;
import src.Artigo.Tshirt.Tshirt;
import src.Artigo.Sapatilha.*;
import src.Utilizador.*;
import src.Admin;

import java.awt.Color;
import java.time.LocalDate;
import java.util.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.lang.String;
import java.nio.charset.StandardCharsets;
import java.io.Serializable;

public class Parser implements Serializable {

    private String fileName;

    public Parser() {
        this.fileName = "Logs.txt";
    }

    public Parser(String fileName) {
        this.fileName = fileName;
    }

    public Parser(Parser p) {
        this.fileName = p.getFileName();
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o.getClass() != this.getClass())
            return false;
        Parser p = (Parser) o;
        return this.fileName == p.getFileName();
    }

    public Parser clone() {
        return new Parser(this);
    }

    public String toString() {
        return "Ficheiro que vai ser lido: " + this.fileName + ".\n";
    }

    public void lerFicheiro (IModel model) throws IOException {
        List<String> lines = Collections.emptyList();
        lines = Files.readAllLines(Paths.get(this.fileName), StandardCharsets.UTF_8);
        for (String linha : lines){
            processarLinha(linha,model);
        }
    }

    private void processarLinha(String l, IModel model) {
        String[] elementos = l.split(":", 2);
        switch (elementos[0]) {
            case "CompradorVendedor":
                CompradorVendedor cv = parseCompradorVendedor(elementos[1]);
                model.adicionaCompradorVendedor(cv.clone());
                break;
            case "Transportador":
                Transportador t = parseTransportador(elementos[1],model);
                model.adicionaTransportador(t.clone());
                break;
            case "Admin":
                Admin a = parseAdmin(elementos[1],model);
                model.adicionaAdmin(a);
                break;
            case "Mala":
                Mala m = parseMala(elementos[1],model);
                CompradorVendedor vm = m.getVendedor();
                model.adicionaArtigo(m.clone(),vm);
                break;
            case "Tshirt":
                Tshirt ts = parseTshirt(elementos[1],model);
                CompradorVendedor vt = ts.getVendedor();
                model.adicionaArtigo(ts.clone(),vt);
                break;
            case "Sapatilha":
                Sapatilha s = parseSapatilha(elementos[1],model);
                CompradorVendedor cs = s.getVendedor();
                model.adicionaArtigo(s.clone(),cs);
                break;
            default:
                break;
        }
    }

    public CompradorVendedor parseCompradorVendedor(String str) {
        String[] elementos = str.split(",",5);
        int nif = Integer.parseInt(elementos[3]);
        return new CompradorVendedor(elementos[1], elementos[2], elementos[0], elementos[4], nif, new Encomenda(),
                new ArrayList<>(), new ArrayList<>(), 0.0, 0.0,
                new ArrayList<>(), new ArrayList<>(),new HashMap<>(), new ArrayList<>(), new ArrayList<>(),
                0.0, 0.0);
    }

    public Transportador parseTransportador(String str,IModel model) {
        String[] elementos = str.split(",", 12);
        int nif = Integer.parseInt(elementos[3]);
        double pequeno = Double.parseDouble(elementos[5]);
        double medio = Double.parseDouble(elementos[6]);
        double grande = Double.parseDouble(elementos[7]);
        double fatorM = Double.parseDouble(elementos[8]);
        double margemL = Double.parseDouble(elementos[9]);
        boolean premium = true;
        if (elementos[10].equals("S")) {
            premium = false;
        }
        return new Transportador(elementos[1],elementos[2],elementos[0],elementos[4],nif,
                pequeno,medio,grande,fatorM,margemL,premium,elementos[11],
                new ArrayList<>(),0.0);
    }

    public Admin parseAdmin(String str,IModel model) {
        String[] elementos = str.split(",",3);
        return new Admin(elementos[0],elementos[1],elementos[2]);
    }

    public Mala parseMala(String str, IModel model) {
        String[] elementos = str.split(",", 14);
        Random r = new Random();
        long codigo = (long) (r.nextInt(9) + 1) * 1000000000000L + r.nextLong() % 100000000000L;
        double precoBase = Double.parseDouble(elementos[3]);
        int stock = Integer.parseInt(elementos[4]);
        int numeroDonos = Integer.parseInt(elementos[8]);
        double estadoUtilizacao = Double.parseDouble(elementos[9]);
        double dimensao = Double.parseDouble(elementos[10]);
        int anoColecao = Integer.parseInt(elementos[12]);
        CompradorVendedor vendedor = null;
        for (Map.Entry<String,CompradorVendedor> cv : model.getListaDeCompradoresVendedores().entrySet()) {
            if (cv.getValue().getNome().equals(elementos[5])) {
                vendedor = cv.getValue().clone();
            }
        }
        Transportador transportador = null;
        if (elementos[13].equals("S")){
            for (Transportador t : model.getListaDeTransportadores()) {
                if (t.getNome().equals(elementos[6])){
                    transportador = t.clone();
                }
            }
        } else {
            for (Transportador t : model.getListaDeTransportadoresPremium()) {
                if (t.getNome().equals(elementos[6])){
                    transportador = t.clone();
                }
            }
        }
        if (elementos[13].equals("S")) {
            return new Mala(elementos[0],elementos[1],codigo,elementos[2],precoBase,stock,vendedor,transportador,
                    elementos[7],numeroDonos,estadoUtilizacao,dimensao,elementos[11],anoColecao);
        } else {
            return new MalaPremium(elementos[0],elementos[1],codigo,elementos[2],precoBase,stock,vendedor,
                    transportador, elementos[7],numeroDonos,estadoUtilizacao,dimensao,elementos[11],
                    anoColecao,5.0f);
        }
    }

    public Tshirt parseTshirt(String str, IModel model) {
        String[] elementos = str.split(",", 13);
        Random r = new Random();
        long codigo = (long) (r.nextInt(9) + 1) * 1000000000000L + r.nextLong() % 100000000000L;
        float precoBase = Float.parseFloat(elementos[3]);
        int stock = Integer.parseInt(elementos[4]);
        int numeroDonos = Integer.parseInt(elementos[8]);
        float estadoUtilizacao = Float.parseFloat(elementos[9]);
        CompradorVendedor vendedor = null;
        for (Map.Entry<String,CompradorVendedor> cv : model.getListaDeCompradoresVendedores().entrySet()) {
            if (cv.getValue().getNome().equals(elementos[5])) {
                vendedor = cv.getValue().clone();
            }
        }
        Transportador transportador = null;
        if (elementos[12].equals("S")){
            for (Transportador t : model.getListaDeTransportadores()) {
                if (t.getNome().equals(elementos[6])){
                    transportador = t.clone();
                }
            }
        } else {
            for (Transportador t : model.getListaDeTransportadoresPremium()) {
                if (t.getNome().equals(elementos[6])){
                    transportador = t.clone();
                }
            }
        }
        Tshirt.Tamanho tamanho = null;
        if (elementos[10].equals("S")) {
            tamanho = Tshirt.Tamanho.S;
        } else if (elementos[10].equals("M")) {
            tamanho = Tshirt.Tamanho.M;
        } else if (elementos[10].equals("L")) {
            tamanho = Tshirt.Tamanho.L;
        } else if (elementos[10].equals("XL")) {
            tamanho = Tshirt.Tamanho.XL;
        }
        Tshirt.Padrao padrao = null;
        if (elementos[11].equals("LISO")) {
            padrao = Tshirt.Padrao.LISO;
        } else if (elementos[11].equals("RISCAS")) {
            padrao = Tshirt.Padrao.RISCAS;
        } else if (elementos[11].equals("PALMEIRAS")) {
            padrao = Tshirt.Padrao.PALMEIRAS;
        }
        int desconto = 50;
        return new Tshirt(elementos[0],elementos[1],codigo,elementos[2],precoBase,stock,vendedor,
                transportador,tamanho,padrao,desconto,elementos[7],numeroDonos,estadoUtilizacao);
    }

    public Sapatilha parseSapatilha(String str, IModel model){
        String[] elementos = str.split(",", 15);
        Random r = new Random();
        long codigo = (long) (r.nextInt(9) + 1) * 1000000000000L + r.nextLong() % 100000000000L;
        float precoBase = Float.parseFloat(elementos[3]);
        int stock = Integer.parseInt(elementos[4]);
        int numeroDonos = Integer.parseInt(elementos[8]);
        float estadoUtilizacao = Float.parseFloat(elementos[9]);
        int tamanho = Integer.parseInt(elementos[10]);
        LocalDate dataLancamento = LocalDate.parse(elementos[13]);
        CompradorVendedor vendedor = null;
        for (Map.Entry<String,CompradorVendedor> cv : model.getListaDeCompradoresVendedores().entrySet()) {
            if (cv.getValue().getNome().equals(elementos[5])) {
                vendedor = cv.getValue().clone();
            }
        }
        Transportador transportador = null;
        if (elementos[14].equals("S")){
            for (Transportador t : model.getListaDeTransportadores()) {
                if (t.getNome().equals(elementos[6])){
                    transportador = t.clone();
                }
            }
        } else {
            for (Transportador t : model.getListaDeTransportadoresPremium()) {
                if (t.getNome().equals(elementos[6])){
                    transportador = t.clone();
                }
            }
        }
        boolean atacadores = true;
        if (elementos[11].equals("false")) {
            atacadores = false;
        }
        Color cor = null;
        if (elementos[13].equals("Red")) {
            cor = Color.RED;
        } else if (elementos[12].equals("Blue")) {
            cor = Color.BLUE;
        } else if (elementos[12].equals("Green")) {
            cor = Color.GREEN;
        } else if (elementos[12].equals("Black")) {
            cor = Color.BLACK;
        } else {
            cor = Color.WHITE;
        }
        if (elementos[14].equals("S")) {
            return new Sapatilha(elementos[0],elementos[1],codigo,elementos[2],precoBase,stock,vendedor,
                    transportador, elementos[7],numeroDonos,estadoUtilizacao,tamanho,atacadores,
                    cor,dataLancamento);
        } else {
            return new SapatilhaPremium(elementos[0],elementos[1],codigo,elementos[2],precoBase,stock,vendedor,
                    transportador,elementos[7],numeroDonos,estadoUtilizacao,tamanho,atacadores,
                    cor,dataLancamento,5.0f);
        }
    }
}