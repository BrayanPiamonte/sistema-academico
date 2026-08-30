package co.edu.uptc.sistema_academico.dto;

public enum EstudianteSortField {

    NOMBRE("nombre"),
    APELLIDO("apellido"),
    CORREO("correo"),
    PROGRAMA("programa"),
    FECHA_NACIMIENTO("fechaNacimiento");

    private final String value;

    EstudianteSortField(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}