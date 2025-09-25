package model;

public enum StatusProjeto {
    PLANEJADO("planejado"),
    EM_ANDAMENTO("em andamento"),
    CONCLUIDO("concluído"),
    CANCELADO("cancelado");

    private final String value;

    StatusProjeto(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
