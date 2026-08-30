package co.edu.uptc.sistema_academico.dto;

import java.time.LocalDate;

public class EstudianteResponse {

    private Long id;
    private String nombres;
    private String apellidos;
    private String correo;
    private LocalDate fechaNacimiento;
    private String programa;

    public EstudianteResponse() {
    }

    public EstudianteResponse(
            Long id,
            String nombres,
            String apellidos,
            String correo,
            LocalDate fechaNacimiento,
            String programa) {

        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correo = correo;
        this.fechaNacimiento = fechaNacimiento;
        this.programa = programa;
    }

    public Long getId() {
        return id;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getPrograma() {
        return programa;
    }
}