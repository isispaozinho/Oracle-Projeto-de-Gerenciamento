package data;

import model.Usuario;
import model.Projeto;
import model.PerfilUsuario;

import java.util.*;
import java.io.*;
import java.time.LocalDate;

public class Repository {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Projeto> projetos = new ArrayList<>();
    private List<model.Equipe> equipes = new ArrayList<>();
    private static final String USUARIOS_FILE = "data/usuarios.json";
    private static final String PROJETOS_FILE = "data/projetos.json";
    private static final String EQUIPES_FILE = "data/equipes.json";

    public Repository() {
    loadData();
    }

    private void loadData() {
    usuarios = JsonUtil.readListFromJson(USUARIOS_FILE, new com.google.gson.reflect.TypeToken<List<Usuario>>() {});
    projetos = JsonUtil.readListFromJson(PROJETOS_FILE, new com.google.gson.reflect.TypeToken<List<Projeto>>() {});
    equipes = JsonUtil.readListFromJson(EQUIPES_FILE, new com.google.gson.reflect.TypeToken<List<model.Equipe>>() {});
    }

    private void saveUsuarios() {
    JsonUtil.writeListToJson(USUARIOS_FILE, usuarios);
    }

    private void saveEquipes() {
        JsonUtil.writeListToJson(EQUIPES_FILE, equipes);
    }

    private void saveProjetos() {
    JsonUtil.writeListToJson(PROJETOS_FILE, projetos);
    }

    public Usuario addUsuario(Usuario usuario) {
        int lastId = usuarios.isEmpty() ? 0 : usuarios.get(usuarios.size() - 1).getId();
        usuario.setId(lastId + 1);
        usuarios.add(usuario);
        saveUsuarios();
        return usuario;
    }

    public Usuario getUsuarioById(int id) {
        return usuarios.stream().filter(u -> Objects.equals(u.getId(), id)).findFirst().orElse(null);
    }

    public Usuario getUsuarioByLogin(String login) {
        return usuarios.stream().filter(u -> u.getLogin().equalsIgnoreCase(login)).findFirst().orElse(null);
    }

    public List<Usuario> getAllUsuarios() {
        return usuarios;
    }

    public Projeto addProjeto(Projeto projeto) {
        int lastId = projetos.isEmpty() ? 0 : projetos.get(projetos.size() - 1).getId();
        projeto.setId(lastId + 1);
        projetos.add(projeto);
        saveProjetos();
        return projeto;
    }

    public List<Projeto> getAllProjetos() {
        return projetos;
    }

    public model.Equipe addEquipe(model.Equipe equipe) {
        int lastId = equipes.isEmpty() ? 0 : equipes.get(equipes.size() - 1).getId() == null ? 0 : equipes.get(equipes.size() - 1).getId();
        equipe.setId(lastId + 1);
        equipes.add(equipe);
        saveEquipes();
        return equipe;
    }

    public List<model.Equipe> getAllEquipes() {
        return equipes;
    }

    public model.Equipe getEquipeById(int id) {
        return equipes.stream().filter(e -> e.getId() != null && e.getId() == id).findFirst().orElse(null);
    }
}
