package co.edu.uptc.sistema_academico.dto;

public enum MateriaSortField {

    NOMBRE("nombre"),
    CODIGO("codigo"),
    CREDITOS("creditos"),
    SEMESTRE("semestre");

    private final String value;

    MateriaSortField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}