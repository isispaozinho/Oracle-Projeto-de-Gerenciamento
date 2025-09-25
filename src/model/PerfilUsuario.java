package model;

public enum PerfilUsuario {
    ADMINISTRADOR("administrador"),
    GERENTE("gerente"),
    COLABORADOR("colaborador");

    private final String value;

    PerfilUsuario(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
