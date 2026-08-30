package co.edu.uptc.sistema_academico.exception;

public class EstudianteNoEncontradoException extends RuntimeException {

    public EstudianteNoEncontradoException(Long id) {
        super("No se encontró el estudiante con id: " + id);
    }
}