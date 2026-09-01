package co.edu.uptc.sistema_academico.Controller;

import co.edu.uptc.sistema_academico.dto.InscripcionRequest;
import co.edu.uptc.sistema_academico.entity.Inscripcion;
import co.edu.uptc.sistema_academico.service.InscripcionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@Tag(
    name = "Inscripciones",
    description = "Operaciones CRUD para gestionar la inscripción de estudiantes en materias"
)
public class InscripcionController {

    private final InscripcionService inscripcionService;

    public InscripcionController(InscripcionService inscripcionService) {
        this.inscripcionService = inscripcionService;
    }

 @Operation(
    summary = "Listar inscripciones",
    description = "Obtiene todas las inscripciones registradas, relacionando estudiantes, materias y periodos académicos."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Lista de inscripciones obtenida correctamente"
    )
})
@GetMapping
public List<Inscripcion> obtenerTodas() {
    return inscripcionService.obtenerTodas();
}

    @Operation(
        summary = "Consultar inscripción por ID",
        description = "Obtiene una inscripción específica a partir de su identificador."
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "Inscripción encontrada"
        ),
        @ApiResponse(
            responseCode = "404",
            description = "No existe una inscripción con el ID indicado"
        )
    })
    @GetMapping("/{id}")
    public Inscripcion obtenerPorId(@PathVariable Long id) {
        return inscripcionService.obtenerPorId(id);
    }

@Operation(
    summary = "Crear inscripción",
    description = "Registra una inscripción relacionando un estudiante con una materia y un periodo académico."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "201",
        description = "Inscripción creada correctamente"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no son válidos"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "El estudiante o la materia indicados no existen"
    )
})
@PostMapping
@ResponseStatus(HttpStatus.CREATED)
public Inscripcion crear(
        @Valid @RequestBody InscripcionRequest request) {

    return inscripcionService.guardar(request);
}

@Operation(
    summary = "Eliminar inscripción",
    description = "Elimina una inscripción existente a partir de su identificador."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "204",
        description = "Inscripción eliminada correctamente"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe la inscripción indicada"
    )
})
@DeleteMapping("/{id}")
@ResponseStatus(HttpStatus.NO_CONTENT)
public void eliminar(@PathVariable Long id) {
    inscripcionService.eliminar(id);
}
@Operation(
    summary = "Actualizar inscripción",
    description = "Reemplaza completamente los datos de una inscripción existente."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Inscripción actualizada correctamente"
    ),
    @ApiResponse(
        responseCode = "400",
        description = "Los datos enviados no son válidos"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe la inscripción, el estudiante o la materia indicada"
    )
})
@PutMapping("/{id}")
public Inscripcion actualizar(
        @PathVariable Long id,
        @Valid @RequestBody InscripcionRequest request) {

    return inscripcionService.actualizar(id, request);
}
@Operation(
    summary = "Actualizar parcialmente una inscripción",
    description = "Modifica únicamente los campos enviados de una inscripción existente."
)
@ApiResponses({
    @ApiResponse(
        responseCode = "200",
        description = "Inscripción actualizada correctamente"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "No existe la inscripción, el estudiante o la materia indicada"
    )
})
@PatchMapping("/{id}")
public Inscripcion actualizarParcialmente(
        @PathVariable Long id,
        @RequestBody InscripcionRequest request) {

    return inscripcionService.actualizarParcialmente(id, request);
}
}