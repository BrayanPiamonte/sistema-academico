package co.edu.uptc.sistema_academico.dto;

public class MateriaResponse {

    private Long id;
    private String nombre;
    private String codigo;
    private Integer creditos;
    private Integer semestre;

    public MateriaResponse() {
    }

    public MateriaResponse(
            Long id,
            String nombre,
            String codigo,
            Integer creditos,
            Integer semestre) {

        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.creditos = creditos;
        this.semestre = semestre;
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public Integer getCreditos() {
        return creditos;
    }

    public Integer getSemestre() {
        return semestre;
    }
}