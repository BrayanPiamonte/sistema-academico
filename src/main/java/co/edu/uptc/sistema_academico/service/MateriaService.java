package co.edu.uptc.sistema_academico.service;

import co.edu.uptc.sistema_academico.entity.Materia;
import co.edu.uptc.sistema_academico.exception.MateriaNoEncontradaException;
import co.edu.uptc.sistema_academico.repository.MateriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService {

    private final MateriaRepository materiaRepository;

    public MateriaService(MateriaRepository materiaRepository) {
        this.materiaRepository = materiaRepository;
    }

    public List<Materia> obtenerTodas() {
        return materiaRepository.findAll();
    }

    public Materia obtenerPorId(Long id) {
        return materiaRepository.findById(id)
                .orElseThrow(() -> new MateriaNoEncontradaException(id));
    }

    public Materia guardar(Materia materia) {
        return materiaRepository.save(materia);
    }

    public Materia actualizar(Long id, Materia datos) {

        Materia materia = obtenerPorId(id);

        materia.setNombre(datos.getNombre());
        materia.setCodigo(datos.getCodigo());
        materia.setCreditos(datos.getCreditos());
        materia.setSemestre(datos.getSemestre());

        return materiaRepository.save(materia);
    }

    public Materia actualizarParcialmente(Long id, Materia datos) {

        Materia materia = obtenerPorId(id);

        if (datos.getNombre() != null) {
            materia.setNombre(datos.getNombre());
        }

        if (datos.getCodigo() != null) {
            materia.setCodigo(datos.getCodigo());
        }

        if (datos.getCreditos() != null) {
            materia.setCreditos(datos.getCreditos());
        }

        if (datos.getSemestre() != null) {
            materia.setSemestre(datos.getSemestre());
        }

        return materiaRepository.save(materia);
    }

    public void eliminar(Long id) {
        Materia materia = obtenerPorId(id);
        materiaRepository.delete(materia);
    }
}