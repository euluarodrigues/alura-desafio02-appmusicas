package br.com.alura.appmusicas.domain;

public enum Tipo {
    SOLO("solo"),
    DUPLA("dupla"),
    BANDA("dupla");

    private String tipo;

    Tipo(String tipo) {
        this.tipo = tipo;
    }

    public static Tipo fromString(String text) {
        for (Tipo tipo : Tipo.values()) {
            if (tipo.tipo.equalsIgnoreCase(text)) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("Nenhuma categoria encontrada para a série");
    }
}
