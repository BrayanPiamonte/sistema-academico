package co.edu.uptc.sistema_academico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class InscripcionRequest {

    @NotNull(message = "El estudiante es obligatorio")
    private Long estudianteId;

    @NotNull(message = "La materia es obligatoria")
    private Long materiaId;

    @NotBlank(message = "El periodo es obligatorio")
    private String periodo;

    public InscripcionRequest() {
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Long getMateriaId() {
        return materiaId;
    }

    public void setMateriaId(Long materiaId) {
        this.materiaId = materiaId;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
}