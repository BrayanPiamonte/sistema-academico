package co.edu.uptc.sistema_academico.service;

import co.edu.uptc.sistema_academico.dto.InscripcionRequest;
import co.edu.uptc.sistema_academico.entity.Estudiante;
import co.edu.uptc.sistema_academico.entity.Inscripcion;
import co.edu.uptc.sistema_academico.entity.Materia;
import co.edu.uptc.sistema_academico.exception.EstudianteNoEncontradoException;
import co.edu.uptc.sistema_academico.exception.InscripcionNoEncontradaException;
import co.edu.uptc.sistema_academico.exception.MateriaNoEncontradaException;
import co.edu.uptc.sistema_academico.repository.EstudianteRepository;
import co.edu.uptc.sistema_academico.repository.InscripcionRepository;
import co.edu.uptc.sistema_academico.repository.MateriaRepository;
import org.springframework.stereotype.Service;
import co.edu.uptc.sistema_academico.dto.InscripcionRequest;

import java.util.List;

@Service
public class InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final EstudianteRepository estudianteRepository;
    private final MateriaRepository materiaRepository;

    public InscripcionService(
            InscripcionRepository inscripcionRepository,
            EstudianteRepository estudianteRepository,
            MateriaRepository materiaRepository) {

        this.inscripcionRepository = inscripcionRepository;
        this.estudianteRepository = estudianteRepository;
        this.materiaRepository = materiaRepository;
    }

    public List<Inscripcion> obtenerTodas() {
        return inscripcionRepository.findAll();
    }

    public Inscripcion obtenerPorId(Long id) {
        return inscripcionRepository.findById(id)
                .orElseThrow(() -> new InscripcionNoEncontradaException(id));
    }

    public Inscripcion guardar(InscripcionRequest request) {

    Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
            .orElseThrow(() ->
                    new EstudianteNoEncontradoException(request.getEstudianteId()));

    Materia materia = materiaRepository.findById(request.getMateriaId())
            .orElseThrow(() ->
                    new MateriaNoEncontradaException(request.getMateriaId()));

    Inscripcion inscripcion = new Inscripcion();

    inscripcion.setEstudiante(estudiante);
    inscripcion.setMateria(materia);
    inscripcion.setPeriodo(request.getPeriodo());

        return inscripcionRepository.save(inscripcion);
    }

    public void eliminar(Long id) {
        Inscripcion inscripcion = obtenerPorId(id);
        inscripcionRepository.delete(inscripcion);
    }
    public Inscripcion actualizar(Long id, InscripcionRequest request) {

    Inscripcion inscripcion = obtenerPorId(id);

    Estudiante estudiante = estudianteRepository.findById(request.getEstudianteId())
            .orElseThrow(() ->
                    new EstudianteNoEncontradoException(request.getEstudianteId()));

    Materia materia = materiaRepository.findById(request.getMateriaId())
            .orElseThrow(() ->
                    new MateriaNoEncontradaException(request.getMateriaId()));

    inscripcion.setEstudiante(estudiante);
    inscripcion.setMateria(materia);
    inscripcion.setPeriodo(request.getPeriodo());

    return inscripcionRepository.save(inscripcion);
}
public Inscripcion actualizarParcialmente(
        Long id,
        InscripcionRequest request) {

    Inscripcion inscripcion = obtenerPorId(id);

    if (request.getEstudianteId() != null) {
        Estudiante estudiante = estudianteRepository
                .findById(request.getEstudianteId())
                .orElseThrow(() ->
                        new EstudianteNoEncontradoException(
                                request.getEstudianteId()));

        inscripcion.setEstudiante(estudiante);
    }

    if (request.getMateriaId() != null) {
        Materia materia = materiaRepository
                .findById(request.getMateriaId())
                .orElseThrow(() ->
                        new MateriaNoEncontradaException(
                                request.getMateriaId()));

        inscripcion.setMateria(materia);
    }

    if (request.getPeriodo() != null) {
        inscripcion.setPeriodo(request.getPeriodo());
    }

    return inscripcionRepository.save(inscripcion);
}
}