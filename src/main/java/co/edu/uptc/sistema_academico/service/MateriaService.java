package co.edu.uptc.sistema_academico.service;

import co.edu.uptc.sistema_academico.entity.Materia;
import co.edu.uptc.sistema_academico.exception.MateriaNoEncontradaException;
import co.edu.uptc.sistema_academico.repository.MateriaRepository;
import org.springframework.stereotype.Service;
import co.edu.uptc.sistema_academico.dto.MateriaPageResponse;
import co.edu.uptc.sistema_academico.dto.MateriaResponse;
import co.edu.uptc.sistema_academico.dto.MateriaSortField;
import co.edu.uptc.sistema_academico.dto.PaginationResponse;
import co.edu.uptc.sistema_academico.dto.SortDirection;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Set;

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
    public MateriaPageResponse buscarMaterias(
        int pageNumber,
        int pageSize,
        MateriaSortField sortBy,
        SortDirection sortDirection) {

Sort.Direction direction =
        sortDirection == SortDirection.DESC
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

Pageable pageable = PageRequest.of(
        pageNumber - 1,
        pageSize,
        Sort.by(direction, sortBy.getValue())
);

    Page<Materia> pagina =
            materiaRepository.findAll(pageable);

    List<MateriaResponse> data =
            pagina.getContent()
                    .stream()
                    .map(materia ->
                            new MateriaResponse(
                                    materia.getId(),
                                    materia.getNombre(),
                                    materia.getCodigo(),
                                    materia.getCreditos(),
                                    materia.getSemestre()
                            )
                    )
                    .toList();

    PaginationResponse pagination =
            new PaginationResponse(
                    pageNumber,
                    pageSize,
                    pagina.getTotalElements(),
                    pagina.getTotalPages()
            );

    return new MateriaPageResponse(
            data,
            pagination
    );
}
private static final Set<String> CAMPOS_ORDENAMIENTO =
        Set.of(
                "nombre",
                "codigo",
                "creditos",
                "semestre"
        );
}