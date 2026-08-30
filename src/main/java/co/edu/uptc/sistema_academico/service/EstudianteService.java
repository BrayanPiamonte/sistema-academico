package co.edu.uptc.sistema_academico.service;

import co.edu.uptc.sistema_academico.entity.Estudiante;
import co.edu.uptc.sistema_academico.exception.EstudianteNoEncontradoException;
import co.edu.uptc.sistema_academico.repository.EstudianteRepository;
import org.springframework.stereotype.Service;
import co.edu.uptc.sistema_academico.dto.EstudiantePageResponse;
import co.edu.uptc.sistema_academico.dto.EstudianteResponse;
import co.edu.uptc.sistema_academico.dto.EstudianteSortField;
import co.edu.uptc.sistema_academico.dto.PaginationResponse;
import co.edu.uptc.sistema_academico.dto.SortDirection;
import co.edu.uptc.sistema_academico.specification.EstudianteSpecification;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class EstudianteService {

    private final EstudianteRepository estudianteRepository;

    public EstudianteService(EstudianteRepository estudianteRepository) {
        this.estudianteRepository = estudianteRepository;
    }

    public List<Estudiante> obtenerTodos() {
        return estudianteRepository.findAll();
    }

    public Estudiante guardar(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
    }

    public void eliminar(Long id) {
        estudianteRepository.deleteById(id);
    }
    public Estudiante actualizar(Long id, Estudiante datos) {

    Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    estudiante.setNombre(datos.getNombre());
    estudiante.setApellido(datos.getApellido());
    estudiante.setCorreo(datos.getCorreo());
    estudiante.setFechaNacimiento(datos.getFechaNacimiento());
    estudiante.setPrograma(datos.getPrograma());

    return estudianteRepository.save(estudiante);
    }

    public Estudiante obtenerPorId(Long id) {
    return estudianteRepository.findById(id)
            .orElseThrow(() -> new EstudianteNoEncontradoException(id));
    }
    
    public Estudiante actualizarParcialmente(Long id, Estudiante datos) {

    Estudiante estudiante = estudianteRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Estudiante no encontrado"));

    if (datos.getNombre() != null) {
        estudiante.setNombre(datos.getNombre());
    }

    if (datos.getApellido() != null) {
        estudiante.setApellido(datos.getApellido());
    }

    if (datos.getCorreo() != null) {
        estudiante.setCorreo(datos.getCorreo());
    }

    if (datos.getFechaNacimiento() != null) {
        estudiante.setFechaNacimiento(datos.getFechaNacimiento());
    }

    if (datos.getPrograma() != null) {
        estudiante.setPrograma(datos.getPrograma());
    }

    return estudianteRepository.save(estudiante);
    }
    public EstudiantePageResponse buscarEstudiantes(
        int pageNumber,
        int pageSize,
        EstudianteSortField sortBy,
        SortDirection sortDirection,
        Long id,
        List<String> nombres,
        List<String> apellidos,
        String correo,
        List<String> programas,
        LocalDate fechaDesde,
        LocalDate fechaHasta,
        String search,
        boolean and) {

    Specification<Estudiante> specification =
            construirSpecification(
                    id,
                    nombres,
                    apellidos,
                    correo,
                    programas,
                    fechaDesde,
                    fechaHasta,
                    search,
                    and
            );

        Sort.Direction direction =
        sortDirection == SortDirection.DESC
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        Pageable pageable = PageRequest.of(
        pageNumber - 1,
        pageSize,
        Sort.by(direction, sortBy.getValue())
        );
    Page<Estudiante> pagina =
            estudianteRepository.findAll(
                    specification,
                    pageable
            );

    List<EstudianteResponse> data =
            pagina.getContent()
                    .stream()
                    .map(estudiante ->
                            new EstudianteResponse(
                                    estudiante.getId(),
                                    estudiante.getNombre(),
                                    estudiante.getApellido(),
                                    estudiante.getCorreo(),
                                    estudiante.getFechaNacimiento(),
                                    estudiante.getPrograma()
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

    return new EstudiantePageResponse(
            data,
            pagination
    );
}
private Specification<Estudiante> construirSpecification(
        Long id,
        List<String> nombres,
        List<String> apellidos,
        String correo,
        List<String> programas,
        LocalDate fechaDesde,
        LocalDate fechaHasta,
        String search,
        boolean and) {

    List<Specification<Estudiante>> filtros =
            new ArrayList<>();

    if (id != null) {
        filtros.add(
                EstudianteSpecification.idEquals(id)
        );
    }

    if (nombres != null && !nombres.isEmpty()) {
        filtros.add(
                EstudianteSpecification.nombresContains(nombres)
        );
    }

    if (apellidos != null && !apellidos.isEmpty()) {
        filtros.add(
                EstudianteSpecification.apellidosContains(apellidos)
        );
    }

    if (correo != null && !correo.isBlank()) {
        filtros.add(
                EstudianteSpecification.correoContains(correo)
        );
    }

    if (programas != null && !programas.isEmpty()) {
        filtros.add(
                EstudianteSpecification.programasIn(programas)
        );
    }

    if (fechaDesde != null) {
        filtros.add(
                EstudianteSpecification.fechaDesde(fechaDesde)
        );
    }

    if (fechaHasta != null) {
        filtros.add(
                EstudianteSpecification.fechaHasta(fechaHasta)
        );
    }

    if (search != null && !search.isBlank()) {
        filtros.add(
                EstudianteSpecification.search(search)
        );
    }

    if (filtros.isEmpty()) {
        return null;
    }

    Specification<Estudiante> resultado = filtros.get(0);

    for (int i = 1; i < filtros.size(); i++) {

        if (and) {
            resultado = resultado.and(filtros.get(i));
        } else {
            resultado = resultado.or(filtros.get(i));
        }
    }

    return resultado;
}
}