package co.edu.uptc.sistema_academico.dto;

public enum SortDirection {

    ASC("asc"),
    DESC("desc");

    private final String value;

    SortDirection(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}