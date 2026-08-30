package co.edu.uptc.sistema_academico.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> manejarValidaciones(
            MethodArgumentNotValidException ex) {

        Map<String, String> errores = new LinkedHashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errores.put(error.getField(), error.getDefaultMessage())
        );

        Map<String, Object> respuesta = new LinkedHashMap<>();
        respuesta.put("status", 400);
        respuesta.put("mensaje", "Datos inválidos");
        respuesta.put("errores", errores);

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(respuesta);
    }
    @ExceptionHandler(EstudianteNoEncontradoException.class)
public ResponseEntity<Map<String, Object>> manejarEstudianteNoEncontrado(
        EstudianteNoEncontradoException ex) {

    Map<String, Object> respuesta = new LinkedHashMap<>();
    respuesta.put("status", 404);
    respuesta.put("mensaje", ex.getMessage());

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(respuesta);
        }
        @ExceptionHandler(MateriaNoEncontradaException.class)
public ResponseEntity<Map<String, Object>> manejarMateriaNoEncontrada(
        MateriaNoEncontradaException ex) {

    Map<String, Object> respuesta = new LinkedHashMap<>();
    respuesta.put("status", 404);
    respuesta.put("mensaje", ex.getMessage());

    return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(respuesta);
}
}