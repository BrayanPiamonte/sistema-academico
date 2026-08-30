package co.edu.uptc.sistema_academico.exception;

public class InscripcionNoEncontradaException extends RuntimeException {

    public InscripcionNoEncontradaException(Long id) {
        super("No se encontró la inscripción con id: " + id);
    }
}