package src.Utilizador;

import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class Utilizador implements Serializable {

    private String password;
    private String email;
    private String nome;
    private String morada;
    private int numeroFiscal;

    // Construtor por Omissão
    public Utilizador() {
        this.password = "";
        this.email = "";
        this.nome = "";
        this.morada = "";
        this.numeroFiscal = 0;
    }

    // Construtor Parametrizado
    public Utilizador(String email, String password, String nome, String morada, int numeroFiscal) {
        this.password = password;
        this.email = email;
        this.nome = nome;
        this.morada = morada;
        this.numeroFiscal = numeroFiscal;
    }

    // Construtor por Cópia
    public Utilizador(Utilizador utilizador) {
        this.password = utilizador.getPassword();
        this.email = utilizador.getEmail();
        this.nome = utilizador.getNome();
        this.morada = utilizador.getMorada();
        this.numeroFiscal = utilizador.getNumeroFiscal();
    }

    // Getters e Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public int getNumeroFiscal() {
        return numeroFiscal;
    }

    public void setNumeroFiscal(int numeroFiscal) {
        this.numeroFiscal = numeroFiscal;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Clone
    @Override
    public Utilizador clone() {
        return new Utilizador(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Nome: ").append(nome).append(", ");
        sb.append("Email: ").append(email).append(", ");
        sb.append("Morada: ").append(morada).append(", ");
        sb.append("Número Fiscal:").append(numeroFiscal).append(", ");
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
        Utilizador u = (Utilizador) o;
        return  this.password.equals(getPassword()) &&
                this.email.equals(u.getEmail()) &&
                this.nome.equals(u.getNome()) &&
                this.morada.equals(u.getMorada()) &&
                this.numeroFiscal == u.getNumeroFiscal();
    }
}