package model;

import java.io.Serializable;

public class Usuario implements Serializable {
    @Override
    public String toString() {
        return nomeCompleto + " (" + login + ")";
    }
    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String email;
    private PerfilUsuario perfil;
    private String login;
    private String senha;

    public Usuario(String nomeCompleto, String cpf, String email, PerfilUsuario perfil, String login, String senha, Integer id) {
        this.id = id;
        this.nomeCompleto = nomeCompleto;
        this.cpf = cpf;
        this.email = email;
        this.perfil = perfil;
        this.login = login;
        this.senha = senha;
    }

    public Usuario(String nomeCompleto, String cpf, String email, PerfilUsuario perfil, String login, String senha) {
        this(nomeCompleto, cpf, email, perfil, login, senha, null);
    }

    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public String getNomeCompleto() { return nomeCompleto; }
    public void setNomeCompleto(String nomeCompleto) { this.nomeCompleto = nomeCompleto; }
    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public PerfilUsuario getPerfil() { return perfil; }
    public void setPerfil(PerfilUsuario perfil) { this.perfil = perfil; }
    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}
