package src;

import java.io.Serializable;

public class Admin implements Serializable {

    private String nome;
    private String email;
    private String password;

    // Construtor por Omissão
    public Admin() {
        this.nome = "";
        this.email = "";
        this.password = "";
    }

    // Construtor Parametrizado
    public Admin (String nome, String email, String password) {
        this.nome = nome;
        this.email = email;
        this.password = password;
    }

    // Construtor por Cópia
    public Admin (Admin admin) {
        this.nome = admin.getNome();
        this.email = admin.getEmail();
        this.password = admin.getPassword();
    }

    // Getters e Setters
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Clone
    @Override
    public Admin clone() {
        return new Admin(this);
    }

    // ToString
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Admin: ").append(this.nome).append(", ");
        sb.append("Email: ").append(this.email).append("\n");
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
        Admin a = (Admin) o;
        return  this.getNome().equals(a.getNome()) &&
                this.getEmail().equals(a.getEmail()) &&
                this.getPassword().equals(a.getPassword());
    }
}