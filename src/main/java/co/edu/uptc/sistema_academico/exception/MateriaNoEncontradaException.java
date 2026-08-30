package co.edu.uptc.sistema_academico.exception;

public class MateriaNoEncontradaException extends RuntimeException {

    public MateriaNoEncontradaException(Long id) {
        super("No se encontró la materia con id: " + id);
    }
}