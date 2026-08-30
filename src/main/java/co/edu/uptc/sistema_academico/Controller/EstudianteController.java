package co.edu.uptc.sistema_academico.Controller;

import co.edu.uptc.sistema_academico.dto.EstudiantePageResponse;
import co.edu.uptc.sistema_academico.dto.EstudianteSortField;
import co.edu.uptc.sistema_academico.dto.SortDirection;
import co.edu.uptc.sistema_academico.entity.Estudiante;
import co.edu.uptc.sistema_academico.service.EstudianteService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.http.ResponseEntity;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PutMapping;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
@Tag(
    name = "Estudiantes",
    description = "Operaciones CRUD para la gestión de estudiantes"
)
@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    private final EstudianteService estudianteService;

    public EstudianteController(EstudianteService estudianteService) {
        this.estudianteService = estudianteService;
    }

@GetMapping("/{id}")
public Estudiante obtenerPorId(@PathVariable Long id) {
    return estudianteService.obtenerPorId(id);
}
    @Operation(
    summary = "Crear estudiante",
    description = "Registra un nuevo estudiante en el sistema académico."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Estudiante creado correctamente"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no cumplen las validaciones"
    )
})
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Estudiante crear(@Valid @RequestBody Estudiante estudiante) {
    return estudianteService.guardar(estudiante);
}
    
@Operation(
    summary = "Eliminar estudiante",
    description = "Elimina un estudiante existente a partir de su identificador."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "204",
        description = "Estudiante eliminado correctamente"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe el estudiante indicado"
    )
})
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void eliminar(@PathVariable Long id) {
    estudianteService.eliminar(id);
}
    @Operation(
    summary = "Actualizar estudiante",
    description = "Reemplaza completamente los datos de un estudiante existente."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Estudiante actualizado correctamente"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no son válidos"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe el estudiante indicado"
    )
})
@PutMapping("/{id}")
public Estudiante actualizar(
        @PathVariable Long id,
        @Valid @RequestBody Estudiante estudiante) {

    return estudianteService.actualizar(id, estudiante);
}
    @Operation(
    summary = "Actualizar parcialmente un estudiante",
    description = "Modifica únicamente los campos enviados del estudiante."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Estudiante actualizado correctamente"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe el estudiante indicado"
    )
})
@PatchMapping("/{id}")
public Estudiante actualizarParcialmente(
        @PathVariable Long id,
        @RequestBody Estudiante estudiante) {

    return estudianteService.actualizarParcialmente(id, estudiante);
}
@GetMapping
@Operation(
        summary = "Consultar estudiantes",
        description = "Obtiene estudiantes mediante paginación, ordenamiento y filtros."
)
@ApiResponses({
        @ApiResponse(
                responseCode = "200",
                description = "Consulta realizada correctamente"
        ),
        @ApiResponse(
                responseCode = "400",
                description = "Parámetros de consulta inválidos"
        )
})
public EstudiantePageResponse obtenerEstudiantes(

        @RequestParam(defaultValue = "1")
        @Min(1)
        int pageNumber,

        @RequestParam(defaultValue = "20")
        @Min(1)
        @Max(100)
        int pageSize,

        @RequestParam
        EstudianteSortField sortBy,

        @RequestParam
        SortDirection sortDirection,

        @RequestParam(required = false)
        Long id,

        @RequestParam(required = false)
        List<String> nombres,

        @RequestParam(required = false)
        List<String> apellidos,

        @RequestParam(required = false)
        String correo,

        @RequestParam(required = false)
        List<String> programas,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fechaDesde,

        @RequestParam(required = false)
        @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
        LocalDate fechaHasta,

        @RequestParam(required = false)
        String search,

        @RequestParam(defaultValue = "true")
        boolean and) {

    return estudianteService.buscarEstudiantes(
            pageNumber,
            pageSize,
            sortBy,
            sortDirection,
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
}

}